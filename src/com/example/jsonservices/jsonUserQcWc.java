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
import com.example.model.UserQcJb;
import com.example.model.UserQcWc;
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonUserQcWc {
	public static List<UserQcWc> getjsonlastarticles(String url)
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

	private static List<UserQcWc> parsejson(InputStream jsonStream)
			throws Exception {
		List<UserQcWc> list = new ArrayList<UserQcWc>();
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
			String endtime = jsonobject.getString("endtime");
			String memo = jsonobject.getString("memo");
			String adddate = jsonobject.getString("adddate");
			
			list.add(new UserQcWc(_id, _userid, _companycode,
					startdate, starttime, endtime, memo,
					adddate) );
		}
		return list;
	}
}
