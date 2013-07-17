package summer.inf;

import summer.dao.UserDAO;
import summer.pojo.Stuff;
import summer.pojo.Tasklet;

/**
 * 
 * �ͷ����������������Э���ʹ�õĳ�����
 * 
 * @author zhenzxie
 */
public final class I {

	public final static class Sys {

		/**
		 * address of server
		 */
		public final static String URL = "211.87.230.39";

		/**
		 * port that client used to connect to server
		 */
		public final static int PORT = 8088;

	}

	/**
	 * �ͻ��˺ͷ���������Э����ʹ�õ�������ţ���10��ʼ
	 * <p>
	 * TODO:USER_MODIFIED ��
	 * TASKLET_ITEM_PUSH����������������bug�����������ǣ�Ҫ�޸ĵ����������ݿ��в����ڣ����ṩ��id������
	 * ������ǣ��������ݿ��в���һ���µļ�¼�������Ǹ���ĳ����¼��ԭ��{@link UserDAO#merge(summer.pojo.User)}
	 * ����û����id�Ƿ���ڵ���֤��������ͻ����ύ���ݵ�ʱ��ȷ��id����ʾ�������Ǵ��ڵġ�
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
		 * session.write(Request.createRequest(what, Req.LOGIN, user));
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
		 * session.write(Request.createRequest(what,Req.USER_MODIFIED, user));
		 * </pre>
		 */
		public final static int USER_MODIFIED = 11;

		/**
		 * ��ȡ���������
		 * <p>
		 * ����requestCode = TASKLET_PULL;requestArgs =
		 * {User{id},Long,Long,Boolean;String}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs =
		 * {List<Tasklet>{Tasklet,Tasklet,...}|String}
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
		 * �������ڻ�����֧��ͬʱ��ȡ��ʷ�����δ�������:(
		 * <p>
		 * Ϊ�˽�Լ����ֻ�ǽ�Tasklet�����͸��ͻ��ˣ���û�к��о����TaskletItem���������Ҫ��ʹ��
		 * {@link Req#TASKLET_ITEM_PULL}
		 * <p>
		 * ������ʱ�����ڣ�����Ҫ���ִ�е�����:�ڻ�ȡ��ʷ�����ʱ�����currentTime < last_time +
		 * cycle,������һ����ʷ������Ϊ���������һ���Ѿ�ִ���ˡ�����������������������ʷ����
		 * �ڻ�ȡδ��������ʱ��currentTime >= last_time + cycle && account !=
		 * 0,����һ��δ�����������������㡣 </strong>
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(10000);
		 * session.write(what, Req.TASKLET_PULL, user, Long.valueOf(start),
		 * 		Long.valueOf(end), false, TASKLET_LIST_ORDER_TIME_DES);
		 * 
		 * User user = new User();
		 * user.setId(10000);
		 * session.write(what, Req.TASKLET_PULL, user, Long.valueOf(TASKLET_ALL),
		 * 		Long.valueOf(TASKLET_ALL), false, TASKLET_LIST_ORDER_TIME_DES);
		 * </pre>
		 */
		public final static int TASKLET_PULL = 12;

		/**
		 * ��ȡ����һ�������
		 * <p>
		 * ����requestCode = TASKLET_ITEM_PULLL;requestArgs = {Tasklet{id}}
		 * <p>
		 * Ӧ��responseCode = OK | BAD_*;responseArgs =
		 * {List<TaskletItem>{TaskletItem,TaskletItem,...}|String}
		 * <p>
		 * ���ͣ�ͨ��Tasklet��id������һ��Tasklet�ľ������ݡ�
		 * ���ص�responseArgs�����а�����Tasklet������TaskletItem����
		 * ��ÿ��TaskletItem�а�����������Ҫ�������ݣ������ݽṹ��һ������name-value pair������
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * Tasklet tasklet = new TaskLet();
		 * tasklet.setId(1L);
		 * session.write(Request.createRequest(what, Req.TASKLET_ITEM_PULL, tasklet));
		 * </pre>
		 */
		public final static int TASKLET_ITEM_PULL = 13;

