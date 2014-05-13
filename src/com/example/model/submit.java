package com.example.model;

import java.sql.Date;
import java.util.Calendar;

import android.content.Context;

public class submit {
	private int id;
	private String submitnum;
	private String username;
	private String tel;
	private int renshu;
	private String cantingname;
	private String daodiantime;
	private String contract;
	private boolean fukuan;
	private boolean queding;
	private Double totalmoney;
	private String adddate;
	private Context context;

	public submit(Context context) {
		this.context = context;

	}

	public submit(int id, String submitnum, String username, String tel,
			int renshu, String cantingname, String daodiantime, String contract,
			boolean fukuan, boolean queding, Double totalmoney, String adddate) {
		this.id = id;
		this.submitnum = submitnum;
		this.username = username;
		this.tel = tel;
		this.renshu = renshu;
		this.cantingname = cantingname;
		this.daodiantime = daodiantime;
		this.contract = contract;
		this.fukuan = fukuan;
		this.queding = queding;
		this.totalmoney = totalmoney;
		this.adddate = adddate;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public int get_id() {
		return id;
	}

	public void set_submitnum(String submitnum) {
		this.submitnum = submitnum;
	}

	public String get_submitnum() {
		return submitnum;
	}

	public void set_username(String username) {
		this.username = username;
	}

	public String get_username() {
		return username;
	}

	public void set_tel(String tel) {
		this.tel = tel;
	}

	public String get_tel() {
		return tel;
	}

	public void set_renshu(int renshu) {
		this.renshu = renshu;
	}

	public int get_renshu() {
		return renshu;
	}

	public void set_cantingname(String cantingname) {
		this.cantingname = cantingname;
	}

	public String get_cantingname() {
		return cantingname;
	}

	public void set_daodiantime(String daodiantime) {
		this.daodiantime = daodiantime;
	}

	public String get_daodiantime() {
		return daodiantime;
	}

	public void set_contract(String contract) {
		this.contract = contract;
	}

	public String get_contract() {
		return contract;
	}

	public void set_fukuan(boolean fukuan) {
		this.fukuan = fukuan;
	}

	public boolean get_fukuan() {
		return fukuan;
	}

	public void set_queding(boolean queding) {
		this.queding = queding;
	}

	public boolean get_queding() {
		return queding;
	}

	public void set_adddate(String adddate) {
		this.adddate = adddate;
	}

	public String get_adddate() {
		return adddate;
	}

	public void set_totalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public Double get_totalmoney() {
		return totalmoney;
	}

}
