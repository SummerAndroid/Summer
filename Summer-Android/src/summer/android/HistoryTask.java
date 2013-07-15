package summer.android;

import java.util.Calendar;

import summmer.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
/**
 * @author ZhangJun
 */
public class HistoryTask extends Activity implements View.OnTouchListener {
	private EditText etStartTime;
	private EditText etEndTime;
	private ImageButton search;
	// 起始年月日和结束年月日
	int startYear, startMonth, startDay;
	int endYear, endMonth, endDay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historytask);
		etStartTime = (EditText) this.findViewById(R.id.et_start_time);
		etEndTime = (EditText) this.findViewById(R.id.et_end_time);
		search=(ImageButton)this.findViewById(R.id.OK);
		etStartTime.setOnTouchListener(this);
		etEndTime.setOnTouchListener(this);
		search.setOnClickListener(new SearchListener());
	}
	class SearchListener implements OnClickListener{
        //获取一段时间的任务列表并进行显示
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = View.inflate(this, R.layout.dateselect, null);
			final DatePicker datePicker = (DatePicker) view
					.findViewById(R.id.date_picker);
			builder.setView(view);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH), null);
			// 开始日期选取
			if (v.getId() == R.id.et_start_time) {
				final int inType = etStartTime.getInputType();
				etStartTime.setInputType(InputType.TYPE_NULL);
				etStartTime.onTouchEvent(event);
				etStartTime.setInputType(inType);
				etStartTime.setSelection(etStartTime.getText().length());
				builder.setTitle("选取起始日期");
				// 点击确定按钮，将选定时间显示在选取开始时间EditText中
				builder.setPositiveButton("确  定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								StringBuffer sb = new StringBuffer();
								sb.append(String.format("%d-%02d-%02d",
										datePicker.getYear(),
										datePicker.getMonth() + 1,
										datePicker.getDayOfMonth()));
								startYear = datePicker.getYear();
								startMonth = datePicker.getMonth();
								startDay = datePicker.getDayOfMonth();
								sb.append("  ");
								etStartTime.setText(sb);
								etEndTime.requestFocus();
								dialog.cancel();
							}

						});

			}
			// 结束日期选取
			else if (v.getId() == R.id.et_end_time) {
				int inType = etEndTime.getInputType();
				etEndTime.setInputType(InputType.TYPE_NULL);
				etEndTime.onTouchEvent(event);
				etEndTime.setInputType(inType);
				etEndTime.setSelection(etEndTime.getText().length());
				builder.setTitle("选取结束日期");
				builder.setPositiveButton("确  定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								StringBuffer sb = new StringBuffer();
								sb.append(String.format("%d-%02d-%02d",
										datePicker.getYear(),
										datePicker.getMonth() + 1,
										datePicker.getDayOfMonth()));
								endYear = datePicker.getYear();
								endMonth = datePicker.getMonth();
								endDay = datePicker.getDayOfMonth();
								sb.append("  ");
								if (startYear <= endYear
										&& startMonth <= endMonth
										&& startDay <= endDay) {
									etEndTime.setText(sb);
									dialog.cancel();
								} else {
									// 结束日期早于开始日期，弹框提示错误
									AlertDialog.Builder builder1 = new AlertDialog.Builder(
											HistoryTask.this);
									builder1.setMessage(R.string.dialog_message)
											.setTitle(R.string.dialog_title);
									builder1.setPositiveButton(
											"确  定",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													dialog.cancel();
												}

											});
									AlertDialog dialog1 = builder1.create();
									dialog1.show();
								}
							}
						});
			}

			Dialog dialog = builder.create();
			dialog.show();
		}

		return true;
	}
}
