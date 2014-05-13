package com.example.jsonservices;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.example.model.users;
import com.example.utils.StreamTool;

public class jsonusers {
	/***
	 * 读取地区分店列表
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static List<users> getjsonlastusers(String url)
			throws Exception {
		try {
			String path = url;
			HttpURLConnection conn = (HttpURLConnection) new URL(path)
					.openConnection();
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

	private static List<users> parsejson(InputStream jsonStream)
			throws Exception {
		List<users> list = new ArrayList<users>();
		try {
			byte[] data = StreamTool.read(jsonStream);
			String json = new String(data);
			JSONArray jsonarray = new JSONArray(json);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				int _id = jsonobject.getInt("id");
				int _group_id = jsonobject.getInt("group_id");
				int _usercatagory = jsonobject.getInt("usercatagory");				
				String _group_name= jsonobject.getString("group_name");
				String _user_name = jsonobject.getString("user_name");
				String _password = jsonobject.getString("password");
				String _email = jsonobject.getString("email");
				String _nick_name = jsonobject.getString("nick_name");
				String _avatar = jsonobject.getString("avatar");
				String _sex = jsonobject.getString("sex");
				String _birthday = jsonobject.getString("birthday");
				String _telphone = jsonobject.getString("telphone");
				String _mobile = jsonobject.getString("mobile");
				String _qq = jsonobject.getString("qq");
				String _address = jsonobject.getString("address");
				String _safe_question = jsonobject.getString("safe_question");
				String _safe_answer = jsonobject.getString("safe_answer");
				String _amount = jsonobject.getString("amount");
				int _point = jsonobject.getInt("point");
				int _exp = jsonobject.getInt("exp");
				int _is_lock = jsonobject.getInt("is_lock");
				String _reg_time = jsonobject.getString("reg_time");
				String _reg_ip = jsonobject.getString("reg_ip");
				list.add(new users(_id, _group_id, _user_name, _password,
						_email, _nick_name, _avatar, _sex, _birthday,
						_telphone, _mobile, _qq, _address, _safe_question,
						_safe_answer, _amount, _point, _exp, _is_lock,
						_reg_time, _reg_ip,_usercatagory,_group_name));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
}
