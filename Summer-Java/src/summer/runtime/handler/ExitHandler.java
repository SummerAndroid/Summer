package summer.runtime.handler;

import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;

/**
 *
 * @author zhenzxie
 * @since 1.0
 */
public class ExitHandler extends Handler {

	@Override public Response handle(Request request) {
		// TODO:now do nothing...
		return Response.createResponse(Res.OK, "ÍË³ö³É¹¦£¡");
	}

}