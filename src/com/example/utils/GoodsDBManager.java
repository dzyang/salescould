package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.model.goods;

public class GoodsDBManager {
	private DBhelper helper;
	private SQLiteDatabase db;
	public String strwhere = "";

	public GoodsDBManager(Context context) {
		helper = new DBhelper(context);
		db = helper.getWritableDatabase();
	}

	/***
	 * 添加goods到数据库，主要用到点菜时
	 * 
	 * @param dt_goods
	 */
	public void add(goods dt_goods) {
		StringBuilder strsql = new StringBuilder();
		strsql.append("INSERT INTO dt_goods ");
		strsql.append("(_id ,_channel_id ,_category_id ,_title ,_goods_no ,_stock_quantity ,_market_price ,_sell_price ,_point ,_link_url ,");
		strsql.append("_img_url ,_seo_title ,_seo_keywords ,_seo_description ,_content ,_sort_id ,_click ,_is_msg ,_is_top ,_is_red ,_is_hot");
		strsql.append(",_is_slide ,_is_lock ,_user_id ,_add_time ,_digg_good ,_digg_bad ,_buynumber ,_user )");
		strsql.append("VALUES (" + dt_goods.get_id() + ","
				+ dt_goods.get_channel_id() + "," + dt_goods.get_category_id()
				+ ",'" + dt_goods.get_title() + "','" + dt_goods.get_goods_no()
				+ "'," + dt_goods.get_stock_quantity() + ",'"
				+ dt_goods.get_market_price() + "','"
				+ dt_goods.get_sell_price() + "'," + dt_goods.get_point()
				+ ",'" + dt_goods.get_link_url() + "','"
				+ dt_goods.get_img_url() + "','" + dt_goods.get_seo_title()
				+ "','" + dt_goods.get_seo_keywords() + "','"
				+ dt_goods.get_seo_description() + "','"
				+ dt_goods.get_content() + "'," + dt_goods.get_sort_id() + ","
				+ dt_goods.get_click() + "," + dt_goods.get_is_msg() + ","
				+ dt_goods.get_is_top() + "," + dt_goods.get_is_red() + ","
				+ dt_goods.get_is_hot() + "," + dt_goods.get_is_slide() + ","
				+ dt_goods.get_is_lock() + "," + dt_goods.get_user_id() + ",'"
				+ dt_goods.get_add_time() + "'," + dt_goods.get_digg_good()
				+ "," + dt_goods.get_digg_bad() + ","
				+ dt_goods.get_buynumber() + ",'" + dt_goods.get_user() + "')");
		db.execSQL(strsql.toString());
	}

	/***
	 * 用来更新点菜的份数
	 * 
	 * @param _buynumber
	 * @param _id
	 */
	public void addbuynumber(int _buynumber, int _id) {
		String UPDATE_DATA = "update [dt_goods] set _buynumber=" + _buynumber
				+ " where _id = " + _id;
		db.execSQL(UPDATE_DATA);
	}

	/**
	 * 指定删除
	 * @param _id
	 */
	public void delete(int _id) {
		String sql = "delete from [dt_goods] where _id = " + _id;
		db.execSQL(sql);
	}
	
	public void alldelete(){
		String sqlString = "delete from [dt_goods]";
		db.execSQL(sqlString);
	}

