package summer.android;

import summmer.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * @author ZhangJun
 */
public class Change_passwd extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_passwd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_passwd, menu);
		return true;
	}

}
