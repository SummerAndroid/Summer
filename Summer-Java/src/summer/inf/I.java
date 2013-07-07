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
		 * 其他的实例域可设置为新值。使用name修改用户信息时，{@code User}其他实例域可以设置为新值，而id可以不设置。
		 * <strong>不可修改id</strong>
		 */
		public final static int USER_MODIFIED = 11;

		/**
		 * 退出请求号。
		 * <p>
		 * 请求：requestCode = EXIT; requestArgs = {User{id|name}}
		 * <p>
		 * 应答：responseCode = OK | BAD_*;responseArgs = {String}
		 * <p>
		 * 解释：退出需要用户的id或者name。退出成功返回{@link Res#OK}，退出失败，返回{@link Res}
		 * 中BAD_*，具体含义参加注释，且链表中的对象String TODO:确定exit的值，使得这些请求号是连续的。
		 */
		public final static int EXIT = 50;

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
				default:
					return "";
			}
		}
	}
}