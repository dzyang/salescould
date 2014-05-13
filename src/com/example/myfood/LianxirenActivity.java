package com.example.myfood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.utils.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LianxirenActivity extends Activity {
	private myapplication myapplication1;
	private List<lianxiren> lxr;
	private ListView listview1;

	class lianxiren {
		public String nameString;
		public String telString;
		public int id;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lianxiren);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		listview1 = (ListView) findViewById(R.id.lxr_listView1);

		SimpleAdapter adapter = new SimpleAdapter(LianxirenActivity.this,
				bindlxr(), R.layout.item_lianxiren,
				new String[] { "username" }, new int[] { R.id.lxr_textView1 });
		listview1.setAdapter(adapter);
	}

	public List<Map<String, Object>> bindlxr() {
		List<Map<String, Object>> lianxirenArrayList = new ArrayList<Map<String, Object>>();
		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		while (cursor.moveToNext()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("username", cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			lianxirenArrayList.add(item);
		}
		cursor.close();
		return lianxirenArrayList;
	}

}
