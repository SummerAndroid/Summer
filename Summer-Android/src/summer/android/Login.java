package summer.android;
import summer.android.net.LoginUtil;
import summer.android.net.module.HandlerDecorator;
import summer.pojo.User;
import summmer.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Login extends Activity {
	private static final String PREFS_NAME = "MyUserInfo";
	private Button login;// ��½
	private Button exit;// �˳�
	private CheckBox chkSaveInfo;// ����ѡ���Ƿ��¼����
	private EditText loginname;// �û���
	private EditText password;// ����
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.setProperty("java.net.preferIPv6Addresses", "false");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		sp = getSharedPreferences(PREFS_NAME, 0);
		login = (Button) findViewById(R.id.login);
		exit = (Button) findViewById(R.id.exit);
		loginname = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.userpsw);
		chkSaveInfo = (CheckBox) this.findViewById(R.id.rempsw);
		LoadUserData();
		login.setOnClickListener(new LoginListener());
		exit.setOnClickListener(new ExitListener());
		chkSaveInfo
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// sp = getSharedPreferences("UserInfo", 0);
				// sp.edit().putBoolean("cbrp", isChecked).commit();
			}
		});
	}
	
	class LoginListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// �������ݿ���е�¼

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
						//��user���󴫵�
						if (chkSaveInfo.isChecked()) {
							SaveUserData(true);
						} else {
							SaveUserData(false);
						}
						Intent intent = new Intent();
						intent.putExtra("user",user);
						intent.setClass(Login.this, Main.class);
						startActivity(intent);
						

					} else {
						String message = (String) msg.obj;
						Log.i("!!!!!!!!!!!", message);
						AlertDialog.Builder builder = new AlertDialog.Builder(
								Login.this);
						builder.setMessage(message).setTitle(
								R.string.dialog_title);
						builder.setPositiveButton("ȷ  ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}

								});
						AlertDialog dialog = builder.create();
						dialog.show();
					}

				}
			};
			LoginUtil loginUtil = new LoginUtil(handler);
			loginUtil.login(ID, passwd);
		}
	}

	class ExitListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			System.exit(0);
		}
	}
	/**
	 * �����Ѽ�ס�û���Ϣ
	 */
	private void LoadUserData() {
		// ���������ļ�
		// SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);
		// ��ȡ�����ļ�
		if (sp.getBoolean("isSave", false)) {
			String userName = sp.getString("userName", "");
			String userPassword = sp.getString("userPassword", "");
			if (!("".equals(userName) && "".equals(userPassword))) {
				loginname.setText(userName);
				password.setText(userPassword);
				chkSaveInfo.setChecked(true);
			}
		}
	}

	/**
	 * �����û���Ϣ
	 */
	private void SaveUserData(boolean isChecked) {
		// ���������ļ�
		// SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);
		// д�������ļ�
		Editor spEd = sp.edit();
		if (isChecked) {
			spEd.putBoolean("isSave", true);
			spEd.putString("userName", loginname.getText().toString());
			spEd.putString("userPassword", password.getText().toString());
		} else {
			spEd.putBoolean("isSave", false);
			spEd.putString("userName", "");
			spEd.putString("userPassword", "");
		}
		spEd.commit();
	}

}
