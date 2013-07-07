package summer.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import summer.SysConfig.NetConfig;
import summer.inf.I;
import summer.inf.I.Req;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.runtime.HandlerRunnable.Message;
import summer.runtime.handler.ExitHandler;
import summer.runtime.handler.Handler;
import summer.runtime.handler.LoginHandler;

/**
 * 
 * 请求处理器，将请求分发到不同的请求处理，然后将结果通过IoSession返回给相应的客户端
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class RequestHandler extends IoHandlerAdapter {

	private Log log = LogFactory.getLog(RequestHandler.class);
	private Map<IoSession, HandlerRunnable> clients = new HashMap<IoSession, HandlerRunnable>(
			NetConfig.CLIENT_COUNT_PREDICT);
	private InnerThread innerThread;

	public RequestHandler() {
		innerThread = new InnerThread();
		innerThread.start();
	}

	@Override public void messageReceived(IoSession session, Object message)
			throws Exception {
		log.info("message received from:" + session + "\n" + message);
		innerThread.postMessage(new Arg(session, message));
	}

	@Override public void messageSent(IoSession session, Object message)
			throws Exception {
		log.info("message sent to:" + session + "\n" + message);
	}

	@Override public void sessionClosed(IoSession session) throws Exception {
		log.info("session closed:" + session);
		clients.remove(session);
	}

	/**
	 * data structure for {@link InnerThread}'s message queue.
	 * 
	 * @author zhenzxie
	 * @since 1.0
	 */
	private class Arg {
		IoSession session;
		Object message;

		public Arg(IoSession s, Object m) {
			session = s;
			message = m;
		}
	}

	/**
	 * 网络线程将请求发送到InnerThread的消息队列中。InnerThread按顺序完成以下任务。
	 * <ol>
	 * <li>从消息队列中取出消息。
	 * <li>判断会话池中是否存在为会话分派线程。如果不存在则创建一个({@link HandlerRunnable})，并将会话线程对加入到会话池中。
	 * <li>验证请求的消息对象是否是{@link Request}类型，验证消息的请求码是否正确（请参考{@link I.Req}）。
	 * <li>如果以上验证无法通过，则返回给客户端一个{@link Response}
	 * <li>如果以上验证通过，则选择一个合适的{@link Handler},创建{@link Message},并发派到相应的线程中
	 * </ol>
	 * <strong>这里并没有进行具体请求协议的验证</strong>
	 * 
	 * @author zhenzxie
	 * @since 1.0
	 */
	private class InnerThread extends Thread {

		private Log log = LogFactory.getLog(InnerThread.class);
		private LinkedBlockingQueue<Arg> messageQueue = new LinkedBlockingQueue<Arg>();
		private ExecutorService executorService = Executors
				.newCachedThreadPool();
		private Map<IoSession, HandlerRunnable> clients = RequestHandler.this.clients;

		@Override public void run() {
			while (true) {
				try {
					Arg arg = messageQueue.take();
					IoSession session = arg.session;
					Object message = arg.message;
					if (clients.containsKey(session)) {
						HandlerRunnable runnable = clients.get(session);
						handle(session, runnable, message);
					} else {
						HandlerRunnable runnable = new HandlerRunnable(session);
						clients.put(session, runnable);
						handle(session, runnable, message);
						executorService.execute(runnable);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error("InnerThread", e);
				}
			}
		}

		public void postMessage(Arg arg) {
			assert arg != null;
			messageQueue.add(arg);
		}

		/**
		 * 
		 * 将请求进行验证，分发
		 * 
		 * @param session
		 * @param runnable
		 * @param message
		 */
		private void handle(IoSession session, HandlerRunnable runnable,
				Object message) {
			if (message instanceof Request) {
				Request request = (Request) message;
				if (verifyRequest(request)) {
					runnable.postMessage(createMessage(request));
				} else {
					session.write(Response.createResponse(
							Res.BAD_REQUESTCODE,
							Res.valueOf(Res.BAD_REQUESTCODE)
									+ request.getRequestCode()));
				}
			} else {
				session.write(Response.createResponse(Res.BAD_REQUEST,
						Res.valueOf(Res.BAD_REQUEST) + message));
			}
		}

		/**
		 * 验证request是否正确
		 * 
		 * @param request
		 * @return
		 */
		private boolean verifyRequest(Request request) {
			int requestCode = request.getRequestCode();
			return Req.LOGIN <= requestCode && requestCode <= Req.EXIT;
		}

		/**
		 * 依据request中的code来创建message
		 * 
		 * @param request
		 * @return
		 */
		private Message createMessage(Request request) {
			Message message = new Message();
			message.request = request;
			message.handler = getHandlerByRequestCode(request.getRequestCode());
			return message;
		}

		private Handler getHandlerByRequestCode(int code) {
			Handler handler = null;
			switch (code) {
				case Req.LOGIN:
					handler = new LoginHandler();
					break;
				case Req.EXIT:
					handler = new ExitHandler();
					break;
				default:
					throw new RuntimeException("code不对，请参考Res.java");// TODO:不应该会执行到这里，因为已经verifyCode
			}
			return handler;
		}
	}
}