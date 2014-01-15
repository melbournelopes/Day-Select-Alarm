package com.tut.myalarmclock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service{
	
	@Override
	public void onCreate() {
		super.onCreate();
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Intent i=new Intent(getApplicationContext(),AlarmReceiver.class);
		i.setAction(Commons.ALARM_BROADCAST);
		i.putExtra("id", intent.getIntExtra("id", 0));
		getApplicationContext().sendBroadcast(i);
		
		return Service.START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
}
