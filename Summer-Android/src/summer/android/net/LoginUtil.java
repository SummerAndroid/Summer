package summer.android.net;

import summer.android.net.module.NetCallback;
import summer.android.net.module.NetUtil;
import summer.inf.Response;
import android.os.Handler;

/**
 * 
 * ��¼����
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class LoginUtil {

	private NetUtil netUtil;
	private Handler handler;

	public LoginUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	public void login(long id, String password) {
		// netUtil.request(what, request);
	}

	public void login(String name, String password) {

	}

	private class InnerNetCallback implements NetCallback {

		@Override public void sent(int what, String message) {
		}

		@Override public void response(int what, Response response) {
		}

		@Override public void error(Throwable throwable) {
		}

		@Override public void error(int what, Throwable throwable) {
		}
	}
}