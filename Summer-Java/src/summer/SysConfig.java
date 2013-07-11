package summer;

/**
 *
 * @author zhenzxie
 * @since 1.0
 */
public final class SysConfig {
	public final static class NetConfig {
		public final static int CLIENT_COUNT_PREDICT = 20;
		public final static int MESSAGE_QUEUE_LENGTH = 30;
	}

	public final static class DB {
		public final static int TYPE_ADMINISTRATOR = 1;
		public final static int TYPE_USER = 0;
		public final static int PERMISSION_MAX = 7;
		public final static int PERMISSION_MIN = 0;
	}
}
