package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.model.category;

public class CategoryDBManager {
	private DBhelper helper;
	private SQLiteDatabase db;
	public String strwhere = "";

	public CategoryDBManager(Context context) {
		helper = new DBhelper(context);	
		db = helper.getWritableDatabase();
	}

	/**
	 * add dt_category
	 * 
	 * @param dt_category
	 */
	public void add(category dt_categorys) {
		ContentValues values = new ContentValues();
		values.put("_id", dt_categorys.get_id());
		values.put("_title", dt_categorys.get_title());
		values.put("_content", dt_categorys.get_content());
		//values.put("_buynumber", dt_categorys.get_buynumber());
		db.insert("[dt_category]", null, values);
	}

	public void updata(int _buynumber, int _id) {
		String UPDATE_DATA = "update [dt_category] set _buynumber="
				+ _buynumber + " where _id = " + _id;
		db.execSQL(UPDATE_DATA);
	}

	public void delete(int _id) {
		String sql = "delete from [dt_category] where _id = " + _id;
		db.execSQL(sql);
	}

	/**
	 * query all dt_category, return list
	 * 
	 * @return List<Person>
	 */
//	public List<category> query() {
//		ArrayList<category> dt_categorys = new ArrayList<category>();
//		Cursor c = queryTheCursor(strwhere);
//		while (c.moveToNext()) {
//			category category1 = new category();
//			category1.set_id(c.getInt(c.getColumnIndex("_id")));
//			category1.set_title(c.getString(c.getColumnIndex("_title")));
//			category1.set_content(c.getString(c.getColumnIndex("_content")));
//			//category1._buynumber = c.getInt(c.getColumnIndex("_buynumber"));
//			categorys.add(category1);
//		}
//		c.close();
//		return dt_categorys;
//	}

	/**
	 * query all persons, return cursor
	 * 
	 * @return Cursor
	 */
	public Cursor queryTheCursor(String strwhere) {
		Cursor c;

		if ("".equals(strwhere)) {
			c = db.rawQuery("SELECT * FROM [dt_category]", null);
			return c;
		} else {
			c = db.rawQuery("SELECT * FROM [dt_category] where " + strwhere,
					null);
			return c;
		}
	}

	public String totalnum() {
		String resultString = "show me the money";
		return resultString;
	}
}
