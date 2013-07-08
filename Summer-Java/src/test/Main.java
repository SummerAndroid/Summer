/**
 * 
 */
package test;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import summer.inf.I.Req;
import summer.inf.I.Sys;
import summer.inf.Request;
import summer.pojo.User;

/**
 * @author zhenzxie
 */
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.setHandler(new IoHandlerAdapter() {

			@Override public void sessionOpened(IoSession session)
					throws Exception {
				User user = new User();
				user.setId(10001L);
				session.write(Request.createRequest(Req.TASKLET_PULL, user,
						Req.TASKLET_ALL, Req.TASKLET_ALL, false,
						Req.TASKLET_LIST_ORDER_TIME_ASC));

			}

			@Override public void messageSent(IoSession session, Object message)
					throws Exception {
				System.out.println(message.toString());
			}

			@Override public void messageReceived(IoSession session,
					Object message) throws Exception {
				System.out.println(message.toString());
			}
		});
		connector.connect(new InetSocketAddress("localhost", Sys.PORT));
	}
}