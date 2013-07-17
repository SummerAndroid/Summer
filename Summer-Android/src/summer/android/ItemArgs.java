package summer.android;

import java.util.ArrayList;
import java.util.HashMap;

import summer.android.net.LoginUtil;
import summer.android.net.TaskletItemPushUtil;
import summer.android.net.module.HandlerDecorator;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
import summer.pojo.User;
import summmer.android.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
/**
 * @author ZhangJun
 */
public class ItemArgs extends ListActivity {
	private String[] mStrings;
	CheckedTextView item;
	int[] errorState;// ��ż����Ĵ�����Ϣ��0��Ĭ��ֵ�޴�״̬��1Ϊ�д�
	ArrayList<TaskletItemArg> list;
	ArrayList<TaskletItemArg> argList;
	ArrayList<TaskletItem> list1;
	int count = 0,index,length;
	//boolean[] isFinished;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_args);
		Button taskletFinish = (Button) findViewById(R.id.tasklet_commit);
		Intent intent = getIntent();
		// ��ȡTaskletItem��ID
		long itemId = intent.getLongExtra("ItemId", 0L);
		Log.i("!!!!!!!!!!!!!!!!!!!", itemId + "");

		list = (ArrayList<TaskletItemArg>) intent
				.getSerializableExtra("ItemArg");
		list1 =  (ArrayList<TaskletItem>) intent.getSerializableExtra("taskletItemList");
		argList=(ArrayList<TaskletItemArg>) intent
				.getSerializableExtra("ArgList");
		item = (CheckedTextView) findViewById(android.R.id.text1);
		//ע�Ͳ���ʵ�ֵ��ǣ�listview��Ŀ����checkbox���Ҷ�ѡ������д���
		// int length = list.size();
		// mStrings = new String[length];
		// errorState = new int[length];
		// for (int i = 0; i < length; i++) {
		//
		// TaskletItemArg itemArg = list.get(i);
		// String name = itemArg.getName();
		// String value = itemArg.getValue();
		// String comment = itemArg.getComment();
		// mStrings[i] = name + "      " + value + "      " + comment;
		//
		// }
		// setListAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_multiple_choice, mStrings));
		//
		// final ListView listView = getListView();
		// listView.setItemsCanFocus(false);
		// listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // ���ö�ѡģʽ
		// handle_error.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// // ����listView���ѡ����
		// for (int i = 0; i < listView.getChildCount(); i++) {
		// CheckedTextView item = (CheckedTextView) listView
		// .getChildAt(i);
		// if (item.isChecked()) {
		// errorState[i] = 1;
		// count++;
		// Log.i("!!!!!!!!!!", "��" + i + "��");
		// Log.i("!!!!!!!!!!", "״̬" + errorState[i] + "");
		// } else {
		// errorState[i] = 0;
		// }
		// }
		// Intent intent = new Intent();
		// intent.putExtra("error", errorState);
		// intent.putExtra("count", count);
		// intent.putExtra("Args", list);
		// intent.setClass(ItemArgs.this, ErrorHandling.class);
		// startActivity(intent);
		// }
		// });
		// ����list���󣬽�name��value,comment������ʾ

		ArrayList<HashMap<String, String>> listShow = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "����");
		map1.put("value", "Ĭ��ֵ");
		map1.put("comment", "��ע");
		listShow.add(map1);
		length = list.size();
		//isFinished=new boolean[length];
		for (int i = 0; i < length; i++) {

			TaskletItemArg itemArg = list.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			String name = itemArg.getName();
			String value = itemArg.getValue();
			String comment = itemArg.getComment();
			map.put("name", name);
			map.put("value", value);
			map.put("comment", comment);
			listShow.add(map);

		} 
		// ����һ��SimpleAdapter���͵ı������������ SimpleAdapter listAdapter = new
		SimpleAdapter listAdapter=new SimpleAdapter(ItemArgs.this, listShow, R.layout.item_arg_layout,
				new String[] { "name", "value", "comment" }, new int[] {
						R.id.name, R.id.value, R.id.comment }); // ������ʾListView
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();
		taskletFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//�ύ��������tasklet��argList
			   list1.get(index).setArgList(argList);
               //isFinished[index]=true;
               // for(int i=0;i<length;i++)
                	//if(isFinished[i]==false)
                		//break;
                	//else
                	//{
                		Intent intent=new Intent();
                		intent.putExtra("taskletItem", list1);
                		intent.setClass(ItemArgs.this,TaskletPush.class);
                		startActivity(intent);
                		ItemArgs.this.finish();
                	//}
                		
			
	}
		});
	}
	// ��дonListItemClick����ListView��Ŀ�¼�
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		index=position-1;
		// ����С�������û���д��ֵ��������ȷ����ť
		Intent intent = new Intent();
		// ��edit����׼�����ݣ���Ѳ����Ա��д��Ϣ����
		Log.i("position!!!!!!!!!!", position + "");
		// debug֮�����±��Ǵ�1��ʼ��
		intent.putExtra("list", list);
		intent.putExtra("taskletItemList", list1);
		intent.putExtra("index", position - 1);
		intent.putExtra("name", list.get(position - 1).getName());
		Log.i("name!!!!!!", list.get(position - 1).getName());
		intent.putExtra("value", list.get(position - 1).getValue());
		Log.i("value!!!!!!", list.get(position - 1).getValue());
		intent.putExtra("comment", list.get(position - 1).getComment());
		Log.i("comment!!!!!!", list.get(position - 1).getComment());
		// intent.putExtra("ItemArg",(ArrayList<TaskletItemArg>)
		// list1.get(position).getArgList());
		intent.setClass(ItemArgs.this, Edit.class);
		ItemArgs.this.startActivity(intent);
		ItemArgs.this.finish();
	}
}
