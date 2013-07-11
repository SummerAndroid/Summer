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
 * 获取Tasklet列表。调用{@link #taskletPull(User, long, long, boolean, String)}
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
	 * 获取任务列表。结果通过handleMessage的Message参数的obj返回，成功则obj是List<Tasklet>对象，失败，
	 * 则obj是失败信息的字符串。
	 * 
	 * @param user
	 *            人物巡视员对象。只是需要设置id。
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param isFinish
	 *            任务是否完成。暂时不支持同时获取历史任务和未完成任务。
	 * @param order
	 *            结果的排序，参考{@code Res.TASKLET_LIST_ORDER_*}那四个字段。
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
