package summer.android;

import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Scan extends Activity {
	private ImageButton yiwancheng;
	private ImageButton weiwancheng;
	private ImageButton book;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		yiwancheng=(ImageButton)findViewById(R.id.finished);
		weiwancheng=(ImageButton)findViewById(R.id.unfinished);
		book=(ImageButton)findViewById(R.id.book);
		yiwancheng.setOnClickListener(new HistoryTaskListener());
		book.setOnClickListener(new BookListener());
	}
   class HistoryTaskListener implements OnClickListener{

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setClass(Scan.this,HistoryTask.class);
		startActivity(intent);
	}
	   
   }
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan, menu);
		return true;
	}
*/
   class BookListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(Scan.this,Book.class);
			startActivity(intent);
		}
	   
   }
}
