package summer.runtime.handler;

import java.util.List;

import summer.dao.StuffArgDAO;
import summer.dao.StuffCategoryDAO;
import summer.dao.StuffDAO;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;
import summer.pojo.Stuff;
import summer.pojo.StuffCategory;

/**
 * 
 * �豸��Ϣ������
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class StuffInfoPullHandler extends Handler {

	@Override public Response handle(Request request) {
		Response response = super.handle(request);
		if (response != null)
			return response;
		Stuff target;
		try {
			StuffDAO stuffDAO = new StuffDAO();
			Stuff stuff = (Stuff) request.getRequestArgs().get(0);
			target = stuffDAO.findById(stuff.getId());
			if (target != null) {
				StuffCategoryDAO stuffCategoryDAO = new StuffCategoryDAO();
				StuffCategory category = stuffCategoryDAO.findById(target
						.getStuffCategoryId());
				target.setCategoryName(category.getName() == null ? ""
						: category.getName());// TODO:
												// ���ʹ�ô�������֤���ݵ�Լ����Ӧ�þͲ��������Ŀ�������ˡ�

				StuffArgDAO stuffArgDAO = new StuffArgDAO();
				List list = stuffArgDAO.findByStuffId(stuff.getId());
				target.setArgList(list);
				return Response.createResponse(Res.OK, target);
			}
			System.out.println("------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StuffInfoPullHandler", e);
		}
		return Response.createResponse(Res.BAD_SYS, Res.valueOf(Res.BAD_SYS)
				+ "��ѯ�豸��Ϣʱ���������ԣ�");
	}

	@Override protected boolean verify(Request request) {
		if (super.verify(request)) {
			if (verifyArgsLength(request, 1)) {
				Object o = request.getRequestArgs().get(0);
				if (o instanceof Stuff) {
					Stuff stuff = (Stuff) o;
					if (stuff.getId() != null) {
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