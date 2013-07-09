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
 * 修改用户信息。
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
