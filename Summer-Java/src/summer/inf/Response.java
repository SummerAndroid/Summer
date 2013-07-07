package summer.inf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenzxie
 */
public class Response implements Serializable {
	
	private static final long serialVersionUID = -1972870269086864563L;
	/**
	 * 返回号，请参考I.java中的{@link I.Res}
	 */
	private int responseCode;
	/**
	 * 返回结果列表，请参考每个具体协议
	 */
	private List<Object> responseArgs;

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseArgs
	 */
	public List<Object> getResponseArgs() {
		return responseArgs;
	}

	/**
	 * @param responseArgs
	 *            the responseArgs to set
	 */
	public void setResponseArgs(List<Object> responseArgs) {
		this.responseArgs = responseArgs;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return "Response [responseCode=" + responseCode + ", responseArgs="
				+ responseArgs + "]";
	}

	/**
	 * convenient method for create response
	 * 
	 * @param code
	 *            responseCode
	 * @param object
	 *            要放到{@code responseArgs}中的对象
	 * @return
	 */
	public static Response createResponse(int code, Object object) {
		Response response = new Response();
		response.responseCode = code;
		ArrayList<Object> list = new ArrayList<Object>(0);
		list.add(object);
		response.responseArgs = list;
		return response;
	}

	/**
	 * convenient method for create response
	 * 
	 * @param code
	 *            responseCode
	 * @param objects
	 *            要放到{@code responseArgs}中的对象数组
	 * @return
	 */
	public static Response createResponse(int code, Object... objects) {
		Response response = new Response();
		response.responseCode = code;
		ArrayList<Object> list = new ArrayList<Object>(objects.length);
		for (Object object : objects) {
			list.add(object);
		}
		response.responseArgs = list;
		return response;
	}
}