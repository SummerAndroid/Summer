package summer.android;

import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Book extends Activity  {

	private TextView myTextView01,myTextView02;
	private EditText myEditText01;
	private TableLayout myTableLayout01;
	private TableRow myTableRow01;
	private Button myButton01,myButton02;
	int counter=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);
		myTextView01 = (TextView) findViewById(R.id.myTextView01);
		myTextView02 = (TextView) findViewById(R.id.myTextView02);
		myEditText01 = (EditText) findViewById(R.id.myEditText01);
		myButton01=(Button)findViewById(R.id.myButton01);
		myButton01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}
