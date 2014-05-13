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
import com.example.model.UserQcCc;
import com.example.model.UserTask;
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonUserTask {
	public static List<UserTask > getjsonlastarticles(String url)
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

	private static List<UserTask> parsejson(InputStream jsonStream)
			throws Exception {
		List<UserTask> list = new ArrayList<UserTask>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _userid = jsonobject.getInt("userid");
			int _companycode = jsonobject.getInt("companycode");		
			
			String tasklevel = jsonobject.getString("tasklevel");
			String workuserid = jsonobject.getString("workuserid");
			
			String task = jsonobject.getString("task");
			String finshdate = jsonobject.getString("finshdate");
			
			int status = jsonobject.getInt("status");
			String adddate = jsonobject.getString("adddate");
			
			list.add(new UserTask(_id, _userid,_companycode,
					tasklevel, workuserid, task, finshdate,
					status, adddate) );
		}
		return list;
	}

	
}
