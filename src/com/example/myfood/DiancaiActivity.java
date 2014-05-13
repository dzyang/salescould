package com.example.myfood;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.adapter.goodsAdapter;
import com.example.control.MyListView;
import com.example.control.MyListView.OnRefreshListener;
import com.example.jsonservices.jsoncategory;
import com.example.jsonservices.jsongoods;
import com.example.model.category;
import com.example.model.goods;
import com.example.utils.GoodsDBManager;
import com.example.utils.StreamTool;
import com.example.utils.dinglikeDBManager;
import com.example.utils.myapplication;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

public class DiancaiActivity extends Activity {
	final int code = 0x717;

	private myapplication myapplication1;
	private GoodsDBManager GoodsDBManager1;
	private dinglikeDBManager dinglikeDBManager;
	private ProgressDialog ProgressDialog1; // 加载对话框
	private Bundle Bundle1;// 传值
	private goodsAdapter adapter; // 主体listview的baseadapter
	private SimpleAdapter dqadapter; // 地区adapter
	private List<goods> list1 = null;
	private List<HashMap<String, Object>> goodslist = new ArrayList<HashMap<String, Object>>();// 实际用到的listview绑定
	private List<HashMap<String, Object>> dqlist = null;// 地区用到的绑定
	public int page = 1;// 默认页数
	private MyListView MyListView1;
	private Button Button1;// 全部
	private Button Button2;// 分类
	private Button Button3;// 搜索
	private Button Button4;// 购物车
	private Button Button5;// 喜欢
	private Thread Thread1;
	private boolean havedata = true; // 来判断是否还有数据
	private EditText setedit;
	private ImageButton yuyinButton;
	private String textString = "";

