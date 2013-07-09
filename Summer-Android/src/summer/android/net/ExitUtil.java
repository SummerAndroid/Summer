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
 * �û��˳�������{@link #exit(User)}����{@link #exit(long)}����{@link #exit(String)}
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
	 * �˳��Ľ��ͨ��handleMessage��Message������obj���أ��ɹ���obj��String����ʧ�ܣ���obj��ʧ����Ϣ���ַ�����
	 * 
	 * @param user
	 *            Ҫ�ǳ����û����������id����name
	 */
	public void exit(User user) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.EXIT, user);
		netUtil.request(what, request, new InnerNetCallback(handler));
	}

	/**
	 * �˳��Ľ��ͨ��handleMessage��Message������obj���أ��ɹ���obj��String����ʧ�ܣ���obj��ʧ����Ϣ���ַ�����
	 * 
	 * @param id
	 *            Ҫ�ǳ��û���id
	 */
	public void exit(long id) {
		User user = new User();
		user.setId(id);
		exit(user);
	}

	/**
	 * �˳��Ľ��ͨ��handleMessage��Message������obj���أ��ɹ���obj��String����ʧ�ܣ���obj��ʧ����Ϣ���ַ�����
	 * 
	 * @param name
	 *            Ҫ�ǳ��û���name
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