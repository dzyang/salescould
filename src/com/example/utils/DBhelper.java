package com.example.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME_STRING = "datastorage";
	private static final int DATABASE_VERSION = 1;

	public DBhelper(Context context) {
		super(context, DATABASE_NAME_STRING, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 下面的表是一个默认的购物车表，不关系注册不注册
		db.execSQL("CREATE TABLE [dt_goods] ([id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,[_id] INTEGER  NULL,[_channel_id] INTEGER  NULL,[_category_id] INTEGER  NULL,[_title] VARCHAR(500)  NULL,[_goods_no] VARCHAR(500)  NULL,[_stock_quantity] INTEGER  NULL,[_market_price] VARCHAR(500)  NULL,[_sell_price] VARCHAR(500)  NULL,[_point] INTEGER  NULL,[_link_url] TEXT  NULL,[_img_url] TEXT  NULL,[_seo_title] TEXT  NULL,[_seo_keywords] TEXT  NULL,[_seo_description] TEXT  NULL,[_content] TEXT  NULL,[_sort_id] INTEGER  NULL,[_click] INTEGER  NULL,[_is_msg] INTEGER  NULL,[_is_top] INTEGER  NULL,[_is_red] INTEGER  NULL,[_is_hot] INTEGER  NULL,[_is_slide] INTEGER  NULL,[_is_lock] INTEGER  NULL,[_user_id] INTEGER  NULL,[_add_time] VARCHAR(500)  NULL,[_digg_good] INTEGER  NULL,[_digg_bad] INTEGER  NULL,[_buynumber] INTEGER  NULL,[_user] VARCHAR(500)  NULL)");
		// 下面表控制注册
		db.execSQL("CREATE TABLE [dt_users] ([id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,[username] VARCHAR(500)  NULL,[pass] BOOLEAN  NULL)");
		// 下面个表是当注册并且到达订单时从dt_goods中把id导入到这个表里面，这个表和dt_goods，submit关联
		db.execSQL("CREATE TABLE [dingnum] ([id] INTEGER  PRIMARY KEY NOT NULL,[ding_id] INTEGER  NULL,[buynumber] INTEGER  NULL,[user_name] VARCHAR(500)  NULL,[submitnum] VARCHAR(500)  NULL,[date] DATE  NULL)");
		// 下面个表是订单
		db.execSQL("CREATE TABLE [submit] ([id] INTEGER  PRIMARY KEY NOT NULL,[submitnum] varcHAR(500)  NULL,[username] VARCHAR(500)  NULL,[tel] vARCHAR(500)  NULL,[renshu] INTEGER  NULL,[cantingname] varcHAR(500)  NULL,[daodiantime] daTE  NULL,[contract] TEXT  NULL,[fukuan] BOOLEAN  NULL,[queding] BOOLEAN  NULL,[totalmoney] FLOAT  NULL,[adddate] DATE  NULL)");
		// 喜欢表
		db.execSQL("CREATE TABLE [dinglike] ([id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,[goodsid] INTEGER  NULL,[users] VARCHAR(500)  NULL)");
		// 下面个表是系统设置,提前设置ip地址
		db.execSQL("CREATE TABLE [system] ([id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,[localhost] VARCHAR(500) DEFAULT 'http://10.0.2.2' NULL)");
		//db.execSQL("insert into [system] (localhost) values('http://postdep.yw.wh-baidu.com')");
		
		db.execSQL("insert into [system] (localhost) values('http://10.0.2.2')");
		//db.execSQL("insert into [system] (localhost) values('http://www.xiaodoubao.cn/taotao')");
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists [dt_goods]");
		db.execSQL("drop table if exists [dt_users]");
		db.execSQL("drop table if exists [dingnum]");
		db.execSQL("drop table if exists [submit]");
		db.execSQL("drop table if exists [dinglike]");
		db.execSQL("drop table if exists [system]");		
		onCreate(db);
	}
}
