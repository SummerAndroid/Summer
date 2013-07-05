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
				return Response.createResponse(Res.BAD_LOGIN_NAME, "用户名为："
						+ user.getName() + "的用户不存在！");
			}
			target = list.get(0);
		} else {
			target = dao.findById(user.getId());
			if (target == null) {
				return Response.createResponse(Res.BAD_LOGIN_ID,
						"ID为：" + user.getId() + "的用户不存在！");
			}
		}
		if (target.getPassword().equals(user.getPassword())) {
			return Response.createResponse(Res.OK, target);
		}
		return Response.createResponse(Res.BAD_LOGIN_PASSWORD, "密码错误！");
	}
}