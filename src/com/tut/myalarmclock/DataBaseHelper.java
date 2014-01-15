/**@Info: This class is used to create database and all the respective tables. 
 * 
 * @author Melbourne Lopes.
 * @version:1.0
 * */
package com.tut.myalarmclock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	public static int version = 1;
	private static final String DB_SUFFIX = ".db";
	private static String databaseName = "alarms_db";

	public DataBaseHelper(Context context) {
		super(context, databaseName + DB_SUFFIX, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(AlarmsModel.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
