package com.example.custom;

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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.daily.DailyTaskActivity.MyAdapter;
import com.example.daily.DailyTaskActivity.ViewHolder;
import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonarticle;
import com.example.jsonservices.jsonbfjl;
import com.example.jsonservices.jsonusers;
import com.example.model.CustomerBf;
import com.example.model.article;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;

public class BfjlActivity extends Activity {
	private myapplication myapplication1;
	// model
	List<Map<String, Object>> data;
    // view
    ListView lv;
	private Button button1;
	
	 MyAdapter adapter;
	 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 private Button bt;
	 private ProgressBar pg;
	 private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bfjl);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		button1 = (Button) findViewById(R.id.button1);	
		lv=(ListView)findViewById(R.id.list);
		bt = (Button) findViewById(R.id.bt_load);
	    pg = (ProgressBar) findViewById(R.id.pg);	  
		adapter = new MyAdapter(this.getApplicationContext());
		handler = new Handler();		 
		
		initData();
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(BfjlActivity.this, MainFragment.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
	

		bt.setOnClickListener(new OnClickListener() {
       	 @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);// 将进度条可见
                bt.setVisibility(View.GONE);// 按钮不可见
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        loadMoreDate();// 加载更多数据
                        bt.setVisibility(View.VISIBLE);
                        pg.setVisibility(View.GONE);
                        MyAdapter sAdapter = (MyAdapter)lv.getAdapter();
                        sAdapter.notifyDataSetChanged();
                    }
                }, 2000);
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
      lv.setAdapter(adapter);
	}

	
	 private List<Map<String, Object>> getData() {
		 String url=myapplication1.getlocalhost()+ "/android/json_bfdj/list.php?companycode="+myapplication1.getCompanycode()+"&userid="+myapplication1.getUserid();
	    	list = new ArrayList<Map<String, Object>>();
	    	try {
				List<CustomerBf> bfjllist=jsonbfjl.getjsonlastarticles(url);
				for (CustomerBf CustomerBfs : bfjllist) {
					HashMap<String, Object> item = new HashMap<String, Object>();
					item.put("title", CustomerBfs.getBz());
					item.put("img",R.drawable.chinaz1_);
					
					list.add(item);
				}					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		return list;
		}
		 
	 
	 
	 private void loadMoreDate() {
			int count = adapter.getCount();
			String url=myapplication1.getlocalhost()+ "/android/json_bfdj/json_bfdj.php?page="+count;
	    	list = new ArrayList<Map<String, Object>>();
	    	try {
	    		List<CustomerBf> bfjllist=jsonbfjl.getjsonlastarticles(url);
	    		for (CustomerBf CustomerBfs : bfjllist) {
					HashMap<String, Object> item = new HashMap<String, Object>();
					item.put("title", CustomerBfs.getBz());
					item.put("img",R.drawable.chinaz1_);
					list.add(item);
				}					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if(list.size()<5)
	    	{
				 bt.setText("加载完成");
				 bt.setVisibility(View.GONE);// 按钮不可见
	    	}
	    	
	  
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
