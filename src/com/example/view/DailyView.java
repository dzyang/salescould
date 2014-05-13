package com.example.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.control.dailymainmenu.MyAdapter;
import com.example.control.dailymainmenu.ViewHolder;
import com.example.custom.BfdjActivity;
import com.example.custom.BfjlActivity;
import com.example.custom.KhbzActivity;
import com.example.custom.KhglActivity;
import com.example.custom.KhtxlActivity;
import com.example.daily.DailyFeeActivity;
import com.example.daily.DailyKqActivity;
import com.example.daily.DailyReortActivity;
import com.example.daily.DailyTaskActivity;
import com.example.fragments.GeneralFragment;
import com.example.myfood.DiquActivity;
import com.example.myfood.R;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * 订单页面
 * @author ZHF
 *
 */
public class DailyView extends GeneralFragment{
	View view;
	// model
	List<Map<String, Object>> data;
    // view
    ListView lv;
    // controller
	//MyAdapter adapter = new MyAdapter(this.getActivity().getApplicationContext());
    int size = 1;
    
 
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("日常工作");
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.menu2, container, false);
		lv=(ListView)view.findViewById(R.id.list);
		initData();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Log.v("MyListView4-click", (String)data.get(position).get("title"));
				if(data.get(position).get("title").equals("工作日报"))
				{
					startActivity(new Intent(getActivity(),DailyReortActivity.class));						
				}else if(data.get(position).get("title").equals("考勤管理"))
				{
					startActivity(new Intent(getActivity(),DailyKqActivity.class));						
				}else if(data.get(position).get("title").equals("任务交办"))
				{
					startActivity(new Intent(getActivity(),DailyTaskActivity.class));						
				}else	if(data.get(position).get("title").equals("费用申请"))
				{
					startActivity(new Intent(getActivity(),DailyFeeActivity.class));						
				}
			}
			
		});
		return view;
	}
	
	 // 初始化绑定数据

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
		map.put("title", "工作日报");
		map.put("img",R.drawable.chinaz1_);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "考勤管理");
		map.put("img", R.drawable.chinaz2_);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "任务交办");
		map.put("img",R.drawable.chinaz3_);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "费用申请");
		map.put("img",R.drawable.chinaz4_);
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
