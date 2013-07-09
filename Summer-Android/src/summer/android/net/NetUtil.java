package summer.android.net;

import summer.inf.Request;
import android.os.Handler;

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

	public NetUtil(NetCallback callback) {
		mNetCallback = callback;
	}

	public void request(int what, Request request) {
		assert mHandler != null || mNetCallback != null;
		NetController controller = NetController.getInstance(mNetCallback);
		controller.open();
		NetController.Message message = new NetController.Message();
		message.what = what;
		message.request = request;
		controller.postMessage(message);
	}

	public void shutdown() {
		NetController.getInstance(mNetCallback).shutdown();
	}
}