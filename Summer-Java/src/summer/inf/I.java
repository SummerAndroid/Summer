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
		 * 使用用户名登录时，请将user的id置为default_id
		 */
		public final static long DEFAULT_ID = -1;
	}

	/**
	 * 客户端和服务器交互协议中使用到的请求号，从10开始
	 * 
	 * @author zhenzxie
	 */
	public final static class Req {
		/**
		 * <p>
		 * 登录请求号。
		 * <p>
		 * 请求：questCode = LOGIN; questArgs = {User{id|name,password}}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs =
		 * {User{id,name,password,...}|String}
		 * <p>
		 * 附加： 登录支持id或者用户名登录，如果用户名登录，则需要用{@link Sys#DEFAULT_ID}设置user的id
		 */
		public final static int LOGIN = 10;
		public final static int EXIT = 11;

	}

	/**
	 * 客服端和服务器交互协议中使用的返回号，从100开始
	 * 
	 * @author zhenzxie
	 */
	public final static class Res {
		public final static int OK = 100;

		// --------客户端提交参数错误101开始--------
		public final static int BAD_REQUEST = 101;
		public final static int BAD_REQUESTCODE = 102;

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
	}
}