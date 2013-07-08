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
				taskletItem.setName("�����߸�1");
				taskletItem.setTaskletId(1L);// ���item������idΪ1L���������id�ſ���ʹ���������������л�õ��Ǹ�����id
				taskletItem.setStuffId(1L);// ���Item�Ǽ��idΪ1L���豸�����id�ſ���ʹ���������������л�õ��Ǹ��豸id
				List<TaskletItemArg> list = new ArrayList<TaskletItemArg>();
				TaskletItemArg arg = new TaskletItemArg();
				arg.setId(50L);
				arg.setName("����");
				arg.setValue("10");
				arg.setTaskletItemId(1L);
				arg.setComment("������ô�������أ�");
				list.add(arg);
				taskletItem.setArgList(list);// list������Ϊ�������Ժͽ���ԡ��ο�TaskletItemArg��
				session.write(Request.createRequest(Req.TASKLET_ITEM_PUSH,
						taskletItem));// ����ֻ����һ��Item
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