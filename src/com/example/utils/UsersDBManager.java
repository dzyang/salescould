package com.example.utils;

import com.example.myfood.R.string;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersDBManager {
	private DBhelper helper;
	private SQLiteDatabase db;
	public String strwhere = "";

	public UsersDBManager(Context context) {
		helper = new DBhelper(context);
		db = helper.getWritableDatabase();
	}

	/***
	 * 首次登陆时同时也是为了保持登录状态
	 * 
	 * @param username
	 */
	public void login(String username) {
		String sqlstr = "insert into dt_users (username,pass) values('"
				+ username + "','1')";
		db.execSQL(sqlstr);
		db.close();
	}

	/***
	 * 退出操作
	 */
	public void quit() {
		String sqlstr = "delete from dt_users";
		db.execSQL(sqlstr);
		db.close();
	}

	/**
	 * 返回用户名
	 * 
	 * @return
	 */
	public String username() {
		Cursor cursor;
		String username = null;
		cursor = db.rawQuery("select * from dt_users", null);
		if (cursor.moveToNext()) {
			username = cursor.getString(cursor.getColumnIndex("username"));
			cursor.close();
		}
		cursor.close();
		return username.trim();
	}

	/**
	 * 是否已经登录
	 * 
	 * @return
	 */
	public boolean ifpass() {
		Cursor cursor;
		cursor = db.rawQuery("select pass from dt_users", null);
		if (cursor.moveToNext()) {
			if ("1".equals(cursor.getString(cursor.getColumnIndex("pass")))) {
				cursor.close();
				return true;
			}
		} else {
			cursor.close();
			return false;
		}
		cursor.close();
		return false;
	}

}
