package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.example.model.category;
import com.example.model.customer;
import com.example.utils.StreamTool;

public class jsoncustomer {
	/***
	 * 读取地区分店列表
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static List<customer> getjsoncustomer(String url)
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

	private static List<customer> parsejson(
			InputStream jsonStream) throws Exception {
		List<customer> list = new ArrayList<customer>();
		try {
			byte[] data = StreamTool.read(jsonStream);
			String json = new String(data);
			JSONArray jsonarray = new JSONArray(json);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				int id = jsonobject.getInt("id");
				Log.v("row", String.valueOf(id));
/*				int channel_id = jsonobject.getInt("channel_id");
				int class_layer = jsonobject.getInt("class_layer");
				int sort_id = jsonobject.getInt("sort_id");
				int parent_id = jsonobject.getInt("parent_id");
*/
				int channel_id=0;
				int class_layer=0;
				int sort_id=0;
				int parent_id=0;
				int _userid=0;
				if(!jsonobject.getString("userid").toLowerCase().equals("null")&&jsonobject.getString("userid").trim().length()>0)
				{
					_userid = Integer.parseInt(jsonobject.getString("userid"));
				}
				
				String title = jsonobject.getString("title");
				String call_index = jsonobject.getString("call_index");
				String class_list = jsonobject.getString("class_list");
				String link_url = jsonobject.getString("link_url");
				String img_url = jsonobject.getString("img_url");
				String content = jsonobject.getString("content");
				String seo_title = jsonobject.getString("seo_title");
				String seo_keywords = jsonobject.getString("seo_keywords");
				String seo_description = jsonobject.getString("seo_description");
				
				String _tel = jsonobject.getString("tel");
				String _fax = jsonobject.getString("fax");
				String _mailcode = jsonobject.getString("mailcode");
				String _website = jsonobject.getString("website");
				String _area = jsonobject.getString("area");
				String _address = jsonobject.getString("address");
				String _catagory = jsonobject.getString("catagory");
				String _companycode = jsonobject.getString("companycode");
			
				
				list.add(new customer(id, channel_id, title, call_index,
						parent_id, class_list, class_layer, sort_id, link_url,
						img_url, content, seo_title, seo_keywords,
						seo_description,_tel,_fax,_mailcode,_website,_area,_address,_catagory,_companycode,_userid));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}

}
