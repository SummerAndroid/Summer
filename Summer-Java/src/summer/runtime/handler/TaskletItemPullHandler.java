package summer.runtime.handler;

import java.util.List;

import summer.dao.TaskletItemArgDAO;
import summer.dao.TaskletItemDAO;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;

/**
 *
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletItemPullHandler extends Handler {

	@SuppressWarnings("unchecked") @Override public Response handle(
			Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		long tasklet_id = ((Tasklet) request.getRequestArgs().get(0)).getId();
		TaskletItemDAO itemDAO = new TaskletItemDAO();
		List<TaskletItem> itemList;
		try {
			itemList = itemDAO
					.findByTaskletId(tasklet_id);
			TaskletItemArgDAO argDAO = new TaskletItemArgDAO();
			List<TaskletItemArg> argList;
			for (TaskletItem taskletItem : itemList) {
				argList = argDAO.findByTaskletItemId(taskletItem.getId());
				taskletItem.setArgList(argList);
			}
			return Response.createResponse(Res.OK, itemList);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("TaskLetItemPullHandler", e);
			return Response.createResponse(Res.BAD_SYS,
					Res.valueOf(Res.BAD_SYS) + "查询任务时出错了，请重试！");
		}
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			if (verifyArgsLength(request, 1)) {
				Object o = request.getRequestArgs().get(0);
				if (o instanceof Tasklet) {
					Tasklet tasklet = (Tasklet) o;
					if (tasklet.getId() != null) {
						return true;
					} else {
						verifyError = new VerifyError(Res.BAD_REQUESTARG_ARG,
								Res.valueOf(Res.BAD_REQUESTARG_ARG)
										+ request.getRequestArgs());
					}
				} else {
					verifyError = new VerifyError(Res.BAD_REQUESTARG_TYPE,
							Res.valueOf(Res.BAD_REQUESTARG_TYPE)
									+ request.getRequestArgs());
				}
			}
	 	}
	 	return false;
	}
}