	/**
	 * query all dt_category, return list
	 * 
	 * @return List<Person>
	 */
	public List<goods> query() {
		ArrayList<goods> dt_goods = new ArrayList<goods>();
		Cursor c = queryTheCursor(strwhere);
		while (c.moveToNext()) {
			goods goods1 = new goods();
			goods1.set_id(c.getInt(c.getColumnIndex("_id")));
			goods1.set_channel_id(c.getInt(c.getColumnIndex("_channel_id")));
			goods1.set_category_id(c.getInt(c.getColumnIndex("_category_id")));
			goods1.set_title(c.getString(c.getColumnIndex("_title")));
			goods1.set_goods_no(c.getString(c.getColumnIndex("_goods_no")));
			goods1.set_stock_quantity(c.getInt(c
					.getColumnIndex("_stock_quantity")));
			goods1.set_market_price(c.getString(c
					.getColumnIndex("_market_price")));
			goods1.set_sell_price(c.getString(c.getColumnIndex("_sell_price")));
			goods1.set_point(c.getInt(c.getColumnIndex("_point")));
			goods1.set_link_url(c.getString(c.getColumnIndex("_link_url")));
			goods1.set_img_url(c.getString(c.getColumnIndex("_img_url")));
			goods1.set_seo_title(c.getString(c.getColumnIndex("_seo_title")));
			goods1.set_seo_keywords(c.getString(c
					.getColumnIndex("_seo_keywords")));
			goods1.set_seo_description(c.getString(c
					.getColumnIndex("_seo_description")));
			goods1.set_content(c.getString(c.getColumnIndex("_content")));
			goods1.set_sort_id(c.getInt(c.getColumnIndex("_sort_id")));
			goods1.set_click(c.getInt(c.getColumnIndex("_click")));
			goods1.set_is_msg(c.getInt(c.getColumnIndex("_is_msg")));
			goods1.set_is_top(c.getInt(c.getColumnIndex("_is_top")));
			goods1.set_is_red(c.getInt(c.getColumnIndex("_is_red")));
			goods1.set_is_hot(c.getInt(c.getColumnIndex("_is_hot")));
			goods1.set_is_slide(c.getInt(c.getColumnIndex("_is_slide")));
			goods1.set_is_lock(c.getInt(c.getColumnIndex("_is_lock")));
			goods1.set_user_id(c.getInt(c.getColumnIndex("_user_id")));
			goods1.set_add_time(c.getString(c.getColumnIndex("_add_time")));
			goods1.set_digg_good(c.getInt(c.getColumnIndex("_digg_good")));
			goods1.set_digg_bad(c.getInt(c.getColumnIndex("_digg_bad")));
			goods1.set_buynumber(c.getInt(c.getColumnIndex("_buynumber")));
			goods1.set_user(c.getString(c.getColumnIndex("_user")));
			dt_goods.add(goods1);
		}
		c.moveToFirst();
		c.close();
		return dt_goods;
	}

	/**
	 * 返回数据库中的所有记录，但是如果有where传递后则是返回条件记录和query配合使用
	 * 
	 * @return Cursor
	 */
	public Cursor queryTheCursor(String strwhere) {
		Cursor c;

		if ("".equals(strwhere)) {
			c = db.rawQuery("SELECT * FROM [dt_goods]", null);
			return c;
		} else {
			c = db.rawQuery("SELECT * FROM [dt_goods] where " + strwhere, null);
			return c;
		}
	}

	/***
	 * 确定数据库中有没有对应的记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean queryid(int id) {
		int sid = id;
		Cursor c = db.rawQuery("SELECT * FROM [dt_goods] where _id = " + sid,
				null);
		if (c.getCount() > 0) {
			c.close();
			return true;
		}
		c.close();
		return false;
	}

	/***
	 * 获得总订单数
	 * @return
	 */
	public String totalcount() {
		String result = null;
		Cursor c = db.rawQuery("SELECT count(*) as totalnum FROM [dt_goods]",
				null);
		if (c.moveToNext()) {
			result = c.getString(c.getColumnIndex("totalnum"));
			c.close();
			return result;
		}
		c.close();
		return null;
	}

	/***
	 * 获得订单总金额
	 * 
	 * @return
	 */
	public double  totalnum() {
		double result = 0;
		Cursor c = db
				.rawQuery(
						"SELECT sum(_sell_price * _buynumber) as totalnum FROM [dt_goods]",
						null);
		if (c.moveToNext()) {
			result = c.getDouble(c.getColumnIndex("totalnum"));
			c.close();
			return result;
		}
		c.close();
		return 0;
	}

}
