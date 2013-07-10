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

public class Main extends Activity {
	private ImageButton lingqu;
	private ImageButton liulan;
	private ImageButton dangqian;
	private ImageButton xinxi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lingqu=(ImageButton)findViewById(R.id.lingqurenwu);
		liulan=(ImageButton)findViewById(R.id.liulanrenwu);
		dangqian=(ImageButton)findViewById(R.id.dangqianrenwu);
		xinxi=(ImageButton)findViewById(R.id.xinxiguanli);
		liulan.setOnClickListener(new LiulanListener());
		xinxi.setOnClickListener(new XinxiListener());
		dangqian.setOnClickListener(new DangqianListener());

	}
    class LinquListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    class LiulanListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent1=new Intent();
			intent1.setClass(Main.this,Scan.class);
			startActivity(intent1);
		}
    	
    }
    class DangqianListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(Main.this,Tasklist.class);
			startActivity(intent);
		}
    	
    }
    class XinxiListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(Main.this,EditInfo.class);
			startActivity(intent);
		}
    	
    }
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
*/
}
