package com.tut.myalarmclock;

import java.util.Calendar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{
	AlarmsDAO alarmsDAO;
	int day;
	long id;
	@Override
	public void onReceive(Context context, Intent intent) {		
		
        id=intent.getIntExtra("id", 0);
        alarmsDAO=AlarmsDAO.getInstence(context);
       	AlarmsModel alarmsModel=alarmsDAO.findByPrimaryKey(id);
        
    	Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        switch(day){
        case Calendar.SUNDAY:
        	 if(alarmsModel.getState()&&alarmsModel.getSunday()){
                 Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
             }
        	break;
        case Calendar.MONDAY:
        	 if(alarmsModel.getState()&&alarmsModel.getMonday()){
                 Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
             }
        	break;
        case Calendar.TUESDAY:
        	 if(alarmsModel.getState()&&alarmsModel.getTuesday()){
                 Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
             }
        	break;
        case Calendar.WEDNESDAY:
        	 if(alarmsModel.getState()&&alarmsModel.getWednesday()){
                 Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
             }
        	break;
        case Calendar.THURSDAY:
        	 if(alarmsModel.getState()&&alarmsModel.getThusday()){
                 Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
             }
        	break;
        case Calendar.FRIDAY:
        	 if(alarmsModel.getState()&&alarmsModel.getFriday()){
                 Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
             }
        	break;
        case Calendar.SATURDAY:
        	  if(alarmsModel.getState()&&alarmsModel.getSaturday()){
                  Toast.makeText(context.getApplicationContext(), "welcome "+alarmsModel.getQuote(), Toast.LENGTH_SHORT).show();
              }
        	break;
        	
        }
  
	}

}
