package summer.android.net;

import java.util.List;

import summer.android.net.module.HandlerDecorator;
import summer.android.net.module.NetUtil;
import summer.android.net.module.WhatUtil;
import summer.inf.I.Req;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.TaskletItem;
import android.os.Handler;

/**
 * 
 * �ش����񡣵��÷���{@link #itemPush(List)}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletItemPushUtil {
	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public TaskletItemPushUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * ���ͨ��handleMessage��Message������obj���أ��ɹ���obj��String����ʧ�ܣ� ��obj��ʧ����Ϣ���ַ�����
	 * 
	 * @param list
	 *            �ش�������ľ�������
	 */
	public void itemPush(List<TaskletItem> list) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.TASKLET_ITEM_PUSH,
				list);
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
				String message = (String) response.getResponseArgs().get(0);
				post(what, HANDLER_RESPONSE, message);
			}
		}
	}
}