package summer.android;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;

import summmer.android.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/**
 * @author ZhangJun
 */
public class Tasklist extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasklist);

		// ����һ��ArrayList���͵ı���list
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		// ��������HashMap���͵ı���map1 �� map2
		// HashMpaΪ��ֵ�����͡���һ������Ϊ�����ڶ�������Ϊֵ
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		HashMap<String, String> map4 = new HashMap<String, String>();
		HashMap<String, String> map5 = new HashMap<String, String>();
		// ��������䵽map1��map2�С�
		map1.put("device", "�豸1");
		map2.put("device", "�豸2");
		map3.put("device", "�豸3");
		map4.put("device", "�豸4");
		map5.put("device", "�豸5");
		// ��map1��map2��ӵ�list��
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		// ����һ��SimpleAdapter���͵ı������������
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.devicelist, new String[] { "device" },
				new int[] { R.id.device });
		// ������ʾListView
		setListAdapter(listAdapter);

	}

	// ��дonListItemClick����ListView��Ŀ�¼�
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			// ת�Ƶ��豸����
			Intent intent = new Intent();
			intent.setClass(Tasklist.this, Device.class);
			Tasklist.this.startActivity(intent);
		}

		/*
		 * // ��ʾ������ĿID�� System.out.println("id = " + id); // ��ʾ��������Ŀ��λ����
		 * System.out.println("position = " + position);
		 */
	}

}
