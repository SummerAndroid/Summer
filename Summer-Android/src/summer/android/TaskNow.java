package summer.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import summer.pojo.Tasklet;
import summer.pojo.User;
import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TaskNow extends ListActivity {
	List<Tasklet> list1;
	int now;
	int length;
	User user;
	Tasklet[] tasklet;// 传递tasklet对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_now);

		// 成功返回的obj是List<Tasklet>,放入链表中进行处理
		// 生成一个ArrayList类型的变量list
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list1 = TaskletPull.list1;
		now = TaskletPull.now;
		Log.i("!!!!!!!!!!!", list1.toString());
		length = list1.size();
		tasklet = new Tasklet[length];
		String taskletName = list1.get(now).getName();
		// HashMpa为键值对类型。第一个参数为建，第二个参数为值
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("device", taskletName);
		list.add(map);

		// 生成一个SimpleAdapter类型的变量来填充数据
		SimpleAdapter listAdapter = new SimpleAdapter(TaskNow.this, list,
				R.layout.taskletpullitem, new String[] { "device" },
				new int[] { R.id.device });
		// 设置显示ListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();

	}
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (int i = 0; i < length; i++) {
			if (id == i) {
				// 转移到设备界面
				Intent intent = new Intent();
				intent.putExtra("tasklet", tasklet[i]);
				intent.setClass(TaskNow.this, Tasklist.class);
				TaskNow.this.startActivity(intent);
			}
		}
	}


}
