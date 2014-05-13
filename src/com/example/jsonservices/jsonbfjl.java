package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.example.model.CustomerBf;
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonbfjl {

	public static List<CustomerBf> getjsonlastarticles(String url)
			throws Exception {
		String path = url;
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(50000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream json = conn.getInputStream();
			return parsejson(json);
		}
		return null;
	}

	private static List<CustomerBf> parsejson(InputStream jsonStream)
			throws Exception {
		List<CustomerBf> list = new ArrayList<CustomerBf>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _userid = jsonobject.getInt("userid");
			int _companycode = jsonobject.getInt("companycode");
			String _customerid = jsonobject.getString("customerid");
			String _latlng = jsonobject.getString("latlng");
			String _pic = jsonobject.getString("pic");
			String _bz = jsonobject.getString("bz");			
			String _adddate = jsonobject.getString("adddate");
			list.add(new CustomerBf(_id, _userid, _companycode, _customerid,
					_latlng, _pic, _bz, _adddate));
		}
		return list;
	}
	
	
}
