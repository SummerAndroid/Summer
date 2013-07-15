package summer.android;

import java.util.ArrayList;

import summer.pojo.TaskletItemArg;
import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
/**
 * @author ZhangJun
 */
public class ErrorHandling extends Activity {
	int[] errorState;// 存放检查项的错误信息，0是默认值无错状态，1为有错
	ArrayList<TaskletItemArg> list;
	int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.error_handling);
		Intent intent=getIntent();
		errorState=(int[]) intent.getSerializableExtra("error");
		list=(ArrayList<TaskletItemArg>) intent.getSerializableExtra("Args");
		count=intent.getIntExtra("count", 0);
		for(int i=0;i<count;i++)
		{
		  TextView textview1=new TextView(this);
		  textview1.setText(list.get(i).getName());
		  Log.i("!!!!!!!!",list.get(i).getName());
		  TextView textview2=new TextView(this);
		  textview2.setText(list.get(i).getValue());
		  Log.i("!!!!!!!!",list.get(i).getValue());
		  EditText edittext1=new EditText(this);
		  setContentView(textview1);
		  setContentView(textview2);
		  setContentView(edittext1);

		}
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.error_handling, menu);
		return true;
	}

}
