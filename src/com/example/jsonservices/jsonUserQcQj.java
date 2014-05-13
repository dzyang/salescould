package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.model.Traget;
import com.example.model.UserCustomerBf;
import com.example.model.UserQcQj;
import com.example.model.UserQcWc;
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonUserQcQj {
	public static List<UserQcQj> getjsonlastarticles(String url)
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

	private static List<UserQcQj> parsejson(InputStream jsonStream)
			throws Exception {
		List<UserQcQj> list = new ArrayList<UserQcQj>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _userid = jsonobject.getInt("userid");
			int _companycode = jsonobject.getInt("companycode");
			
			
			String startdate = jsonobject.getString("startdate");
			String starttime = jsonobject.getString("starttime");
			String enddate = jsonobject.getString("enddate");
			String endtime = jsonobject.getString("endtime");
			
			String days = jsonobject.getString("days");
			String type = jsonobject.getString("type");
			String memo = jsonobject.getString("memo");
			String adddate = jsonobject.getString("adddate");
			
			list.add(new UserQcQj(_id, _userid,_companycode,
					startdate, starttime, enddate, endtime,
					days, type, memo, adddate) );
		}
		return list;
	}
}
