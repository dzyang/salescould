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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.daily.DailyKqActivity.MyAdapter;
import com.example.daily.DailyKqActivity.ViewHolder;
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

public class DailyKqQjAddActivity extends Activity {
	private myapplication myapplication1;
	private Button button1;
	private Button button2;
	// model
	//List<Map<String, Object>> data;
    // view
  //  ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dailykq_qj_new);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		
		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		//lv=(ListView)findViewById(R.id.list);
		
		
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(DailyKqQjAddActivity.this, DailyKqQjActivity.class);
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
		
			
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_kq/qj_add.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号

			
			/*$stardate=$_POST['_stardate'];
			$starttime=$_POST['_starttime'];
			
			$enddate=$_POST['_enddate'];
			$endtime=$_POST['_endtime'];
			
			$days=$_POST['_days'];
			$type=$_POST['_type'];	
			$memo=$_POST['_memo'];*/

			
		EditText startate_edit= (EditText) findViewById(R.id.daily_kq_qj_startdate);
			paramsList.add(new BasicNameValuePair("_stardate",startate_edit.getText().toString()));//完成目标
			
			EditText startime_edit= (EditText) findViewById(R.id.daily_kq_qj_starttime);
			paramsList.add(new BasicNameValuePair("_starttime",startime_edit.getText().toString()));//完成目标
			
			EditText enddate_edit= (EditText) findViewById(R.id.daily_kq_qj_enddate);
			paramsList.add(new BasicNameValuePair("_enddate",enddate_edit.getText().toString()));//完成目标
			
			EditText endtime_edit= (EditText) findViewById(R.id.daily_kq_qj_endtime);
			paramsList.add(new BasicNameValuePair("_endtime",endtime_edit.getText().toString()));//完成目标
			
			
			EditText days_edit= (EditText) findViewById(R.id.daily_kq_qj_days);
			paramsList.add(new BasicNameValuePair("_days",days_edit.getText().toString()));//完成目标
			
			EditText type_edit= (EditText) findViewById(R.id.daily_kq_qj_type);
			paramsList.add(new BasicNameValuePair("_type",type_edit.getText().toString()));//完成目标
			
			EditText memo_edit= (EditText) findViewById(R.id.daily_kq_qj_memo);
			paramsList.add(new BasicNameValuePair("_memo",memo_edit.getText().toString()));//完成目标

			
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
				
		        	Toast.makeText(DailyKqQjAddActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(DailyKqQjAddActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};
	

		 
	 

	
}
