package com.example.model;

import android.content.Context;

public class dingnum {
	private int id;
	private int ding_id;
	private int buynumber;
	private String submitnum;
	private String user_name;

	public dingnum(Context context) {
	}

	public dingnum(int id, int ding_id, int buynumber, String submitnum,
			String user_name) {
		this.id = id;
		this.ding_id = ding_id;
		this.buynumber = buynumber;
		this.submitnum = submitnum;
		this.user_name = user_name;
	}

	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public int get_ding_id() {
		return ding_id;
	}

	public void set_ding_id(int ding_id) {
		this.ding_id = ding_id;
	}

	public int get_buynumber() {
		return buynumber;
	}

	public void set_buynumber(int buynumber) {
		this.buynumber = buynumber;
	}

	public String get_user_name() {
		return user_name;
	}

	public void set_user_name(String user_name) {
		this.user_name = user_name;
	}

	public String get_submitnum() {
		return submitnum;
	}

	public void set_submitnum(String submitnum) {
		this.submitnum = submitnum;
	}

}
