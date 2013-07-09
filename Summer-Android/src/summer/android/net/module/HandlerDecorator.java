package summer.android.net.module;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import summer.inf.Response;
import android.os.Handler;
import android.os.Message;

/**
 * convenient class for some Util's Inner Class
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class HandlerDecorator implements NetCallback{

	public static final int HANDLER_SENT = 0;
	public static final int HANDLER_RESPONSE = 1;
	public static final int HANDLER_ERROR = 2;

	protected Log log = LogFactory.getLog(HandlerDecorator.class);
	private Handler mHandler;

	public HandlerDecorator(Handler handler) {
		mHandler = handler;
	}

	@Override public void sent(int what, String message) {
		log.info("sent: "+what+"     "+message);
		post(what, HANDLER_SENT, message);
	}
	
	@Override public void response(int what, Response response) {
		log.info("response: "+what+"    "+response);
	}

	@Override public void error(Throwable throwable) {
		log.error("error£º "+throwable);
		post(HANDLER_ERROR, throwable);
	}

	@Override public void error(int what, Throwable throwable) {
		log.error("error£º¡¡"+what +"   "+throwable);
		post(what, HANDLER_ERROR, throwable);
	}

	public void post(int sign, Object object) {
		Message message = Message.obtain(mHandler);
		message.arg1 = sign;
		message.obj = object;
		mHandler.sendMessage(message);
	}
	
	public void post(int what, int sign, Object object) {
		Message message = Message.obtain(mHandler);
		message.what = what;
		message.arg1 = sign;
		message.obj = object;
		mHandler.sendMessage(message);
	}
}