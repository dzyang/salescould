package com.example.view;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.adapter.ImageLoader;
import com.example.fragments.GeneralFragment;
import com.example.jsonservices.jsonarticle;
import com.example.model.article;
import com.example.myfood.ArticleActivity;
import com.example.myfood.MainActivity;
import com.example.myfood.R;
import com.example.utils.StreamTool;
import com.example.utils.myapplication;
import com.example.view.DailyView.MyAdapter;
import com.example.view.DailyView.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 主页面
 * @author ZHF
 *
 */
public class HomeView extends GeneralFragment{
	private  View view;
	private  List<Map<String, Object>> data;
	private  ListView lv;
    private int size = 1;
    private View moreView;
    private Button bt;
    private ProgressBar pg;
    private Handler handler;
    private  MyAdapter adapter ;
    private int MaxDateNum;
    private myapplication myapplication1;   
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
	//Button bt1,bt2;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setTitle("最新消息");		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myapplication1 = (myapplication) this.getActivity().getApplication();
		view = inflater.inflate(R.layout.home, container, false);		
		lv=(ListView)view.findViewById(R.id.Newslist);		
		bt = (Button) view.findViewById(R.id.bt_load);
	    pg = (ProgressBar) view.findViewById(R.id.pg);	        
		//MaxDateNum = 22; // 设置最大数据条数		 
		
	    initData();
			
		 
		 handler = new Handler();		 
		 bt.setOnClickListener(new OnClickListener() {
        	 @Override
             public void onClick(View v) {
                 pg.setVisibility(View.VISIBLE);// 将进度条可见
                 bt.setVisibility(View.GONE);// 按钮不可见
                 handler.postDelayed(new Runnable() {

                     @Override
                     public void run() {
                         loadMoreDate();// 加载更多数据
                         bt.setVisibility(View.VISIBLE);
                         pg.setVisibility(View.GONE);
                         MyAdapter sAdapter = (MyAdapter)lv.getAdapter();
                         sAdapter.notifyDataSetChanged();
                     }
                 }, 2000);
             }
        });
		 
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int location,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println("触发了第"+location+"行");  
				/*Bundle arguments = new Bundle();
				arguments.putSerializable("title",(String)data.get(location).get("title"));				
				toIntent(new More1View(),arguments);*/				
				Intent Intent1 = new Intent();
				Intent1.setClass(getActivity(), ArticleActivity.class);
				Bundle arguments = new Bundle();
				arguments.putString("_id", data.get(location).get("id").toString());
				arguments.putString("_title", data.get(location).get("title").toString());
				arguments.putString("_img_url",data.get(location).get("img_url").toString());
				arguments.putString("_content",data.get(location).get("content").toString());
				arguments.putString("_add_time",data.get(location).get("add_time").toString());
				Intent1.putExtras(arguments);
				startActivity(Intent1);
				//overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
			}
			
		});  
		return  view;
	}
	
	 // 初始化绑定数据

    private void initData() {    
        if (lv == null)
        {
          return;
        }
        data = getData() ;	
        adapter = new MyAdapter(this.getActivity().getApplicationContext());
        lv.setAdapter(adapter);
      
      
    }
    
    private List<Map<String, Object>> getData() {
    	String url=myapplication1.getlocalhost()+ "/android/json_article/list.php?channel_id=1&page=0";
    	
    	try {
			List<article> articlelist=jsonarticle.getjsonlastarticles(url);			
			for (article articles : articlelist) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("title", articles.get_title());
				item.put("id", articles.get_id());
				item.put("zhaiyao", articles.get_zhaiyao());
				item.put("img_url", articles.get_img_url());
				item.put("content", articles.get_content());
				item.put("add_time", articles.get_add_time());
				list.add(item);
			}					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return list;
  	}
    
	private void loadMoreDate() {
		int count = adapter.getCount();
		String url=myapplication1.getlocalhost()+ "/android/json_article/list.php?channel_id=1&page="+count;
    	list = new ArrayList<Map<String, Object>>();
    	try {
			List<article> articlelist=jsonarticle.getjsonlastarticles(url);			
			for (article articles : articlelist) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("title", articles.get_title());
				item.put("id", articles.get_id());
				item.put("zhaiyao", articles.get_zhaiyao());
				item.put("img_url", articles.get_img_url());
				item.put("content", articles.get_content());
				item.put("add_time", articles.get_add_time());
				list.add(item);
			}					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(list.size()<5)
    	{
			 bt.setText("加载完成");
			 bt.setVisibility(View.GONE);// 按钮不可见
    	}
    	
  
	}
	

	public class ViewHolder{
		public ImageView mImageView;
		public TextView textview1;
		public TextView textview2;
		public TextView textview3;
	}
	
	public Bitmap bitmap(String url) {
		StreamTool StreamTool1 = new StreamTool();
		try {
			InputStream isInputStream = StreamTool1.getis(myapplication1.getlocalhost()+ url);
			Bitmap bitmap = BitmapFactory.decodeStream(isInputStream);
			isInputStream.close();
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (view == null) {				
				holder=new ViewHolder();  
				view = mInflater.inflate(R.layout.news, null);				
				holder.mImageView = (ImageView) view.findViewById(R.id.home_view_imageView2);
				holder.textview1 = (TextView) view.findViewById(R.id.home_view_article_textView1);
				holder.textview2 = (TextView) view.findViewById(R.id.home_view_article_textView2);
				holder.textview3 = (TextView) view.findViewById(R.id.home_view_article_textView3);
				 view.setTag(holder);  
			}else {				
				holder = (ViewHolder)view.getTag();
			}
			holder.textview1.setText(data.get(position).get("title").toString());
			holder.textview2.setText(data.get(position).get("zhaiyao").toString());
			holder.textview3.setText(data.get(position).get("add_time").toString());			
			ImageLoader mImageLoader = new ImageLoader();			
			//Bitmap bitmap = mImageLoader.getBitmapFromCache(myapplication1.getlocalhost()+ data.get(position).get("img_url").toString());
			Bitmap bitmap=bitmap(data.get(position).get("img_url").toString());
			if (bitmap != null) {
				holder.mImageView.setImageBitmap(bitmap);
			} else {
				holder.mImageView.setImageResource(R.drawable.ic_launcher);
			}
			return view;
		}
    }

}
