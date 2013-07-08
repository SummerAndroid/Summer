package summer.runtime.handler;

import summer.dao.UserDAO;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.User;

/**
 * �û��޸���Ϣ����
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class UserModifiedHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		User user = (User) request.getRequestArgs().get(0);
		UserDAO dao = new UserDAO();
		try {
			User target = dao.merge(user);
			if (target != null)
				return Response.createResponse(Res.OK, target);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("UserModifiedHandler", e);
		}
		return Response.createResponse(Res.BAD_SYS, Res.valueOf(Res.BAD_SYS)
				+ "����Userʱ���������ԣ�");
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			if (verifyArgsLength(request, 1)) {
				Object o = request.getRequestArgs().get(0);
				if (o instanceof User) {
					User user = (User) o;
					if (user.getId() != null || user.getName() != null) {
						// TODO:����û�м��User������ʵ�����Ƿ�Ϊnull
						return true;
					} else {
						verifyError = new VerifyError(Res.BAD_REQUESTARGS_ARG,
								Res.valueOf(Res.BAD_REQUESTARGS_ARG)
										+ request.getRequestArgs());
					}
				} else {
					verifyError = new VerifyError(Res.BAD_REQUESTARGS_TYPE,
							Res.valueOf(Res.BAD_REQUESTARGS_TYPE)
									+ request.getRequestArgs());
				}
			}
		}
		return false;

	}
}