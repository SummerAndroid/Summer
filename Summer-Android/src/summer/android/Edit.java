package summer.android;

import java.util.ArrayList;

import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * @author ZhangJun
 */
public class Edit extends Activity {
	TextView name;
	EditText editValue, editComment;
	String name1, value, comment;
	Button commit;
	ArrayList<TaskletItemArg> list;
	ArrayList<TaskletItem> list1;
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		name = (TextView) findViewById(R.id.name);
		editValue = (EditText) findViewById(R.id.editvalue);
		editComment = (EditText) findViewById(R.id.editcomment);
		commit=(Button) findViewById(R.id.commit);
		Intent intent = getIntent();
		list1 =  (ArrayList<TaskletItem>) intent.getSerializableExtra("taskletItemList");
		index=intent.getIntExtra("index", 0);
		name1 = intent.getStringExtra("name");
		Log.i("name!!!!!!",name1);
		name.setText(name1);
		value = intent.getStringExtra("value");
		Log.i("value!!!!!!",value);
		comment = intent.getStringExtra("comment");
		Log.i("comment!!!!!!",comment);
		editValue.setText(value);
		editComment.setText(comment);
		list=(ArrayList<TaskletItemArg>) intent.getSerializableExtra("list");
		commit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//设置listItemArgs链表
				String value=editValue.getText().toString();
				String comment=editComment.getText().toString();
			    list.get(index).setValue(value);
			    list.get(index).setComment(comment);
				//数据写回到listView中，重新显示
			    Intent intent = new Intent();
				intent.putExtra("ItemId",index);
				intent.putExtra("ArgList",list);
				intent.putExtra("ItemArg",list);
				intent.putExtra("taskletItemList", list1);
				intent.setClass(Edit.this, ItemArgs.class);
				Edit.this.startActivity(intent);
				Edit.this.finish();
			}
		});

	}

}
