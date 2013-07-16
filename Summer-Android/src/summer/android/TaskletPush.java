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
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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

		// ͬʱȡ��TaskletItemArg������
		//list2 = (ArrayList<TaskletItemArg>) msg.obj;

		for (int i = 0; i < length; i++) {
			String taskletItemName = listPush.get(i).getName();
			// HashMpaΪ��ֵ�����͡���һ������Ϊ�����ڶ�������Ϊֵ
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("device", taskletItemName);
			list.add(map);
		}
		// ����һ��SimpleAdapter���͵ı������������
		SimpleAdapter listAdapter = new SimpleAdapter(
				TaskletPush.this, list,
				R.layout.taskletpullitem,
				new String[] { "device" },
				new int[] { R.id.device });
		// ������ʾListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();
	
	taskletFinish.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//�ύ��������tasklet��argList
			//list1.get(index).setArgList(argList);
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
						String message = (String) msg.obj;
						Log.i("!!!!!!!!!!!", message);
					} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
						//�����ύ
						AlertDialog.Builder builder = new AlertDialog.Builder(
								TaskletPush.this);
						builder.setMessage("�����ύ�ɹ�").setTitle(
								"��ʾ");
						builder.setPositiveButton("ȷ  ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
										Intent intent=new Intent();
										intent.setClass(TaskletPush.this, TaskletPull.class);
										startActivity(intent);
									}

								});
						AlertDialog dialog = builder.create();
						dialog.show();
					} else {
						//������Ϣ
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
}
