package com.tut.myalarmclock;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomAlarmAdapter extends ArrayAdapter<AlarmsModel> implements OnClickListener{
	Context context;
	public CustomAlarmAdapter(Context context, List<AlarmsModel> objects) {
		super(context, R.layout.alarms_row,objects);
		this.context=context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if(convertView==null){
			LayoutInflater inflater =((Activity)context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.alarms_row, null);
			vh = new ViewHolder();
			vh.lblQuote = (TextView) convertView.findViewById(R.id.lblQuote);
			vh.lblTime= (TextView) convertView.findViewById(R.id.lblTime);
			vh.lblDays = (TextView) convertView.findViewById(R.id.lblDays);
			vh.chkStatus = (CheckBox) convertView.findViewById(R.id.chkState);
			
			
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		
		vh.chkStatus.setTag(position);
		vh.chkStatus.setOnClickListener(this);
		
		vh.lblQuote.setText(getItem(position).getQuote());
		vh.lblTime.setText(getItem(position).getTime());
//		vh.lblDays.setText(getItem(position).get);
		if(getItem(position).getState().equals(true)){
			vh.chkStatus.setChecked(true);
		}else{
			vh.chkStatus.setChecked(false);
		}
    	
		return convertView;
	}
	
	protected static class ViewHolder {
		protected TextView lblQuote;
		protected TextView lblTime;
		protected TextView lblDays;
		protected CheckBox chkStatus;
	}

	@Override
	public void onClick(View v) {
		int position=(Integer)v.getTag();
		AlarmsDAO alarmsDAO=AlarmsDAO.getInstence(context);
	
		if(((CheckBox) v).isChecked()){
			getItem(position).setState(true);
			alarmsDAO.updateWhereId(getItem(position), AlarmsModel.ID, getItem(position).getId());
		}else{
			getItem(position).setState(false);
			alarmsDAO.updateWhereId(getItem(position), AlarmsModel.ID, getItem(position).getId());
		}
	}

}
