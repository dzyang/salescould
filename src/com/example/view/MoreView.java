package com.example.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.custom.BfdjActivity;
import com.example.custom.BfjlActivity;
import com.example.custom.KhbzActivity;
import com.example.custom.KhglActivity;
import com.example.custom.KhtxlActivity;
import com.example.fragments.GeneralFragment;
import com.example.myfood.DinglikeActivity;
import com.example.myfood.DiquActivity;
import com.example.myfood.DmanagerActivity;
import com.example.myfood.R;
import com.example.myfood.SystemActivity;
import com.example.myfood.UsercenterActivity;
import com.example.utils.UsersDBManager;
import com.example.view.DailyView.MyAdapter;
import com.example.view.DailyView.ViewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * 更多页面
 * @author ZHF
 *
 */
public class MoreView extends GeneralFragment{
	View view;
	// model
	List<Map<String, Object>> data;
    // view
    ListView lv;
    // controller
	//MyAdapter adapter = new MyAdapter(this.getActivity().getApplicationContext());
    int size = 1;
    
	private UsersDBManager UsersDBManager1;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("用户中心");
		UsersDBManager1 = new UsersDBManager(this.getActivity());
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.more, container, false);
		lv=(ListView)view.findViewById(R.id.more_menu_list);
		initData();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Log.v("MyListView4-click", (String)data.get(position).get("title"));
				if(data.get(position).get("title").equals("预定订单"))
				{
					startActivity(new Intent(getActivity(),DmanagerActivity.class));				
				}else if(data.get(position).get("title").equals("我的喜欢"))
				{
					startActivity(new Intent(getActivity(),DinglikeActivity.class));						
				}else if(data.get(position).get("title").equals("系统设置"))
				{
					startActivity(new Intent(getActivity(),SystemActivity.class));						
				}else if(data.get(position).get("title").equals("退出登录"))
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setTitle("提示")
							.setMessage("确认退出吗?")
							.setPositiveButton("退出",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											UsersDBManager1.quit();
											getActivity().finish();
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
		map.put("title", "预定订单");
		map.put("img",R.drawable.chinaz1_);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "我的喜欢");
		map.put("img", R.drawable.chinaz2_);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "系统设置");
		map.put("img", R.drawable.chinaz2_);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "退出登录");
		map.put("img",R.drawable.chinaz3_);
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
