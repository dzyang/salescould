package com.example.daily;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonusers;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.DiquActivity;
import com.example.myfood.MainActivity;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;
import com.example.view.DailyView.MyAdapter;
import com.example.view.DailyView.ViewHolder;

public class DailyKqActivity extends Activity {
	private myapplication myapplication1;
	private ImageView button1;
	
	

	// model
	List<Map<String, Object>> data;
    // view
    ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dailykq);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		
		button1 = (ImageView) findViewById(R.id.topbar_img_left);
		lv=(ListView)findViewById(R.id.list);
		initData();
		

		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(DailyKqActivity.this, MainFragment.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Log.v("MyListView4-click", (String)data.get(position).get("title"));
				if(data.get(position).get("title").equals("考勤打卡"))
				{
					Intent intent = new Intent();
					intent.setClass(DailyKqActivity.this, DailyKqDkActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}else if(data.get(position).get("title").equals("出差申请"))
				{
					Intent intent = new Intent();
					intent.setClass(DailyKqActivity.this, DailyKqCcActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}else if(data.get(position).get("title").equals("外出登记"))
				{
					Intent intent = new Intent();
					intent.setClass(DailyKqActivity.this, DailyKqWcActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}else	if(data.get(position).get("title").equals("请假申请"))
				{
					Intent intent = new Intent();
					intent.setClass(DailyKqActivity.this, DailyKqQjActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}else	if(data.get(position).get("title").equals("加班登记"))
				{
					Intent intent = new Intent();
					intent.setClass(DailyKqActivity.this, DailyKqJbActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
			}
			
		});
		
		
		
		
	}

	private void initData() {
		// TODO Auto-generated method stub
	    if (lv == null)
        {
          return;
        }
      data = getData() ;

      
      MyAdapter adapter = new MyAdapter(this.getApplicationContext());
      lv.setAdapter(adapter);
	}

	
	 private List<Map<String, Object>> getData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "考勤打卡");
			map.put("img",R.drawable.chinaz1_);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "出差申请");
			map.put("img", R.drawable.chinaz2_);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "外出登记");
			map.put("img",R.drawable.chinaz3_);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("title", "请假申请");
			map.put("img",R.drawable.chinaz4_);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("title", "加班登记");
			map.put("img",R.drawable.chinaz5_);
			list.add(map);
			
		/*	map = new HashMap<String, Object>();
			map.put("title", "考勤查询");
			map.put("img",R.drawable.chinaz6_);
			list.add(map);*/
			return list;
		}
		 
	 
	 public final class ViewHolder{
			public ImageView img;
			public TextView title;
		}
		
	    public class MyAdapter extends BaseAdapter{

	    	private LayoutInflater mInflater;
			
			
			public MyAdapter(Context context){
				this.mInflater = LayoutInflater.from(context);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return data.size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ViewHolder holder = null;
				if (convertView == null) {				
					holder=new ViewHolder();  
					convertView = mInflater.inflate(R.layout.dailymainmenu, null);
					holder.img = (ImageView)convertView.findViewById(R.id.img);
					holder.title = (TextView)convertView.findViewById(R.id.title);
					
				}else {				
					holder = (ViewHolder)convertView.getTag();
				}
				holder.img.setBackgroundResource((Integer)data.get(position).get("img"));
				holder.title.setText((String)data.get(position).get("title"));
				return convertView;
			}
			
	    }
	
}
