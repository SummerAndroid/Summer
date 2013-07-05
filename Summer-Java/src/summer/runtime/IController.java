package summer.runtime;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import summer.inf.I;

/**
 * 
 * ÍøÂçÍ¨ÐÅ¿ØÖÆÆ÷
 * 
 * @author zhenzxie
 * @since 1.0
 */
public final class IController extends Thread {

	private static IController controller = new IController();

	private Log log = LogFactory.getLog(IController.class);
	private IoAcceptor acceptor;

	private IController() {
	}

	public static IController getInstance() {
		return controller;
	}

	@Override public void run() {

		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.setHandler(new RequestHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 100);
		try {
			acceptor.bind(new InetSocketAddress(I.Sys.PORT));
		} catch (IOException e) {
			e.printStackTrace();
			log.error("IController", e);
		}
		log.info("server started...");
	}
}