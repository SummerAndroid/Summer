package summer.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import summer.pojo.Tasklet;
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
import summer.android.TaskletPull;
public class TaskletPull_return extends ListActivity {
    int length;
    Tasklet[] tasklet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasklet_pull_return);

		// 成功返回的obj是List<Tasklet>,放入链表中进行处理
		// 生成一个ArrayList类型的变量list
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Log.i("!!!!!!!!!!!",TaskletPull.list_save.toString());
		length = TaskletPull.list_save.size();
		tasklet=new Tasklet[length];
		for (int i = 0; i < length; i++) {
			tasklet[i]=TaskletPull.list_save.get(i);
			String taskletName = TaskletPull.list_save.get(i).getName();
			// HashMpa为键值对类型。第一个参数为建，第二个参数为值
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("device", taskletName);
			list.add(map);

		}
		// 生成一个SimpleAdapter类型的变量来填充数据
		SimpleAdapter listAdapter = new SimpleAdapter(
				TaskletPull_return.this, list, R.layout.taskletpullitem,
				new String[] { "device" },
				new int[] { R.id.device });
		// 设置显示ListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();

	
	}
	// 重写onListItemClick但是ListView条目事件
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (int i = 0; i < length; i++) {
			if (id == i) {
				// 转移到设备界面
				Intent intent = new Intent();
				intent.putExtra("tasklet", tasklet[i]);
				intent.setClass(TaskletPull_return.this, TaskletItemPull.class);
				TaskletPull_return.this.startActivity(intent);
			}
		}
	}

	

}
