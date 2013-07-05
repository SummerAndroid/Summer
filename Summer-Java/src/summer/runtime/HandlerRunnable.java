package summer.runtime;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import summer.SysConfig.NetConfig;
import summer.inf.I.Req;
import summer.inf.Request;
import summer.inf.Response;
import summer.runtime.handler.Handler;

/**
 * 
 * �������̣߳������������������̳߳��У����������һ����Ϣ���У�ѭ���������е���Ϣ��
 * <p>
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class HandlerRunnable implements Runnable {

	/**
	 * data structure for {@link HandlerRunnable}'s message queue
	 * 
	 * @author zhenzxie
	 * @since 1.0
	 */
	public static class Message {
		public Request request;
		public Handler handler;
	}

	private Log log = LogFactory.getLog(HandlerRunnable.class);
	private BlockingQueue<Message> messageQueue = new LinkedBlockingDeque<Message>(
			NetConfig.MESSAGE_QUEUE_LENGTH);
	private IoSession session;

	public HandlerRunnable(IoSession s) {
		assert s != null;
		session = s;
	}

	@Override public void run() {
		boolean flag = true;
		while(flag){
			Message message = null;
			try {
				message = messageQueue.take();// block until quest becomes available
				handle(message);
				flag = message.request.getRequestCode() != Req.EXIT;
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error("HandlerRunnable", e);
			}
		}
	}

	public void postMessage(Message message) {
		assert message != null;
		messageQueue.add(message);
	}

	private void handle(Message message) {
		Handler handler = message.handler;
		Request request = message.request;
		assert handler != null && request != null;
		Response response = handler.handle(request);
		session.write(response);
	}
}