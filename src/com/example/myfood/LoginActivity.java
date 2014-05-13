package com.example.myfood;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fragments.MainFragment;
import com.example.myfood.R.string;
import com.example.utils.StreamTool;
import com.example.utils.SystemDBManager;
import com.example.utils.UsersDBManager;
import com.example.utils.myapplication;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private myapplication myapplication1;
	private UsersDBManager UsersDBManager1;
	private SystemDBManager SystemDBManager1;
	private EditText EditText1;
	private EditText EditText2;
	private EditText EditText3;
	private Button Button1;
	private Button Button2;
	private int result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			//Caused by: android.os.NetworkOnMainThreadException，查了下原因上在4.0之后在主线程里面执行Http请求都会报这个错
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		UsersDBManager1 = new UsersDBManager(this);
		SystemDBManager1= new SystemDBManager(this);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		if (myapplication1.ifpass()) {
			Intent Intent1 = new Intent();
			Intent1.setClass(LoginActivity.this, MainActivity.class);
			startActivity(Intent1);
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			finish();
		}
		EditText1 = (EditText) findViewById(R.id.register_editText1);
		EditText2 = (EditText) findViewById(R.id.register_editText2);
		EditText3 = (EditText) findViewById(R.id.register_editText3);
		
		EditText3.setText(SystemDBManager1.localhost());
		
		Button1 = (Button) findViewById(R.id.register_button1);
		Button2 = (Button) findViewById(R.id.register_button2);
		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//login();
				new Thread(loginRun).start();
			

			}
		});
		
		Button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register();
				
				  

			}
		});
	}
	
	public void register() {
		Toast.makeText(LoginActivity.this, "注册关闭！", 1).show();
	}


	
	Runnable loginRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
			
			SystemDBManager1.updatelocalhost(EditText3.getText().toString());
			
			String target = myapplication1.getlocalhost()+ "/android/json_users/login.php";

			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_user_name", EditText1.getText().toString()));
			paramsList.add(new BasicNameValuePair("_password", EditText2.getText().toString()));
			try {
				httprequest
						.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
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
		        loginhandler.sendMessage(msg);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		}  
	 };  

	 Handler loginhandler = new Handler(){
		    @Override
		    public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		        Bundle data = msg.getData();
		        int result = data.getInt("result");
		        Log.i("mylog","请求结果-->" + result);
		        if (result == 1) {
					getcompany();
					UsersDBManager1.login(EditText1.getText().toString());
					Intent intent = new Intent(LoginActivity.this,MainFragment.class); // 从启动动画ui跳转到主ui
					startActivity(intent);
					finish();
				}else
				{
					Toast.makeText(LoginActivity.this, "登录失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};


	public void login() {
		String target = myapplication1.getlocalhost()+ "/android/json_users/login.php";

		HttpPost httprequest = new HttpPost(target);
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new BasicNameValuePair("_user_name", EditText1.getText()
				.toString()));
		paramsList.add(new BasicNameValuePair("_password", EditText2.getText()
				.toString()));
		try {
			httprequest
					.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
			HttpClient HttpClient1 = new DefaultHttpClient();
			HttpResponse httpResponse = HttpClient1.execute(httprequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity()));
			} else {
				result = 0;
			}
			// 针对这里不能用字符串来判断，因为字符串判断是失效的，必须用数字来进行条件判断读取返回结果达到想要的页面跳转
			if (result == 1) {
				//getcompany();
				UsersDBManager1.login(EditText1.getText().toString());
				Intent intent = new Intent(LoginActivity.this,MainFragment.class); // 从启动动画ui跳转到主ui
				startActivity(intent);
				finish();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getcompany() {
		String target = myapplication1.getlocalhost()+ "/android/json_users/getuserinfo.php";

		HttpPost httprequest = new HttpPost(target);
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new BasicNameValuePair("_user_name", EditText1.getText().toString()));
		paramsList.add(new BasicNameValuePair("_password", EditText2.getText().toString()));
		try {
			httprequest.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
			HttpClient HttpClient1 = new DefaultHttpClient();
			HttpResponse httpResponse = HttpClient1.execute(httprequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				//{"jsonObj":["1","2014001","test","test","test@qq.com","ydz","\/android\/upload\/5.jpg","1","1983-02-10","010-0000000","15810369811","594904292","\u5317\u4eac","\u5c0f\u5b66","\u53cb\u8c0a","100","100","1","0","2014-12-23","10.1.1.2"]}
				String json = EntityUtils.toString(httpResponse.getEntity());
				JSONArray jsonarray = null;
				try {
					jsonarray = new JSONObject(json).getJSONArray("jsonObj");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					String user_id = null;
					try {
						user_id = jsonarray.getString(0);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String company_code = null;
					try {
						company_code = jsonarray.getString(1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject jsonobject = jsonarray.getJSONObject(i);
						String user_id=jsonobject.getString("id");
						String company_code=jsonobject.getString("group_id");*/
						myapplication1.setUserid(user_id);
						myapplication1.setCompanycode(company_code);
						System.out.println("---------");
						System.out.println("---------");
					//}
			
			}			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}

}
