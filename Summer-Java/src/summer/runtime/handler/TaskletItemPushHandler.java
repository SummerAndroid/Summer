package summer.runtime.handler;

import java.util.List;

import summer.dao.TaskletDAO;
import summer.dao.TaskletItemArgDAO;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;

/**
 * 
 * 任务内容回传处理
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletItemPushHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		TaskletDAO taskletDAO = new TaskletDAO();
		TaskletItemArgDAO argDAO = new TaskletItemArgDAO();
		List<Object> list = request.getRequestArgs();
		for (Object object : list) {
			TaskletItem item = (TaskletItem) object;
			// TODO:先用循环一个一个修改TaskletItemArg,然后再优化。可以使用事务。等第二版本吧
			List<TaskletItemArg> itemArgs = item.getArgList();
			for (TaskletItemArg taskletItemArg : itemArgs) {
				argDAO.merge(taskletItemArg);
			}
		}
		// 修改Tasklet里的记录
		TaskletItem item = (TaskletItem) list.get(0);
		Tasklet tasklet = taskletDAO.findById(item.getTaskletId());
		tasklet.setAccount(tasklet.getAccount() - 1);
		tasklet.setLast_time(System.currentTimeMillis());
		taskletDAO.attachDirty(tasklet);
		return Response.createResponse(request.getWhat(), Res.OK,
				Res.valueOf(Res.OK));
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			// 不用验证参数列表的长度。
			List<Object> list = request.getRequestArgs();
			for (Object object : list) {
				if (object instanceof TaskletItem) {
					TaskletItem item = (TaskletItem) object;
					if ((item.getId() != null) && (item.getName() != null)
							&& (item.getStuffId() != null)
							&& (item.getTaskletId() != null)
							&& (item.getArgList() != null)
							&& !item.getArgList().isEmpty()) {} else {
						verifyError = new VerifyError(Res.BAD_REQUESTARGS_ARG,
								Res.valueOf(Res.BAD_REQUESTARGS_ARG)
										+ request.getRequestArgs());
						return false;
					}
				} else {
					verifyError = new VerifyError(Res.BAD_REQUESTARGS_TYPE,
							Res.valueOf(Res.BAD_REQUESTARGS_TYPE)
									+ request.getRequestArgs());
					return false;
				}
			}
		}
		return true;
	}
}