package summer.runtime.handler;

import java.util.List;

import summer.dao.TaskletDAO;
import summer.inf.I.Req;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.Tasklet;
import summer.pojo.User;

/**
 * �����ȡ������
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletPullHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		List<Object> list = request.getRequestArgs();
		long id = ((User) list.get(0)).getId();
		long startTime = (Long) list.get(1);
		long endTime = (Long) list.get(2);
		boolean isfinish = (Boolean) list.get(3);
		String order = (String) list.get(4);
		TaskletDAO dao = new TaskletDAO();
		List<Tasklet> targetList = dao
				.findBy(id, startTime, endTime, isfinish, order);
		return Response.createResponse(request.getWhat(), Res.OK, targetList);
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			if (verifyArgsLength(request, 5)) {
				if (verifyArgsType(request)) {
					return verifyArgsData(request);// last verification
				}
			}
		}
		return false;
	}

	/**
	 * ��֤requestArgs�еĲ�����������
	 * 
	 * @param request
	 */
	private boolean verifyArgsType(Request request) {
		List<Object> list = request.getRequestArgs();
		Object o0 = list.get(0);
		Object o1 = list.get(1);
		Object o2 = list.get(2);
		Object o3 = list.get(3);
		Object o4 = list.get(4);
		if ((o0 instanceof User) && (o1 instanceof Long)
				&& (o2 instanceof Long) && (o3 instanceof Boolean)
				&& (o4 instanceof String)) {
			return true;
		} else {// TODO:��ʱ�����嵽�ĸ��������ʹ���Ҳû̫��Ҫ��
			verifyError = new VerifyError(Res.BAD_REQUESTARGS_TYPE,
					Res.valueOf(Res.BAD_REQUESTARGS_TYPE)
							+ request.getRequestArgs());
			return false;
		}
	}

	/**
	 * ��֤requestArgs�еĲ���������
	 * 
	 * @param request
	 */
	private boolean verifyArgsData(Request request) {
		List<Object> list = request.getRequestArgs();
		User user = (User) list.get(0);
		long startTime = (Long) list.get(1);
		long endTime = (Long) list.get(2);
		String order = (String) list.get(4);
		// bad :(
		if (user.getId() != null) {
			if (startTime == Req.TASKLET_ALL || startTime > 0) {
				if (endTime == Req.TASKLET_ALL || endTime > 0) {
					if (startTime <= endTime) {
						if (Req.TASKLET_LIST_ORDER_TIME_DES.equals(order)
								|| Req.TASKLET_LIST_ORDER_TIME_ASC
										.equals(order)
								|| Req.TASKLET_LIST_ORDER_CYCLE_DES
										.equals(order)
								|| Req.TASKLET_LIST_ORDER_CYCYLE_ASC
										.equals(order)) {
							return true;
						}
					}
				}
			}
		}
		verifyError = new VerifyError(Res.BAD_REQUESTARGS_ARG,
				Res.valueOf(Res.BAD_REQUESTARGS_ARG) + request.getRequestArgs());
		return false;
	}
}