package com.example.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.custom.BfdjActivity;
import com.example.custom.BfjlActivity;
import com.example.custom.KhaddActivity;
import com.example.custom.KhbzActivity;
import com.example.custom.KhglActivity;
import com.example.custom.KhtxlActivity;
import com.example.fragments.GeneralFragment;
import com.example.myfood.DiquActivity;
import com.example.myfood.R;
import com.example.view.DailyView.MyAdapter;
import com.example.view.DailyView.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * 公告页面
 * @author ZHF
 *
 */
public class CustomerView extends GeneralFragment{
	View view;
	// model
	List<Map<String, Object>> data;
    // view
    ListView lv;
    // controller	
    int size = 1;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("客户拜访");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		view = inflater.inflate(R.layout.menu3, container, false);
		lv=(ListView)view.findViewById(R.id.list);
		initData();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Log.v("MyListView4-click", (String)data.get(position).get("title"));
				if(data.get(position).get("title").equals("拜访登记"))
				{
					startActivity(new Intent(getActivity(),BfdjActivity.class));						
				}else if(data.get(position).get("title").equals("拜访记录"))
				{
					startActivity(new Intent(getActivity(),BfjlActivity.class));						
				}else if(data.get(position).get("title").equals("我的客户"))
				{
					startActivity(new Intent(getActivity(),DiquActivity.class));						
				}else	if(data.get(position).get("title").equals("客户标注"))
				{
					startActivity(new Intent(getActivity(),KhbzActivity.class));						
				}else if(data.get(position).get("title").equals("添加客户"))
				{
					startActivity(new Intent(getActivity(),KhaddActivity.class));						
				}else if(data.get(position).get("title").equals("客户通迅录"))
				{
					startActivity(new Intent(getActivity(),KhtxlActivity.class));						
				}
			}
			
		});
		return view;
		
	}
	 private void initData() {
		    
	        if (lv == null)
	        {
	          return;
	        }
	      data = getData() ;

	      
	      MyAdapter adapter = new MyAdapter(this.getActivity().getApplicationContext());
	      lv.setAdapter(adapter);
	    }
		
	 private List<Map<String, Object>> getData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "拜访登记");
			map.put("img",R.drawable.chinaz1_);
			map.put("bg",R.drawable.cuslogin_circle_top_n);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "拜访记录");
			map.put("img", R.drawable.chinaz2_);
			map.put("bg",R.drawable.cuslogin_circle_middle_n);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "我的客户");
			map.put("img",R.drawable.chinaz3_);
			map.put("bg",R.drawable.cuslogin_circle_middle_n);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("title", "添加客户");
			map.put("img",R.drawable.chinaz4_);
			map.put("bg",R.drawable.cuslogin_circle_middle_n);
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("title", "客户标注");
			map.put("img",R.drawable.chinaz5_);
			map.put("bg",R.drawable.cuslogin_circle_middle_n);
			list.add(map);
									
			map = new HashMap<String, Object>();
			map.put("title", "客户通迅录");
			map.put("img",R.drawable.chinaz6_);
			map.put("bg",R.drawable.cuslogin_circle_bottom_n);
			list.add(map);
			
			return list;
		}
	 
    public final class ViewHolder{
		public ImageView img;
		public TextView title;
	}
	
    public class MyAdapter extends BaseAdapter{

    	private LayoutInflater mInflater;
		
		
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
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
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {				
				holder=new ViewHolder();  
				convertView = mInflater.inflate(R.layout.dailymainmenu, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				
			}else {				
				holder = (ViewHolder)convertView.getTag();
			}
			holder.img.setBackgroundResource((Integer)data.get(position).get("img"));
			holder.title.setText((String)data.get(position).get("title"));
			return convertView;
		}
		
    }
	
    
}
