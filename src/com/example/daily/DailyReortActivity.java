package com.example.daily;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonusers;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.DiquActivity;
import com.example.myfood.LoginActivity;
import com.example.myfood.MainActivity;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;

public class DailyReortActivity extends Activity {
	private myapplication myapplication1;
	private ImageView button1;
	private ImageView button2;
	private TextView textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dailyreport);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		
		button1 = (ImageView) findViewById(R.id.topbar_img_left);
		button2= (ImageView) findViewById(R.id.topbar_img_right);
		textview= (TextView) findViewById(R.id.topbar_tv_center);
		textview.setText("工作日报");
		/*
		binddata();*/
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(DailyReortActivity.this, MainFragment.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//updateding();
				new Thread(saveRun).start();
			}
		});
	}
	
	
Runnable saveRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
			//report_customernum_text
			//report_salesnum_text
			//report_salesmoney_text
			//report_salestraget_text
			//report_memo_text
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_report/add.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号

			
			EditText customernum__edit= (EditText) findViewById(R.id.report_customernum_text);			
			paramsList.add(new BasicNameValuePair("_customernum",customernum__edit.getText().toString()));//拜访顾客数
			
			EditText salesnum_edit= (EditText) findViewById(R.id.report_salesnum_text);
			paramsList.add(new BasicNameValuePair("_salesnum",salesnum_edit.getText().toString()));//销售量
			
			EditText salesmoney__edit= (EditText) findViewById(R.id.report_salesmoney_text);
			paramsList.add(new BasicNameValuePair("_salesmoney",salesmoney__edit.getText().toString()));//销售额
			
			EditText salestraget__edit= (EditText) findViewById(R.id.report_salestraget_text);
			paramsList.add(new BasicNameValuePair("_salestraget",salestraget__edit.getText().toString()));//完成目标
			
			EditText memo_edit= (EditText) findViewById(R.id.report_memo_text);
			paramsList.add(new BasicNameValuePair("_memo",memo_edit.getText().toString()));//备注
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
				
		        	Toast.makeText(DailyReortActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(DailyReortActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};
	
}
