package summer.android.net;

import summer.inf.Request;
import summer.inf.Response;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * 网络模块的外观。
 * 
 * @author zhenzxie
 * @since 1.0
 */
public final class NetUtil {

	public static final int HANDLER_SENT = 0;
	public static final int HANDLER_RESPONSE = 1;
	public static final int HANDLER_ERROR = 2;

	private Handler mHandler;
	private NetCallback mNetCallback;
	private NetCallback mInnerNetCallback = new NetCallback() {
		@Override public void sent(int what, String message) {
			if (mHandler != null) {
				Message msg = Message.obtain(mHandler, what, HANDLER_SENT, 0,
						message);
				mHandler.sendMessage(msg);
			} else {
				mNetCallback.sent(what, message);
			}
		}

		@Override public void response(int what, Response response) {
			if (mHandler != null) {
				Message message = Message.obtain(mHandler, what,
						HANDLER_RESPONSE, 0, response);
				mHandler.sendMessage(message);
			} else {
				mNetCallback.response(what, response);
			}
		}

		@Override public void error(int what, Throwable throwable) {
			if (mHandler != null) {
				Message message = Message.obtain(mHandler, what, HANDLER_ERROR,
						0, throwable);
				mHandler.sendMessage(message);
			} else {
				mNetCallback.error(what, throwable);
			}
		}

		@Override public void error(Throwable throwable) {
			if (mHandler != null) {
				Message message = Message.obtain(mHandler);
				message.arg1 = HANDLER_ERROR;
				message.obj = throwable;
				mHandler.sendMessage(message);
			} else {
				mNetCallback.error(throwable);
			}
		}
	};

	public NetUtil(Handler handler) {
		mHandler = handler;
	}

	public NetUtil(NetCallback callback) {
		mNetCallback = callback;
	}

	public void request(int what, Request request) {
		assert mHandler != null || mNetCallback != null;
		NetController controller = NetController.getInstance(mInnerNetCallback);
		controller.open();
		NetController.Message message = new NetController.Message();
		message.what = what;
		message.request = request;
		controller.postMessage(message);
	}

	public void shutdown() {
		NetController.getInstance(mInnerNetCallback).shutdown();
	}
}