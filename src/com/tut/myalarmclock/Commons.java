package com.tut.myalarmclock;

import java.util.ArrayList;
import java.util.List;

public class Commons {
	public static List<Integer> arrSelected=new ArrayList<Integer>();
	public static final String ALARM_BROADCAST="com.tut.myalarmclock.AlarmBroadcast";
	
	public static final List<String> arrRepeat() {
		List<String> arrValue = new ArrayList<String>();
		arrValue.add("Sun");
		arrValue.add("Mon");
		arrValue.add("Tue");
		arrValue.add("Wed");
		arrValue.add("Thus");
		arrValue.add("Fri");
		arrValue.add("Sat");

		return arrValue;
	}
	
}
