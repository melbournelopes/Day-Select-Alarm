package com.tut.myalarmclock;

import android.database.Cursor;

public class CursorUtils {

	public static Integer extractIntegerOrNull(Cursor c, String columnName) {
		int columnIndex = c.getColumnIndex(columnName);
		return c.isNull(columnIndex) ? null : c.getInt(columnIndex);
	}	

	public static Long extractLongOrNull(Cursor c, String columnName) {
		int columnIndex = c.getColumnIndex(columnName);
		return c.isNull(columnIndex) ? null : c.getLong(columnIndex);
	}	

	public static Double extractDoubleOrNull(Cursor c, String columnName) {
		int columnIndex = c.getColumnIndex(columnName);
		return c.isNull(columnIndex) ? null : c.getDouble(columnIndex);
	}
	
    public static boolean extractBoolean(Cursor c, String columnName) {
        int value = c.getInt(c.getColumnIndex(columnName));
        return value == 1;
    }
    
    public static byte[] extractBlobOrNull(Cursor c, String columnName) {
        int columnIndex = c.getColumnIndex(columnName);
        return c.isNull(columnIndex) ? null : c.getBlob(columnIndex);
    }
    
    public static String extractStringOrNull(Cursor c, String columnName) {
        int columnIndex = c.getColumnIndex(columnName);
        return c.isNull(columnIndex) ? null : c.getString(columnIndex);
    } 
}
