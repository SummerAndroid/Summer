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
				user.setId(Long.valueOf(10000));// modified by id
				user.setName("sduxzz");// old value
				user.setPassword("654321");// new value
				user.setPermission(7);// new value
				user.setType(1);// old value
				user.setTellphone("18769783279");// old value
				user.setAddress("Ìì¹ú");// old value
				session.write(Request.createRequest(Req.USER_MODIFIED, user));

			}

			@Override public void messageReceived(IoSession session,
					Object message) throws Exception {
				System.out.println(message.toString());
			}
		});
		connector.connect(new InetSocketAddress("localhost", Sys.PORT));
	}
}