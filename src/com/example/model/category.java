package com.example.model;

public class category {
	private int _id;
	private int _channel_id;
	private String _title;
	private String _call_index;
	private int _parent_id = 0;
	private String _class_list;
	private int _class_layer;
	private int _sort_id;
	private String _link_url;
	private String _img_url;
	private String _content;
	private String _seo_title;
	private String _seo_keywords;
	private String _seo_description;

	public category() {
	}

	public category(int _id, int _channel_id, String _title,
			String _call_index, int _parent_id, String _class_list,
			int _class_layer, int _sort_id, String _link_url, String _img_url,
			String _content, String _seo_title, String _seo_keywords,
			String _seo_description) {
		this._id = _id;
		this._channel_id = _channel_id;
		this._title = _title;
		this._call_index = _call_index;
		this._parent_id = _parent_id;
		this._class_list = _class_list;
		this._class_layer = _class_layer;
		this._sort_id = _sort_id;
		this._link_url = _link_url;
		this._img_url = _img_url;
		this._content = _content;
		this._seo_title = _seo_title;
		this._seo_keywords = _seo_keywords;
		this._seo_description = _seo_description;
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

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_call_index() {
		return _call_index;
	}

	public void set_call_index(String _call_index) {
		this._call_index = _call_index;
	}

	public int get_parent_id() {
		return _parent_id;
	}

	public void set_parent_id(int _parent_id) {
		this._parent_id = _parent_id;
	}

	public String get_class_list() {
		return _class_list;
	}

	public void set_class_list(String _class_list) {
		this._class_list = _class_list;
	}

	public int get_class_layer() {
		return _class_layer;
	}

	public void set_class_layer(int _class_layer) {
		this._class_layer = _class_layer;
	}

	public int get_sort_id() {
		return _sort_id;
	}

	public void set_sort_id(int _sort_id) {
		this._sort_id = _sort_id;
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
