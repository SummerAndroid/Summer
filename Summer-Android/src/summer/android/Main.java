package summer.android;

import summmer.android.R;
import android.os.Bundle;
import summer.pojo.User;
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
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		lingqu = (ImageButton) findViewById(R.id.lingqurenwu);
		liulan = (ImageButton) findViewById(R.id.liulanrenwu);
		dangqian = (ImageButton) findViewById(R.id.dangqianrenwu);
		xinxi = (ImageButton) findViewById(R.id.xinxiguanli);
		lingqu.setOnClickListener(new LinquListener());
		liulan.setOnClickListener(new LiulanListener());
		xinxi.setOnClickListener(new XinxiListener());
		dangqian.setOnClickListener(new DangqianListener());
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");

	}

	class LinquListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("user", user);
			intent.setClass(Main.this, TaskletPull.class);
			startActivity(intent);
		}

	}

	class LiulanListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent1 = new Intent();
			intent1.putExtra("user", user);
			intent1.setClass(Main.this, Scan.class);
			startActivity(intent1);
		}

	}

	class DangqianListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Main.this, Tasklist.class);
			startActivity(intent);
		}

	}

	class XinxiListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("user", user);
			intent.setClass(Main.this, EditInfo.class);
			startActivity(intent);
		}

	}
	
}
