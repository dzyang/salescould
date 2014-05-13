package com.example.myfood;

import com.example.utils.SystemDBManager;
import com.example.utils.UpdateManager;
import com.example.utils.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SystemActivity extends Activity {
	private myapplication myapplication1;
	private UpdateManager mUpdateManager;
	private SystemDBManager SystemDBManager1;
	private Button Button1; // 推荐朋友
	//private Button Button2; // 意见反馈
	private Button Button3; // 帮助
	private Button Button4; // 检查更新
	private Button Button5; // 关于
	private Button Button6; // 修改hostlocal
	private EditText EditText1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		SystemDBManager1 = new SystemDBManager(this);
		Button1 = (Button) findViewById(R.id.mbutton1);
		//Button2 = (Button) findViewById(R.id.mbutton2);
		Button3 = (Button) findViewById(R.id.mbutton3);
		Button4 = (Button) findViewById(R.id.mbutton4);
		Button5 = (Button) findViewById(R.id.mbutton5);
		Button6 = (Button) findViewById(R.id.mbutton6);
		EditText1 = (EditText) findViewById(R.id.EditText1);
		EditText1.setText(localhost());
		
		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemActivity.this, LianxirenActivity.class);
				startActivity(intent);
			}
		});

		Button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(SystemActivity.this, HelpActivity.class);
				startActivity(Intent1);
			}
		});

		Button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 这里来检测版本是否需要更新
				try {
					mUpdateManager = new UpdateManager(SystemActivity.this,
							myapplication1.getlocalhost());
					mUpdateManager.checkUpdateInfo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(SystemActivity.this, AboutActivity.class);
				Bundle Bundle1 = new Bundle();
				Bundle1.putString("type", "about");
				Intent1.putExtras(Bundle1);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});

		Button6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SystemDBManager1
						.updatelocalhost(EditText1.getText().toString());
				finish();
			}
		});
	}

	public String localhost() {
		return myapplication1.getlocalhost();
	}

}
