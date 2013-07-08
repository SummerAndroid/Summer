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
import summer.pojo.Stuff;

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
				Stuff stuff = new Stuff();
				stuff.setId(1L);
				session.write(Request.createRequest(Req.STUFF_INFO_PULL, stuff));
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