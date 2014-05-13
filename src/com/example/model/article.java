package com.example.model;

import java.util.Date;

public class article {
	private int _id;
	private int _channel_id = 0;
	private int _category_id = 0;
	private String _title = "";
	private String _author = "";
	private String _from = "";
	private String _zhaiyao = "";
	private String _link_url = "";
	private String _img_url = "";
	private String _seo_title = "";
	private String _seo_keywords = "";
	private String _seo_description = "";
	private String _content = "";
	private int _sort_id = 99;
	private int _click = 0;
	private int _is_msg = 0;
	private int _is_top = 0;
	private int _is_red = 0;
	private int _is_hot = 0;
	private int _is_slide = 0;
	private int _is_lock = 0;
	private int _user_id = 0;
	private String _add_time;
	private int _digg_good = 0;
	private int _digg_bad = 0;

	public article() {
	}

	public article(int _id, int _channel_id, int _category_id, String _title,
			String _author, String _from, String _zhaiyao, String _link_url,
			String _img_url, String _seo_title, String _seo_keywords,
			String _seo_description, String _content, int _sort_id, int _click,
			int _is_msg, int _is_top, int _is_red, int _is_hot, int _is_slide,
			int _is_lock, int _user_id, String _add_time, int _digg_good,
			int _digg_bad) {
		this._id = _id;
		this._channel_id = _channel_id;
		this._category_id = _category_id;
		this._title = _title;
		this._author = _author;
		this._from = _from;
		this._zhaiyao = _zhaiyao;
		this._link_url = _link_url;
		this._img_url = _img_url;
		this._seo_title = _seo_title;
		this._seo_keywords = _seo_keywords;
		this._seo_description = _seo_description;
		this._content = _content;
		this._sort_id = _sort_id;
		this._click = _click;
		this._is_msg = _is_msg;
		this._is_top = _is_top;
		this._is_red = _is_red;
		this._is_hot = _is_hot;
		this._is_slide = _is_slide;
		this._is_lock = _is_lock;
		this._user_id = _user_id;
		this._add_time = _add_time;
		this._digg_good = _digg_good;
		this._digg_bad = _digg_bad;
	}

	public int get_user_id() {
		return _user_id;
	}

	public void set_user_id(int _user_id) {
		this._user_id = _user_id;
	}

	public int get_digg_bad() {
		return _digg_bad;
	}

	public void set_digg_bad(int _digg_bad) {
		this._digg_bad = _digg_bad;
	}

	public int get_digg_good() {
		return _digg_good;
	}

	public void set_digg_good(int _digg_good) {
		this._digg_good = _digg_good;
	}

	public String get_add_time() {
		return _add_time;
	}

	public void set_add_time(String _add_time) {
		this._add_time = _add_time;
	}

	public int get_is_lock() {
		return _is_lock;
	}

	public void set_is_lock(int _is_lock) {
		this._is_lock = _is_lock;
	}

	public int get_is_slide() {
		return _is_slide;
	}

	public void set_is_slide(int _is_slide) {
		this._is_slide = _is_slide;
	}

	public int get_is_hot() {
		return _is_hot;
	}

	public void set_is_hot(int _is_hot) {
		this._is_hot = _is_hot;
	}

	public int get_is_red() {
		return _is_red;
	}

	public void set_is_red(int _is_red) {
		this._is_red = _is_red;
	}

	public int get_is_top() {
		return _is_top;
	}

	public void set_is_top(int _is_top) {
		this._is_top = _is_top;
	}

	public int get_is_msg() {
		return _is_msg;
	}

	public void set_is_msg(int _is_msg) {
		this._is_msg = _is_msg;
	}

	public int get_click() {
		return _click;
	}

	public void set_click(int _click) {
		this._click = _click;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_channel_id() {
		return _channel_id;
	}

	public void set_channel_id(int _channel_id) {
		this._channel_id = _channel_id;
	}

	public int get_category_id() {
		return _category_id;
	}

	public void set_category_id(int _category_id) {
		this._category_id = _category_id;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_author() {
		return _author;
	}

	public void set_author(String _author) {
		this._author = _author;
	}

	public String get_from() {
		return _from;
	}

	public void set_from(String _from) {
		this._from = _from;
	}

	public String get_zhaiyao() {
		return _zhaiyao;
	}

	public void set_zhaiyao(String _zhaiyao) {
		this._zhaiyao = _zhaiyao;
	}

	public String get_link_url() {
		return _link_url;
	}

	public void set_link_url(String _link_url) {
		this._link_url = _link_url;
	}

	public String get_img_url() {
		return _img_url;
	}

	public void set_img_url(String _img_url) {
		this._img_url = _img_url;
	}

	public int get_sort_id() {
		return _sort_id;
	}

	public void set_sort_id(int _sort_id) {
		this._sort_id = _sort_id;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}

	public String get_seo_title() {
		return _seo_title;
	}

	public void set_seo_title(String _seo_title) {
		this._seo_title = _seo_title;
	}

	public String get_seo_keywords() {
		return _seo_keywords;
	}

	public void set_seo_keywords(String _seo_keywords) {
		this._seo_keywords = _seo_keywords;
	}

	public String get_seo_description() {
		return _seo_description;
	}

	public void set_seo_description(String _seo_description) {
		this._seo_description = _seo_description;
	}
}
