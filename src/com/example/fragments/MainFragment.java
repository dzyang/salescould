package com.example.fragments;

import com.example.lib.SlidingMenu;
import com.example.myfood.LoginActivity;
import com.example.myfood.MainActivity;
import com.example.myfood.R;
import com.example.ui.LeftFragment;
import com.example.ui.RightFragment;
import com.example.ui.SampleListFragment;
import com.example.utils.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;
/**
 *MainView
 * @author ZHF
 *
 */
public class MainFragment extends FragmentActivity implements BottomFragment.Callbacks {
	private myapplication myapplication1;
	public final static String Item = "item";
	private long touchTime = 0;
	private long waitTime = 2000;
	/*SlidingMenu mSlidingMenu;
	LeftFragment leftFragment;
	RightFragment rightFragment;
	SampleListFragment centerFragment;*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
																		// or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
			
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());
		
		
		
		super.onCreate(savedInstanceState);
		
		
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		if(myapplication1.ifpass())
		{
			setContentView(R.layout.main);
			//初始化默认调用接口中item选中方法
			if(myapplication1.getMainselitem()==R.id.fragment_bottom_home)
			{
				onItemSelected(R.id.fragment_bottom_home);
			}else if(myapplication1.getMainselitem()==R.id.fragment_bottom_order)
			{
				onItemSelected(R.id.fragment_bottom_order);
			}else if(myapplication1.getMainselitem()==R.id.fragment_bottom_notice)
			{
				onItemSelected(R.id.fragment_bottom_notice);
			}else if(myapplication1.getMainselitem()==R.id.fragment_bottom_more)
			{
				onItemSelected(R.id.fragment_bottom_more);
			}else
			{
				onItemSelected(R.id.fragment_bottom_home);
			}
				
			
			/*mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
			mSlidingMenu.setLeftView(getLayoutInflater().inflate(
					R.layout.left_frame, null));
			mSlidingMenu.setRightView(getLayoutInflater().inflate(
					R.layout.right_frame, null));
			mSlidingMenu.setCenterView(getLayoutInflater().inflate(
					R.layout.center_frame, null));
			
			FragmentTransaction t = this.getSupportFragmentManager()
					.beginTransaction();
			leftFragment = new LeftFragment();
			t.replace(R.id.left_frame, leftFragment);

			rightFragment = new RightFragment();
			t.replace(R.id.right_frame, rightFragment);

			centerFragment = new SampleListFragment();
			t.replace(R.id.center_frame, centerFragment);
			t.commit();*/
			
		}else
		{
			Intent Intent1 = new Intent();
			Intent1.setClass(MainFragment.this,LoginActivity.class);
			startActivity(Intent1);
		}
	}

	/*BottomFragment的回调函数*/
	@Override
	public void onItemSelected(int item) {
		myapplication1.setMainselitem(item);//全局变量杨大志添加
		
		Bundle arguments = new Bundle();
		arguments.putInt(Item, item); //将选中的底部radio的Id放进去
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments); //设置参数值
		//这里根据item的ID进行界面跳转
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.main_detail_FrameLayout, generalFragment).commit();
	}
	

	/**
	 * 退出系统
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				touchTime = currentTime;
			} else {
				myapplication1.getInstance().exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
/*	public void showLeft() {
		mSlidingMenu.showLeftView();
	}

	public void showRight() {
		mSlidingMenu.showRightView();
	}*/

}
