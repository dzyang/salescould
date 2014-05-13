package com.example.adapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.article;
import com.example.myfood.MainActivity;
import com.example.myfood.R;
import com.example.utils.StreamTool;

/***
 * 自定义baseadapter
 * 
 * @author Administrator
 * 
 */
public class articleAdapter extends BaseAdapter {
	// 自定义图片Adapter以内部类形式存在于MainActivity中，方便访问MainActivity中的各个变量，特别是imgs数组
	private MainActivity context;// 用于接收传递过来的Context对象
	private LayoutInflater layoutinflater;
	private String inflater = Context.LAYOUT_INFLATER_SERVICE; // 这个必须得有
	private List<article> list;
	private List<HashMap<String, Object>> articleslist = new ArrayList<HashMap<String, Object>>();
	private ProgressDialog ProgressDialog1; // 加载对话框
	private ImageLoader mImageLoader;
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public articleAdapter(MainActivity context, List<article> list) {
		super();
		this.context = context;
		layoutinflater = (LayoutInflater) context.getSystemService(inflater); // 这个必须得写
		this.list = list;
		mImageLoader = new ImageLoader();
	}

	@Override
	public int getCount() {
		return articleslist.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addlist(List<article> addlist) {
		try {
			for (article articles : addlist) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("title", articles.get_title());
				item.put("id", articles.get_id());
				item.put("zhaiyao", articles.get_zhaiyao());
				item.put("img_url", articles.get_img_url());
				item.put("content", articles.get_content());
				item.put("add_time", articles.get_add_time());
				articleslist.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		// LinearLayout linearlayout = (LinearLayout) layoutinflater.inflate(
		// R.layout.item_article, null);
		try {
			if (convertView == null) {
				convertView = layoutinflater.from(context).inflate(
						R.layout.item_article, null);
				viewHolder = new ViewHolder();
				viewHolder.mImageView = (ImageView) convertView
						.findViewById(R.id.main_list_imageView2);
				viewHolder.textview1 = (TextView) convertView
						.findViewById(R.id.main_list_article_textView1);
				viewHolder.textview2 = (TextView) convertView
						.findViewById(R.id.main_list_article_textView2);
				viewHolder.textview3 = (TextView) convertView
						.findViewById(R.id.main_list_article_textView3);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {
				if (!mBusy) {
					mImageLoader.articleloadImage(context.localhost()
							+ articleslist.get(position).get("img_url")
									.toString(), this, viewHolder);
					viewHolder.textview1.setText(articleslist.get(position)
							.get("title").toString());
					viewHolder.textview2.setText(articleslist.get(position)
							.get("zhaiyao").toString());
					viewHolder.textview3.setText(articleslist.get(position)
							.get("add_time").toString());

				} else {
					Bitmap bitmap = mImageLoader.getBitmapFromCache(context
							.localhost()
							+ articleslist.get(position).get("img_url")
									.toString());
					if (bitmap != null) {
						viewHolder.mImageView.setImageBitmap(bitmap);
					} else {
						viewHolder.mImageView
								.setImageResource(R.drawable.ic_launcher);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return linearlayout;
		return convertView;
	}

	public Bitmap bitmap(String url) {
		StreamTool StreamTool1 = new StreamTool();
		try {
			InputStream isInputStream = StreamTool1.getis(context.localhost()
					+ url);
			Bitmap bitmap = BitmapFactory.decodeStream(isInputStream);
			isInputStream.close();
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static class ViewHolder {
		ImageView mImageView;
		TextView textview1;
		TextView textview2;
		TextView textview3;
	}

}
