package com.example.utils;

import java.util.Currency;
import java.util.List;

import com.example.myfood.R.string;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class dinglikeDBManager {
	private DBhelper helper;
	private SQLiteDatabase db;
	public String strwhere = "";

	public dinglikeDBManager(Context context) {
		helper = new DBhelper(context);
		db = helper.getWritableDatabase();
	}

	public boolean exists(int goodsid) {
		Cursor cursor = db.rawQuery("select * from [dinglike] where goodsid = "
				+ goodsid, null);
		if (cursor.moveToNext()) {
			cursor.close();
			return true;

		}
		cursor.close();
		return false;

	}

	/**
	 * 添加喜欢
	 */
	public void add(int goodsid, String users) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into [dinglike] (goodsid,users) values("
				+ goodsid + ",'" + users + "')");
		db.execSQL(stringBuilder.toString());
	}
	
	/**
	 * 删除喜欢
	 */
	public void del(int id) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from [dinglike] where goodsid =" + id);
		db.execSQL(stringBuilder.toString());
	}

	/**
	 * 刷出喜欢的菜品id，用来刷新喜欢列表
	 * 
	 * @param users
	 * @return
	 */
	public String query(String users) {
		String str = "";
		StringBuilder stringBuilder = new StringBuilder();
		Cursor cursor = db.rawQuery("select * from [dinglike] where users ='"
				+ users + "'", null);
		while (cursor.moveToNext()) {

			str += cursor.getString(cursor.getColumnIndex("goodsid")) + ",";
		}

		stringBuilder.append(str.substring(0,str.length()-1));
		cursor.close();
		return stringBuilder.toString();

	}
	
	public boolean havadata(){
		Cursor cursor = db.rawQuery("select * from [dinglike]", null);
		if(cursor.moveToNext()){
			cursor.close();
			return true;
		}
		cursor.close();
		return false;
		
		
	}

}
