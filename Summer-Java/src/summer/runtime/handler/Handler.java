package summer.runtime.handler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import summer.inf.I;
import summer.inf.I.Res;
import summer.inf.Request;
import summer.inf.Response;

/**
 * 
 * �������������ĳ����ࡣ�����˴������֤�����߼�����ʱ���ṩ��������֤�ӿ�
 * <p>
 * ������������̳����������,��д{@link #handle(Request)}��{@link #verify(Request)},eg.
 * {@link LoginHandler}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public abstract class Handler {

	/**
	 * ���ڴ����֤��������Ϣ
	 * 
	 * @author zhenzxie
	 * @since 1.0
	 */
	public static final class VerifyError {
		/**
		 * ��ͬ����������������ݾ���Э�����ò�ͬ�Ĵ�����
		 */
		public int errorCode;
		/**
		 * �������Ϣ�������ڷ��ظ��ͻ���
		 */
		public String errorMessage;

		public VerifyError(int code, String message) {
			errorCode = code;
			errorMessage = message;
		}
	}

	protected Log log = LogFactory.getLog(Handler.this.getClass());
	/**
	 * ���������Ŵ�����Ϣ
	 */
	protected VerifyError verifyError;
	
	/**
	 * 
	 * �������������������ɹ�����responseCodeΪ{@link I.Res#OK} �����÷���ֵΪ��������������ɹ���������
	 * {@link I.Res} ������responseCode�����÷���ֵΪ��������
	 * <p>
	 * ��Handler���ʵ���У�handle����ֻ�ǵ���<strong> �����verify���� </strong>����֤��������Ƿ����Э�顣
	 * <strong>
	 * <li>���ͨ����֤���򷵻ؿգ��Ҳ�����{@link #verifyError}
	 * �����ﷵ��null��Υ���ӿڶ��壬ԭ���ǣ�����handle�гɹ�����󲢲���˵��˵��ҵ��Ĵ�����ɣ����޷�����һ��
	 * {@code Response}
	 * <li>�����ͨ����֤���򷵻ص�Response��Ϊnull,������ {@link #verifyError}�����ﷵ��һ��
	 * {@code Response}����Ϊ����޷���֤ͨ������˵��ҵ�����Ѿ����������Է��ؽ���ˡ� </strong>
	 * <p>
	 * �������һ�����า�����������ʵ�ֵ����ӣ�
	 * 
	 * <pre class="prettyprint">
	 * &#064;Override public Response handle(Request request) {
	 * 	Response response = super.handle(request);
	 * 	if (response != null)
	 * 		return response;
	 * 	...// add some code here to do business logical.
	 * 	return Response.createResponse(responseCode, Res.valueOf(responseCode)+"other message");
	 * }
	 * </pre>
	 * 
	 * @param request
	 *            ����
	 * @return response ����Ľ������Ϊnull
	 */
	public Response handle(Request request) {
		if (verify(request))
			return null;
		assert verifyError != null;
		return Response.createResponse(verifyError.errorCode,
				verifyError.errorMessage);
	}

	/**
	 * ���ݾ���Э�飨�ο�{@link I.Req}��
	 * ����֤�ͻ��˴�������<strong>Request����requestCode������֤��</strong>�Ƿ����Э�顣 <li>
	 * ����Э�飬����true���������� {@link #verifyError} <li>������Э�飬����false��<strong>��������Ϣ������
	 * {@link #verifyError} ������</strong>���Թ�{@link #handle(Request)}����ʹ��
	 * 
	 * ��Handler���ʵ������֤�ˣ�requestArg�Ƿ�Ϊnull�������Ƿ�Ϊ0����Ϊnull�򳤶�Ϊ0���򷵻�false������
	 * {@link #verifyError}�������Ϊnull�ҳ��Ȳ�Ϊ0�򷵻�true��������Ե��������������ȥһЩ�ظ��߼�
	 * 
	 * <p>
	 * �������һ�����า�����������ʵ�ֵ����ӣ�
	 * 
	 * <pre class="prettyprint">
	 * &#064;Override protected boolean verify(Request request) {
	 * 	if (super.verify(request)) {
	 * 		...// add some code here to do detail verification.
	 * 		if(result)
	 * 			return true;
	 * 		else
	 * 			verifyError = new VerifyError(errorCode,errorMessage);
	 * 	}
	 * 	return false;
	 * }
	 * </pre>
	 * 
	 * 
	 * @param request
	 *            ����
	 * @return boolean ���
	 */
	protected boolean verify(Request request) {
		List<Object> list = request.getRequestArgs();
		if (list == null) {
			verifyError = new VerifyError(Res.BAD_REQUESTARG_NULL,
					Res.valueOf(Res.BAD_REQUESTARG_NULL));
			return false;
		}
		if (list.isEmpty()) {
			verifyError = new VerifyError(Res.BAD_REQUESTARG_EMPTY,
					Res.valueOf(Res.BAD_REQUESTARG_EMPTY));
			return false;
		}
		return true;
	}

	/**
	 * ��֤args�ĳ����Ƿ���������ȣ�����������������ȣ�������{@code verifyError}
	 * 
	 * @param request
	 *            ����
	 * @param expected
	 *            �����ĳ���
	 * @return true��������������ĳ��ȣ�false�������������������
	 */
	protected final boolean verifyArgsLength(Request request, int expected) {
		List<Object> list = request.getRequestArgs();
		if (list.size() == expected) {
			return true;
		} else if (list.size() > expected) {
			verifyError = new VerifyError(Res.BAD_REQUESTARG_MORE,
					Res.valueOf(Res.BAD_REQUESTARG_MORE)
							+ request.getRequestArgs());
		} else {
			verifyError = new VerifyError(Res.BAD_REQUESTARG_LESS,
					Res.valueOf(Res.BAD_REQUESTARG_LESS)
							+ request.getRequestArgs());
		}
		return false;
	}
}