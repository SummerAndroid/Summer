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

		// 生成一个ArrayList类型的变量list
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		// 生成两个HashMap类型的变量map1 ， map2
		// HashMpa为键值对类型。第一个参数为建，第二个参数为值
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		HashMap<String, String> map4 = new HashMap<String, String>();
		HashMap<String, String> map5 = new HashMap<String, String>();
		// 把数据填充到map1和map2中。
		map1.put("device", "设备1");
		map2.put("device", "设备2");
		map3.put("device", "设备3");
		map4.put("device", "设备4");
		map5.put("device", "设备5");
		// 把map1和map2添加到list中
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		// 生成一个SimpleAdapter类型的变量来填充数据
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.devicelist, new String[] { "device" },
				new int[] { R.id.device });
		// 设置显示ListView
		setListAdapter(listAdapter);

	}

	// 重写onListItemClick但是ListView条目事件
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			// 转移到设备界面
			Intent intent = new Intent();
			intent.setClass(Tasklist.this, Device.class);
			Tasklist.this.startActivity(intent);
		}

		/*
		 * // 显示单击条目ID号 System.out.println("id = " + id); // 显示所单击条目的位置数
		 * System.out.println("position = " + position);
		 */
	}

}
