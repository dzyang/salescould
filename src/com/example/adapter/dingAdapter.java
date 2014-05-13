package com.example.adapter;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.adapter.goodsAdapter.goodsViewHolder;
import com.example.myfood.DingActivity;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.StreamTool;

/***
 * 自定义baseadapter
 * 
 * @author Administrator
 * 
 */
public class dingAdapter extends SimpleAdapter {
	private DingActivity context;
	private GoodsDBManager GoodsDBManager1;
	private List<? extends Map<String, ?>> list;
	private ImageLoader mImageLoader;
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public dingAdapter(DingActivity context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
		GoodsDBManager1 = new GoodsDBManager(context);
		this.context = context;
		list = data;
		mImageLoader = new ImageLoader(); // 声明图片文件流
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		dingViewHolder viewHolder = null;
		View v = super.getView(position, convertView, parent);
		try {

			viewHolder = new dingViewHolder();
			viewHolder.mImageView = (ImageView) v
					.findViewById(R.id.ding_imageView1);
			viewHolder.textview1 = (TextView) v.findViewById(R.id.ding_textView1);
			viewHolder.textview2 = (TextView) v.findViewById(R.id.ding_textView2);
			viewHolder.textview3 = (TextView) v.findViewById(R.id.ding_textView3);
			viewHolder.Button1 = (Button) v.findViewById(R.id.ding_button1);
			viewHolder.Button2 = (Button) v.findViewById(R.id.ding_button2);

			try {
				if (!mBusy) {
					mImageLoader.dingloadImage(
							context.localhost()
									+ list.get(position).get("_img_url")
											.toString(), this, viewHolder);
					viewHolder.textview1.setText(list.get(position)
							.get("_title").toString());
					viewHolder.textview2.setText("价格："
							+ list.get(position).get("_sell_price").toString()
							+ "元");
					viewHolder.textview3.setText(String.valueOf(
							list.get(position).get("_buynumber")).toString());
				} else {
					Bitmap bitmap = mImageLoader.getBitmapFromCache(context
							.localhost()
							+ list.get(position).get("_img_url").toString());
					if (bitmap != null) {
						viewHolder.mImageView.setImageBitmap(bitmap);
					} else {
						viewHolder.mImageView
								.setImageResource(R.drawable.ic_launcher);
					}
				}

				viewHolder.Button1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int sbuynumber = Integer.parseInt(list.get(position)
								.get("_buynumber").toString());
						int sid = Integer.parseInt(list.get(position)
								.get("_id").toString());
						GoodsDBManager1.addbuynumber(sbuynumber + 1, sid);
						context.query();
					}
				});

				viewHolder.Button2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int sbuynumber = Integer.parseInt(list.get(position)
								.get("_buynumber").toString());
						final int sid = Integer.parseInt(list.get(position)
								.get("_id").toString());
						if ("1".equals(String.valueOf(sbuynumber))) {
							new AlertDialog.Builder(context)
									.setTitle("移除这商品?")
									.setIcon(android.R.drawable.ic_dialog_info)

									.setPositiveButton(
											"取消",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// 无需操作
												}
											})
									.setNegativeButton(
											"确定",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													GoodsDBManager1.delete(sid);
													context.query();
												}
											}).show();
						} else {
							GoodsDBManager1.addbuynumber(sbuynumber - 1, sid);
							context.query();
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	/***
	 * html图片替换
	 */
	ImageGetter imgGetter = new Html.ImageGetter() {
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			Log.d("Image Path", source);
			URL url;
			try {
				url = new URL(source);
				drawable = Drawable.createFromStream(url.openStream(), "");
			} catch (Exception e) {
				return null;
			}
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	static class dingViewHolder {
		ImageView mImageView;
		TextView textview1;
		TextView textview2;
		TextView textview3;
		Button Button1;
		Button Button2;
	}

}
