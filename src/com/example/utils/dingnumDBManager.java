package com.example.utils;

import java.util.Currency;

import com.example.myfood.R.string;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class dingnumDBManager {
	private DBhelper helper;
	private SQLiteDatabase db;
	public String strwhere = "";

	public dingnumDBManager(Context context) {
		helper = new DBhelper(context);
		db = helper.getWritableDatabase();
	}

	/**
	 * 添加
	 */
	public void insertdingnum(String submitnum, String user_name) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("insert into [dingnum] (ding_id,buynumber,submitnum,user_name,date) select _id,_buynumber,'"
						+ submitnum
						+ "','"
						+ user_name
						+ "',datetime('now') from dt_goods ");
		db.execSQL(stringBuilder.toString());
	}

	public String dingnum(String submitnum, String user_name) {
		StringBuilder stringBuilder = new StringBuilder();
		Cursor cursor;
		stringBuilder
				.append("select count(*) as totalcount from [dingnum] where  submitnum = '"
						+ submitnum + "' and user_name = '" + user_name + "'");
		cursor = db.rawQuery(stringBuilder.toString(), null);
		String dingnum = "";
		if (cursor.moveToNext()) {
			dingnum = cursor.getString(cursor.getColumnIndex("totalcount"));
		}
		cursor.close();
		return dingnum;
	}

}
