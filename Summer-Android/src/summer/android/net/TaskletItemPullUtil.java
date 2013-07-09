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
import summer.pojo.TaskletItem;
import android.os.Handler;

/**
 * 
 * 任务具体内容请求。调用{@link #itemPull(Tasklet)}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletItemPullUtil {
	private NetUtil netUtil;
	private Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public TaskletItemPullUtil(Handler handler) {
		netUtil = new NetUtil();
		this.handler = handler;
	}

	/**
	 * 结果通过handleMessage的Message参数的obj返回，成功则obj是List<TaskletItem>对象，失败，
	 * 则obj是失败信息的字符串。
	 * 
	 * @param tasklet
	 *            要获取内容的任务
	 */
	public void itemPull(Tasklet tasklet) {
		int what = WhatUtil.what();
		Request request = Request.createRequest(what, Req.TASKLET_ITEM_PULL,
				tasklet);
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
				@SuppressWarnings("unchecked") List<TaskletItem> itemList = (List<TaskletItem>) response
						.getResponseArgs().get(0);
				post(what, HANDLER_RESPONSE, itemList);
			}
		}
	}
}
