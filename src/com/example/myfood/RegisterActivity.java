package com.example.myfood;

import java.io.IOException;
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

import com.example.myfood.R.string;
import com.example.utils.UsersDBManager;
import com.example.utils.myapplication;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private UsersDBManager UsersDBManager1;
	private myapplication myapplication1;
	private EditText EditText1;
	private EditText EditText2;
	private Button Button1;
	private Button Button2;
	private int result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		UsersDBManager1 = new UsersDBManager(this);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		if (myapplication1.ifpass()) {
			Intent Intent1 = new Intent();
			Intent1.setClass(RegisterActivity.this, UsercenterActivity.class);
			startActivity(Intent1);
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			finish();
		}
		EditText1 = (EditText) findViewById(R.id.register_editText1);
		EditText2 = (EditText) findViewById(R.id.register_editText2);
		Button1 = (Button) findViewById(R.id.register_button1);
		Button2 = (Button) findViewById(R.id.register_button2);
		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();

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
		Toast.makeText(RegisterActivity.this, "注册关闭！", 1).show();
	}



	public void login() {
		String target = myapplication1.getlocalhost()+ "/android/json_users/login.php";
		
		//String target ="http://10.0.2.2/android/json_users/login.php";
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
				result = Integer.parseInt(EntityUtils.toString(httpResponse
						.getEntity()));
			} else {
				result = 0;
			}
			// 针对这里不能用字符串来判断，因为字符串判断是失效的，必须用数字来进行条件判断读取返回结果达到想要的页面跳转
			if (result == 1) {
				UsersDBManager1.login(EditText1.getText().toString());
				finish();
			}
			Toast.makeText(RegisterActivity.this, Integer.toString(result),
					Toast.LENGTH_SHORT).show();

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
