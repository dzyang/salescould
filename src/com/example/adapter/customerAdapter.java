package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.category;
import com.example.model.customer;
import com.example.myfood.DiquActivity;
import com.example.myfood.R;
import com.example.myfood.StarActivity;

/***
 * 自定义baseadapter
 * 
 * @author Administrator
 * 
 */
public class customerAdapter extends BaseAdapter {
	// 自定义图片Adapter以内部类形式存在于MainActivity中，方便访问MainActivity中的各个变量，特别是imgs数组
	private DiquActivity context;// 用于接收传递过来的Context对象
	private LayoutInflater layoutinflater;
	private String inflater = Context.LAYOUT_INFLATER_SERVICE; // 这个必须得有
	private List<customer> list;
	private List<HashMap<String, Object>> categorylist = new ArrayList<HashMap<String, Object>>();
	private ProgressDialog ProgressDialog1; // 加载对话框
	private ImageLoader mImageLoader;
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public customerAdapter(DiquActivity context, List<customer> list) {
		super();
		this.context = context;
		layoutinflater = (LayoutInflater) context.getSystemService(inflater); // 这个必须得写
		this.list = list;
		mImageLoader = new ImageLoader(); // 声明图片文件流
	}

	@Override
	public int getCount() {
		return categorylist.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addlist(List<customer> addlist) {
		try {
			for (customer customers : addlist) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				
				item.put("_id", customers.get_id());
				item.put("_channel_id", customers.get_channel_id());
				item.put("_title", customers.get_title());
				item.put("_call_index", customers.get_call_index());
				item.put("_parent_id", customers.get_parent_id());
				item.put("_class_list", customers.get_class_list());
				item.put("_class_layer", customers.get_class_layer());
				item.put("_sort_id", customers.get_sort_id());
				item.put("_link_url", customers.get_link_url());
				item.put("_img_url", customers.get_img_url());
				item.put("_content", customers.get_content());
				item.put("_seo_title", customers.get_seo_title());
				item.put("_seo_keywords", customers.get_seo_keywords());
				item.put("_seo_description", customers.get_seo_description());		
				item.put("_tel", customers.get_tel());
				item.put("_fax", customers.get_fax());
				item.put("_mailcode", customers.get_mailcode());
				item.put("_website", customers.get_website());
				item.put("_area", customers.get_area());
				item.put("_address", customers.get_address());
				item.put("_catagory", customers.get_catagory());
				item.put("_companycode", customers.get_companycode());
				//item.put("_companycode", customers.get_tel());
				categorylist.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		customberViewHolder viewHolder = null;
		try {
			if (convertView == null) {
				convertView = layoutinflater.from(context).inflate(
						R.layout.item_new, null);
				viewHolder = new customberViewHolder();
				viewHolder.mImageView = (ImageView) convertView
						.findViewById(R.id.diqu_imageView1);
				viewHolder.textview1 = (TextView) convertView
						.findViewById(R.id.diqu_tv1);
				viewHolder.textview2 = (Button) convertView
						.findViewById(R.id.diqu_tv2);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (customberViewHolder) convertView.getTag();
			}
			if (!mBusy) {
				mImageLoader.customerloadImage(
						context.localhost()
								+ categorylist.get(position).get("_img_url")
										.toString(), this, viewHolder);
				viewHolder.textview1.setText(categorylist.get(position)
						.get("_title").toString());

				try {
					String[] spStr = categorylist.get(position).get("_tel").toString().split(",");
					viewHolder.textview2.setText("联系电话：" + spStr[0]);
				} catch (Exception e) {
					viewHolder.textview2.setText("联系电话："
							+ categorylist.get(position).get("_tel")
									.toString());
					e.printStackTrace();

				}

			} else {
				Bitmap bitmap = mImageLoader
						.getBitmapFromCache(context.localhost()
								+ categorylist.get(position).get("_img_url")
										.toString());
				if (bitmap != null) {
					viewHolder.mImageView.setImageBitmap(bitmap);
				} else {
					viewHolder.mImageView
							.setImageResource(R.drawable.ic_launcher);
				}
			}
			viewHolder.textview2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					context.calltel(categorylist.get(position).get("_tel").toString());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

	static class customberViewHolder {
		ImageView mImageView;
		TextView textview1;
		Button textview2;
	}

}
