package summer.runtime.handler;

import summer.inf.I;
import summer.inf.Request;
import summer.inf.Response;

/**
 * 
 * �������������ʵ�����������,��д{@link #handle(Request)}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public abstract class Handler {
	private int code;
	private String message;
	private Throwable throwable;

	public String getMessage() {
		return message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * 
	 * 
	 * �������������������ɹ�����codeΪ{@link I.Res#OK} �����÷���ֵΪ��������������ɹ���������{@link I.Res}
	 * ������code�����÷���ֵΪnull
	 * 
	 * @param request
	 *            ����
	 * @return response ����Ľ��
	 */
	public abstract Response handle(Request request) ;

	/**
	 * @return the code
	 */
	protected int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	protected void setCode(int code) {
		this.code = code;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	protected void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param throwable
	 *            the throwable to set
	 */
	protected void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}