package summer.android.net;

import java.util.List;

import summer.android.net.module.HandlerDecorator;
import summer.android.net.module.NetUtil;
import summer.android.net.module.WhatUtil;
import summer.inf.I.Req;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.Tasklet;
import summer.pojo.User;
import android.os.Handler;

/**
 * ��ȡTasklet�б�����{@link #taskletPull(User, long, long, boolean, String)}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletPullUtil {
	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public TaskletPullUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * ��ȡ�����б����ͨ��handleMessage��Message������obj���أ��ɹ���obj��List<Tasklet>����ʧ�ܣ�
	 * ��obj��ʧ����Ϣ���ַ�����
	 * 
	 * @param user
	 *            ����Ѳ��Ա����ֻ����Ҫ����id��
	 * @param start
	 *            ��ʼʱ��
	 * @param end
	 *            ����ʱ��
	 * @param isFinish
	 *            �����Ƿ���ɡ���ʱ��֧��ͬʱ��ȡ��ʷ�����δ�������
	 * @param order
	 *            ��������򣬲ο�{@code Res.TASKLET_LIST_ORDER_*}���ĸ��ֶΡ�
	 */
	public void taskletPull(User user, long start, long end, boolean isFinish,
			String order) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.TASKLET_PULL, user,
				Long.valueOf(start), Long.valueOf(end), isFinish, order);
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
				@SuppressWarnings("unchecked") List<Tasklet> list = (List<Tasklet>) response
						.getResponseArgs()
						.get(0);
				post(what, HANDLER_RESPONSE, list);
			}
		}
	}
}
