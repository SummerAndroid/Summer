package summer.android.net;

import summer.inf.Response;
import android.os.Handler;

/**
 * 
 * µÇÂ¼¹¤¾ß
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class LoginUtil {

	private NetUtil netUtil;

	public LoginUtil(Handler handler) {
		netUtil = new NetUtil(new InnerNetCallback());
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