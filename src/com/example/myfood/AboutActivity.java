package com.example.myfood;

import com.example.myfood.R.id;
import com.example.utils.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AboutActivity extends Activity {
	private TextView textView;
	private myapplication myapplication1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		textView = (TextView) findViewById(R.id.about_textView1);
		textView.setText(Html
				.fromHtml(getResources().getString(R.string.about)));
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}
}
