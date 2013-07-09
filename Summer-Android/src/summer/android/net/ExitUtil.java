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
 * 用户退出。调用{@link #exit(User)}或者{@link #exit(long)}或者{@link #exit(String)}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class ExitUtil {
	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public ExitUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * 退出的结果通过handleMessage的Message参数的obj返回，成功则obj是String对象，失败，则obj是失败信息的字符串。
	 * 
	 * @param user
	 *            要登出的用户，必须包含id或者name
	 */
	public void exit(User user) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.EXIT, user);
		netUtil.request(what, request, new InnerNetCallback(handler));
	}

	/**
	 * 退出的结果通过handleMessage的Message参数的obj返回，成功则obj是String对象，失败，则obj是失败信息的字符串。
	 * 
	 * @param id
	 *            要登出用户的id
	 */
	public void exit(long id) {
		User user = new User();
		user.setId(id);
		exit(user);
	}

	/**
	 * 退出的结果通过handleMessage的Message参数的obj返回，成功则obj是String对象，失败，则obj是失败信息的字符串。
	 * 
	 * @param name
	 *            要登出用户的name
	 */
	public void exit(String name) {
		User user = new User();
		user.setName(name);
		exit(user);
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
				String message = (String) response.getResponseArgs().get(0);
				post(what, HANDLER_RESPONSE, message);
			}
		}
	}
}