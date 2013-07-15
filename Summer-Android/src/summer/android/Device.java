package summer.android;

import summmer.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
/**
 * @author ZhangJun
 */
public class Device extends Activity {
	private Spinner spinner1 = null,spinner2=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device);
		spinner1 = (Spinner) findViewById(R.id.Spinnered1);
		spinner2 = (Spinner) findViewById(R.id.Spinnered2);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.label_array,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		/*
		 * //方法2 List<String> list = new ArrayList<String>(); list.add("test1");
		 * list.add("test2"); ArrayAdapter adapter = new
		 * ArrayAdapter(this,R.layout.item,R.id.TextViewId,list);
		 * 如果按上面这样做：则需要新建一个item.xml
		 */
		spinner1.setAdapter(adapter);
		spinner1.setPrompt("下拉菜单");
		spinner1.setOnItemSelectedListener(new SpinnerOnItemSelectListener());
		spinner2.setAdapter(adapter);
		spinner2.setPrompt("下拉菜单");
		spinner2.setOnItemSelectedListener(new SpinnerOnItemSelectListener());
	}
}

class SpinnerOnItemSelectListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> AdapterView, View view,
			int position, long arg3) {
		// TODO Auto-generated method stub
		String selected = AdapterView.getItemAtPosition(position).toString();
		System.out.println(selected);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		System.out.println("NothingSelected");

	}

}
