package com.tut.myalarmclock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tut.myalarmclock.MultiSpinner.MultiSpinnerListener;

public class MainActivity extends FragmentActivity implements OnClickListener, MultiSpinnerListener{
	Button  btnOk, btnDelete;
	TextView lblTime;
	EditText txtQuote;
	ListView lstAlarms;
	List<AlarmsModel> arrAlarms;
	TimepickerFragment timePicker;
	CustomAlarmAdapter customAlarmAdapter;
	MultiSpinner spnRepeat;
	List<String> selectedItems;
	AlarmService alarmService;
	String time,data;
	int hour,min;
	Intent alarmIntent;
	AlarmManager alarmManager;
	private PendingIntent pendingIntent;
	Intent myIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lblTime = (TextView) findViewById(R.id.lblTime);
		txtQuote=(EditText)findViewById(R.id.txtQuote);
		spnRepeat = (MultiSpinner) findViewById(R.id.spnRepeat);
		btnOk = (Button) findViewById(R.id.btnOk);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		lstAlarms=(ListView)findViewById(R.id.lstAlarms);
		
		AlarmsDAO alarmsDAO=AlarmsDAO.getInstence(this);
		arrAlarms=alarmsDAO.findAll();
		if(arrAlarms!=null){
			customAlarmAdapter=new CustomAlarmAdapter(this, arrAlarms);
			lstAlarms.setAdapter(customAlarmAdapter);
		}
		
		/**To get current time*/
		Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);
		lblTime.setText("  "+String.format("%02d", hour)+":"+String.format("%02d", min));

		selectedItems=new ArrayList<String>();
		/**To set items in customized spinner for spnRepeat*/
		spnRepeat.setItems(Commons.arrRepeat(), "All day", this);
		
		alarmIntent=new Intent(MainActivity.this,AlarmService.class);
		
		lblTime.setOnClickListener(this);
		btnOk.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lblTime:
			timePicker = new TimepickerFragment(handler);
	        timePicker.show(getSupportFragmentManager(), "timepickerdialog");
			break;
		case R.id.btnOk:
			if(txtQuote.getText().toString().equals("")){
				Toast.makeText(this, "pls enter name for alarm", Toast.LENGTH_SHORT).show();
			}else{
				AlarmsModel alarmsModel=new AlarmsModel();
				alarmsModel.setQuote(txtQuote.getText().toString());
				alarmsModel.setTime(lblTime.getText().toString());
				alarmsModel.setState(true);
				if(!Commons.arrSelected.isEmpty()){
					for(int i=0;i<Commons.arrSelected.size();i++){
						switch (Commons.arrSelected.get(i)) {
						case 0:
							alarmsModel.setSunday(true);
							break;
						case 1:
							alarmsModel.setMonday(true);
							break;
						case 2:
							alarmsModel.setTuesday(true);
							break;
						case 3:
							alarmsModel.setWednesday(true);
							break;
						case 4:
							alarmsModel.setThusday(true);
							break;
						case 5:
							alarmsModel.setFriday(true);
							break;
						case 6:
							alarmsModel.setSaturday(true);
							break;
						}
					}
				}else{
					alarmsModel.setSunday(true);
					alarmsModel.setMonday(true);
					alarmsModel.setTuesday(true);
					alarmsModel.setWednesday(true);
					alarmsModel.setThusday(true);
					alarmsModel.setFriday(true);
					alarmsModel.setSaturday(true);
				}
				AlarmsDAO alarmsDAO=AlarmsDAO.getInstence(this);
				alarmsDAO.create(alarmsModel);
				alarmsModel=null;
				
				ArrayList<AlarmsModel> arrAlarmsModels=new ArrayList<AlarmsModel>();
				arrAlarmsModels=alarmsDAO.findAllByCol(AlarmsModel.QUOTE, txtQuote.getText().toString());
				alarmsModel=new AlarmsModel();
				alarmsModel=arrAlarmsModels.get(0);
				
				int id=(int)alarmsModel.getId().intValue();
				setAlarm(id, hour, min);
				
				
				arrAlarms=alarmsDAO.findAll();
				if(customAlarmAdapter!=null){
					customAlarmAdapter.clear();
					customAlarmAdapter.addAll(arrAlarms);
					customAlarmAdapter.notifyDataSetChanged();
				}else{
					customAlarmAdapter=new CustomAlarmAdapter(this, arrAlarms);
					lstAlarms.setAdapter(customAlarmAdapter);
				}
			}
		
			break;
		case R.id.btnDelete:
			
			break;
		}

	}
	
	
	public void setAlarm(int id,int hour,int min){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND,0);
		
		myIntent = new Intent(this,AlarmService.class);
		myIntent.putExtra("id", id);
		pendingIntent = PendingIntent.getService(this, id,myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
		Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onItemsSelected(boolean[] selected) {
			/**Here you can get list of selected items*/
			selectedItems=spnRepeat.getSelectedItems();
	}

	Handler handler = new Handler(){
		 /**This handler handles time sent by TimePickerFragment*/
	        @Override
	        public void handleMessage(Message msg){
	        	time=msg.getData().getString("time");
	            hour=msg.getData().getInt("hour");
	            min=msg.getData().getInt("min");
	        	lblTime.setText("  "+time);
	            
	        }
	    };

}
