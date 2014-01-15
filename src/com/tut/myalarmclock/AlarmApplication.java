package com.tut.myalarmclock;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AlarmApplication extends Application{
	private SQLiteDatabase db;
	private DataBaseHelper openHelper;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	private void createOpenHelper(Context ctx) {
		openHelper = new DataBaseHelper(ctx);
		db = openHelper.getWritableDatabase();
	}

	public SQLiteDatabase getWritableDatabase(Context ctx) {

//		if (db != null && db.isOpen()) {
//			db.close();
//			db = null;
//		}
		createOpenHelper(ctx);
		return db;
	}
}
