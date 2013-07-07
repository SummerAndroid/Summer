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
 * 具体请求处理器的抽象类。包括了处理和验证两个逻辑。暂时不提供单独的验证接口
 * <p>
 * 具体请求处理请继承这个抽象类,覆写{@link #handle(Request)}和{@link #verify(Request)},eg.
 * {@link LoginHandler}
 * 
 * @author zhenzxie
 * @since 1.0
 */
public abstract class Handler {

	/**
	 * 用于存放验证错误结果信息
	 * 
	 * @author zhenzxie
	 * @since 1.0
	 */
	public static final class VerifyError {
		/**
		 * 不同的请求参数错误依据具体协议设置不同的错误码
		 */
		public int errorCode;
		/**
		 * 错误的消息，可用于返回给客户端
		 */
		public String errorMessage;

		public VerifyError(int code, String message) {
			errorCode = code;
			errorMessage = message;
		}
	}

	protected Log log = LogFactory.getLog(Handler.this.getClass());
	/**
	 * 用于子类存放错误信息
	 */
	protected VerifyError verifyError;
	
	/**
	 * 
	 * 具体请求处理。处理后，如果成功则置responseCode为{@link I.Res#OK} ，且置返回值为处理结果；如果不成功，则依据
	 * {@link I.Res} 来设置responseCode，且置返回值为处理结果。
	 * <p>
	 * 在Handler类的实现中，handle方法只是调用<strong> 子类的verify方法 </strong>来验证请求参数是否符合协议。
	 * <strong>
	 * <li>如果通过验证，则返回空，且不设置{@link #verifyError}
	 * 。这里返回null，违背接口定义，原因是，父类handle中成功处理后并不能说明说明业务的处理完成，还无法生成一个
	 * {@code Response}
	 * <li>如果不通过验证，则返回的Response不为null,且设置 {@link #verifyError}。这里返回一个
	 * {@code Response}，因为如果无法验证通过，则说明业务处理已经结束，可以返回结果了。 </strong>
	 * <p>
	 * 这里给出一个子类覆盖这个方法的实现的例子：
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
	 *            请求
	 * @return response 请求的结果，不为null
	 */
	public Response handle(Request request) {
		if (verify(request))
			return null;
		assert verifyError != null;
		return Response.createResponse(verifyError.errorCode,
				verifyError.errorMessage);
	}

	/**
	 * 根据具体协议（参考{@link I.Req}）
	 * 来验证客户端传递来的<strong>Request对象（requestCode无需验证）</strong>是否符合协议。 <li>
	 * 符合协议，返回true，不用设置 {@link #verifyError} <li>不符合协议，返回false，<strong>将错误信息设置在
	 * {@link #verifyError} 对象中</strong>，以供{@link #handle(Request)}方法使用
	 * 
	 * 在Handler类的实现中验证了，requestArg是否为null，长度是否为0。若为null或长度为0，则返回false，且置
	 * {@link #verifyError}。如果不为null且长度不为0则返回true。子类可以调用这个方法，除去一些重复逻辑
	 * 
	 * <p>
	 * 这里给出一个子类覆盖这个方法的实现的例子：
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
	 *            请求
	 * @return boolean 结果
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
	 * 验证args的长度是否的期望长度，如果不等于期望长度，则设置{@code verifyError}
	 * 
	 * @param request
	 *            请求
	 * @param expected
	 *            期望的长度
	 * @return true，如果等于期望的长度；false，如果不等于期望长度
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