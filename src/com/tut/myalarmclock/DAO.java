package com.tut.myalarmclock;

import android.content.ContentValues;
import android.database.Cursor;
 

public interface DAO<T> {
	 public T findByPrimaryKey(Long id);
	 public void create(T object);
	    public void update(T object);
	    public void createOrUpdate(T object);
	    public void delete(Long id);
	    public boolean exists(Long id);
	    public void updateWhereId(T object,String colName,Long id);
	    public T fromCursor(Cursor c);
	    public ContentValues values(T t);
	    public String getTableName();
	    public String getPrimaryColumnName();
}
