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
 * ��ȡ�豸�ľ�����Ϣ��
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
	 * ��ȡ�Ľ��ͨ��handleMessage��Message������obj���أ��ɹ���obj��Stuff����ʧ�ܣ���obj��ʧ����Ϣ���ַ�����
	 * 
	 * @param stuff
	 *            Ҫ��ȡ��Ϣ���豸��ֻ����Ҫ����id
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