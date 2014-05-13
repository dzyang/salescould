package com.example.custom;

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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonusers;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;

public class KhActivity extends Activity {
	private myapplication myapplication1;
	/*private ProgressDialog ProgressDialog1; // 加载对话框
	private SubmitDBManager submitDBManager1;
	private dingnumDBManager dingnumDBManager1;
	private GoodsDBManager goodsDBManager1;
	private Button button1;// 确认预定
	private List<users> Users;*/
	private Button button1;
	private Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kh);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		/*
		binddata();*/
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(KhActivity.this, MainFragment.class);
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
			}
		});
	}

	/**
	 * 更新订单
	 *//*
	public void updateding() {

		if (AEditText1.getText().toString().equals("")) {
			Toast.makeText(this, "客户信息不能为空", 1).show();
		} else if (AEditText2.getText().toString().equals("")) {
			Toast.makeText(this, "联系方式不能为空", 1).show();
		}  else if (BEditText2.getText().toString().equals("")) {
			Toast.makeText(this, "到店时间不能为空", 1).show();
		} else {
			new AlertDialog.Builder(SubmitActivity.this)
					.setTitle("提示")
					.setMessage("确认提交吗?")
					.setPositiveButton("取消", null)
					.setNegativeButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									// 保存本地数据库
									step1();
									// 提交到服务器
									step2();
									// 清空购物车，跳转到地区介绍页面
									step3();
								}

							}).show();

		}
	}

	*//**
	 * 提交订单第一步，将订单存入sqlite数据库中
	 *//*
	private void step1() {
		ProgressDialog1 = new ProgressDialog(this);
		ProgressDialog1.setMessage("提交操作中...");
		ProgressDialog1.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					handler1.sendEmptyMessage(0);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	*//**
	 * 提交订单第一步，将订单存入sqlite数据库中
	 *//*
	private void step2() {
		ProgressDialog1 = new ProgressDialog(this);
		ProgressDialog1.setMessage("提交操作中...");
		ProgressDialog1.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					handler2.sendEmptyMessage(0);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	*//**
	 * 提交订单第一步，将订单存入sqlite数据库中
	 *//*
	private void step3() {
		ProgressDialog1 = new ProgressDialog(this);
		ProgressDialog1.setMessage("提交操作中...");
		ProgressDialog1.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					handler3.sendEmptyMessage(0);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				*//**
				 * 提交订单
				 *//*
				submit modelSubmit = new submit(SubmitActivity.this);
				modelSubmit.set_username(AEditText1.getText().toString());
				modelSubmit.set_tel(AEditText2.getText().toString());
				modelSubmit.set_renshu(Integer.valueOf(AEditText3.getText()
						.toString()));
				modelSubmit.set_cantingname(BEditText1.getText().toString());
				modelSubmit.set_daodiantime(BEditText2.getText().toString());
				if ("".equals(DEditText1.getText().toString())) {
					modelSubmit.set_contract("无附加要求");
				} else {
					modelSubmit.set_contract(DEditText1.getText().toString());
				}
				modelSubmit.set_fukuan(false);
				modelSubmit.set_queding(true);
				modelSubmit.set_totalmoney(Double.valueOf(CEditText2.getText()
						.toString()));
				modelSubmit.set_submitnum(myapplication1.get_dingdanstring());
				submitDBManager1.updateding(modelSubmit);
				*//**
				 * 插入对应订单所点菜品
				 *//*
				dingnumDBManager1
						.insertdingnum(myapplication1.get_dingdanstring(),
								loaddata().get(0).get_user_name());
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				ProgressDialog1.dismiss();
				e.printStackTrace();
			}
		}
	};

	private Handler handler2 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				int result;
				String target = myapplication1.getlocalhost()+ "/android/json_submit/add.php";
				HttpPost httprequest = new HttpPost(target);
				List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
				paramsList.add(new BasicNameValuePair("_submitnum",myapplication1.get_dingdanstring()));
				paramsList.add(new BasicNameValuePair("_username", AEditText1.getText().toString()));
				paramsList.add(new BasicNameValuePair("_tel", AEditText2.getText().toString()));
				paramsList.add(new BasicNameValuePair("_detail", myapplication1.gettotalgoods_id_num()));//人数改为IDS和单价
				paramsList.add(new BasicNameValuePair("_cantingname",BEditText1.getText().toString()));
				paramsList.add(new BasicNameValuePair("_daodiantime",BEditText2.getText().toString()));
				paramsList.add(new BasicNameValuePair("_contract", DEditText1.getText().toString()));
				paramsList.add(new BasicNameValuePair("_fukuan", "false"));
				paramsList.add(new BasicNameValuePair("_queding", "false"));
				paramsList.add(new BasicNameValuePair("_totalmoney", CEditText2.getText().toString()));
				
				String submitnum=myapplication1.get_dingdanstring();
				String username=AEditText1.getText().toString();
				String tel=AEditText2.getText().toString();
				//String rensu=AEditText3.getText().toString();
				String cantingname=BEditText1.getText().toString();
				String daodiantime=BEditText2.getText().toString();
				String contract=DEditText1.getText().toString();
				String fukuan="false";
				String queding="false";
				String totalmoney=CEditText2.getText().toString();
				
				try {
					httprequest.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
					HttpClient HttpClient1 = new DefaultHttpClient();
					HttpResponse httpResponse = HttpClient1.execute(httprequest);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						result = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity()));
					} else {
						result = 0;
					}
					// 针对这里不能用字符串来判断，因为字符串判断是失效的，必须用数字来进行条件判断读取返回结果达到想要的页面跳转
					if (result == 1) {
						Toast.makeText(SubmitActivity.this, "提交成功", 1).show();
					}

				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				ProgressDialog1.dismiss();
				e.printStackTrace();
			}
		}
	};

	private Handler handler3 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				myapplication1.settotalgoods("0");
				goodsDBManager1.alldelete();
				Toast.makeText(SubmitActivity.this, "您的订单已经提交，客服会尽快与您取得联系!", 1)
						.show();

				Intent intent = new Intent();
				intent.setClass(SubmitActivity.this, DiquActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	*//**
	 * 初始设置
	 *//*
	public void binddata() {
		try {
			if (!myapplication1.ifpass()) {
				Intent Intent1 = new Intent();
				Intent1.setClass(SubmitActivity.this, RegisterActivity.class);
				startActivity(Intent1);
				finish();
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			} else {
				AEditText1.setText(loaddata().get(0).get_user_name());
				AEditText2.setText(loaddata().get(0).get_telphone());
				//AEditText3.setText("2");
				BEditText1.setText(myapplication1.getdiqu());
				BEditText2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						*//***
						 * 设置到店时间
						 *//*
						new DatePickerDialog(SubmitActivity.this, dateListener,
								calendar.get(Calendar.YEAR), calendar
										.get(Calendar.MONTH), calendar
										.get(Calendar.DAY_OF_MONTH)).show();

					}
				});
				CEditText1.setText(myapplication1.gettotalgoods());
				CEditText2.setText(myapplication1.gettotalmoney());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Intent Intent1 = new Intent();
			Intent1.setClass(SubmitActivity.this, RegisterActivity.class);
			startActivity(Intent1);
			finish();
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		}
	}

	*//***
	 * 日期选择监听事件，注意：与其配套的有 private Calendar calendar; calendar =
	 * Calendar.getInstance(); private String selectdate = null; 调用方法 new
	 * DatePickerDialog(SubmitActivity.this, dateListener,
	 * calendar.get(Calendar.YEAR), calendar .get(Calendar.MONTH), calendar
	 * .get(Calendar.DAY_OF_MONTH)).show();
	 *//*
	DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month,
				int dayOfMonth) {
			// Calendar月份是从0开始,所以month要加1

			selectdate = year + "-" + (month + 1) + "-" + dayOfMonth;
			new TimePickerDialog(SubmitActivity.this, timeListener,
					calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
					false).show();

		};
	};

	TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			selectdate += " " + hourOfDay + ":" + minute + ":00";
			BEditText2.setText(selectdate);
		};
	};

	*//***
	 * 日期选择监听事件
	 * 
	 * @return
	 *//*

	*//***
	 * 读取文件流
	 * 
	 * @param page
	 * @return
	 *//*
	public List<users> loaddata() {

		try {
			Users = jsonusers.getjsonlastusers(myapplication1.getlocalhost()+ "/android/json_users/list.php?id="	+ myapplication1.getusername());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Users;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			new AlertDialog.Builder(SubmitActivity.this)
					.setTitle("是否取消")
					.setMessage("你确定要取消订单吗?")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("取消", null)
					.setNegativeButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// 数据获取 ,顺序不要改
									myapplication1.deleteding(myapplication1
											.get_dingdanstring());
									myapplication1.set_dingdansring("");
									finish();
									overridePendingTransition(
											R.anim.in_from_right,
											R.anim.out_to_left);
								}
							}).show();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}*/
}
