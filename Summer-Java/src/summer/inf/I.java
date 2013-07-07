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
	 * 客户端和服务器交互协议中使用到的请求号，从10开始
	 * 
	 * @author zhenzxie
	 */
	public final static class Req {
		/**
		 * 登录请求号。
		 * <p>
		 * 请求：questCode = LOGIN; requestArgs = {User{id|name,password}}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs =
		 * {User{id,name,password,...}|String}
		 * <p>
		 * 解释： 登录支持id或者用户名登录。登入成功，返回 {@link Res#OK}，且链表中包含User对象。登入失败，返回
		 * {@link Res} 中BAD_*，具体含义参见注释，且链表中的对象或为String或为Throwable
		 * 
		 * <p>
		 * 如：
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
		 * 用户信息修改请求号
		 * <p>
		 * 请求：requestCode = USER_MODIFIED;reuquestArgs = {User{id,name,...}}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs = {User{id,name,...}|String}
		 * <p>
		 * 解释：修改用户支持使用id或者用户名修改。使用id修改用户信息时，{@code User}
		 * 其他的实例域可设置为新值。使用name修改用户信息时，{@code User}
		 * 其他实例域可以设置为新值。如果不设置新值，则让实例域保持旧值。
		 * <p>
		 * <strong>不支持修改用户id</strong>
		 * <p>
		 * 如：
		 * 
		 * <pre class="prettyprint">
		 * User user = new User()//or use full constructor
		 * user.setId(Long.valueOf(10000));// modified by id
		 * user.setName(&quot;sduxzz&quot;);// old value
		 * user.setPassword(&quot;654321&quot;);// new value
		 * user.setPermission(7);// new value
		 * user.setType(1);// old value
		 * user.setTellphone(&quot;18769783279&quot;);// old value
		 * user.setAddress(&quot;天国&quot;);// old value
		 * session.write(Request.createRequest(Req.USER_MODIFIED, user));
		 * </pre>
		 */
		public final static int USER_MODIFIED = 11;

		/**
		 * 领取任务请求号
		 * <p>
		 * 请求：requestCode = TASKLET_PULL;requestArgs =
		 * {User{id},Long,Long,Boolean;String}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs = {}
		 * <p>
		 * 解释：领取任务请求包括历史任务请求和未完成任务请求的领取。requestArgs中第一个long表示开始的时间，
		 * 第二个long表示结束的时间，boolean表示任务是否完成。String表示对结果的排序方法，参考
		 * {@code TASKLET_LIST_ORDER_*}。 返回的responseArgs是包含了{@link Tasklet}
		 * 的结果链表。<strong>如果要获取所有未完成任务，两个long值都设置为{@link #TASKLET_ALL}
		 * ，boolean设置为false；如果要获取所有完成任务， 两个long值设置为{@link #TASKLET_ALL}，
		 * boolean设置为true；
		 * <p>
		 * 并且现在还不想支持用用户名来获取任务:(
		 * <p>
		 * 并且现在还不想支持多字段排序 :(
		 * <p>
		 * 为了节约带宽，只是将Tasklet对象发送给客户端，并没有含有具体的TaskletItem对象。如果需要请使用
		 * {@link Req#TASkLETITEM_PULL} </strong>
		 * <p>
		 * 如：
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
		 * 退出请求号。
		 * <p>
		 * 请求：requestCode = EXIT; requestArgs = {User{id|name}}
		 * <p>
		 * 应答：responseCode = OK | BAD_*;responseArgs = {String}
		 * <p>
		 * 解释：退出需要用户的id或者name。退出成功返回{@link Res#OK}，退出失败，返回{@link Res}
		 * 中BAD_*，具体含义参加注释，且链表中的对象String TODO:确定exit的值，使得这些请求号是连续的。
		 * <p>
		 * 如：
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(Long.valueOf(10000));
		 * session.write(Request.createRequest(Req.EXIT, user));
		 * </pre>
		 */
		public final static int EXIT = 50;

		// --------一些默认的特殊值，从-1开始--------
		/**
		 * 用于获取所以任务的默认特殊值
		 */
		public final static long TASKLET_ALL = -1;

		// --------TASKLET的排序--------
		public final static String TASKLET_LIST_ORDER_TIME_DES = " last_time desc ";

		public final static String TASKLET_LIST_ORDER_TIME_ASC = " last_time asc ";

		public final static String TASKLET_LIST_ORDER_CYCLE_DES = " cycle desc ";

		public final static String TASKLET_LIST_ORDER_CYCYLE_ASC = " cycle asc ";
	}

	/**
	 * 客服端和服务器交互协议中使用的返回号，从100开始
	 * 
	 * @author zhenzxie
	 */
	public final static class Res {
		public final static int OK = 100;

		// --------客户端提交参数错误101开始--------
		/**
		 * 传递的对象不是Request类型错误
		 */
		public final static int BAD_REQUEST = 101;
		/**
		 * requestCode错误
		 */
		public final static int BAD_REQUESTCODE = 102;
		/**
		 * requestArg为null错误
		 */
		public final static int BAD_REQUESTARG_NULL = 103;
		/**
		 * requestArg为empty错误
		 */
		public final static int BAD_REQUESTARG_EMPTY = 104;
		/**
		 * requestArg中参数太少错误
		 */
		public final static int BAD_REQUESTARG_LESS = 105;
		/**
		 * requestArg中参数太多错误
		 */
		public final static int BAD_REQUESTARG_MORE = 106;
		/**
		 * requestArg中参数类型不正确错误
		 */
		public final static int BAD_REQUESTARG_TYPE = 107;
		/**
		 * requestArg中的参数不符合协议错误
		 */
		public final static int BAD_REQUESTARG_ARG = 108;

		// --------客户端登录失败111开始--------
		/**
		 * id错误
		 */
		public final static int BAD_LOGIN_ID = 111;
		/**
		 * name错误
		 */
		public final static int BAD_LOGIN_NAME = 112;
		/**
		 * password错误
		 */
		public final static int BAD_LOGIN_PASSWORD = 113;

		// --------服务器内部错误201开始--------
		public final static int BAD_SYS = 201;

		/**
		 * 由code生成信息
		 * 
		 * @param code
		 * @return
		 */
		public final static String valueOf(int code) {
			switch (code) {
				case OK:
					return "操作成功!";
				case BAD_REQUEST:
					return "传递的消息不是Requst类型的对象!message = ";
				case BAD_REQUESTCODE:
					return "requestCode错误!requestCode = ";
				case BAD_REQUESTARG_NULL:
					return "requestArg为null!";
				case BAD_REQUESTARG_EMPTY:
					return "requestArg为Empty!";
				case BAD_REQUESTARG_LESS:
					return "requestArg中的参数太少了!requestArgs = ";
				case BAD_REQUESTARG_MORE:
					return "requestArg中的参数太多了!requestArgs = ";
				case BAD_REQUESTARG_TYPE:
					return "requestArg中的参数类型不正确!requestArgs = ";
				case BAD_REQUESTARG_ARG:
					return "requestAra中的参数不符合协议！requestArgs = ";
				case BAD_LOGIN_ID:
					return "用户ID不存在!ID = ";
				case BAD_LOGIN_NAME:
					return "用户名不存在!name = ";
				case BAD_LOGIN_PASSWORD:
					return "密码错误!";
				case BAD_SYS:
					return "系统内部错误!message = ";
				default:
					return "";
			}
		}
	}
}