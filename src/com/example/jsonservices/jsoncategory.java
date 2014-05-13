package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.example.model.category;
import com.example.utils.StreamTool;

public class jsoncategory {
	/***
	 * 读取地区分店列表
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static List<category> getjsonlastcategory(String url)
			throws Exception {
		try {
			String path = url;
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setConnectTimeout(50000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				InputStream json = conn.getInputStream();
				return parsejson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<category> parsejson(
			InputStream jsonStream) throws Exception {
		List<category> list = new ArrayList<category>();
		try {
			byte[] data = StreamTool.read(jsonStream);
			String json = new String(data);
			JSONArray jsonarray = new JSONArray(json);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				int id = jsonobject.getInt("id");
				int channel_id = jsonobject.getInt("channel_id");
				String title = jsonobject.getString("title");
				String call_index = jsonobject.getString("call_index");
				int parent_id = jsonobject.getInt("parent_id");
				String class_list = jsonobject.getString("class_list");
				int class_layer = jsonobject.getInt("class_layer");
				int sort_id = jsonobject.getInt("sort_id");
				String link_url = jsonobject.getString("link_url");
				String img_url = jsonobject.getString("img_url");
				String content = jsonobject.getString("content");
				String seo_title = jsonobject.getString("seo_title");
				String seo_keywords = jsonobject.getString("seo_keywords");
				String seo_description = jsonobject.getString("seo_description");
				list.add(new category(id, channel_id, title, call_index,
						parent_id, class_list, class_layer, sort_id, link_url,
						img_url, content, seo_title, seo_keywords,
						seo_description));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}

}
