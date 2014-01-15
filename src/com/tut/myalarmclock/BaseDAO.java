package com.tut.myalarmclock;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDAO<T extends Model> implements DAO<T> {

	protected final SQLiteDatabase db;
	protected final Context context;

	public BaseDAO(Context context, SQLiteDatabase db) {
		this.context = context;
		this.db = db;
	}

	public boolean isNotEmpty() {
		Cursor c = null;
		try {
			c = db.rawQuery("SELECT " + getPrimaryColumnName() + " FROM "
					+ getTableName(), null);
			return c.moveToFirst();
		} catch (Exception e) {
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return false;
	}

	@Override
	public boolean exists(Long id) {
		Cursor c = null;

		try {
			String query = "SELECT " + getPrimaryColumnName() + " FROM "
					+ getTableName() + " WHERE " + getPrimaryColumnName()
					+ " = ?";
			c = db.rawQuery(query, new String[] { String.valueOf(id) });
			return c.moveToFirst();
		} catch (Exception e) {
			if (c != null) {
				c.close();
			}
			return false;
		}

	}

	public boolean exists(String colName, String value) {
		Cursor c = null;

		try {
			String query = "SELECT " + colName + " FROM " + getTableName()
					+ " WHERE " + colName + " = ?";
			c = db.rawQuery(query, new String[] { value });
			return c.moveToFirst();
		} catch (Exception e) {
			if (c != null) {
				c.close();
			}
			return false;
		}

	}
	
	public ArrayList<T> findAll() {
		Cursor c = null;
		ArrayList<T> arrayT = null;

		c = db.rawQuery("SELECT * FROM " + getTableName(), null);
		if (c != null && c.moveToFirst()) {
			arrayT = new ArrayList<T>();
			do {
				arrayT.add(fromCursor(c));
			} while (c.moveToNext());
		}

		c.close();
		c = null;
		return arrayT;
	}

	public ArrayList<T> findAllByCol(String columnName, String value) {
		Cursor c = null;
		ArrayList<T> arrayT = null;
		c = db.rawQuery("SELECT * FROM " + getTableName() + " where "
				+ columnName + " = ?", new String[] { value /*
															 * String.valueOf(value
															 * )
															 */});
		if (c != null && c.moveToFirst()) {
			arrayT = new ArrayList<T>();
			do {
				arrayT.add(fromCursor(c));
			} while (c.moveToNext());
		}
		c.close();
		c = null;
		return arrayT;
	}

	@Override
	public T findByPrimaryKey(Long id) {
		Cursor c = null;
		T t = null;

		c = db.rawQuery("select * from " + getTableName() + " where "
				+ getPrimaryColumnName() + " = ?",
				new String[] { String.valueOf(id) });

		if (c != null && c.moveToFirst()) {
			t = fromCursor(c);
			c.close();
		}

		return t;
	}

	public T findByPrimaryKey(Long id, Long userId) {
		Cursor c = null;
		T t = null;

		c = db.rawQuery("select * from " + getTableName() + " where "
				+ getPrimaryColumnName() + " = ? AND user_id = ?",
				new String[] { String.valueOf(id), String.valueOf(userId) });

		if (c != null && c.moveToFirst()) {
			t = fromCursor(c);
			c.close();
		}

		return t;
	}

	public T findByEmail(String email) {
		Cursor c = null;
		T t = null;

		c = db.rawQuery("select * from " + getTableName() + " where email = ?",
				new String[] { String.valueOf(email) });

		if (c != null && c.moveToFirst()) {
			t = fromCursor(c);
			c.close();
		}
		return t;
	}

	protected List<T> allFromCursor(Cursor cursor) {
		ArrayList<T> result = new ArrayList<T>();
		if (cursor.moveToFirst()) {
			do {
				result.add(fromCursor(cursor));
			} while (cursor.moveToNext());
		}
		return result;
	}

	@Override
	public void create(T t) {
		long id = db.insert(getTableName(), "XXX", values(t));
		t.setId(id);
	}

	@Override
	public void update(T object) {
		db.update(getTableName(), values(object), getPrimaryColumnName()
				+ " = ? ", new String[] { String.valueOf(object.getId()) });
	}

	@Override
	public void updateWhereId(T object, String colName, Long id) {
		db.update(getTableName(), values(object), colName + " = ? ",
				new String[] { String.valueOf(id) });
	}

	public void updateWhereCol(T object, String colName, String value) {
		db.update(getTableName(), values(object), colName + " = ? ",
				new String[] { String.valueOf(value) });
	}

	@Override
	public void createOrUpdate(T object) {
		if (exists(object.getId()))
			update(object);
		else
			create(object);
	}

	@Override
	public void delete(Long id) {
		db.delete(getTableName(), getPrimaryColumnName() + " = ? ",
				new String[] { String.valueOf(id) });
	}

	public long deleteByCol(String columnName, String value) {
		return db.delete(getTableName(), columnName + " = ? ",
				new String[] { value });
	}

	public void truncate() {
		db.delete(getTableName(), null, null);
	}

}
