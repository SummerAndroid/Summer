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
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.runtime.HandlerRunnable.Message;
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
	private class Arg{
		IoSession session;
		Object message;

		public Arg(IoSession s, Object m) {
			session = s;
			message = m;
		}
	}

	private class InnerThread extends Thread {
		
		private Log log = LogFactory.getLog(InnerThread.class);
		private LinkedBlockingQueue<Arg> messageQueue = new LinkedBlockingQueue<Arg>();
		private ExecutorService executorService = Executors
				.newCachedThreadPool();

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
					session.write(Response.createResponse(Res.BAD_REQUESTCODE,
							"请求号错误！"));
				}
			} else {
				session.write(Response.createResponse(Res.BAD_REQUEST,
						"请求对象不是Request类型！"));
			}
		}

		/**
		 * 验证request是否正确
		 * 
		 * @param request
		 * @return
		 */
		private boolean verifyRequest(Request request) {
			return true;
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
			return new LoginHandler();
		}
	}
}