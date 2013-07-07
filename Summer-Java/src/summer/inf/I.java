package summer.inf;

import summer.pojo.Tasklet;



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
		 * 
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(Long.valueOf(10000));
		 * user.setPassword(&quot;123456&quot;);
		 * session.write(Request.createRequest(Req.LOGIN, user));
		 * </pre>
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
		 * ������ʵ���������Ϊ��ֵ��ʹ��name�޸��û���Ϣʱ��{@code User}
		 * ����ʵ�����������Ϊ��ֵ�������������ֵ������ʵ���򱣳־�ֵ��
		 * <p>
		 * <strong>��֧���޸��û�id</strong>
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * User user = new User()//or use full constructor
		 * user.setId(Long.valueOf(10000));// modified by id
		 * user.setName(&quot;sduxzz&quot;);// old value
		 * user.setPassword(&quot;654321&quot;);// new value
		 * user.setPermission(7);// new value
		 * user.setType(1);// old value
		 * user.setTellphone(&quot;18769783279&quot;);// old value
		 * user.setAddress(&quot;���&quot;);// old value
		 * session.write(Request.createRequest(Req.USER_MODIFIED, user));
		 * </pre>
		 */
		public final static int USER_MODIFIED = 11;

		/**
		 * ��ȡ���������
		 * <p>
		 * ����requestCode = TASKLET_PULL;requestArgs =
		 * {User{id},Long,Long,Boolean;String}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs = {}
		 * <p>
		 * ���ͣ���ȡ�������������ʷ���������δ��������������ȡ��requestArgs�е�һ��long��ʾ��ʼ��ʱ�䣬
		 * �ڶ���long��ʾ������ʱ�䣬boolean��ʾ�����Ƿ���ɡ�String��ʾ�Խ�������򷽷����ο�
		 * {@code TASKLET_LIST_ORDER_*}�� ���ص�responseArgs�ǰ�����{@link Tasklet}
		 * �Ľ������<strong>���Ҫ��ȡ����δ�����������longֵ������Ϊ{@link #TASKLET_ALL}
		 * ��boolean����Ϊfalse�����Ҫ��ȡ����������� ����longֵ����Ϊ{@link #TASKLET_ALL}��
		 * boolean����Ϊtrue��
		 * <p>
		 * �������ڻ�����֧�����û�������ȡ����:(
		 * <p>
		 * �������ڻ�����֧�ֶ��ֶ����� :(
		 * <p>
		 * Ϊ�˽�Լ����ֻ�ǽ�Tasklet�����͸��ͻ��ˣ���û�к��о����TaskletItem���������Ҫ��ʹ��
		 * {@link Req#TASkLETITEM_PULL} </strong>
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(10000);
		 * session.write(Req.TASKLET_PULL, user, Long.valueOf(start), Long.valueOf(end),
		 * 		false);
		 * 
		 * User user = new User();
		 * user.setId(10000);
		 * session.write(Req.TASKLET_PULL, user, Long.valueOf(TASKLET_ALL),
		 * 		Long.valueOf(TASKLET_ALL), false);
		 * </pre>
		 */
		public final static int TASKLET_PULL = 12;

		public final static int TASkLETITEM_PULL = 13;

		/**
		 * �˳�����š�
		 * <p>
		 * ����requestCode = EXIT; requestArgs = {User{id|name}}
		 * <p>
		 * Ӧ��responseCode = OK | BAD_*;responseArgs = {String}
		 * <p>
		 * ���ͣ��˳���Ҫ�û���id����name���˳��ɹ�����{@link Res#OK}���˳�ʧ�ܣ�����{@link Res}
		 * ��BAD_*�����庬��μ�ע�ͣ��������еĶ���String TODO:ȷ��exit��ֵ��ʹ����Щ������������ġ�
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(Long.valueOf(10000));
		 * session.write(Request.createRequest(Req.EXIT, user));
		 * </pre>
		 */
		public final static int EXIT = 50;

		// --------һЩĬ�ϵ�����ֵ����-1��ʼ--------
		/**
		 * ���ڻ�ȡ���������Ĭ������ֵ
		 */
		public final static long TASKLET_ALL = -1;

		// --------TASKLET������--------
		public final static String TASKLET_LIST_ORDER_TIME_DES = " last_time desc ";

		public final static String TASKLET_LIST_ORDER_TIME_ASC = " last_time asc ";

		public final static String TASKLET_LIST_ORDER_CYCLE_DES = " cycle desc ";

		public final static String TASKLET_LIST_ORDER_CYCYLE_ASC = " cycle asc ";
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
				case BAD_SYS:
					return "ϵͳ�ڲ�����!message = ";
				default:
					return "";
			}
		}
	}
}