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
	Tasklet[] tasklet;// ����tasklet����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_now);

		// �ɹ����ص�obj��List<Tasklet>,���������н��д���
		// ����һ��ArrayList���͵ı���list
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list1 = TaskletPull.list1;
		now = TaskletPull.now;
		Log.i("!!!!!!!!!!!", list1.toString());
		length = list1.size();
		tasklet = new Tasklet[length];
		String taskletName = list1.get(now).getName();
		// HashMpaΪ��ֵ�����͡���һ������Ϊ�����ڶ�������Ϊֵ
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("device", taskletName);
		list.add(map);

		// ����һ��SimpleAdapter���͵ı������������
		SimpleAdapter listAdapter = new SimpleAdapter(TaskNow.this, list,
				R.layout.taskletpullitem, new String[] { "device" },
				new int[] { R.id.device });
		// ������ʾListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();

	}
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (int i = 0; i < length; i++) {
			if (id == i) {
				// ת�Ƶ��豸����
				Intent intent = new Intent();
				intent.putExtra("tasklet", tasklet[i]);
				intent.setClass(TaskNow.this, Tasklist.class);
				TaskNow.this.startActivity(intent);
			}
		}
	}


}
