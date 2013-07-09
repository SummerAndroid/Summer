package summer.inf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenzxie
 */
public class Request implements Serializable {

	private static final long serialVersionUID = -5392965478362669553L;

	/**
	 * 标识请求
	 */
	private int what;

	/**
	 * 请求号，请参考I.java中的{@link I.Req}
	 */
	private int requestCode;

	/**
	 * 请求参数列表，请参考每个具体的请求协议({@link I.Req}中的文档描述)
	 */
	private List<Object> requestArgs;

	/**
	 * @return the what
	 */
	public int getWhat() {
		return what;
	}

	/**
	 * @param what
	 *            the what to set
	 */
	public void setWhat(int what) {
		this.what = what;
	}

	/**
	 * @return the requestCode
	 */
	public int getRequestCode() {
		return requestCode;
	}

	/**
	 * @param requestCode
	 *            the requestCode to set
	 */
	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	/**
	 * @return the requestArgs
	 */
	public List<Object> getRequestArgs() {
		return requestArgs;
	}

	/**
	 * @param requestArgs
	 *            the requestArgs to set
	 */
	public void setRequestArgs(List<Object> requestArgs) {
		this.requestArgs = requestArgs;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return "Request [what=" + what + ", requestCode=" + requestCode
				+ ", requestArgs=" + requestArgs + "]";
	}

	/**
	 * convenient method for create response
	 * 
	 * @param code
	 *            requestCode
	 * @param object
	 *            要放到{@code requestArgs}中的对象
	 * @return
	 */
	public static Request createRequest(int what, int code, Object object) {
		Request request = new Request();
		request.what = what;
		request.requestCode = code;
		ArrayList<Object> list = new ArrayList<Object>(0);
		list.add(object);
		request.requestArgs = list;
		return request;
	}

	/**
	 * convenient method for create response
	 * 
	 * @param code
	 *            requestCode
	 * @param objects
	 *            要放到{@code requestArgs}中的对象数组
	 * @return
	 */
	public static Request createRequest(int what, int code, Object... objects) {
		Request request = new Request();
		request.what = what;
		request.requestCode = code;
		ArrayList<Object> list = new ArrayList<Object>(objects.length);
		for (Object object : objects) {
			list.add(object);
		}
		request.requestArgs = list;
		return request;
	}
}