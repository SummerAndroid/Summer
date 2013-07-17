package summer.android;

import summer.android.net.UserModifiedUtil;
import summer.android.net.module.HandlerDecorator;
import summer.pojo.User;
import summmer.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author ZhangJun
 */

public class EditInfo extends Activity {
	// �޸���Ϣ���޸�����
	private Button edit_save, change_pass;
	TextView showID;
	EditText editName, editAddress, editTele;
    User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// ���޸���Ϣ����������ʾ��ǰID����Ϣ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_info);
		edit_save = (Button) findViewById(R.id.edit_info);
		change_pass = (Button) findViewById(R.id.change_pass);
		edit_save.setOnClickListener(new saveInfoListener());
		change_pass.setOnClickListener(new ChangPasswdListener());
		showID = (TextView) findViewById(R.id.showID);
		editName = (EditText) findViewById(R.id.editName);
		editAddress = (EditText) findViewById(R.id.editAdress);
		editTele = (EditText) findViewById(R.id.editTele);
		Intent intent = getIntent();
	    user = (User) intent.getSerializableExtra("user");
		showID.setText(user.getId().toString());
		editName.setText(user.getName());
		editAddress.setText(user.getAddress());
		editTele.setText(user.getTellphone());
	}
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
				String message = (String) msg.obj;
				Log.i("!!!!!!!!!!!", message);
			} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
				// �ɹ����ص�obj��User����
				user= (User) msg.obj;
				editName.setText(user.getName());
				editAddress.setText(user.getAddress());
				editTele.setText(user.getTellphone());
			    Log.i("!!!!!!!!!",user.toString());
			    AlertDialog.Builder builder = new AlertDialog.Builder(
						EditInfo.this);
				builder.setMessage("������Ϣ�ɹ�").setTitle(
						"��Ϣ��ʾ");
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
			    
			} else {
				String message = (String) msg.obj;
				Log.i("!!!!!!!!!!!", message);

			}
		}
	};
	
	class saveInfoListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// �˴����޸�֮������ݱ������ݿ�
			//�û���д�޸���Ϣ����ȡ�༭����Ϣ��װ��user����
			user.setName(editName.getText().toString());
			user.setAddress(editAddress.getText().toString());
			user.setTellphone(editTele.getText().toString());
			UserModifiedUtil saveUtil = new UserModifiedUtil(handler);
			saveUtil.modified(user);
		}
	}
     class ChangPasswdListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("user", user);
			intent.setClass(EditInfo.this, Password.class);
			startActivity(intent);
		}
    	 
     }
}