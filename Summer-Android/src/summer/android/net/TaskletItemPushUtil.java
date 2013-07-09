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
 * 回传任务。调用方法{@link #itemPush(List)}
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
	 * 结果通过handleMessage的Message参数的obj返回，成功则obj是String对象，失败， 则obj是失败信息的字符串。
	 * 
	 * @param list
	 *            回传的任务的具体内容
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