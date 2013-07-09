package summer.android.net.module;

import summer.inf.Request;

/**
 * 
 * ����ģ�����ۡ�
 * 
 * @author zhenzxie
 * @since 1.0
 */
public final class NetUtil {

	public static final int HANDLER_SENT = 0;
	public static final int HANDLER_RESPONSE = 1;
	public static final int HANDLER_ERROR = 2;

	public NetUtil() {
	}

	public void request(int what, Request request, NetCallback callback) {
		NetController controller = NetController.getInstance();
		controller.open();
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