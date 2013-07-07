package summer.inf;


/**
 * @author zhenzxie
 */
public final class I {

	public final static class Sys {

		/**
		 * address of server
		 */
		public final static String URL = "127.0.0.1";

		/**
		 * port that client used to connect to server
		 */
		public final static int PORT = 8088;

	}

	/**
	 * �ͻ��˺ͷ���������Э����ʹ�õ�������ţ���10��ʼ
	 * 
	 * @author zhenzxie
	 */
	public final static class Req {
		/**
		 * ��¼����š�
		 * <p>
		 * ����questCode = LOGIN; requestArgs = {User{id|name,password}}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs =
		 * {User{id,name,password,...}|String}
		 * <p>
		 * ���ͣ� ��¼֧��id�����û�����¼������ɹ������� {@link Res#OK}���������а���User���󡣵���ʧ�ܣ�����
		 * {@link Res} ��BAD_*�����庬��μ�ע�ͣ��������еĶ����ΪString��ΪThrowable
		 */
		public final static int LOGIN = 10;

		/**
		 * �û���Ϣ�޸������
		 * <p>
		 * ����requestCode = USER_MODIFIED;reuquestArgs = {User{id,name,...}}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs = {User{id,name,...}|String}
		 * <p>
		 * ���ͣ��޸��û�֧��ʹ��id�����û����޸ġ�ʹ��id�޸��û���Ϣʱ��{@code User}
		 * ������ʵ���������Ϊ��ֵ��ʹ��name�޸��û���Ϣʱ��{@code User}����ʵ�����������Ϊ��ֵ����id���Բ����á�
		 * <strong>�����޸�id</strong>
		 */
		public final static int USER_MODIFIED = 11;

		/**
		 * �˳�����š�
		 * <p>
		 * ����requestCode = EXIT; requestArgs = {User{id|name}}
		 * <p>
		 * Ӧ��responseCode = OK | BAD_*;responseArgs = {String}
		 * <p>
		 * ���ͣ��˳���Ҫ�û���id����name���˳��ɹ�����{@link Res#OK}���˳�ʧ�ܣ�����{@link Res}
		 * ��BAD_*�����庬��μ�ע�ͣ��������еĶ���String TODO:ȷ��exit��ֵ��ʹ����Щ������������ġ�
		 */
		public final static int EXIT = 50;

	}

	/**
	 * �ͷ��˺ͷ���������Э����ʹ�õķ��غţ���100��ʼ
	 * 
	 * @author zhenzxie
	 */
	public final static class Res {
		public final static int OK = 100;

		// --------�ͻ����ύ��������101��ʼ--------
		/**
		 * ���ݵĶ�����Request���ʹ���
		 */
		public final static int BAD_REQUEST = 101;
		/**
		 * requestCode����
		 */
		public final static int BAD_REQUESTCODE = 102;
		/**
		 * requestArgΪnull����
		 */
		public final static int BAD_REQUESTARG_NULL = 103;
		/**
		 * requestArgΪempty����
		 */
		public final static int BAD_REQUESTARG_EMPTY = 104;
		/**
		 * requestArg�в���̫�ٴ���
		 */
		public final static int BAD_REQUESTARG_LESS = 105;
		/**
		 * requestArg�в���̫�����
		 */
		public final static int BAD_REQUESTARG_MORE = 106;
		/**
		 * requestArg�в������Ͳ���ȷ����
		 */
		public final static int BAD_REQUESTARG_TYPE = 107;
		/**
		 * requestArg�еĲ���������Э�����
		 */
		public final static int BAD_REQUESTARG_ARG = 108;

		// --------�ͻ��˵�¼ʧ��111��ʼ--------
		/**
		 * id����
		 */
		public final static int BAD_LOGIN_ID = 111;
		/**
		 * name����
		 */
		public final static int BAD_LOGIN_NAME = 112;
		/**
		 * password����
		 */
		public final static int BAD_LOGIN_PASSWORD = 113;

		// --------�������ڲ�����201��ʼ--------
		public final static int BAD_SYS = 201;

		/**
		 * ��code������Ϣ
		 * 
		 * @param code
		 * @return
		 */
		public final static String valueOf(int code) {
			switch (code) {
				case OK:
					return "�����ɹ�!";
				case BAD_REQUEST:
					return "���ݵ���Ϣ����Requst���͵Ķ���!message = ";
				case BAD_REQUESTCODE:
					return "requestCode����!requestCode = ";
				case BAD_REQUESTARG_NULL:
					return "requestArgΪnull!";
				case BAD_REQUESTARG_EMPTY:
					return "requestArgΪEmpty!";
				case BAD_REQUESTARG_LESS:
					return "requestArg�еĲ���̫����!requestArgs = ";
				case BAD_REQUESTARG_MORE:
					return "requestArg�еĲ���̫����!requestArgs = ";
				case BAD_REQUESTARG_TYPE:
					return "requestArg�еĲ������Ͳ���ȷ!requestArgs = ";
				case BAD_REQUESTARG_ARG:
					return "requestAra�еĲ���������Э�飡requestArgs = ";
				case BAD_LOGIN_ID:
					return "�û�ID������!ID = ";
				case BAD_LOGIN_NAME:
					return "�û���������!name = ";
				case BAD_LOGIN_PASSWORD:
					return "�������!";
				default:
					return "";
			}
		}
	}
}