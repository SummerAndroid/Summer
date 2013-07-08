package summer.runtime.handler;

import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.User;

/**
 * 
 * ÍË³ö´¦Àí
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class ExitHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		log.info(((User) request.getRequestArgs().get(0)).toString() + " exit.");
		return Response.createResponse(Res.OK, Res.valueOf(Res.OK));
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			if (verifyArgsLength(request, 1)) {
				Object o = request.getRequestArgs().get(0);
				if (o instanceof User) {
					User user = (User) o;
					if (user.getId() != null || user.getName() != null) {
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