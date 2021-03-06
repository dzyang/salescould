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
import com.example.model.article;
import com.example.utils.StreamTool;

public class jsonUserCustomerBf {
	public static List<UserCustomerBf> getjsonlastarticles(String url)
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

	private static List<UserCustomerBf> parsejson(InputStream jsonStream)
			throws Exception {
		List<UserCustomerBf> list = new ArrayList<UserCustomerBf>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			int _id = jsonobject.getInt("id");
			int _userid = jsonobject.getInt("userid");
			int _companyid = jsonobject.getInt("companyid");
			int _customerid = jsonobject.getInt("customerid");
			String _tragetname = jsonobject.getString("tragetname");
			String _tragetvulue = jsonobject.getString("tragetvulue");
			list.add(new UserCustomerBf(_id, _userid, _companyid, _customerid,_tragetname,
					_tragetvulue));
		}
		return list;
	}
}
