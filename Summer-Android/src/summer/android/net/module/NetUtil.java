package summer.android.net.module;

import summer.inf.Request;

/**
 * 
 * 网络模块的外观。
 * 
 * @author zhenzxie
 * @since 1.0
 */
public final class NetUtil {

	public NetUtil() {
	}

	public void request(int what, Request request, NetCallback callback) {
		NetController controller = NetController.getInstance();
		NetController.Message message = new NetController.Message();
		message.what = what;
		message.request = request;
		message.callback = callback;
		controller.postMessage(message);
	}

	public void shutdown() {
		NetController.getInstance().shutdown();
	}
}