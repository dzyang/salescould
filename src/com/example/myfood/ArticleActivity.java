package com.example.myfood;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.ImageLoader;
import com.example.utils.StreamTool;
import com.example.utils.myapplication;

public class ArticleActivity extends Activity {
	private myapplication myapplication1;
	private Bundle Bundle1;
	private TextView TextView1;
	private TextView TextView2;
	private ImageView ImageView1;
	private ImageLoader ImageLoader1;
	ProgressDialog dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		ImageLoader1 = new ImageLoader();
		Bundle Bundle1 = this.getIntent().getExtras();
		int id = Integer.parseInt(Bundle1.getString("_id"));
		TextView1 = (TextView) findViewById(R.id.article_textView1);
		TextView2 = (TextView) findViewById(R.id.article_textView2);
		ImageView1 = (ImageView) findViewById(R.id.article_imageView1);
		TextView1.setText(Bundle1.getString("_title"));
		TextView2.setText(Html.fromHtml(Bundle1.getString("_content"),
				imgGetter, null));
		ImageView1.setImageBitmap(bitmap(myapplication1.getlocalhost()
				+ Bundle1.getString("_img_url").toString()));
	}

	/**
	 * 从网络读取
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap bitmap(String url) {
		StreamTool StreamTool1 = new StreamTool();
		try {
			InputStream isInputStream = StreamTool1.getis(url);
			Bitmap bitmap = BitmapFactory.decodeStream(isInputStream);
			isInputStream.close();
			return bitmap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}
}
