package com.example.myfood;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.GoodsDBManager;
import com.example.utils.StreamTool;
import com.example.utils.myapplication;

/**
 * 
 * 顾客信息
 * @author ydz
 *
 */
public class CantingActivity extends Activity {
	private myapplication myapplication1;
	private GoodsDBManager goodsDBManager1;
	private ImageView ImageView1; // 图像
	private TextView TextView1; // 店名
	private TextView TextView2; // 地址
	private Button Button1; // 预订餐厅
	private Button Button2; // 餐厅简介
	//private Button Button3; // 经典菜品
	private Button Button4; // 地图位置
	private Button Button5; // 热线电话
	private Bundle Bundle1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canting);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		goodsDBManager1 = new GoodsDBManager(CantingActivity.this);
		ImageView1 = (ImageView) findViewById(R.id.imageView1);
		TextView1 = (TextView) findViewById(R.id.textView1);
		TextView2 = (TextView) findViewById(R.id.kq_dk_map_address);
		Button1 = (Button) findViewById(R.id.button1);
		Button2 = (Button) findViewById(R.id.button2);
		//Button3 = (Button) findViewById(R.id.button3);
		Button4 = (Button) findViewById(R.id.button4);
		Button5 = (Button) findViewById(R.id.button5);

		Bundle1 = CantingActivity.this.getIntent().getExtras();
		ImageView1.setImageBitmap(bitmap(Bundle1.getString("img_url")
				.toString()));
		TextView1.setText(Bundle1.getString("title"));
		TextView2.setText(Bundle1.getString("seo_title"));
		try {
			String[] spStr = Bundle1.getString("seo_keywords").toString()
					.split(",");
			Button5.setText("联系电话：" + spStr[0]);
		} catch (Exception e) {
			Button5.setText("联系电话："
					+ Bundle1.getString("tel").toString());
			e.printStackTrace();

		}

		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(CantingActivity.this, DiancaiActivity.class);
				Bundle Bundle1 = new Bundle();
				Bundle1.putString("tg", "1");
				Intent1.putExtras(Bundle1);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});

		/***
		 * 餐厅简介
		 */
		Button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(CantingActivity.this,
						CantingjianjieActivity.class);
				Bundle bundle_content = new Bundle();
				bundle_content.putString("content",
						Bundle1.getString("content"));
				Intent1.putExtras(bundle_content);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});

		/**
		 * 经典菜品
		 */
		/*Button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(CantingActivity.this, DiancaiActivity.class);
				Bundle Bundle1 = new Bundle();
				Bundle1.putString("tg", "0");
				Intent1.putExtras(Bundle1);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});*/

		Button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qdmap();
			}
		});

		Button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calltel(Bundle1.getString("tel").toString());
			}
		});

	}

	/**
	 * 读取图片
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap bitmap(String url) {
		StreamTool StreamTool1 = new StreamTool();
		try {
			InputStream isInputStream = StreamTool1.getis(myapplication1
					.getlocalhost() + url);
			Bitmap bitmap = BitmapFactory.decodeStream(isInputStream);
			isInputStream.close();
			return bitmap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 打电话
	 * 
	 * @param tel
	 */
	public void calltel(String tel) {
		try {
			final String[] spStr = tel.split(",");
			new AlertDialog.Builder(this).setItems(spStr,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(
									"android.intent.action.CALL", Uri
											.parse("tel:" + spStr[which]));
							startActivity(intent);
						}
					}).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动地图
	 */
	private void qdmap() {
		ArrayList<String> arrayList = new ArrayList<String>();

		if (!"".equals(Bundle1.getString("link_url").toString())) {
			arrayList.add(Bundle1.getString("link_url").toString() + ","
					+ Bundle1.getString("title").toString() + ","
					+ Bundle1.getString("seo_title").toString());

			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			intent.setClass(CantingActivity.this, MapActivity.class);

			bundle.putStringArrayList("map", arrayList);
			intent.putExtras(bundle);
			startActivity(intent);
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		} else {
			Toast.makeText(CantingActivity.this, "似乎没有设置地图位置", 1).show();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			if (goodsDBManager1.query().size() > 0) {
				new AlertDialog.Builder(CantingActivity.this)
						.setTitle("提示")
						.setMessage("您的购物车有订购的商品，退出将清空购物车，是否确定")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setPositiveButton("取消", null)
						.setNegativeButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// 数据获取 ,顺序不要改

										myapplication1.settotalgoods("0");
										goodsDBManager1.alldelete();
										finish();
										overridePendingTransition(
												R.anim.in_from_right,
												R.anim.out_to_left);
									}
								}).show();
				return true;
			} else {
				finish();
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);

	}

	/*
	 * @Override public void onBackPressed() { super.onBackPressed();
	 * overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	 * finish(); }
	 */

}
