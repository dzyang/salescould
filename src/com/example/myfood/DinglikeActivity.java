package com.example.myfood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jsonservices.jsongoods;
import com.example.model.goods;
import com.example.utils.dinglikeDBManager;
import com.example.utils.myapplication;

public class DinglikeActivity extends Activity {
	private myapplication myapplication1;
	private ProgressDialog ProgressDialog1; // 加载对话框
	private Thread Thread1;
	private ListView listView1;
	private SimpleAdapter adapter;
	private List<goods> list1 = null;
	private List<HashMap<String, Object>> goodslist = new ArrayList<HashMap<String, Object>>();
	private dinglikeDBManager dinglikeDBManager;
	private List<goods> goods;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dinglike);
		myapplication1 = (myapplication) getApplication();
		dinglikeDBManager = new dinglikeDBManager(DinglikeActivity.this);
		listView1 = (ListView) findViewById(R.id.dinglike_listView1);
		threadstart();
	}

	private void threadstart() {
		ProgressDialog1 = new ProgressDialog(this);
		ProgressDialog1.setMessage("数据加载中，请稍后...");
		ProgressDialog1.show();
		Thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread1.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Handler1.sendEmptyMessage(0);
			}
		});
		Thread1.start();
	}

	private Handler Handler1 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				if (loaddata().size() > 0) {
					list1 = loaddata();
					bindlist(list1);
					adapter = new SimpleAdapter(DinglikeActivity.this,
							goodslist, R.layout.item_dinglike, new String[] {
									"_title", "_sell_price" }, new int[] {
									R.id.item_dinglike_textView1,
									R.id.item_dinglike_textView2 });

					listView1.setAdapter(adapter);
					listView1.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								final int arg2, long arg3) {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(DinglikeActivity.this)
									.setNegativeButton(
											"查看详情",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													Intent Intent1 = new Intent();
													Intent1.setClass(
															DinglikeActivity.this,
															JindianActivity.class);
													Bundle Bundle1 = new Bundle();
													Bundle1.putBoolean(
															"_dinglike", true);
													Bundle1.putString("_id",
															goodslist.get(arg2)
																	.get("_id")
																	.toString());
													Bundle1.putString(
															"_channel_id",
															goodslist
																	.get(arg2)
																	.get("_channel_id")
																	.toString());
													Bundle1.putString(
															"_category_id",
															goodslist
																	.get(arg2)
																	.get("_category_id")
																	.toString());
													Bundle1.putString(
															"_title",
															goodslist
																	.get(arg2)
																	.get("_title")
																	.toString());
													Bundle1.putString(
															"_goods_no",
															goodslist
																	.get(arg2)
																	.get("_goods_no")
																	.toString());
													Bundle1.putString(
															"_stock_quantity",
															goodslist
																	.get(arg2)
																	.get("_stock_quantity")
																	.toString());
													Bundle1.putString(
															"_market_price",
															goodslist
																	.get(arg2)
																	.get("_market_price")
																	.toString());
													Bundle1.putString(
															"_sell_price",
															goodslist
																	.get(arg2)
																	.get("_sell_price")
																	.toString());
													Bundle1.putString(
															"_point",
															goodslist
																	.get(arg2)
																	.get("_point")
																	.toString());
													Bundle1.putString(
															"_link_url",
															goodslist
																	.get(arg2)
																	.get("_link_url")
																	.toString());
													Bundle1.putString(
															"_img_url",
															goodslist
																	.get(arg2)
																	.get("_img_url")
																	.toString());
													Bundle1.putString(
															"_seo_title",
															goodslist
																	.get(arg2)
																	.get("_seo_title")
																	.toString());
													Bundle1.putString(
															"_seo_keywords",
															goodslist
																	.get(arg2)
																	.get("_seo_keywords")
																	.toString());
													Bundle1.putString(
															"_seo_description",
															goodslist
																	.get(arg2)
																	.get("_seo_description")
																	.toString());
													Bundle1.putString(
															"_content",
															goodslist
																	.get(arg2)
																	.get("_content")
																	.toString());
													Bundle1.putString(
															"_sort_id",
															goodslist
																	.get(arg2)
																	.get("_sort_id")
																	.toString());
													Bundle1.putString(
															"_click",
															goodslist
																	.get(arg2)
																	.get("_click")
																	.toString());
													Bundle1.putString(
															"_is_msg",
															goodslist
																	.get(arg2)
																	.get("_is_msg")
																	.toString());
													Bundle1.putString(
															"_is_top",
															goodslist
																	.get(arg2)
																	.get("_is_top")
																	.toString());
													Bundle1.putString(
															"_is_red",
															goodslist
																	.get(arg2)
																	.get("_is_red")
																	.toString());
													Bundle1.putString(
															"_is_hot",
															goodslist
																	.get(arg2)
																	.get("_is_hot")
																	.toString());
													Bundle1.putString(
															"_is_slide",
															goodslist
																	.get(arg2)
																	.get("_is_slide")
																	.toString());
													Bundle1.putString(
															"_is_lock",
															goodslist
																	.get(arg2)
																	.get("_is_lock")
																	.toString());
													Bundle1.putString(
															"_user_id",
															goodslist
																	.get(arg2)
																	.get("_user_id")
																	.toString());
													Bundle1.putString(
															"_add_time",
															goodslist
																	.get(arg2)
																	.get("_add_time")
																	.toString());
													Bundle1.putString(
															"_digg_good",
															goodslist
																	.get(arg2)
																	.get("_digg_good")
																	.toString());
													Bundle1.putString(
															"_digg_bad",
															goodslist
																	.get(arg2)
																	.get("_digg_bad")
																	.toString());
													Intent1.putExtras(Bundle1);
													startActivity(Intent1);
													overridePendingTransition(
															R.anim.in_from_right,
															R.anim.out_to_left);
												}
											})
									.setPositiveButton(
											"取消喜欢",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub
													dinglikeDBManager.del(Integer
															.parseInt(goodslist
																	.get(arg2)
																	.get("id")
																	.toString()));
													threadstart();
												}
											}).show();
						}
					});
				} else {
					Toast.makeText(DinglikeActivity.this, "没有喜欢的商品记录哦！", 1)
							.show();
				}
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();
				Toast.makeText(DinglikeActivity.this, "网络不给力，无法获得商品介绍。", 1)
						.show();
				// neterror();//网络重试
			}
		}
	};

	/**
	 * 活动新闻绑定
	 * 
	 * @param list
	 */
	public void bindlist(List<goods> list) {
		for (goods goodss : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("_id", goodss.get_id());
			item.put("_channel_id", goodss.get_channel_id());
			item.put("_category_id", goodss.get_category_id());
			item.put("_title", goodss.get_title());
			item.put("_goods_no", goodss.get_goods_no());
			item.put("_stock_quantity", goodss.get_stock_quantity());
			item.put("_market_price", goodss.get_market_price());
			item.put("_sell_price", goodss.get_sell_price());
			item.put("_point", goodss.get_point());
			item.put("_link_url", goodss.get_link_url());
			item.put("_img_url", goodss.get_img_url());
			item.put("_seo_title", goodss.get_seo_title());
			item.put("_seo_keywords", goodss.get_seo_keywords());
			item.put("_seo_description", goodss.get_seo_description());
			item.put("_content", goodss.get_content());
			item.put("_sort_id", goodss.get_sort_id());
			item.put("_click", goodss.get_click());
			item.put("_is_msg", goodss.get_is_msg());
			item.put("_is_top", goodss.get_is_top());
			item.put("_is_red", goodss.get_is_red());
			item.put("_is_hot", goodss.get_is_hot());
			item.put("_is_slide", goodss.get_is_slide());
			item.put("_is_lock", goodss.get_is_lock());
			item.put("_user_id", goodss.get_user_id());
			item.put("_add_time", goodss.get_add_time());
			item.put("_digg_good", goodss.get_digg_good());
			item.put("_digg_bad", goodss.get_digg_bad());
			goodslist.add(item);
		}

	}

	private List<goods> loaddata() {

		try {
			goods = jsongoods.getjsonlastgoods(

			myapplication1.getlocalhost() + myapplication1.getgoodslikeurl()
					+ dinglikeDBManager.query(myapplication1.getusername()));

		} catch (Exception e) {
			e.printStackTrace();
			goods = new ArrayList<goods>();
		}
		return goods;
	}

}
