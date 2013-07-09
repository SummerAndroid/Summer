package summer.android.net;

import summer.android.net.module.HandlerDecorator;
import summer.android.net.module.NetUtil;
import summer.android.net.module.WhatUtil;
import summer.inf.I.Req;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.Stuff;
import android.os.Handler;

/**
 * 
 * 获取设备的具体信息。
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class StuffInfoPullUtil {
	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public StuffInfoPullUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * 获取的结果通过handleMessage的Message参数的obj返回，成功则obj是Stuff对象，失败，则obj是失败信息的字符串。
	 * 
	 * @param stuff
	 *            要获取信息的设备。只是需要设置id
	 */
	public void infoPull(Stuff stuff) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.STUFF_INFO_PULL,
				stuff);
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
				Stuff stuff = (Stuff) response.getResponseArgs().get(0);
				post(what, HANDLER_RESPONSE, stuff);
			}
		}
	}
}