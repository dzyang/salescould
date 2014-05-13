package com.example.myfood;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jsonservices.jsonusers;
import com.example.model.users;
import com.example.utils.UsersDBManager;
import com.example.utils.myapplication;

public class UsercenterActivity extends Activity {
	private myapplication myapplication1;
	private UsersDBManager UsersDBManager1;
	private ProgressDialog ProgressDialog1; // 加载对话框
	private ImageView ImageView1;
	private TextView TextView1;
	private TextView TextView2;
	private Button Button1;// 预定订单
	private Button Button2;// 我的喜欢
	//private Button Button3;// 参与问卷调查
	//private Button Button4;// 修改密码
	private Button Button5;// 退出登录
	private List<users> list1 = null;
	private List<users> Users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usercenter);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		UsersDBManager1 = new UsersDBManager(this);
		list1 = loaddata();
		ImageView1 = (ImageView) findViewById(R.id.usercenter_imageView1);
		TextView1 = (TextView) findViewById(R.id.usercenter_textView1);
		TextView2 = (TextView) findViewById(R.id.usercenter_textView2);
		Button1 = (Button) findViewById(R.id.usercenter_button1);
		Button2 = (Button) findViewById(R.id.usercenter_button2);
		//Button3 = (Button) findViewById(R.id.usercenter_button3);
		//Button4 = (Button) findViewById(R.id.usercenter_button4);
		Button5 = (Button) findViewById(R.id.usercenter_button5);

		binddata();

		/**
		 * 预定订单
		 */
		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(UsercenterActivity.this, DmanagerActivity.class);
				startActivity(intent);
			}
		});

		/**
		 * 我的喜欢
		 */
		Button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(UsercenterActivity.this, DinglikeActivity.class);
				startActivity(intent);
			}
		});

		Button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						UsercenterActivity.this);
				builder.setTitle("提示")
						.setMessage("确认退出吗?")
						.setPositiveButton("退出",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										UsersDBManager1.quit();
										finish();
									}
								});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.show();
			}
		});

	}

	public void binddata() {
		ProgressDialog1 = new ProgressDialog(this);
		ProgressDialog1.setMessage("数据加载中，请稍后...");
		ProgressDialog1.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				TextView1.setText(list1.get(0).get_user_name());
				TextView2.setText("手机号：" + list1.get(0).get_telphone());
				ImageView1.setImageBitmap(myapplication1.bitmap(list1.get(0)
						.get_avatar()));
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();
				Toast.makeText(UsercenterActivity.this, "网络不给力，无法获得活动信息!", 1)
						.show();
			}
		}
	};

	/***
	 * 读取文件流
	 * 
	 * @param page
	 * @return
	 */
	public List<users> loaddata() {

		try {
			Users = jsonusers.getjsonlastusers(myapplication1.getlocalhost()
					+ "/android/json_users/list.php?id="
					+ myapplication1.getusername());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Users;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}

}
