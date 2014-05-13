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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.daily.DailyFeeNewActivity;
import com.example.daily.DailyTaskActivity.MyAdapter;
import com.example.daily.DailyTaskActivity.ViewHolder;
import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonUserFee;
import com.example.jsonservices.jsoncustomer;
import com.example.jsonservices.jsonusers;
import com.example.model.UserFee;
import com.example.model.customer;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;

public class KhtxladdActivity extends Activity implements AdapterView.OnItemSelectedListener {
	private myapplication myapplication1;
	private Button button1;
	private Button button2;

	private Spinner customerid_edit;
	private EditText name_edit;
	private EditText sex_edit;
	private EditText tel_edit;
	private EditText age_edit;
	private EditText bz_edit;
	
	private String customername="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.khtxladd);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		
		customerid_edit= (Spinner) findViewById(R.id.khtxladd_customerid);
		name_edit= (EditText) findViewById(R.id.khtxladd_name);
		sex_edit= (EditText) findViewById(R.id.khtxladd_sex);
		tel_edit= (EditText) findViewById(R.id.khtxladd_tex);
		age_edit= (EditText) findViewById(R.id.khtxladd_age);
		bz_edit= (EditText) findViewById(R.id.khtxladd_bz);
		

	
	  String obj[]=this.getData();
	   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, obj);  
	   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	 
			
		customerid_edit.setAdapter(adapter);
		customerid_edit.setOnItemSelectedListener((OnItemSelectedListener) this);  
		
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(KhtxladdActivity.this, MainFragment.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(saveRun).start();
			}

			
		});
	}

	private String[] getData() {
		 String url=myapplication1.getlocalhost()+ "/android/json_customer/list.php?channel_id=2&parent_id=0&page=";
		 ArrayList list = new ArrayList<String>();
	    	try {
				List<customer> listcustomer=jsoncustomer.getjsoncustomer(url);
				for (customer customers : listcustomer) {							
					Log.v("--", String.valueOf(customers.get_id()));
					list.add(customers.get_title().toString());
				}					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	String[] arr=new String[list.size()];
	    	for(int i=0;i<list.size();i++)
	    	{
	    		arr[i]=list.get(i).toString();
	    	}
	    //String[] arr=new String[list.size()];
	  		return arr;
		}
		 
	


Runnable saveRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
		
			
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_customer/addtxl.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号
		
			//paramsList.add(new BasicNameValuePair("_customerid",customerid_edit.getText().toString()));//顾客
			//customerid_edit.getse
			
			paramsList.add(new BasicNameValuePair("_customerid",customername));
			
			paramsList.add(new BasicNameValuePair("_name",name_edit.getText().toString()));//名称

			paramsList.add(new BasicNameValuePair("_sex",sex_edit.getText().toString()));//性别
			
			paramsList.add(new BasicNameValuePair("_tel",tel_edit.getText().toString()));//电话
			
			paramsList.add(new BasicNameValuePair("_age",age_edit.getText().toString()));//年龄
			
			paramsList.add(new BasicNameValuePair("_bz",bz_edit.getText().toString()));//备注
			
	
			try {
				httprequest.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
				HttpClient HttpClient1 = new DefaultHttpClient();
				HttpResponse httpResponse = HttpClient1.execute(httprequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					result = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity()));
				} else {
					result = 0;
				}
				Message msg = new Message();
		        Bundle data = new Bundle();
		        data.putInt("result",result);
		        msg.setData(data);
		        savehandler.sendMessage(msg);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
	 };  

	 Handler savehandler = new Handler(){
		    @Override
		    public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		        Bundle data = msg.getData();
		        int result = data.getInt("result");
		        Log.i("mylog","请求结果-->" + result);
		        if (result == 1) {
				
		        	Toast.makeText(KhtxladdActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(KhtxladdActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  
		// TODO Auto-generated method stub
		customername=parent.getItemAtPosition(position).toString();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
