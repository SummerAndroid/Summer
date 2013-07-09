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
	 * ��ʶ����
	 */
	private int what;

	/**
	 * ����ţ���ο�I.java�е�{@link I.Req}
	 */
	private int requestCode;

	/**
	 * ��������б���ο�ÿ�����������Э��({@link I.Req}�е��ĵ�����)
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
	 *            Ҫ�ŵ�{@code requestArgs}�еĶ���
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
	 *            Ҫ�ŵ�{@code requestArgs}�еĶ�������
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