package com.example.myfood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.example.adapter.articleAdapter;
import com.example.control.cusmainmenu;
import com.example.control.dailymainmenu;
import com.example.jsonservices.jsonarticle;
import com.example.model.article;
import com.example.myfood.R.color;
import com.example.utils.myapplication;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private myapplication myapplication1;
	private ProgressDialog ProgressDialog1; // 加载对话框
	private ListView listview1;
	private articleAdapter adapter;
	private int page = 1;
	private long waitTime = 2000;
	private long touchTime = 0;
	private TextView textView;
	private ImageButton ImageButton1;// 语音
	private ImageButton ImageButton2;// 语音
	private List<article> list1 = null;
	private List<HashMap<String, Object>> articleslist = new ArrayList<HashMap<String, Object>>();

	private String textString = "";
	private Thread Thread1;
	private boolean havedata = true; // 来判断是否还有数据
	private EditText setedit;
	private ImageButton yuyinButton;
	private RelativeLayout rela;

	private List<article> Articles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		

		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());
		super.onCreate(savedInstanceState);
	//	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		setContentView(R.layout.activity_main);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.screen_title); 
		
		ActionBar actionBar = this.getActionBar();
		 actionBar.setCustomView(R.layout.screen_title);
		 actionBar.setDisplayShowCustomEnabled(true);
		 actionBar.setDisplayShowHomeEnabled(false);
		 actionBar.show();


		
		
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		
		
		rela = (RelativeLayout) findViewById(R.id.rlay);
		listview1 = (ListView) findViewById(R.id.mlistView1);
		textView = (TextView) findViewById(R.id.kq_dk_map_address);
		ImageButton1 = (ImageButton) findViewById(R.id.xunfeiimageButton1);
		ImageButton2 = (ImageButton) findViewById(R.id.systemimageButton1);
		final TabHost tabhost = (TabHost) findViewById(android.R.id.tabhost);
		tabhost.setup();

		TabWidget tabwidget = tabhost.getTabWidget();
		tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("促销信息")
				.setContent(R.id.main_tab1));
		tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("日常工作")
				.setContent(R.id.main_tab1_1));
		tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("商家拜访")
				.setContent(R.id.main_tab2));
		tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator("用户中心")
				.setContent(R.id.main_tab3));

		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout LinearLayout1 = (LinearLayout) getLayoutInflater()
						.inflate(R.layout.searchdialog, null);

				setedit = (EditText) LinearLayout1
						.findViewById(R.id.searchdialog_editText1);
				yuyinButton = (ImageButton) LinearLayout1
						.findViewById(R.id.searchdialog_imageButton1);
				new AlertDialog.Builder(MainActivity.this)
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
				/*yuyinButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						showDialog(1);
					}
				});*/
			}
		});

		/**
		 * 系统设置
		 */
		ImageButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SystemActivity.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		});

		/***
		 * 菜单
		 */
		tabhost.getTabWidget().getChildAt(1)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tabhost.setCurrentTab(0);

						Intent intent = new Intent();
						intent.setClass(MainActivity.this, dailymainmenu.class);
						startActivity(intent);
						overridePendingTransition(android.R.anim.slide_in_left,
								android.R.anim.slide_out_right);
						
					}
				});
		
		/***
		 * 预定
		 */
		tabhost.getTabWidget().getChildAt(2)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tabhost.setCurrentTab(0);

						Intent intent = new Intent();
						intent.setClass(MainActivity.this,cusmainmenu.class);
						startActivity(intent);
						overridePendingTransition(android.R.anim.slide_in_left,
								android.R.anim.slide_out_right);
						
					/*	Intent intent = new Intent();
						intent.setClass(MainActivity.this, DiquActivity.class);
						startActivity(intent);
						overridePendingTransition(android.R.anim.slide_in_left,
								android.R.anim.slide_out_right);*/
					}
				});
		/***
		 * 用户中心
		 */
		tabhost.getTabWidget().getChildAt(3)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tabhost.setCurrentTab(0);
						Intent intent = new Intent();
						intent.setClass(MainActivity.this,
								RegisterActivity.class);
						startActivity(intent);
						overridePendingTransition(android.R.anim.slide_in_left,
								android.R.anim.slide_out_right);
					}

				});
		if(myapplication1.ifpass())
		{
			bindarticle();
		}else
		{
			Intent Intent1 = new Intent();
			Intent1.setClass(MainActivity.this,LoginActivity.class);
			startActivity(Intent1);
		}
		
	}

	public String localhost() {
		return myapplication1.getlocalhost();
	}

	public void bindarticle() {
		threadstart();
		listview1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent Intent1 = new Intent();
				Intent1.setClass(MainActivity.this, ArticleActivity.class);
				Bundle Bundle1 = new Bundle();
				Bundle1.putString("_id", articleslist.get(arg2).get("id")
						.toString());
				Bundle1.putString("_title", articleslist.get(arg2).get("title")
						.toString());
				Bundle1.putString("_img_url",
						articleslist.get(arg2).get("img_url").toString());
				Bundle1.putString("_content",
						articleslist.get(arg2).get("content").toString());
				Bundle1.putString("_add_time",
						articleslist.get(arg2).get("add_time").toString());
				Intent1.putExtras(Bundle1);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});

		listview1.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
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
			}
		});
	}

	/***
	 * 配套使用加载读取
	 */
	public void threadstart() {
		ProgressDialog1 = new ProgressDialog(this);
		ProgressDialog1.setMessage("数据加载中，请稍后...");
		ProgressDialog1.show();
		Thread1 = new Thread() {

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
		};
		Thread1.start();
	}

	private Handler Handler1 = new Handler() {
		public void handleMessage(Message msg) {
			try {
				list1 = loaddata(page);
				bindlist(list1);//生成articleslist
				adapter = new articleAdapter(MainActivity.this, list1);//给适配器传值
				adapter.addlist(list1);//绑定
				
				listview1.setAdapter(adapter);//设置适配器
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();
				Toast.makeText(MainActivity.this, e.toString(), 1).show();
				neterror();// 网络重试
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
			} catch (Exception e) {
				e.printStackTrace();
				ProgressDialog1.dismiss();

			}
		}
	};

	/**
	 * 活动新闻绑定
	 * 
	 * @param list
	 */
	public void bindlist(List<article> list) {
		for (article articles : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("title", articles.get_title());
			item.put("id", articles.get_id());
			item.put("zhaiyao", articles.get_zhaiyao());
			item.put("img_url", articles.get_img_url());
			item.put("content", articles.get_content());
			item.put("add_time", articles.get_add_time());
			articleslist.add(item);
		}

	}

	/**
	 * 网络重试
	 */
	public void neterror() {

		final LinearLayout linearLayout = (LinearLayout) getLayoutInflater()
				.inflate(R.layout.networkerror, null);
		linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		rela.addView(linearLayout);

		linearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				rela.removeView(linearLayout);
				threadstart();
			}
		});
	}

	/***
	 * 读取文件流
	 * 
	 * @param page
	 * @return
	 */
	public List<article> loaddata(int page) {

		try {
			String URL=myapplication1.getlocalhost()
					+ "/android/json_article/list.php?channel_id=1&page="
					+ page;
			Articles = jsonarticle.getjsonlastarticles(URL);
		} catch (Exception e) {
			e.printStackTrace();

			Articles = new ArrayList<article>();
			Toast.makeText(MainActivity.this, "全部显示完毕！", 1).show();
			havedata = false;
		}
		return Articles;
	}

	/**
	 * 退出系统
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				touchTime = currentTime;
			} else {
				myapplication1.getInstance().exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

/*	*//**
	 * 语音查询
	 *//*
	@Override
	protected Dialog onCreateDialog(int id) {

		RecognizerDialog recognizerDialog = new RecognizerDialog(
				MainActivity.this, "appid=5132fe14");// 这里应该写从科大讯飞申请到的appid
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
				// Toast.makeText(MainActivity.this, textString, 1).show();
				setedit.setText(textString.substring(0, textString.length() - 1));
			}
		});
		return recognizerDialog;
	}*/

	/***
	 * 主页面查询操作一个是语音一个是文本输入
	 * 
	 * @param text
	 */
	public void searchtext(String text) {
		if ("".equals(myapplication1.getdiqu())) {
			Intent Intent1 = new Intent();
			Intent1.setClass(MainActivity.this, DiquActivity.class);
			startActivity(Intent1);
			Toast.makeText(MainActivity.this, "请先选择店铺地址", 1).show();
		} else {
			myapplication1
					.setgoodsurl("/android/json_goods/list.aspx?channel_id=2&keywords='"
							+ java.net.URLEncoder.encode(text) + "'&page=");

			Intent Intent1 = new Intent();
			Intent1.setClass(MainActivity.this, DiancaiActivity.class);
			Bundle Bundle1 = new Bundle();
			Bundle1.putString("tg", "0");
			Intent1.putExtras(Bundle1);
			startActivity(Intent1);
		}

	}

}
