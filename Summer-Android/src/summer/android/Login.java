package summer.android;

import summmer.android.R;
import summer.android.net.LoginUtil;
import summer.android.net.module.HandlerDecorator;
import summer.inf.I.Res;
import summer.pojo.User;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	private Button login;// 登陆
	private Button exit;// 退出
	private EditText loginname;// 用户名
	private EditText password;// 密码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 System.setProperty("java.net.preferIPv6Addresses", "false"); 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		login = (Button) findViewById(R.id.login);
		exit = (Button) findViewById(R.id.exit);
		loginname = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.userpsw);
		login.setOnClickListener(new LoginListener());
		exit.setOnClickListener(new ExitListener());
	}

	class LoginListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// 连接数据库进行登录
			String userID = loginname.getText().toString();
			String passwd = password.getText().toString();
			Long ID = Long.parseLong(userID);
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
						String message = (String) msg.obj;
						Log.i("!!!!!!!!!!!", message);
					} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
						User user = (User) msg.obj;
						// do other operator
						Log.i("!!!!!!!!!!!", user.toString());
					} else {
						String message = (String) msg.obj;
						Log.i("!!!!!!!!!!!", message);
					}
				}
			};
			LoginUtil loginUtil = new LoginUtil(handler);
			loginUtil.login(ID, passwd);

//			Intent intent0 = new Intent();
//			intent0.setClass(Login.this, Main.class);
//			startActivity(intent0);

		}
	}

	class ExitListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	}
	// @Override
	/*
	 * public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this
	 * adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.login, menu); return true;
	 * 
	 * }
	 */
}
