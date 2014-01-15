/**This fragment is dialog fragment used to pick time.
 * 
 * @author Melbourne lopes
 * */
package com.tut.myalarmclock;

import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

public class TimepickerFragment extends DialogFragment{
    Handler handler;
    
    public TimepickerFragment(Handler args){
        handler = args;      
    } 

    private TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {         
            Message msg = new Message();
            Bundle data = new Bundle();
            
            data.putString("time", "" + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
            data.putInt("hour", hourOfDay);
            data.putInt("min", minute);
            msg.setData(data);
            handler.sendMessage(msg);             
        }
    };

    public Dialog onCreateDialog(Bundle bundle){
    	Calendar calendar=Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        boolean is24HourView = true;
        TimePickerDialog tpdialog = new TimePickerDialog(getActivity(), callback, hourOfDay, minute, is24HourView); 
        return tpdialog;
    }
}