		/**
		 * �ϴ���ɵ���������������
		 * <p>
		 * ����requestCode = TASKLET_ITEM_PUSH;requestArgs =
		 * {List<TaskletItem>{TaskletItem
		 * {id,tasklet_id,stuff_id,name,List,...},...��}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs = {String}
		 * <p>
		 * ���ͣ��ϴ�����Ĺ���ÿ��ֻ���ϴ�һ�����������Item������requestArgs�����У��������id��tasklet_id,
		 * stuff_id,List<TemplateItemArgs>,name������һ���ַ�����Ϣ��Ϊ����responseCode
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * TaskletItem taskletItem = new TaskletItem();
		 * taskletItem.setId(1L);
		 * taskletItem.setName(&quot;�����߸�1&quot;);
		 * taskletItem.setTaskletId(1L);// ���item������idΪ1L���������id�ſ���ʹ���������������л�õ��Ǹ�����id
		 * taskletItem.setStuffId(1L);// ���Item�Ǽ��idΪ1L���豸�����id�ſ���ʹ���������������л�õ��Ǹ��豸id
		 * List&lt;TaskletItemArg&gt; list = new ArrayList&lt;TaskletItemArg&gt;();
		 * TaskletItemArg arg = new TaskletItemArg();
		 * arg.setId(1L);
		 * arg.setName(&quot;ֱ��&quot;);
		 * arg.setValue(&quot;0.8&quot;);
		 * arg.setTaskletItemId(1L);
		 * arg.setComment(&quot;ֱ����ô��䳤���أ�&quot;);
		 * list.add(arg);
		 * taskletItem.setArgList(list);// list������Ϊ�������Ժͽ���ԡ��ο�TaskletItemArg��
		 * session.write(Request.createRequest(what, Req.TASKLET_ITEM_PUSH, taskletItem));// ����ֻ����һ��Item
		 * </pre>
		 */
		public final static int TASKLET_ITEM_PUSH = 14;

		/**
		 * ��ȡ�豸��Ϣ�����
		 * <p>
		 * ����requestCode = STUFF_INFO_PULL;requestArgs = {Stuff{id}}
		 * <p>
		 * Ӧ��responseCode = OK|BAD_*;responseArgs = {Stuff{id,code,...}|String}
		 * <p>
		 * ���ͣ�ʹ��stuff�����к��е�id�������ض�id��stuff�ľ�����Ϣ��������Ϣ��������id��code�����address���ȵȡ��ο�
		 * {@link Stuff}
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * Stuff stuff = new Stuff();
		 * stuff.setId(1L);
		 * session.write(Request.createRequest(what, Req.STUFF_INFO_PULL, stuff));
		 * </pre>
		 */
		public final static int STUFF_INFO_PULL = 15;

		/**
		 * �˳�����š�
		 * <p>
		 * ����requestCode = EXIT; requestArgs = {User{id|name}}
		 * <p>
		 * Ӧ��responseCode = OK | BAD_*;responseArgs = {String}
		 * <p>
		 * ���ͣ��˳���Ҫ�û���id����name���˳��ɹ�����{@link Res#OK}���˳�ʧ�ܣ�����{@link Res}
		 * ��BAD_*�����庬��μ�ע�ͣ��������еĶ���String
		 * <p>
		 * �磺
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(Long.valueOf(10000));
		 * session.write(Request.createRequest(what, Req.EXIT, user));
		 * </pre>
		 */
		public final static int EXIT = 16;

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
		 * requestArgsΪnull����
		 */
		public final static int BAD_REQUESTARGS_NULL = 103;
		/**
		 * requestArgsΪempty����
		 */
		public final static int BAD_REQUESTARGS_EMPTY = 104;
		/**
		 * requestArgs�в���̫�ٴ���
		 */
		public final static int BAD_REQUESTARGS_LESS = 105;
		/**
		 * requestArgs�в���̫�����
		 */
		public final static int BAD_REQUESTARGS_MORE = 106;
		/**
		 * requestArgs�в������Ͳ���ȷ����
		 */
		public final static int BAD_REQUESTARGS_TYPE = 107;
		/**
		 * requestArgs�еĲ���������Э�����
		 */
		public final static int BAD_REQUESTARGS_ARG = 108;

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
				case BAD_REQUESTARGS_NULL:
					return "requestArgsΪnull!";
				case BAD_REQUESTARGS_EMPTY:
					return "requestArgsΪEmpty!";
				case BAD_REQUESTARGS_LESS:
					return "requestArgs�еĲ���̫����!requestArgs = ";
				case BAD_REQUESTARGS_MORE:
					return "requestArgs�еĲ���̫����!requestArgs = ";
				case BAD_REQUESTARGS_TYPE:
					return "requestArgs�еĲ������Ͳ���ȷ!requestArgs = ";
				case BAD_REQUESTARGS_ARG:
					return "requestArgs�еĲ���������Э�飡requestArgs = ";
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