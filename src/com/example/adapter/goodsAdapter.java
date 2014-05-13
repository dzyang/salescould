package com.example.adapter;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.goods;
import com.example.myfood.DiancaiActivity;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.StreamTool;
import com.example.utils.UsersDBManager;

/***
 * 自定义baseadapter
 * 
 * @author Administrator
 * 
 */
public class goodsAdapter extends BaseAdapter {
	// 自定义图片Adapter以内部类形式存在于MainActivity中，方便访问MainActivity中的各个变量，特别是imgs数组
	private DiancaiActivity context;// 用于接收传递过来的Context对象
	private LayoutInflater layoutinflater;
	private String inflater = DiancaiActivity.LAYOUT_INFLATER_SERVICE; // 这个必须得有
	private List<goods> list; // 这个地方是用来从网络读取json后用来给下面的goodslist来添加数据到listview，注意这只是一个table，相关的数据读取和显示都是下面的goodslist，切记。
	private List<HashMap<String, Object>> goodslist = new ArrayList<HashMap<String, Object>>();
	private ProgressDialog ProgressDialog1; // 加载对话框
	private GoodsDBManager GoodsDBManager1;
	private UsersDBManager usersDBManager1;
	private ImageLoader mImageLoader;
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public goodsAdapter(DiancaiActivity context, List<goods> list) {
		super();
		this.context = context;
		GoodsDBManager1 = new GoodsDBManager(context);
		usersDBManager1 = new UsersDBManager(context);
		layoutinflater = (LayoutInflater) context.getSystemService(inflater); // 这个必须得写
		this.list = list;// 获取json数据流
		mImageLoader = new ImageLoader(); // 声明图片文件流
	}

	@Override
	public int getCount() {
		return goodslist.size();
	}

