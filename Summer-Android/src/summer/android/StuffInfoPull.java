package summer.android;

import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StuffInfoPull extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stuff_info_pull);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stuff_info_pull, menu);
		return true;
	}

}
