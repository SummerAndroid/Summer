package summer.runtime.handler;

import java.util.List;

import summer.dao.TaskletItemArgDAO;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;

/**
 * 
 * �������ݻش�����
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class TaskletItemPushHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		TaskletItemArgDAO argDAO = new TaskletItemArgDAO();
		List<Object> list = request.getRequestArgs();
		for (Object object : list) {
			TaskletItem item = (TaskletItem) object;
			// TODO:����ѭ��һ��һ���޸�TaskletItemArg,Ȼ�����Ż���
			List<TaskletItemArg> itemArgs = item.getArgList();
			for (TaskletItemArg taskletItemArg : itemArgs) {
				argDAO.merge(taskletItemArg);
			}
		}
		return Response.createResponse(Res.OK, Res.valueOf(Res.OK));
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			// ������֤�����б�ĳ��ȡ�
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