package summer.android.net.module;

import java.net.InetSocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import summer.inf.I.Sys;
import summer.inf.Request;
import summer.inf.Response;
import android.util.SparseArray;

/**
 * 
 * 网络通信控制器.顺序发送请求，返回结果的顺序不一定
 * 
 * @author zhenzxie
 * @since 1.0
 */
public final class NetController extends Thread {

	/**
	 * data structure for {@link NetController}'s message queue
	 * 
	 * @author zhenzxie
	 * @since 1.0
	 */
	public static class Message {
		public int what;
		public Request request;
		public NetCallback callback;
	}

	private class InnerNetCallback implements NetCallback {

		@Override public void sent(int what, String message) {
			// never can reach there :(
		}

		@Override public void response(int what, Response response) {
			NetCallback target = what2NetCallback.get(what);//choose right callback to deliver Response
			if (target != null) {
				target.response(what, response);
			}// never can reach else statement
		}

		@Override public void error(Throwable throwable) {
			// TODO:do what?
		}

		@Override public void error(int what, Throwable throwable) {
			NetCallback target = what2NetCallback.get(what);
			if (target != null) {
				target.error(what, throwable);
			}
		}
	}

	private static NetController controller = new NetController();
	private Log log = LogFactory.getLog(NetController.class);
	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();
	private SparseArray<NetCallback> what2NetCallback = new SparseArray<NetCallback>();
	private IoSession ioSession;
	private boolean flag = true;
	private boolean isStart = false;

	private NetController() {
	}

	public static NetController getInstance() {
		return controller;
	}

	public boolean open() {
		if (isStart) {
			return true;
		} else {
			NioSocketConnector connector = new NioSocketConnector();
			connector.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(
							new ObjectSerializationCodecFactory()));
			connector.setHandler(new NetHandler(new InnerNetCallback()));
			ConnectFuture mConnectFuture = connector
					.connect(new InetSocketAddress(Sys.URL, Sys.PORT));
			try {
				mConnectFuture.await();
				if (mConnectFuture.isConnected()) {
					ioSession = mConnectFuture.getSession();
					log.info("network thread started...");
					isStart = true;
					start();
					return true;
				} else {
					log.info("没有成功链接");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(NetController.class.getSimpleName(), e);
			}
			return false;
		}

	}

	public void shutdown() {
		flag = false;
	}

	public void postMessage(Message message) {
		assert message != null;
		messageQueue.add(message);
	}

	@Override public void run() {
		while (flag) {
			try {
				Message message = messageQueue.take();
				WriteFuture writeFuture = ioSession.write(message.request);
				writeFuture.await();
				if (writeFuture.isWritten()) {
					message.callback.sent(message.what, "请求发送成功");
					what2NetCallback.put(message.what, message.callback);
				} else {
					message.callback.error(message.what,
							writeFuture.getException());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error("NetController", e);
			}
		}
	}
}