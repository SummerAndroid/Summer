package summer.inf;

import summer.dao.UserDAO;
import summer.pojo.Stuff;
import summer.pojo.Tasklet;

/**
 * 
 * 客服端与服务器交互的协议和使用的常量。
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
	 * 客户端和服务器交互协议中使用到的请求号，从10开始
	 * <p>
	 * TODO:USER_MODIFIED 和
	 * TASKLET_ITEM_PUSH两个请求有隐含的bug。触发条件是，要修改的数据在数据库中不存在，即提供的id不存在
	 * 。结果是，将在数据库中插入一条新的记录，而不是更新某条记录。原因：{@link UserDAO#merge(summer.pojo.User)}
	 * 并且没有做id是否存在的验证。解决：客户端提交数据的时候，确保id所表示的数据是存在的。
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
		 * session.write(Request.createRequest(what, Req.LOGIN, user));
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
		 * session.write(Request.createRequest(what,Req.USER_MODIFIED, user));
		 * </pre>
		 */
		public final static int USER_MODIFIED = 11;

		/**
		 * 领取任务请求号
		 * <p>
		 * 请求：requestCode = TASKLET_PULL;requestArgs =
		 * {User{id},Long,Long,Boolean;String}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs =
		 * {List<Tasklet>{Tasklet,Tasklet,...}|String}
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
		 * 并且现在还不想支持同时获取历史任务和未完成任务:(
		 * <p>
		 * 为了节约带宽，只是将Tasklet对象发送给客户端，并没有含有具体的TaskletItem对象。如果需要请使用
		 * {@link Req#TASKLET_ITEM_PULL}
		 * <p>
		 * 对于有时间周期，且需要多次执行的任务:在获取历史任务的时候，如果currentTime < last_time +
		 * cycle,则算是一个历史任务。因为这个任务上一次已经执行了。如果是其他情况不则算是历史任务。
		 * 在获取未完成任务的时候currentTime >= last_time + cycle && account !=
		 * 0,则算一个未完成任务，其他情况不算。 </strong>
		 * <p>
		 * 如：
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
		 * 领取具体一个任务号
		 * <p>
		 * 请求：requestCode = TASKLET_ITEM_PULLL;requestArgs = {Tasklet{id}}
		 * <p>
		 * 应答：responseCode = OK | BAD_*;responseArgs =
		 * {List<TaskletItem>{TaskletItem,TaskletItem,...}|String}
		 * <p>
		 * 解释：通过Tasklet的id来请求一个Tasklet的具体内容。
		 * 返回的responseArgs链表中包含了Tasklet的所有TaskletItem对象
		 * 。每个TaskletItem中包含了所有需要检查的内容，其数据结构是一个含有name-value pair的链表
		 * <p>
		 * 如：
		 * 
		 * <pre class="prettyprint">
		 * Tasklet tasklet = new TaskLet();
		 * tasklet.setId(1L);
		 * session.write(Request.createRequest(what, Req.TASKLET_ITEM_PULL, tasklet));
		 * </pre>
		 */
		public final static int TASKLET_ITEM_PULL = 13;

		/**
		 * 上传完成的任务的内容请求号
		 * <p>
		 * 请求：requestCode = TASKLET_ITEM_PUSH;requestArgs =
		 * {List<TaskletItem>{TaskletItem
		 * {id,tasklet_id,stuff_id,name,List,...},...｝}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs = {String}
		 * <p>
		 * 解释：上传任务的功能每次只能上传一个任务的所有Item，放在requestArgs链表中，必须包括id，tasklet_id,
		 * stuff_id,List<TemplateItemArgs>,name。返回一个字符串消息，为解释responseCode
		 * <p>
		 * 如：
		 * 
		 * <pre class="prettyprint">
		 * TaskletItem taskletItem = new TaskletItem();
		 * taskletItem.setId(1L);
		 * taskletItem.setName(&quot;检查电线杆1&quot;);
		 * taskletItem.setTaskletId(1L);// 这个item是属于id为1L的任务，这个id号可以使用下载任务内容中获得的那个任务id
		 * taskletItem.setStuffId(1L);// 这个Item是检查id为1L的设备，这个id号可以使用下载任务内容中获得的那个设备id
		 * List&lt;TaskletItemArg&gt; list = new ArrayList&lt;TaskletItemArg&gt;();
		 * TaskletItemArg arg = new TaskletItemArg();
		 * arg.setId(1L);
		 * arg.setName(&quot;直径&quot;);
		 * arg.setValue(&quot;0.8&quot;);
		 * arg.setTaskletItemId(1L);
		 * arg.setComment(&quot;直径怎么会变长了呢？&quot;);
		 * list.add(arg);
		 * taskletItem.setArgList(list);// list中设置为检查后属性和结果对。参考TaskletItemArg类
		 * session.write(Request.createRequest(what, Req.TASKLET_ITEM_PUSH, taskletItem));// 任务只包含一个Item
		 * </pre>
		 */
		public final static int TASKLET_ITEM_PUSH = 14;

		/**
		 * 获取设备信息请求号
		 * <p>
		 * 请求：requestCode = STUFF_INFO_PULL;requestArgs = {Stuff{id}}
		 * <p>
		 * 应答：responseCode = OK|BAD_*;responseArgs = {Stuff{id,code,...}|String}
		 * <p>
		 * 解释：使用stuff对象中含有的id来请求特定id的stuff的具体信息。具体信息，包括：id，code，类别，address，等等。参考
		 * {@link Stuff}
		 * <p>
		 * 如：
		 * 
		 * <pre class="prettyprint">
		 * Stuff stuff = new Stuff();
		 * stuff.setId(1L);
		 * session.write(Request.createRequest(what, Req.STUFF_INFO_PULL, stuff));
		 * </pre>
		 */
		public final static int STUFF_INFO_PULL = 15;

		/**
		 * 退出请求号。
		 * <p>
		 * 请求：requestCode = EXIT; requestArgs = {User{id|name}}
		 * <p>
		 * 应答：responseCode = OK | BAD_*;responseArgs = {String}
		 * <p>
		 * 解释：退出需要用户的id或者name。退出成功返回{@link Res#OK}，退出失败，返回{@link Res}
		 * 中BAD_*，具体含义参加注释，且链表中的对象String
		 * <p>
		 * 如：
		 * 
		 * <pre class="prettyprint">
		 * User user = new User();
		 * user.setId(Long.valueOf(10000));
		 * session.write(Request.createRequest(what, Req.EXIT, user));
		 * </pre>
		 */
		public final static int EXIT = 16;

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
		 * requestArgs为null错误
		 */
		public final static int BAD_REQUESTARGS_NULL = 103;
		/**
		 * requestArgs为empty错误
		 */
		public final static int BAD_REQUESTARGS_EMPTY = 104;
		/**
		 * requestArgs中参数太少错误
		 */
		public final static int BAD_REQUESTARGS_LESS = 105;
		/**
		 * requestArgs中参数太多错误
		 */
		public final static int BAD_REQUESTARGS_MORE = 106;
		/**
		 * requestArgs中参数类型不正确错误
		 */
		public final static int BAD_REQUESTARGS_TYPE = 107;
		/**
		 * requestArgs中的参数不符合协议错误
		 */
		public final static int BAD_REQUESTARGS_ARG = 108;

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
				case BAD_REQUESTARGS_NULL:
					return "requestArgs为null!";
				case BAD_REQUESTARGS_EMPTY:
					return "requestArgs为Empty!";
				case BAD_REQUESTARGS_LESS:
					return "requestArgs中的参数太少了!requestArgs = ";
				case BAD_REQUESTARGS_MORE:
					return "requestArgs中的参数太多了!requestArgs = ";
				case BAD_REQUESTARGS_TYPE:
					return "requestArgs中的参数类型不正确!requestArgs = ";
				case BAD_REQUESTARGS_ARG:
					return "requestArgs中的参数不符合协议！requestArgs = ";
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