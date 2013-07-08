/**
 * 
 */
package test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import summer.inf.I.Req;
import summer.inf.I.Sys;
import summer.inf.Request;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;

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
				TaskletItem taskletItem = new TaskletItem();
				taskletItem.setId(1L);
				taskletItem.setName("检查电线杆1");
				taskletItem.setTaskletId(1L);// 这个item是属于id为1L的任务，这个id号可以使用下载任务内容中获得的那个任务id
				taskletItem.setStuffId(1L);// 这个Item是检查id为1L的设备，这个id号可以使用下载任务内容中获得的那个设备id
				List<TaskletItemArg> list = new ArrayList<TaskletItemArg>();
				TaskletItemArg arg = new TaskletItemArg();
				arg.setId(50L);
				arg.setName("长度");
				arg.setValue("10");
				arg.setTaskletItemId(1L);
				arg.setComment("长度怎么会变短了呢？");
				list.add(arg);
				taskletItem.setArgList(list);// list中设置为检查后属性和结果对。参考TaskletItemArg类
				session.write(Request.createRequest(Req.TASKLET_ITEM_PUSH,
						taskletItem));// 任务只包含一个Item
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