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
		th.addTab(th.newTabSpec("all").setIndicator("������������¼").setContent(R.id.RelativeLayout01));
		th.addTab(th.newTabSpec("ok").setIndicator("�豸���¼�¼").setContent(R.id.RelativeLayout02));
		
	}

}
