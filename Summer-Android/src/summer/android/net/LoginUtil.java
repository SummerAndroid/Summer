package summer.android.net;

import summer.android.net.module.HandlerDecorator;
import summer.android.net.module.NetUtil;
import summer.android.net.module.WhatUtil;
import summer.inf.I.Req;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.User;
import android.os.Handler;

/**
 * 
 * ��¼���ߡ���¼����{@link #login(long, String)}����{@link #login(String, String)}
 * ��������¼��Ľ��ͨ��Handler��Obj���ء�
 * 
 * <pre class="prettyprint">
 * Handler handler = new Handler() {
 * 	&#064;Override public void handleMessage(Message msg) {
 * 		System.out.println(msg.obj);
 * 	}
 * };
 * LoginUtil loginUtil = new LoginUtil(handler);
 * loginUtil.login(10001L, &quot;123456&quot;);
 * </pre>
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class LoginUtil {

	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public LoginUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * 
	 * @param id
	 *            ��¼���û�id
	 * @param password
	 *            ����
	 */
	public void login(long id, String password) {
		int what = WhatUtil.what();
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		Request request = Request.createRequest(what, Req.LOGIN, user);
		netUtil.request(what, request, new InnerNetCallback(handler));
	}

	/**
	 * 
	 * @param name
	 *            ��¼�û����û���
	 * @param password
	 *            ����
	 */
	public void login(String name, String password) {
		int what = WhatUtil.what();
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		Request request = Request.createRequest(what, Req.LOGIN, user);
		netUtil.request(what, request, new InnerNetCallback(handler));
	}

	private class InnerNetCallback extends HandlerDecorator {

		public InnerNetCallback(Handler handler) {
			super(handler);
		}

		@Override public void response(int what, Response response) {
			super.response(what, response);
			if (response.getResponseCode() != Res.OK) {
				post(what, HANDLER_ERROR, response.getResponseArgs().get(0));
			} else {
				User user = (User) response.getResponseArgs().get(0);
				post(what, HANDLER_RESPONSE, user);
			}
		}
	}
}