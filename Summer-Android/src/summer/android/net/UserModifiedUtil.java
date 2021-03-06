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
 * 修改用户信息。调用方法{@link #modified(User)} 。
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class UserModifiedUtil {
	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public UserModifiedUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * 修改操作的结果通过handleMessage的Message参数的obj返回，成功则obj是User对象，失败，则obj是失败信息的字符串。
	 * 
	 * @param user
	 *            要修改信息的用户。User参数每个实例域都要设置，比如，要修改password，则将password设置为新的值。
	 *            而其他不修改的则保持原来的值。
	 */
	public void modified(User user) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.USER_MODIFIED, user);
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
