package com.example.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myfood.DiquActivity;
import com.example.myfood.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 * @author allin
 * 
 */
public class cusmainmenu extends ListActivity {


	private List<Map<String, Object>> mData;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "拜访登记");
		map.put("img",R.drawable.chinaz3_);
		map.put("bg",R.drawable.cuslogin_circle_top_n);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "拜访记录");
		map.put("img", R.drawable.chinaz3_);
		map.put("bg",R.drawable.cuslogin_circle_middle_n);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "我的客户");
		map.put("img",R.drawable.chinaz3_);
		map.put("bg",R.drawable.cuslogin_circle_middle_n);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "客户标注");
		map.put("img",R.drawable.chinaz8_);
		map.put("bg",R.drawable.cuslogin_circle_middle_n);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "客户管理");
		map.put("img",R.drawable.chinaz3_);
		map.put("bg",R.drawable.cuslogin_circle_middle_n);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "客户通迅录");
		map.put("img",R.drawable.chinaz3_);
		map.put("bg",R.drawable.cuslogin_circle_bottom_n);
		list.add(map);
		
		return list;
	}
	
	// ListView 中某项被选中后的逻辑
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Log.v("MyListView4-click", (String)mData.get(position).get("title"));
		if(mData.get(position).get("title").equals("客户标注"))
		{
			 Intent intent = new Intent();
				intent.setClass(this, DiquActivity.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		}else
		{
			showInfo();
		}
	
	}
	
	/**
	 * listview中点击按键弹出对话框
	 */
	public void showInfo(){
		new AlertDialog.Builder(this)
		.setTitle("提示")
		.setMessage("开发中")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
		
	}
	
	
	
	public final class ViewHolder{
		public ImageView img;
		public TextView title;
		//public LinearLayout linearlayout;
	}
	
	
	public class MyAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		
		
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				convertView = mInflater.inflate(R.layout.cusmainmenu, null);
				holder.img = (ImageView)convertView.findViewById(R.id.cus_menu_img);
				holder.title = (TextView)convertView.findViewById(R.id.cus_menu_title);
				if(holder.title.equals("拜访登记"))
				{
					convertView.setBackgroundResource(R.drawable.cuslogin_circle_top_n);
				}else if(holder.title.equals("客户通迅录"))
				{
					convertView.setBackgroundResource(R.drawable.cuslogin_circle_bottom_n);
				}else
				{
					convertView.setBackgroundResource(R.drawable.cuslogin_circle_middle_n);
				}
				
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String)mData.get(position).get("title"));			
		
			
			
			return convertView;
		}
		
	}
	
	
	
	
}
