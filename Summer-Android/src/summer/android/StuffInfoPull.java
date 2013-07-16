package summer.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import summer.android.net.StuffInfoPullUtil;
import summer.android.net.TaskletPullUtil;
import summer.android.net.module.HandlerDecorator;
import summer.inf.I.Req;
import summer.pojo.Stuff;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
import summmer.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class StuffInfoPull extends Activity {
	TextView name, price, life, address, factory;
	Button ok;
	Long stuffId;
	Intent intent;
	int length;
	ArrayList<TaskletItem> list1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stuff_info_pull);
		name = (TextView) findViewById(R.id.name1);
		price = (TextView) findViewById(R.id.price1);
		life = (TextView) findViewById(R.id.life1);
		address = (TextView) findViewById(R.id.address1);
		factory = (TextView) findViewById(R.id.factory1);
		ok = (Button) findViewById(R.id.commit_ok);
		intent = getIntent();
		length = intent.getIntExtra("length", 0);
		list1 = (ArrayList<TaskletItem>) intent
				.getSerializableExtra("taskletItemList");
		stuffId = intent.getLongExtra("stuffId", 1L);
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点击按钮获得参数列表，ArgList，跳转到ItemArgs界面
               Log.i("!!!!!!!!!!!!!!!","调用onClick方法");
				for (int i = 0; i < length; i++) {
					TaskletItem tasklet = list1.get(i);
					if (tasklet.getStuffId().equals(stuffId) ){
						// 设置参数转移到ItemArgs界面
						Intent intent = new Intent();
						intent.putExtra("taskletItemList", list1);
						intent.putExtra("taskletItem", tasklet);
						intent.putExtra("ItemId", tasklet.getId());
						intent.putExtra("ItemArg",
								(ArrayList<TaskletItemArg>) tasklet
										.getArgList());
						intent.setClass(StuffInfoPull.this,
								ItemArgs.class);
						StuffInfoPull.this.startActivity(intent);
                        StuffInfoPull.this.finish();
					}
					else{
						Log.i("!!!!!!!!!!!!","无此项设备");
					}
				}
			}
		});
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1 == HandlerDecorator.HANDLER_SENT) {
					String message = (String) msg.obj;
					Log.i("!!!!!!!!!!!", message);
				} else if (msg.arg1 == HandlerDecorator.HANDLER_RESPONSE) {
					// 成功返回的obj是Stuff对象
					// 生成一个ArrayList类型的变量list
					Stuff stuff = (Stuff) msg.obj;
					name.setText("    " + stuff.getCode());
					price.setText("    " + stuff.getPrice() + "");
					life.setText("    " + stuff.getLife() + "");
					address.setText("    " + stuff.getAddress());
					factory.setText("    " + stuff.getFactory());
				
				} else {
					String message = (String) msg.obj;
					Log.i("!!!!!!!!!!!", message);

				}
			}
		};
		StuffInfoPullUtil infoUtil = new StuffInfoPullUtil(handler);
		Stuff stuff = new Stuff();
		stuff.setId(stuffId);
		infoUtil.infoPull(stuff);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stuff_info_pull, menu);
		return true;
	}

}
