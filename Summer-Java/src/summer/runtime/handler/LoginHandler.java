package summer.runtime.handler;

import java.util.List;

import summer.dao.UserDAO;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.User;

/**
 * 
 * µÇÂ¼´¦Àí
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class LoginHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		User user = (User) request.getRequestArgs().get(0);
		UserDAO dao = new UserDAO();
		User target = null;
		if (user.getId() == null) {// login by name
			List<User> list = dao.findByName(user.getName());
			if (list == null || list.isEmpty()) {
				return Response.createResponse(Res.BAD_LOGIN_NAME,
						Res.valueOf(Res.BAD_LOGIN_NAME) + user.getName());
			}
			target = list.get(0);
		} else {// login by id
			target = dao.findById(user.getId());
			if (target == null) {
				return Response.createResponse(Res.BAD_LOGIN_ID,
						Res.valueOf(Res.BAD_LOGIN_ID) + user.getId());
			}
		}
		if (target.getPassword().equals(user.getPassword())) {
			return Response.createResponse(Res.OK, target);
		}
		return Response.createResponse(Res.BAD_LOGIN_PASSWORD,
				Res.valueOf(Res.BAD_LOGIN_PASSWORD));
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			if (request.getRequestArgs().size() == 1) {
				Object o = request.getRequestArgs().get(0);
				if (o instanceof User) {
					User user = (User) o;
					if ((user.getId() != null || user.getName() != null)
							&& user.getPassword() != null) {
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
			} else {
				verifyError = new VerifyError(Res.BAD_REQUESTARG_MORE,
						Res.valueOf(Res.BAD_REQUESTARG_MORE)
								+ request.getRequestArgs());
			}
		}
		return false;
	}
}