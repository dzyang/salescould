package com.example.myfood;

import com.example.utils.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class HelpActivity extends Activity {
	private myapplication myapplication1;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		textView = (TextView) findViewById(R.id.help_textView1);
		String textString = getResources().getString(R.string.help);
		textView.setText(Html.fromHtml(textString));
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		finish();
	}

}
