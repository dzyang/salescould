package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.model.Traget;
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonTraget {
	public static List<Traget> getjsonlastarticles(String url)
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

	private static List<Traget> parsejson(InputStream jsonStream)
			throws Exception {
		List<Traget> list = new ArrayList<Traget>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _userid = jsonobject.getInt("userid");
			int _companyid = jsonobject.getInt("companyid");
			String _tragetname = jsonobject.getString("tragetname");
			String _tragetvulue = jsonobject.getString("tragetvulue");
			list.add(new Traget(_id, _userid, _companyid, _tragetname,
					_tragetvulue));
		}
		return list;
	}
}
