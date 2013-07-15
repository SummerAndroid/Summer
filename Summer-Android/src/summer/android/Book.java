package summer.android;

import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class Book extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost th=getTabHost();
		
		LayoutInflater.from(this).inflate(R.layout.book
				,th.getTabContentView(),true);
		th.addTab(th.newTabSpec("all").setIndicator("避雷器动作记录").setContent(R.id.RelativeLayout01));
		th.addTab(th.newTabSpec("ok").setIndicator("设备测温记录").setContent(R.id.RelativeLayout02));
		
	}

}
