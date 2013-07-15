package summer.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import summer.android.net.LoginUtil;
import summer.android.net.TaskletItemPullUtil;
import summer.android.net.TaskletItemPushUtil;
import summer.android.net.TaskletPullUtil;
import summer.android.net.module.HandlerDecorator;
import summer.inf.I.Req;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
import summer.pojo.User;
import summmer.android.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/**
 * @author ZhangJun
 */
public class TaskletItemPull extends ListActivity {
	
	int length,index;
	
	 ArrayList<TaskletItem> list1,listPush;
	//ArrayList<TaskletItemArg> list2;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasklet_item_pull);
		Intent intent = getIntent();
		Tasklet tasklet = (Tasklet) intent.getSerializableExtra("tasklet");
		listPush=(ArrayList<TaskletItem>) intent.getSerializableExtra("taskletItem");
		list1=(ArrayList<TaskletItem>) intent.getSerializableExtra("taskletItemList");
		Button taskletFinish = (Button) findViewById(R.id.tasklet_commit);
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
					String message = (String) msg.obj;
					Log.i("!!!!!!!!!!!", message);
				} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
					// 成功返回的obj是List<TaskletItem>,放入链表中进行处理
					// 生成一个ArrayList类型的变量list
					ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
					list1 = (ArrayList<TaskletItem>) msg.obj;
					length = list1.size();

					// 同时取出TaskletItemArg的数据
					//list2 = (ArrayList<TaskletItemArg>) msg.obj;

					for (int i = 0; i < length; i++) {
						String taskletItemName = list1.get(i).getName();
						// HashMpa为键值对类型。第一个参数为建，第二个参数为值
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("device", taskletItemName);
						list.add(map);
					}
					// 生成一个SimpleAdapter类型的变量来填充数据
					SimpleAdapter listAdapter = new SimpleAdapter(
							TaskletItemPull.this, list,
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
		
		TaskletItemPullUtil pullUtil = new TaskletItemPullUtil(handler);
		pullUtil.itemPull(tasklet);
	
	}

	// 重写onListItemClick但是ListView条目事件
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		//任务回传时使用
		index=position;
		intent.putExtra("taskletItemList", list1);
		intent.putExtra("taskletItem", list1.get(position));
		intent.putExtra("ItemId",list1.get(position).getId());
		intent.putExtra("ItemArg",(ArrayList<TaskletItemArg>) list1.get(position).getArgList());
		intent.setClass(TaskletItemPull.this, ItemArgs.class);
		TaskletItemPull.this.startActivity(intent);
	}
}