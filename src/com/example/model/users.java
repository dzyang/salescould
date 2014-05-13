package com.example.model;

import java.sql.Date;

public class users {
	private int _id;
	private int _group_id = 0;
	private String _user_name;
	private String _password;
	private String _email = "";
	private String _nick_name = "";
	private String _avatar = "";
	private String _sex = "保密";
	private String _birthday;
	private String _telphone = "";
	private String _mobile = "";
	private String _qq = "";
	private String _address = "";
	private String _safe_question = "";
	private String _safe_answer = "";
	private String _amount = "0M";
	private int _point = 0;
	private int _exp = 0;
	private int _is_lock = 0;
	private String _reg_time = "";
	private String _reg_ip;


	public String get_group_name() {
		return _group_name;
	}

	public void set_group_name(String _group_name) {
		this._group_name = _group_name;
	}

	private int _usercatagory;
	public int get_usercatagory() {
		return _usercatagory;
	}

	public void set_usercatagory(int _usercatagory) {
		this._usercatagory = _usercatagory;
	}

	private String _group_name;

	public users(int _id, int _group_id, String _user_name, String _password,
			String _email, String _nick_name, String _avatar, String _sex,
			String _birthday, String _telphone, String _mobile, String _qq,
			String _address, String _safe_question, String _safe_answer,
			String _amount, int _point, int _exp, int _is_lock,
			String _reg_time, String _reg_ip,int _usercatagory,String _group_name) {
		this._id = _id;
		this._group_id = _group_id;
		this._user_name = _user_name;
		this._password = _password;
		this._email = _email;
		this._nick_name = _nick_name;
		this._avatar = _avatar;
		this._sex = _sex;
		this._birthday = _birthday;
		this._telphone = _telphone;
		this._mobile = _mobile;
		this._qq = _qq;
		this._address = _address;
		this._safe_question = _safe_question;
		this._safe_answer = _safe_answer;
		this._amount = _amount;
		this._point = _point;
		this._exp = _exp;
		this._is_lock = _is_lock;
		this._reg_time = _reg_time;
		this._reg_ip = _reg_ip;
		this._usercatagory=_usercatagory;
		this._group_name=_group_name;

	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_group_id() {
		return _group_id;
	}

	public void set_group_id(int _group_id) {
		this._group_id = _group_id;
	}

	public void set_user_name(String _user_name) {
		this._user_name = _user_name;
	}

	public String get_user_name() {
		return _user_name;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_password() {
		return _password;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_email() {
		return _email;
	}

	public void set_nick_name(String _nick_name) {
		this._nick_name = _nick_name;
	}

	public String get_nick_name() {
		return _nick_name;
	}

	public void set_avatar(String _avatar) {
		this._avatar = _avatar;
	}

	public String get_avatar() {
		return _avatar;
	}

	public void set_sex(String _sex) {
		this._sex = _sex;
	}

	public String get_sex() {
		return _sex;
	}

	public void set_birthday(String _birthday) {
		this._birthday = _birthday;
	}

	public String get_birthday() {
		return _birthday;
	}

	public void set_telphone(String _telphone) {
		this._telphone = _telphone;
	}

	public String get_telphone() {
		return _telphone;
	}

	public void set_mobile(String _mobile) {
		this._mobile = _mobile;
	}

	public String get_mobile() {
		return _mobile;
	}

	public void set_qq(String _qq) {
		this._qq = _qq;
	}

	public String get_qq() {
		return _qq;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public String get_address() {
		return _address;
	}

	public void set_safe_question(String _safe_question) {
		this._safe_question = _safe_question;
	}

	public String get_safe_question() {
		return _safe_question;
	}

	public void set_safe_answer(String _safe_answer) {
		this._safe_answer = _safe_answer;
	}

	public String get_safe_answer() {
		return _safe_answer;
	}

	public void set_amount(String _amount) {
		this._amount = _amount;
	}

	public String get_amount() {
		return _amount;
	}

	public int get_point() {
		return _point;
	}

	public void set_point(int _point) {
		this._point = _point;
	}

	public int get_exp() {
		return _exp;
	}

	public void set_exp(int _exp) {
		this._exp = _exp;
	}

	public int get_is_lock() {
		return _is_lock;
	}

	public void set_is_lock(int _is_lock) {
		this._is_lock = _is_lock;
	}

	public void set_reg_time(String _reg_time) {
		this._reg_time = _reg_time;
	}

	public String get_reg_time() {
		return _reg_time;
	}

	public void set_reg_ip(String _reg_ip) {
		this._reg_ip = _reg_ip;
	}

	public String get_reg_ip() {
		return _reg_ip;
	}

}
