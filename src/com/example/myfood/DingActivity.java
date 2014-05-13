package com.example.myfood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.dingAdapter;
import com.example.model.goods;
import com.example.utils.GoodsDBManager;
import com.example.utils.myapplication;

public class DingActivity extends Activity {
	public myapplication myapplication1;
	public GoodsDBManager GoodsDBManager1;
	public dingAdapter adapter;
	public TextView TextView1;
	public TextView TextView2;
	public TextView TextView3;
	public Button Button1;
	public ListView ListView1;
	public List<goods> list1;
	public ArrayList<Map<String, String>> list;
	private int pos; // 滚动位置记录

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ding);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		GoodsDBManager1 = new GoodsDBManager(this);
		TextView1 = (TextView) findViewById(R.id.activity_ding_textView1);
		TextView2 = (TextView) findViewById(R.id.activity_ding_textView2);
		TextView3 = (TextView) findViewById(R.id.activity_ding_textView3);
		Button1 = (Button) findViewById(R.id.activity_ding_button_submit);
		ListView1 = (ListView) findViewById(R.id.activity_ding_listView1);
		TextView1.setText("请确认要订购的商品");
		TextView2.setText("特别提示：订购的商品12小时内就会送达,请安排收货");
		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myapplication1.settotalmoney(String.valueOf(GoodsDBManager1
						.totalnum())); // 总金额
				myapplication1.settotalgoods(String.valueOf(list1.size())); // 点菜数量
				if("".equals(myapplication1.get_dingdanstring())){
					Random random = new Random();
				myapplication1.set_dingdansring(String.valueOf(random.nextInt(9999)));
				myapplication1.createding(myapplication1.get_dingdanstring());
				}
				Intent Intent1 = new Intent();
				//还需要把LIST传送进去
				String ids="";
				int i=0;
				for (goods dt_goods1 : list1) 
				{
					//goods g=(goods)list.get(i);
					System.out.println("----------");
					int id=dt_goods1.get_id();
					int buynumber=dt_goods1.get_buynumber();
					System.out.println("----------");
					ids=ids+id+"_"+buynumber;					
					if(i<list.size()-1)
					{
						ids=ids+"|";
					}
					i++;
				}
				myapplication1.settotalgoods_id_num(ids);
				Intent1.setClass(DingActivity.this, SubmitActivity.class);
				startActivity(Intent1);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});
		query();
	}

	public String localhost() {
		return myapplication1.getlocalhost();
	}

	public void query() {

		TextView3.setText("总计：" + GoodsDBManager1.totalnum() + "元");

		list1 = GoodsDBManager1.query();
		list = new ArrayList<Map<String, String>>();
		for (goods dt_goods1 : list1) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("_id", String.valueOf(dt_goods1.get_id()));
			map.put("_channel_id", String.valueOf(dt_goods1.get_channel_id()));
			map.put("_category_id", String.valueOf(dt_goods1.get_category_id()));
			map.put("_title", dt_goods1.get_title());
			map.put("_goods_no", dt_goods1.get_goods_no());
			map.put("_stock_quantity",
					String.valueOf(dt_goods1.get_stock_quantity()));
			map.put("_market_price", dt_goods1.get_market_price());
			map.put("_sell_price", dt_goods1.get_sell_price());
			map.put("_point", String.valueOf(dt_goods1.get_point()));
			map.put("_link_url", dt_goods1.get_link_url());
			map.put("_img_url", dt_goods1.get_img_url());
			map.put("_seo_title", dt_goods1.get_seo_title());
			map.put("_seo_keywords", dt_goods1.get_seo_keywords());
			map.put("_seo_description", dt_goods1.get_seo_description());
			map.put("_content", dt_goods1.get_content());
			map.put("_sort_id", String.valueOf(dt_goods1.get_sort_id()));
			map.put("_click", String.valueOf(dt_goods1.get_click()));
			map.put("_is_msg", String.valueOf(dt_goods1.get_is_msg()));
			map.put("_is_top", String.valueOf(dt_goods1.get_is_top()));
			map.put("_is_red", String.valueOf(dt_goods1.get_is_red()));
			map.put("_is_hot", String.valueOf(dt_goods1.get_is_hot()));
			map.put("_is_slide", String.valueOf(dt_goods1.get_is_slide()));
			map.put("_is_lock", String.valueOf(dt_goods1.get_is_lock()));
			map.put("_user_id", String.valueOf(dt_goods1.get_user_id()));
			map.put("_add_time", String.valueOf(dt_goods1.get_add_time()));
			map.put("_digg_good", String.valueOf(dt_goods1.get_digg_good()));
			map.put("_digg_bad", String.valueOf(dt_goods1.get_digg_bad()));
			map.put("_buynumber", String.valueOf(dt_goods1.get_buynumber()));
			map.put("_user", dt_goods1.get_user());
			list.add(map);
		}
		adapter = new dingAdapter(this, list, R.layout.item_ding,
				new String[] {}, new int[] {});

		ListView1.setAdapter(adapter);
		ListView1.setSelection(pos); // 恢复到之前的滚动位置
		ListView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Intent Intent1 = new Intent();
				Intent1.setClass(DingActivity.this, JindianActivity.class);
				Bundle Bundle1 = new Bundle();
				Bundle1.putString("_id",String.valueOf(list1.get(arg2).get_id()));
				Bundle1.putString("_title", list1.get(arg2).get_title());
				Bundle1.putString("_sell_price", list1.get(arg2)
						.get_sell_price());
				Bundle1.putString("_content", list1.get(arg2).get_content());
				Intent1.putExtras(Bundle1);
				startActivity(Intent1);
			}
		});

		ListView1.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				// 不滚动时保存当前滚动到的位置
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					pos = ListView1.getLastVisiblePosition();
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

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(0x717);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}

}
