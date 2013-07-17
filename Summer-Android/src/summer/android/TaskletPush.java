package summer.android;

import java.util.ArrayList;
import java.util.HashMap;

import summer.android.net.TaskletItemPushUtil;
import summer.android.net.module.HandlerDecorator;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
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
import android.widget.Toast;
/**
 * @author ZhangJun
 */
public class TaskletPush extends ListActivity {
	 ArrayList<TaskletItem> listPush;
     int index,length;
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
		length = listPush.size();

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
						AlertDialog.Builder builder = new AlertDialog.Builder(
								TaskletPush.this);
						builder.setMessage("任务提交成功").setTitle(
								"提示");
						builder.setPositiveButton("确  定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
//										Intent intent=new Intent();
//										intent.setClass(TaskletPush.this, TaskletPull.class);
//										startActivity(intent);
									}

								});
						AlertDialog dialog = builder.create();
						dialog.show();
					} else {
						//错误信息
						String message = (String) msg.obj;
						Log.i("!!!!!!!!!!!", message);
					}

				}
			};
			TaskletItemPushUtil pushUtil = new TaskletItemPushUtil(handler);
			pushUtil.itemPush(listPush);
		
}
	});

}
	// 重写onListItemClick但是ListView条目事件
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// Intent intent = new Intent();
		// 任务回传时使用
		index = position;
		Toast.makeText(this, "开启摄像头进行二维码扫描", 10).show();
		// intent.putExtra("taskletItemList", list1);
		// intent.putExtra("taskletItem", list1.get(position));
		// intent.putExtra("ItemId",list1.get(position).getId());
		// intent.putExtra("ItemArg",(ArrayList<TaskletItemArg>)
		// list1.get(position).getArgList());
		// intent.setClass(TaskletItemPull.this, ItemArgs.class);
		// TaskletItemPull.this.startActivity(intent);
		Intent intent = new Intent();
		intent.putExtra("length",length);
		intent.putExtra("position", index);
		intent.putExtra("taskletItemList", listPush);
		intent.putExtra("taskletItem", listPush.get(index));
		intent.putExtra("ItemId", listPush.get(index).getId());
		intent.putExtra("ItemArg", (ArrayList<TaskletItemArg>) listPush
				.get(index).getArgList());
		//Toast.makeText(this, "开启摄像头进行二维码扫描", 10).show();
		intent.setClass(TaskletPush.this, CaptureActivity.class);
		TaskletPush.this.startActivity(intent);
		TaskletPush.this.finish();
}
}
