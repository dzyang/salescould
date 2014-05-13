package com.example.model;

import android.content.Context;

public class dinglike {
	private int id;
	private int goodsid;
	private String users;

	public dinglike(Context context) {
	}

	public dinglike(int id, int goodsid, String users) {
		this.id = id;
		this.goodsid = goodsid;
		this.users = users;
	}

	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public int get_goodsid() {
		return goodsid;
	}

	public void set_goodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String get_users() {
		return users;
	}

	public void set_users(String users) {
		this.users = users;
	}

}
