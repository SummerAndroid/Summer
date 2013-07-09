package summer.android.net;

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
	}

	private static NetController controller = new NetController();
	private static NetCallback netCallback;
	private Log log = LogFactory.getLog(NetController.class);
	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();
	private IoSession ioSession;
	private boolean flag = true;
	private boolean isStart = false;

	private NetController() {
	}

	public static NetController getInstance(NetCallback callback) {
		netCallback = callback;
		return controller;
	}

	public void open() {
		if (isStart) {} else {
			NioSocketConnector connector = new NioSocketConnector();
			connector.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(
							new ObjectSerializationCodecFactory()));
			connector.setHandler(new NetHandler(netCallback));
			ConnectFuture mConnectFuture = connector
					.connect(new InetSocketAddress(Sys.URL, Sys.PORT));
			try {
				mConnectFuture.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(NetController.class.getSimpleName(), e);
			}
			if (mConnectFuture.isConnected()) {
				ioSession = mConnectFuture.getSession();
				log.info("network thread started...");
				isStart = true;
				start();
			} else {
				log.info("没有成功链接");
			}
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
					netCallback.sent(message.what, "请求发送成功");
				} else {
					netCallback.error(message.what, writeFuture.getException());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error("NetController", e);
			}
		}
	}
}