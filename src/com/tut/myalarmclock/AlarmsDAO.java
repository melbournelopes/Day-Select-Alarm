package com.tut.myalarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AlarmsDAO extends BaseDAO<AlarmsModel> implements DAO<AlarmsModel>{

	public AlarmsDAO(Context context, SQLiteDatabase db) {
		super(context, db);
	}

	/**The singleton for AlarmsDAO which will be used in entire app*/
	public static AlarmsDAO alarmsDAO;
	public static SQLiteDatabase db;
	public static AlarmsDAO getInstence(Context context) {
		db = ((AlarmApplication) context.getApplicationContext()).getWritableDatabase(context.getApplicationContext());
		if(alarmsDAO == null){
			alarmsDAO = new AlarmsDAO(context, db);
		}
		
		return alarmsDAO;
	}
	
	@Override
	public AlarmsModel fromCursor(Cursor c) {
		AlarmsModel alarmsModel = new AlarmsModel();
		alarmsModel.setId(CursorUtils.extractLongOrNull(c,
				AlarmsModel.ID));
		alarmsModel.setTime(CursorUtils.extractStringOrNull(c,
				AlarmsModel.TIME));
		alarmsModel.setQuote(CursorUtils.extractStringOrNull(c,
				AlarmsModel.QUOTE));
		alarmsModel.setState(CursorUtils.extractBoolean(c,
				AlarmsModel.STATE));
		alarmsModel.setSunday(CursorUtils.extractBoolean(c,
				AlarmsModel.SUNDAY));
		alarmsModel.setMonday(CursorUtils.extractBoolean(c,
				AlarmsModel.MONDAY));
		alarmsModel.setTuesday(CursorUtils.extractBoolean(c,
				AlarmsModel.TUESDAY));
		alarmsModel.setWednesday(CursorUtils.extractBoolean(c,
				AlarmsModel.WEDNESDAY));
		alarmsModel.setThusday(CursorUtils.extractBoolean(c,
				AlarmsModel.THUSDAY));
		alarmsModel.setFriday(CursorUtils.extractBoolean(c,
				AlarmsModel.FRIDAY));
		alarmsModel.setSaturday(CursorUtils.extractBoolean(c,
				AlarmsModel.SATURDAY));
	
		
		return alarmsModel;
	}

	@Override
	public ContentValues values(AlarmsModel alarmsModel) {
		ContentValues values = new ContentValues();
		values.put(AlarmsModel.TIME,
				alarmsModel.getTime());
		values.put(AlarmsModel.QUOTE,
				alarmsModel.getQuote());
		values.put(AlarmsModel.STATE,
				alarmsModel.getState());
		values.put(AlarmsModel.SUNDAY,
				alarmsModel.getSunday());
		values.put(AlarmsModel.MONDAY,
				alarmsModel.getMonday());
		values.put(AlarmsModel.TUESDAY,
				alarmsModel.getTuesday());
		values.put(AlarmsModel.WEDNESDAY,
				alarmsModel.getWednesday());
		values.put(AlarmsModel.THUSDAY,
				alarmsModel.getThusday());
		values.put(AlarmsModel.FRIDAY,
				alarmsModel.getFriday());
		values.put(AlarmsModel.SATURDAY,
				alarmsModel.getSaturday());
		
		return values;
	}

	@Override
	public String getTableName() {
		return AlarmsModel.TABLE_NAME;
	}

	@Override
	public String getPrimaryColumnName() {
		return AlarmsModel.ID;
	}

}
