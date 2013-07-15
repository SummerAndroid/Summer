package summer.android;

import summer.android.net.UserModifiedUtil;
import summer.android.net.module.HandlerDecorator;
import summer.pojo.User;
import summmer.android.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Password extends Activity {
	private EditText oldpsw = null;
	private EditText newpsw1 = null;
	private EditText newpsw2 = null;
	private Button entry;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		oldpsw = (EditText) findViewById(R.id.user_opsw);
		newpsw1 = (EditText) findViewById(R.id.user_npsw1);
		newpsw2 = (EditText) findViewById(R.id.user_npsw2);
		entry = (Button) findViewById(R.id.entry);
		entry.setOnClickListener(new EntryListener());
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
				String message = (String) msg.obj;
				Log.i("!!!!!!!!!!!", message);
			} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
				// 成功返回的obj是User对象
				user = (User) msg.obj;
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Password.this);
				builder.setMessage("密码修改成功").setTitle(R.string.dialog_title);
				builder.setPositiveButton("确  定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}

						});
				AlertDialog dialog = builder.create();
				dialog.show();
				Log.i("!!!!!!!!!", user.toString());
			} else {
				String message = (String) msg.obj;
				Log.i("!!!!!!!!!!!", message);

			}
		}
	};

	class EntryListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			String name1 = newpsw1.getText().toString();
			String name2 = newpsw2.getText().toString();
			if ((oldpsw.getText().toString()).equals(user.getPassword())) {
				if (name1.equals(name2)) {
					// 密码输入一致，设置user的密码域进行修改
					user.setPassword(name1);
					UserModifiedUtil saveUtil = new UserModifiedUtil(handler);
					saveUtil.modified(user);

				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Password.this);
					builder.setMessage("两次输入密码不一致，请重新输入").setTitle(
							R.string.dialog_title);
					builder.setPositiveButton("确  定",
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
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Password.this);
			    Log.i("!!!!!!!!",user.getPassword());
				builder.setMessage("当前密码输入错误").setTitle(R.string.dialog_title);
				builder.setPositiveButton("确  定",
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
	}

}
