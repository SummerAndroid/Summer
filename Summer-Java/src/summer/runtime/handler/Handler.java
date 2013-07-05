package summer.runtime.handler;

import summer.inf.I;
import summer.inf.Request;
import summer.inf.Response;

/**
 * 
 * 具体的请求处理请实现这个抽象类,覆写{@link #handle(Request)}
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
	 * 具体请求处理。处理后，如果成功则置code为{@link I.Res#OK} ，且置返回值为处理结果；如果不成功，则依据{@link I.Res}
	 * 来设置code，且置返回值为null
	 * 
	 * @param request
	 *            请求
	 * @return response 请求的结果
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