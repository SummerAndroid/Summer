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

		// �ɹ����ص�obj��List<Tasklet>,���������н��д���
		// ����һ��ArrayList���͵ı���list
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Log.i("!!!!!!!!!!!",TaskletPull.list_save.toString());
		length = TaskletPull.list_save.size();
		tasklet=new Tasklet[length];
		for (int i = 0; i < length; i++) {
			tasklet[i]=TaskletPull.list_save.get(i);
			String taskletName = TaskletPull.list_save.get(i).getName();
			// HashMpaΪ��ֵ�����͡���һ������Ϊ�����ڶ�������Ϊֵ
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("device", taskletName);
			list.add(map);

		}
		// ����һ��SimpleAdapter���͵ı������������
		SimpleAdapter listAdapter = new SimpleAdapter(
				TaskletPull_return.this, list, R.layout.taskletpullitem,
				new String[] { "device" },
				new int[] { R.id.device });
		// ������ʾListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();

	
	}
	// ��дonListItemClick����ListView��Ŀ�¼�
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (int i = 0; i < length; i++) {
			if (id == i) {
				// ת�Ƶ��豸����
				Intent intent = new Intent();
				intent.putExtra("tasklet", tasklet[i]);
				intent.setClass(TaskletPull_return.this, TaskletItemPull.class);
				TaskletPull_return.this.startActivity(intent);
			}
		}
	}

	

}
