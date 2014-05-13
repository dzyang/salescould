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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.daily.DailyFeeNewActivity;
import com.example.daily.DailyTaskActivity.MyAdapter;
import com.example.daily.DailyTaskActivity.ViewHolder;
import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonusers;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;

public class KhaddActivity extends Activity {
	private myapplication myapplication1;
	private Button button1;
	private Button button2;
	private EditText name_edit;
	private EditText catagory_edit;
	private EditText area_edit;
	private EditText address_edit;
	private EditText tel_edit;
	private EditText fax_edit;
	private EditText mailcode_edit;
	private EditText imgurl_edit;
	private EditText websitel_edit;
	private EditText content_edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.khadd);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		
		 name_edit= (EditText) findViewById(R.id.khadd_name);
		 catagory_edit= (EditText) findViewById(R.id.khadd_catagory);
		 area_edit= (EditText) findViewById(R.id.khadd_area);
		 address_edit= (EditText) findViewById(R.id.khadd_address);
		 tel_edit= (EditText) findViewById(R.id.khadd_tel);
		 fax_edit= (EditText) findViewById(R.id.khadd_fax);
		 mailcode_edit= (EditText) findViewById(R.id.khadd_mailcode);
		 imgurl_edit= (EditText) findViewById(R.id.khadd_imgurl);
		 websitel_edit= (EditText) findViewById(R.id.khadd_website);
		 content_edit= (EditText) findViewById(R.id.khadd_content);

		
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(KhaddActivity.this, MainFragment.class);
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

	
Runnable saveRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
		
			
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_customer/add.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号
			
			
			/*$title="1";//标题
			$seo_title="";//描述
			$catagory="1";//类型
			$area="1";//地区
			$address="1";//地址
			$tel="1";//电话
			$fax="1";//传真
			$mailcode="1";//邮编
			$img_url="1";//拍照
			$website="1";//网址
			$content="1";//备注
*/			
		
			paramsList.add(new BasicNameValuePair("_title",name_edit.getText().toString()));//名称
			
			paramsList.add(new BasicNameValuePair("_catagory",catagory_edit.getText().toString()));//类型

			paramsList.add(new BasicNameValuePair("_area",area_edit.getText().toString()));//地区
			
			paramsList.add(new BasicNameValuePair("_address",address_edit.getText().toString()));//地址
			
			paramsList.add(new BasicNameValuePair("_tel",tel_edit.getText().toString()));//电话
			
			paramsList.add(new BasicNameValuePair("_fax",fax_edit.getText().toString()));//传真
			
			paramsList.add(new BasicNameValuePair("_mailcode",mailcode_edit.getText().toString()));//邮编
			
			
			paramsList.add(new BasicNameValuePair("_img_url",imgurl_edit.getText().toString()));//拍照
			
			
			paramsList.add(new BasicNameValuePair("_website",websitel_edit.getText().toString()));//邮编
			
			paramsList.add(new BasicNameValuePair("_img_url",content_edit.getText().toString()));//邮编

	
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
				
		        	Toast.makeText(KhaddActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(KhaddActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};
	
	
}
