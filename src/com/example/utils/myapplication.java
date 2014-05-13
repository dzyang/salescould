/**
 * 全局application，为什么要用application，主要是因为这就像一个session，
 * 用于临时保存各种传值，服务器url，如果用数据库或者其他的操作来保存这些
 * 数据的话管理起来就很繁琐，而且也不利于程序的运行速度。
 * 
 */
package com.example.utils;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

public class myapplication extends Application {
	private int mainselitem;
	public int getMainselitem() {
		return mainselitem;
	}

	public void setMainselitem(int mainselitem) {
		this.mainselitem = mainselitem;
	}

	private UsersDBManager UsersDBManager1;
	private SystemDBManager SystemDBManager1;
	private SubmitDBManager submitDBManager1;

	/**
	 * 为了完全退出程序调用方法 myapplication1.getInstance().addActivity(this);
	 * myapplication1.getInstance().exit();
	 */
	private static myapplication instance;

	private List<Activity> activityList = new LinkedList<Activity>();

	public myapplication() {
	}

	// 单例模式获取唯一的MyApplication实例
	public static myapplication getInstance() {
		if (null == instance) {
			instance = new myapplication();
		}
		return instance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	/**
	 * 为了完全退出程序
	 */

	/***
	 * localhost是服务器ip地址的设置
	 */
	private String localhost;

	public String getlocalhost() {
		return SystemDBManager1.localhost();
	}
	
	
	private String userid;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private String companycode;
	

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	/***
	 * 全局地区选择
	 */
	private String diqu = "";

	public String getdiqu() {
		return diqu;
	}

	public void setdiqu(String diqu) {
		this.diqu = diqu;
	}

	/***
	 * categoryurl是分类的地址查询提交地址
	 */
	private String customerurl = "/android/json_customer/list.php?channel_id=2&parent_id=0&page=";

	public String getcustomerurl() {
		return customerurl;
	}

	public void setcustomerurl(String customerurl) {
		this.customerurl = customerurl;
	}
	


	/***
	 * articleurl是新闻地址查询提交地址
	 */
	private String articleurl;

	public String getarticleurl() {
		return articleurl;
	}

	public void setarticleurl(String articleurl) {
		this.articleurl = articleurl;
	}

	/***
	 * goodsurl是菜的自定义url提交地址
	 */
	private String goodsurl = "/android/json_goods/list.php?channel_id=2&page=";

	public String getgoodsurl() {
		return goodsurl;
	}

	public void setgoodsurl(String goodsurl) {
		this.goodsurl = goodsurl;
	}

	/**
	 * goodslikeurl喜欢的url提交地址
	 */
	private String goodslikeurl = "/android/json_goods/list.php?channel_id=2&id=";

	public String getgoodslikeurl() {
		return goodslikeurl;
	}

	public void setgoodslikeurl(String goodslikeurl) {
		this.goodslikeurl = goodslikeurl;
	}

	/***
	 * 总金额
	 */
	private String totalmoney = "0";

	public String gettotalmoney() {
		return totalmoney;
	}

	public void settotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}

	/***
	 * 订餐总数
	 */
	private String totalgoods = "0";

	public String gettotalgoods() {
		return totalgoods;
	}

	public void settotalgoods(String totalgoods) {
		this.totalgoods = totalgoods;
	}
	
	
	/***
	 * 订餐详情
	 */
	private String totalgoods_id_num = "";

	public String gettotalgoods_id_num() {
		return totalgoods_id_num;
	}

	public void settotalgoods_id_num(String totalgoods_id_num) {
		this.totalgoods_id_num = totalgoods_id_num;
	}

	/***
	 * 全局判断用户是否登录
	 * 
	 * @return
	 */
	public boolean ifpass() {
		if (UsersDBManager1.ifpass()) {
			return true;
		}
		return false;
	}

	public String getusername() {
		String usernameString = UsersDBManager1.username();
		return usernameString;
	}

	/**
	 * 全局订单设置
	 */
	private String dingdanString = "";

	public void set_dingdansring(String dingdanString) {
		this.dingdanString = dingdanString;
	}

	public String get_dingdanstring() {
		return dingdanString;
	}

	/**
	 * 下订单,当在购物车中点确认订单时，就新建一个基本订单，当单击确认预定时做更新操作
	 * 
	 * @param dingdanString
	 */
	public void createding(String dingdanString) {
		submitDBManager1.createding(dingdanString);
	}

	/**
	 * 删除订单
	 * 
	 * @param dingdanString
	 */
	public void deleteding(String dingdanString) {
		submitDBManager1.deleteding(dingdanString);
	}

	/**
	 * 读取图片
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap bitmap(String url) {
		StreamTool StreamTool1 = new StreamTool();
		try {
			InputStream isInputStream = StreamTool1.getis(getlocalhost() + url);
			Bitmap bitmap = BitmapFactory.decodeStream(isInputStream);
			isInputStream.close();
			return bitmap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		super.onCreate();

		UsersDBManager1 = new UsersDBManager(this);
		SystemDBManager1 = new SystemDBManager(this);
		submitDBManager1 = new SubmitDBManager(this);
		// 初始化全局变量
		try {
			/**
			 * 添加网络权限，安卓4.03必须
			 */
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
					.penaltyLog().penaltyDeath().build());
			/**
			 * 添加网络权限，安卓4.03必须
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
