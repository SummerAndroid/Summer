package summer.android;

import java.util.ArrayList;
import java.util.HashMap;

import summer.android.net.TaskletItemPushUtil;
import summer.android.net.module.HandlerDecorator;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summmer.android.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
/**
 * @author ZhangJun
 */
public class TaskletPush extends ListActivity {
	 ArrayList<TaskletItem> listPush;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasklet_push);
		Intent intent = getIntent();
		Tasklet tasklet = (Tasklet) intent.getSerializableExtra("tasklet");
		listPush=(ArrayList<TaskletItem>) intent.getSerializableExtra("taskletItem");
		for (Object o : listPush) {
			System.out.println(o.getClass());
		}
		
		
		
		Button taskletFinish = (Button) findViewById(R.id.tasklet_commit);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int length = listPush.size();

		// 同时取出TaskletItemArg的数据
		//list2 = (ArrayList<TaskletItemArg>) msg.obj;

		for (int i = 0; i < length; i++) {
			String taskletItemName = listPush.get(i).getName();
			// HashMpa为键值对类型。第一个参数为建，第二个参数为值
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("device", taskletItemName);
			list.add(map);
		}
		// 生成一个SimpleAdapter类型的变量来填充数据
		SimpleAdapter listAdapter = new SimpleAdapter(
				TaskletPush.this, list,
				R.layout.taskletpullitem,
				new String[] { "device" },
				new int[] { R.id.device });
		// 设置显示ListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();
	
	taskletFinish.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//提交任务，设置tasklet的argList
			//list1.get(index).setArgList(argList);
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
						String message = (String) msg.obj;
						Log.i("!!!!!!!!!!!", message);
					} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
						//任务提交

					} else {
						//错误信息
					}

				}
			};
			TaskletItemPushUtil pushUtil = new TaskletItemPushUtil(handler);
			pushUtil.itemPush(listPush);
		
}
	});

}
}
