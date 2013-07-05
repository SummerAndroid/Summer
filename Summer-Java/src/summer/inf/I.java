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

		/**
		 * ʹ���û�����¼ʱ���뽫user��id��Ϊdefault_id
		 */
		public final static long DEFAULT_ID = -1;
	}

	/**
	 * �ͻ��˺ͷ���������Э����ʹ�õ�������ţ���10��ʼ
	 * 
	 * @author zhenzxie
	 */
	public final static class Req {
		/**
		 * <p>
		 * ��¼����š�
		 * <p>
		 * ����questCode = LOGIN; requestArgs = {User{id|name,password}}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs =
		 * {User{id,name,password,...}|String}
		 * <p>
		 * ���ͣ� ��¼֧��id�����û�����¼������û�����¼������Ҫ��{@link Sys#DEFAULT_ID}����user��id������ɹ�������
		 * {@link Res#OK},�������а���User���󡣵���ʧ�ܣ�����{@link Res}
		 * ��BAD_*�����庬��μ�ע�ͣ��������еĶ����ΪString��ΪThrowable
		 */
		public final static int LOGIN = 10;

		/**
		 * <p>
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
		public final static int BAD_REQUEST = 101;
		public final static int BAD_REQUESTCODE = 102;

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
	}
}