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
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonUserQcJb {
	public static List<UserQcJb> getjsonlastarticles(String url)
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

	private static List<UserQcJb> parsejson(InputStream jsonStream)
			throws Exception {
		List<UserQcJb> list = new ArrayList<UserQcJb>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _userid = jsonobject.getInt("userid");
			int _companycode = jsonobject.getInt("companycode");
		
			String _stardate = jsonobject.getString("stardate");
			String _enddate = jsonobject.getString("enddate");
			String _starttime = jsonobject.getString("starttime");
			String _endtime = jsonobject.getString("endtime");
			String _costtime = jsonobject.getString("costtime");
			String _memo = jsonobject.getString("memo");
			String _adddate = jsonobject.getString("adddate");
			
			
		
			list.add(new UserQcJb(_id, _userid, _companycode,
					_stardate, _enddate, _starttime, _endtime,
					_costtime, _memo, _adddate));
		}
		return list;
	}
}
