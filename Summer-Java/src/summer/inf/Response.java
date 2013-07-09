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
	 * ��ʶ����ĺ�
	 */
	private int what;

	/**
	 * ���غţ���ο�I.java�е�{@link I.Res}
	 */
	private int responseCode;
	/**
	 * ���ؽ���б���ο�ÿ������Э��
	 */
	private List<Object> responseArgs;

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
		return "Response [what=" + what + ", responseCode=" + responseCode
				+ ", responseArgs=" + responseArgs + "]";
	}

	/**
	 * convenient method for create response
	 * 
	 * @param code
	 *            responseCode
	 * @param object
	 *            Ҫ�ŵ�{@code responseArgs}�еĶ���
	 * @return
	 */
	public static Response createResponse(int what, int code, Object object) {
		Response response = new Response();
		response.what = what;
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
	 *            Ҫ�ŵ�{@code responseArgs}�еĶ�������
	 * @return
	 */
	public static Response createResponse(int what, int code, Object... objects) {
		Response response = new Response();
		response.what = what;
		response.responseCode = code;
		ArrayList<Object> list = new ArrayList<Object>(objects.length);
		for (Object object : objects) {
			list.add(object);
		}
		response.responseArgs = list;
		return response;
	}
}