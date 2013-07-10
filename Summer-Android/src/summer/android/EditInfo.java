package summer.android;

import summmer.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EditInfo extends Activity {
	// 修改信息和修改密码
	private Button edit_save, change_pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_info);
		edit_save = (Button) findViewById(R.id.edit_info);
		change_pass = (Button) findViewById(R.id.change_pass);
		edit_save.setOnClickListener(new saveInfoListener());

	}

	class saveInfoListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// 此处将修改之后的数据保存数据库
		}

	}
}
