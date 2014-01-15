package com.tut.myalarmclock;

import com.google.gson.annotations.SerializedName;


public class AlarmsModel implements Model{
	public static final String TABLE_NAME = "alarms";
	public static final String ID = "id";
	public static final String TIME = "time";
	public static final String QUOTE = "quote";
	public static final String STATE = "state";
	public static final String SUNDAY = "sun";
	public static final String MONDAY = "mon";
	public static final String TUESDAY = "tues";
	public static final String WEDNESDAY = "wed";
	public static final String THUSDAY = "thus";
	public static final String FRIDAY = "fri";
	public static final String SATURDAY = "sat";
	
	
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + "(" 
			+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ TIME +" TEXT," 
			+ QUOTE + " TEXT,"
			+ STATE + " BOOLEAN DEFAULT 0," 
			+ SUNDAY +" BOOLEAN DEFAULT 0,"
			+ MONDAY +" BOOLEAN DEFAULT 0,"
			+ TUESDAY +" BOOLEAN DEFAULT 0,"
			+ WEDNESDAY +" BOOLEAN DEFAULT 0,"
			+ THUSDAY +" BOOLEAN DEFAULT 0,"
			+ FRIDAY +" BOOLEAN DEFAULT 0,"
			+ SATURDAY +" BOOLEAN DEFAULT 0)";
	
	@SerializedName(ID)
	private Long id;
	
	@SerializedName(TIME)
	private String time;
	
	@SerializedName(QUOTE)
	private String quote;
	
	@SerializedName(STATE)
	private Boolean state;
	
	@SerializedName(SUNDAY)
	private Boolean sunday;
	
	@SerializedName(MONDAY)
	private Boolean monday;
	
	@SerializedName(TUESDAY)
	private Boolean tuesday;
	
	@SerializedName(WEDNESDAY)
	private Boolean wednesday;
	
	@SerializedName(THUSDAY)
	private Boolean thusday;
	
	@SerializedName(FRIDAY)
	private Boolean friday;
	
	@SerializedName(SATURDAY)
	private Boolean saturday;
	
	
	public Boolean getSunday() {
		return sunday;
	}

	public void setSunday(Boolean sunday) {
		this.sunday = sunday;
	}

	public Boolean getMonday() {
		return monday;
	}

	public void setMonday(Boolean monday) {
		this.monday = monday;
	}

	public Boolean getTuesday() {
		return tuesday;
	}

	public void setTuesday(Boolean tuesday) {
		this.tuesday = tuesday;
	}

	public Boolean getWednesday() {
		return wednesday;
	}

	public void setWednesday(Boolean wednesday) {
		this.wednesday = wednesday;
	}

	public Boolean getThusday() {
		return thusday;
	}

	public void setThusday(Boolean thusday) {
		this.thusday = thusday;
	}

	public Boolean getFriday() {
		return friday;
	}

	public void setFriday(Boolean friday) {
		this.friday = friday;
	}

	public Boolean getSaturday() {
		return saturday;
	}

	public void setSaturday(Boolean saturday) {
		this.saturday = saturday;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;	
	}

}
