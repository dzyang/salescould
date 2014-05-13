package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.string;
import android.content.Context;

import com.example.model.goods;
import com.example.utils.StreamTool;

public class jsongoods {

	public static List<goods> getjsonlastgoods(String url)
			throws Exception {
		String path = url;
		HttpURLConnection conn = (HttpURLConnection) new URL(path)
				.openConnection();
		conn.setConnectTimeout(50000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream json = conn.getInputStream();
			return parsejson(json);
		}
		return null;
	}

	private static List<goods> parsejson(InputStream jsonStream)
			throws Exception {
		List<goods> list = new ArrayList<goods>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _channel_id = jsonobject.getInt("channel_id");
			int _category_id = jsonobject.getInt("category_id");
			String _title = jsonobject.getString("title");
			String _goods_no = jsonobject.getString("goods_no");
			int _stock_quantity = jsonobject.getInt("stock_quantity");
			String _market_price = jsonobject.getString("market_price");
			String _sell_price = jsonobject.getString("sell_price");
			int _point = jsonobject.getInt("point");
			String _link_url = jsonobject.getString("link_url");
			String _img_url = jsonobject.getString("img_url");
			String _seo_title = jsonobject.getString("seo_title");
			String _seo_keywords = jsonobject.getString("seo_keywords");
			String _seo_description = jsonobject.getString("seo_description");
			String _content = jsonobject.getString("content");
			int _sort_id = jsonobject.getInt("sort_id");
			int _click = jsonobject.getInt("click");
			int _is_msg = jsonobject.getInt("is_msg");
			int _is_top = jsonobject.getInt("is_top");
			int _is_red = jsonobject.getInt("is_red");
			int _is_hot = jsonobject.getInt("is_hot");
			int _is_slide = jsonobject.getInt("is_slide");
			int _is_lock = jsonobject.getInt("is_lock");
			int _user_id = jsonobject.getInt("user_id");
			String _add_time = jsonobject.getString("add_time");
			int _digg_good = jsonobject.getInt("digg_good");
			int _digg_bad = jsonobject.getInt("digg_bad");
			list.add(new goods(_id, _channel_id, _category_id, _title,
					_goods_no, _stock_quantity, _market_price, _sell_price,
					_point, _link_url, _img_url, _seo_title, _seo_keywords,
					_seo_description, _content, _sort_id, _click, _is_msg,
					_is_top, _is_red, _is_hot, _is_slide, _is_lock, _user_id,
					_add_time, _digg_good, _digg_bad));
		}
		return list;
	}
}