	@Override
	public Object getItem(int position) {

		return position;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	/**
	 * 获取了json数据流后遍历list添加到goodslist
	 * 
	 * @param addlist
	 */
	public void addlist(List<goods> addlist) {
		try {
			for (goods goodss : addlist) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("_id", goodss.get_id());
				item.put("_channel_id", goodss.get_channel_id());
				item.put("_category_id", goodss.get_category_id());
				item.put("_title", goodss.get_title());
				item.put("_goods_no", goodss.get_goods_no());
				item.put("_stock_quantity", goodss.get_stock_quantity());
				item.put("_market_price", goodss.get_market_price());
				item.put("_sell_price", goodss.get_sell_price());
				item.put("_point", goodss.get_point());
				item.put("_link_url", goodss.get_link_url());
				item.put("_img_url", goodss.get_img_url());
				item.put("_seo_title", goodss.get_seo_title());
				item.put("_seo_keywords", goodss.get_seo_keywords());
				item.put("_seo_description", goodss.get_seo_description());
				item.put("_content", goodss.get_content());
				item.put("_sort_id", goodss.get_sort_id());
				item.put("_click", goodss.get_click());
				item.put("_is_msg", goodss.get_is_msg());
				item.put("_is_top", goodss.get_is_top());
				item.put("_is_red", goodss.get_is_red());
				item.put("_is_hot", goodss.get_is_hot());
				item.put("_is_slide", goodss.get_is_slide());
				item.put("_is_lock", goodss.get_is_lock());
				item.put("_user_id", goodss.get_user_id());
				item.put("_add_time", goodss.get_add_time());
				item.put("_digg_good", goodss.get_digg_good());
				item.put("_digg_bad", goodss.get_digg_bad());

				goodslist.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		goodsViewHolder viewHolder = null;
		// 因为采用了异步图片加载方式，书上的这种操作不能再用
		// LinearLayout linearlayout = (LinearLayout) layoutinflater.inflate(
		// R.layout.item_diancai, null);

		// 原理暂时还没有完全弄清楚
		try {
			if (convertView == null) {
				convertView = layoutinflater.from(context).inflate(
						R.layout.item_diancai, null);
				viewHolder = new goodsViewHolder();
				viewHolder.mImageView = (ImageView) convertView
						.findViewById(R.id.main_list_dcimageView1);
				viewHolder.textview1 = (TextView) convertView
						.findViewById(R.id.main_list_dctextView1);
				viewHolder.textview2 = (TextView) convertView
						.findViewById(R.id.main_list_dctextView2);
				viewHolder.Button1 = (Button) convertView
						.findViewById(R.id.main_list_dcbutton1);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (goodsViewHolder) convertView.getTag();
			}

			try {
				if (!mBusy) {
					mImageLoader.goodsloadImage(context.localhost()
							+ goodslist.get(position).get("_img_url")
									.toString(), this, viewHolder);
					viewHolder.textview1.setText(goodslist.get(position)
							.get("_title").toString());
					viewHolder.textview2.setText("价格：" + goodslist.get(position)
							.get("_sell_price").toString() + "元/件");

				} else {
					Bitmap bitmap = mImageLoader.getBitmapFromCache(context
							.localhost()
							+ goodslist.get(position).get("_img_url")
									.toString());
					if (bitmap != null) {
						viewHolder.mImageView.setImageBitmap(bitmap);
					} else {
						viewHolder.mImageView
								.setImageResource(R.drawable.ic_launcher);
					}
				}
				// if (!ifmap(goodslist.get(position).get("_id").toString())) {
				// ImageView1.setImageBitmap(bitmap(goodslist.get(position)
				// .get("_img_url").toString()));
				// } else {
				// ImageView1.setImageBitmap(sdcardbitmap("/sdcard/myfood/"
				// + goodslist.get(position).get("_id").toString()
				// + ".jpg"));
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (GoodsDBManager1.queryid(Integer.parseInt(goodslist
					.get(position).get("_id").toString()))) {
				viewHolder.Button1.setText("已点");
				viewHolder.Button1.setEnabled(false);
			} else {
				viewHolder.Button1.setText("订购");
				viewHolder.Button1.setEnabled(true);
			}
			viewHolder.Button1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					addgoods(position); // 当单击点菜按钮后进行sqlite添加操作
					Message mes = handler.obtainMessage(1);
					handler.sendMessage(mes);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

	/***
	 * 当看中一样菜品后，单击添加就调用这个命令
	 * 
	 * @param position
	 */
	private void addgoods(int position) {
		try {
			goods dt_goods = new goods();
			dt_goods.set_id(Integer.parseInt(goodslist.get(position).get("_id")
					.toString()));
			dt_goods.set_channel_id(Integer.parseInt(goodslist.get(position)
					.get("_channel_id").toString()));
			dt_goods.set_category_id(Integer.parseInt(goodslist.get(position)
					.get("_category_id").toString()));
			dt_goods.set_title(goodslist.get(position).get("_title").toString());
			dt_goods.set_goods_no(goodslist.get(position).get("_goods_no")
					.toString());
			dt_goods.set_stock_quantity(Integer.parseInt(goodslist
					.get(position).get("_stock_quantity").toString()));
			dt_goods.set_market_price(goodslist.get(position)
					.get("_market_price").toString());
			dt_goods.set_sell_price(goodslist.get(position).get("_sell_price")
					.toString());
			dt_goods.set_point(Integer.parseInt(goodslist.get(position)
					.get("_point").toString()));
			dt_goods.set_link_url(goodslist.get(position).get("_link_url")
					.toString());
			dt_goods.set_img_url(goodslist.get(position).get("_img_url")
					.toString());
			dt_goods.set_seo_title(goodslist.get(position).get("_seo_title")
					.toString());
			dt_goods.set_seo_keywords(goodslist.get(position)
					.get("_seo_keywords").toString());
			dt_goods.set_seo_description(goodslist.get(position)
					.get("_seo_description").toString());
			dt_goods.set_content(goodslist.get(position).get("_content")
					.toString());
			dt_goods.set_sort_id(Integer.parseInt(goodslist.get(position)
					.get("_sort_id").toString()));
			dt_goods.set_click(Integer.parseInt(goodslist.get(position)
					.get("_click").toString()));
			dt_goods.set_is_msg(Integer.parseInt(goodslist.get(position)
					.get("_is_msg").toString()));
			dt_goods.set_is_top(Integer.parseInt(goodslist.get(position)
					.get("_is_top").toString()));
			dt_goods.set_is_red(Integer.parseInt(goodslist.get(position)
					.get("_is_red").toString()));
			dt_goods.set_is_red(Integer.parseInt(goodslist.get(position)
					.get("_is_hot").toString()));
			dt_goods.set_is_slide(Integer.parseInt(goodslist.get(position)
					.get("_is_slide").toString()));
			dt_goods.set_is_lock(Integer.parseInt(goodslist.get(position)
					.get("_is_lock").toString()));
			dt_goods.set_user_id(Integer.parseInt(goodslist.get(position)
					.get("_user_id").toString()));
			dt_goods.set_add_time(goodslist.get(position).get("_add_time")
					.toString());
			dt_goods.set_digg_good(Integer.parseInt(goodslist.get(position)
					.get("_digg_good").toString()));
			dt_goods.set_digg_bad(Integer.parseInt(goodslist.get(position)
					.get("_digg_bad").toString()));
			dt_goods.set_buynumber(1);
			dt_goods.set_user(usersDBManager1.username());

			// 保存图片到sd卡，注意这里是调用的diancaiactivity中的图片保存操作
//			context.writeAsFile(
//					goodslist.get(position).get("_img_url").toString(),
//					new File("/sdcard/myfood/"
//							+ goodslist.get(position).get("_id").toString()
//							+ ".jpg"));

			GoodsDBManager1.add(dt_goods);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 当进行了点菜操作命令后，要重新刷新一下点菜页面的购物车和listview状态。
	 */
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				context.reloadadapter();
				context.bindgoods();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	static class goodsViewHolder {
		ImageView mImageView;
		TextView textview1;
		TextView textview2;
		Button Button1;
	}
}
