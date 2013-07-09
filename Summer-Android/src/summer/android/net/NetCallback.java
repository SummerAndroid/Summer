package summer.android.net;

import summer.inf.Response;

/**
 * 
 * �����������ں����ӿ�
 * 
 * @author zhenzxie
 * @since 1.0
 */
public interface NetCallback {
	void sent(int what, String message);

	void response(int what, Response response);

	void error(int what, Throwable throwable);

	void error(Throwable throwable);
}
