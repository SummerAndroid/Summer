package summer.android.net.module;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import summer.inf.Response;


/**
 * 
 * «Î«Û¥¶¿Ì∆˜
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class NetHandler extends IoHandlerAdapter {

	private Log log = LogFactory.getLog(NetHandler.class);

	private NetCallback mNetCallback;

	public NetHandler(NetCallback callback) {
		mNetCallback = callback;
	}

	@Override public void messageReceived(IoSession session, Object message)
			throws Exception {
		log.info("message received from:" + session + "\n" + message);
		Response response = (Response) message;
		mNetCallback.response(response.getWhat(), response);
	}

	@Override public void messageSent(IoSession session, Object message)
			throws Exception {
		log.info("message sent to:" + session + "\n" + message);
	}

	@Override public void sessionClosed(IoSession session) throws Exception {
		log.info("session closed:" + session);
	}

	@Override public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.error(NetHandler.class.getSimpleName(), cause);
		mNetCallback.error(cause);
	}
}