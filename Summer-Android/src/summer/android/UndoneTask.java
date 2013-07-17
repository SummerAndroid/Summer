package summer.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import summer.android.net.TaskletPullUtil;
import summer.android.net.module.HandlerDecorator;
import summer.inf.I.Req;
import summer.pojo.Tasklet;
import summer.pojo.User;
import summmer.android.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.SimpleAdapter;

public class UndoneTask extends ListActivity {
	int length;
	User user;
	Tasklet[] tasklet;// 传递tasklet对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.undone_task);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
					String message = (String) msg.obj;
					Log.i("!!!!!!!!!!!", message);
				} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
					// 成功返回的obj是List<Tasklet>,放入链表中进行处理
					// 生成一个ArrayList类型的变量list
					ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
					List<Tasklet> list1 = (List<Tasklet>) msg.obj;
					length = list1.size();
					tasklet = new Tasklet[length];
					for (int i = 0; i < length; i++) {
						tasklet[i] = list1.get(i);
						String taskletName = list1.get(i).getName();
						// HashMpa为键值对类型。第一个参数为建，第二个参数为值
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("device", taskletName);
						list.add(map);

					}
					// 生成一个SimpleAdapter类型的变量来填充数据
					SimpleAdapter listAdapter = new SimpleAdapter(
							UndoneTask.this, list,
							R.layout.taskletpullitem,
							new String[] { "device" },
							new int[] { R.id.device });
					// 设置显示ListView
					setListAdapter(listAdapter);
					listAdapter.notifyDataSetChanged();

				} else {
					String message = (String) msg.obj;
					Log.i("!!!!!!!!!!!", message);

				}
			}
		};
		TaskletPullUtil pullUtil = new TaskletPullUtil(handler);
		pullUtil.taskletPull(user, Long.valueOf(Req.TASKLET_ALL),
				Long.valueOf(Req.TASKLET_ALL), false,
				Req.TASKLET_LIST_ORDER_TIME_DES);
	}


}
