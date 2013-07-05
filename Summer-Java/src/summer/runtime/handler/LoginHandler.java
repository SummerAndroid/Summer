package summer.runtime.handler;

import java.util.List;

import summer.dao.UserDAO;
import summer.inf.I.Res;
import summer.inf.I.Sys;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.User;

/**
 *
 * @author zhenzxie
 * @since 1.0
 */
public class LoginHandler extends Handler {

	@Override public Response handle(Request request) {
		User user = (User) request.getRequestArgs().get(0);
		UserDAO dao = new UserDAO();
		User target = null;
		if (user.getId().equals(Long.valueOf(Sys.DEFAULT_ID))) {
			List<User> list = dao.findByName(user.getName());
			if (list == null || list.isEmpty()) {
				return Response.createResponse(Res.BAD_LOGIN_NAME, "�û���Ϊ��"
						+ user.getName() + "���û������ڣ�");
			}
			target = list.get(0);
		} else {
			target = dao.findById(user.getId());
			if (target == null) {
				return Response.createResponse(Res.BAD_LOGIN_ID,
						"IDΪ��" + user.getId() + "���û������ڣ�");
			}
		}
		if (target.getPassword().equals(user.getPassword())) {
			return Response.createResponse(Res.OK, target);
		}
		return Response.createResponse(Res.BAD_LOGIN_PASSWORD, "�������");
	}
}