	private List<category> dqcategory;
	List<goods> goodss;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diancai);
		Bundle1 = this.getIntent().getExtras();
		myapplication1 = (myapplication) getApplication(); // 全局变量必须得写
		myapplication1.getInstance().addActivity(this);
		GoodsDBManager1 = new GoodsDBManager(this);
		dinglikeDBManager = new dinglikeDBManager(this);
		MyListView1 = (MyListView) findViewById(R.id.dcmyListView1);
		Button1 = (Button) findViewById(R.id.dcbutton1);
		Button2 = (Button) findViewById(R.id.dcbutton2);
		Button3 = (Button) findViewById(R.id.dcbutton3);
		Button4 = (Button) findViewById(R.id.dcbutton4);
		Button5 = (Button) findViewById(R.id.dcbutton5);
		binddq(); // 地区
		bindgoods(); // 几个按钮的控制
		threadstart(); // listview刷新
		
		MyListView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(DiancaiActivity.this, JindianActivity.class);
				Bundle Bundle1 = new Bundle();
				Bundle1.putBoolean("_dinglike", false);
				Bundle1.putString("_id", goodslist.get(arg2 - 1).get("_id")
						.toString());
				Bundle1.putString("_channel_id",
						goodslist.get(arg2 - 1).get("_channel_id").toString());
				Bundle1.putString("_category_id",
						goodslist.get(arg2 - 1).get("_category_id").toString());
				Bundle1.putString("_title",
						goodslist.get(arg2 - 1).get("_title").toString());
				Bundle1.putString("_goods_no",
						goodslist.get(arg2 - 1).get("_goods_no").toString());
				Bundle1.putString("_stock_quantity", goodslist.get(arg2 - 1)
						.get("_stock_quantity").toString());
				Bundle1.putString("_market_price",
						goodslist.get(arg2 - 1).get("_market_price").toString());
				Bundle1.putString("_sell_price",
						goodslist.get(arg2 - 1).get("_sell_price").toString());
				Bundle1.putString("_point",
						goodslist.get(arg2 - 1).get("_point").toString());
				Bundle1.putString("_link_url",
						goodslist.get(arg2 - 1).get("_link_url").toString());
				Bundle1.putString("_img_url",
						goodslist.get(arg2 - 1).get("_img_url").toString());
				Bundle1.putString("_seo_title",
						goodslist.get(arg2 - 1).get("_seo_title").toString());
				Bundle1.putString("_seo_keywords",
						goodslist.get(arg2 - 1).get("_seo_keywords").toString());
				Bundle1.putString("_seo_description", goodslist.get(arg2 - 1)
						.get("_seo_description").toString());
				Bundle1.putString("_content",
						goodslist.get(arg2 - 1).get("_content").toString());
				Bundle1.putString("_sort_id",
						goodslist.get(arg2 - 1).get("_sort_id").toString());
				Bundle1.putString("_click",
						goodslist.get(arg2 - 1).get("_click").toString());
				Bundle1.putString("_is_msg",
						goodslist.get(arg2 - 1).get("_is_msg").toString());
				Bundle1.putString("_is_top",
						goodslist.get(arg2 - 1).get("_is_top").toString());
				Bundle1.putString("_is_red",
						goodslist.get(arg2 - 1).get("_is_red").toString());
				Bundle1.putString("_is_hot",
						goodslist.get(arg2 - 1).get("_is_hot").toString());
				Bundle1.putString("_is_slide",
						goodslist.get(arg2 - 1).get("_is_slide").toString());
				Bundle1.putString("_is_lock",
						goodslist.get(arg2 - 1).get("_is_lock").toString());
				Bundle1.putString("_user_id",
						goodslist.get(arg2 - 1).get("_user_id").toString());
				Bundle1.putString("_add_time",
						goodslist.get(arg2 - 1).get("_add_time").toString());
				Bundle1.putString("_digg_good",
						goodslist.get(arg2 - 1).get("_digg_good").toString());
				Bundle1.putString("_digg_bad",
						goodslist.get(arg2 - 1).get("_digg_bad").toString());
				Intent1.putExtras(Bundle1);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});

		MyListView1.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				// 当不滚动时
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					// 判断是否滚动到底部
					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						if (havedata) {
							threadmore();
						}
					}
				}
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_FLING:
					adapter.setFlagBusy(true);
					break;
				case OnScrollListener.SCROLL_STATE_IDLE:
					adapter.setFlagBusy(false);
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					adapter.setFlagBusy(false);
					break;
				default:
					break;
				}
				adapter.notifyDataSetChanged();
				ProgressDialog1.dismiss();
			}
		});

		MyListView1.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				if (havedata) {
					threadmore();
				}
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {

						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						ProgressDialog1.dismiss();
						MyListView1.onRefreshComplete();

					}

				}.execute();
			}

		});
	}

	/**
	 * 将服务器地址传递到自定义baseadapter，因为不可以在自定义baseadapter中直接调用全局application
	 * 
	 * @return
	 */
	public String localhost() {
		return myapplication1.getlocalhost();
	}

	/**
	 * 这个地方用于几个按钮的主要操作
	 */
	public void bindgoods() {

		// 全部菜品显示
		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myapplication1
						.setgoodsurl("/android/json_goods/list.php?channel_id=2&page=");
				page = 1;
				havedata = true;
				threadstart();
			}
		});

		// 分类显示
		Button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder mydialog = new AlertDialog.Builder(DiancaiActivity.this);
				mydialog.setTitle("分类选择");
				mydialog.setAdapter(dqadapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method
								// stub

								try {
									String URL="/android/json_goods/list.php?channel_id=2&category_id="
											+ dqlist.get(which)
											.get("id")
											.toString()
									+ "&page=";
									
									myapplication1	.setgoodsurl(URL);
									
									page = 1;
									threadstart();

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
				mydialog.show();
			}
		});

		// 如果从订餐页面传过来的是预订餐厅的按钮，则直接显示跳过，如果是点击的经典菜品则没有跳过选项
		if (("1".equals(Bundle1.getString("tg").toString()))
				&& (Integer.parseInt(GoodsDBManager1.totalcount()) < 1)) {
			Button4.setText("跳过");
			Button4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Random random = new Random();
					myapplication1.set_dingdansring(String.valueOf(random
							.nextInt(9999)));
					myapplication1.createding(myapplication1
							.get_dingdanstring());
					Intent Intent1 = new Intent();
					Intent1.setClass(DiancaiActivity.this, SubmitActivity.class);
					startActivityForResult(Intent1, code);
					overridePendingTransition(R.anim.in_from_right,
							R.anim.out_to_left);
				}
			});
		} else {
			Button4.setText("订购单：" + GoodsDBManager1.totalcount());
			Button4.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent Intent1 = new Intent();
					Intent1.setClass(DiancaiActivity.this, DingActivity.class);
					startActivityForResult(Intent1, code);
					overridePendingTransition(R.anim.in_from_right,
							R.anim.out_to_left);
				}
			});
		}

		// 搜索按钮事件
		Button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout LinearLayout1 = (LinearLayout) getLayoutInflater()
						.inflate(R.layout.searchdialog, null);

				setedit = (EditText) LinearLayout1
						.findViewById(R.id.searchdialog_editText1);
				yuyinButton = (ImageButton) LinearLayout1
						.findViewById(R.id.searchdialog_imageButton1);
				new AlertDialog.Builder(DiancaiActivity.this)
						.setTitle("请输入查询关键字")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(LinearLayout1)
						.setPositiveButton("取消", null)
						.setNegativeButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// 数据获取
										searchtext(setedit.getText().toString());
									}
								}).show();
				yuyinButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						showDialog(1);
					}
				});
			}
		});

		/**
		 * 显示喜欢
		 */
		Button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dinglikeDBManager.havadata()) {
					havedata = true;
					page = 1;
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								handlerlike.sendEmptyMessage(0);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).start();
				} else {
					Toast.makeText(DiancaiActivity.this, "暂无喜欢的", 1).show();
				}
			}
		});

	}

	private Handler handlerlike = new Handler() {

		public void handleMessage(Message msg) {
			try {

				myapplication1
						.setgoodsurl("/android/json_goods/list.php?channel_id=2&id="
								+ dinglikeDBManager.query(myapplication1
										.getusername()) + "&page=");
				threadstart();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	};

	/***
	 * 返回地区列表
	 */
	private void binddq() {
		try {
			String URL=myapplication1.getlocalhost()+ "/android/json_category/list.php?channel_id=2&call_index=cd&page="+ page;
			dqcategory = jsoncategory.getjsonlastcategory(URL);
			dqlist = new ArrayList<HashMap<String, Object>>();
			for (category dqcategorys : dqcategory) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("id", dqcategorys.get_id());
				item.put("title", dqcategorys.get_title());
				dqlist.add(item);
			}
			dqadapter = new SimpleAdapter(DiancaiActivity.this, dqlist,
					R.layout.alertdialog_dq, new String[] { "title", "id" },
					new int[] { R.id.alertdialog_dq_textView1,
							R.id.alertdialog_dq_textView2 });

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 配套使用加载读取
	 */
	public void threadstart() {
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

				list1 = loaddata(page);
				if (list1.size() > 0) {
					bindlist(list1);
					adapter = new goodsAdapter(DiancaiActivity.this, list1);
					adapter.addlist(list1);
					MyListView1.setAdapter(adapter);
					ProgressDialog1.dismiss();
				} else {
					ProgressDialog1.dismiss();
					Toast.makeText(DiancaiActivity.this, "暂时没有对应产品介绍哦", 1)
							.show();
				}

			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();

			}
		}
	};

	/***
	 * 加载更多
	 */
	public void threadmore() {
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
				Handler2.sendEmptyMessage(0);
			}
		});
		Thread1.start();
	}

	private Handler Handler2 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				list1 = loaddata(page += 1);
				bindlist(list1);
				adapter.addlist(list1);
				adapter.notifyDataSetChanged();
				ProgressDialog1.dismiss();
				Button4.setText("购物车：" + GoodsDBManager1.totalcount());
			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();

			}
		}
	};

	/***
	 * 用来更新点菜的按钮状态
	 */
	public void reloadadapter() {

		Thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Handler3.sendEmptyMessage(0);
			}
		});
		Thread1.start();
	}

	private Handler Handler3 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				ProgressDialog1.dismiss();
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();
			}
		}
	};

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

	public List<goods> loaddata(int page) {

		try {
			goodss = jsongoods	.getjsonlastgoods(	myapplication1.getlocalhost()+ myapplication1.getgoodsurl() + page);

		} catch (Exception e) {
			e.printStackTrace();

			ProgressDialog1.dismiss();
			goodss = new ArrayList<goods>();
			Toast.makeText(DiancaiActivity.this, "全部显示完毕！", 1).show();
			havedata = false;
		}
		return goodss;
	}

	/***
	 * 目前下面这个操作没有用到 保存图片到本地sd卡
	 * 
	 * @param inSream
	 * @param file
	 * @throws Exception
	 */
	public void writeAsFile(String url, File file) throws Exception {
		StreamTool StreamTool1 = new StreamTool();
		InputStream isInputStream = StreamTool1.getis(myapplication1
				.getlocalhost() + url);
		FileOutputStream outStream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = isInputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		isInputStream.close();
	}

	protected Dialog onCreateDialog(int id) {

		RecognizerDialog recognizerDialog = new RecognizerDialog(DiancaiActivity.this, "appid=5132fe14");// 这里应该写从科大讯飞申请到的appid
		recognizerDialog.setEngine("sms", null, null);
		recognizerDialog.setListener(new RecognizerDialogListener() {
			@Override
			public void onResults(ArrayList<RecognizerResult> results,
					boolean arg1) {
				for (int i = 0; i < results.size(); i++) {
					textString += results.get(i).text;
				}
			}

			@Override
			public void onEnd(SpeechError arg0) {
				setedit.setText(textString.substring(0, textString.length() - 1));
			}
		});
		return recognizerDialog;
	}

	/***
	 * 主页面查询操作一个是语音一个是文本输入
	 * 
	 * @param text
	 */
	public void searchtext(String text) {
		havedata = true;
		page = 1;
		myapplication1	.setgoodsurl("/android/json_goods/list.php?channel_id=2&keywords='"+ java.net.URLEncoder.encode(text) + "'&page=");
		threadstart();

	}

	/***
	 * 这个地方的操作是用于当点击购物车按钮后来到购物车页面，操作了，点返回按钮时告诉点菜页面进行页面的列表刷新
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == code && resultCode == code) {
			bindgoods();
			reloadadapter();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		myapplication1.setgoodsurl("/android/json_goods/list.php?channel_id=2&page=");
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}
}
