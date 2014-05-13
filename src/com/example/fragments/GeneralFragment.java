package com.example.fragments;

import java.io.Serializable;

import com.example.control.dailymainmenu;
import com.example.myfood.R;
import com.example.view.DailyView;
import com.example.view.HomeView;
import com.example.view.MoreView;
import com.example.view.CustomerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 框架类，抽象公共方法
 * @author ZHF
 *
 */
public class GeneralFragment extends Fragment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int item; //用于区分底部菜单项
	protected static View main_title_RelativeLayout; //标题栏
	protected final static String key = "Bundle";   //跳转值传递key的名称
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(getArguments() != null) {  //根据接收子类传来的arguments判断item的哪一项
			if(getArguments().containsKey(MainFragment.Item)) {
				item = getArguments().getInt(MainFragment.Item);
			}
		}
	}
	
	/**为Fragment加载布局时调用 **/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_general, container, false);
		GeneralFragment fragment = null;
		switch(item) {
		case R.id.fragment_bottom_home:  //主页
			fragment = new HomeView();
			break;
		case R.id.fragment_bottom_order:
			fragment = new DailyView();  //日常工作
			 break;
		case R.id.fragment_bottom_notice:
			fragment = new CustomerView();   //客户拜访
			break;
		case R.id.fragment_bottom_more:
			fragment = new MoreView();  //个人设置
			break;
		default:
			break;
		}
		if(fragment != null) {
			//更换mainView中间的内容（home,msg,at,more）
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, fragment).commit();
		}
		main_title_RelativeLayout =  ((View) container.getParent()).findViewById(R.id.main_title_RelativeLayout);
		//将生成的view返回
		return view;
	}
	
	/**设置标题**/
	protected void setTitle(Object title) {
		if(main_title_RelativeLayout != null) {
			//标题栏中的文字
			TextView mTvTitle = (TextView) main_title_RelativeLayout.findViewById(R.id.main_title_TextView);
			if(mTvTitle != null) {
				if(title instanceof Integer) {  //整型
					mTvTitle.setText((Integer)title);
				} else { //字符类型
					mTvTitle.setText((CharSequence)title);
				}
			}
		}
	}
	
	/**页面跳转值传递**/
	protected void setBundle(Object... objects) {
		Bundle arguments = new Bundle();
		arguments.putSerializable(key, objects);
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments);
	}
	
/*	*//**获取所传递的值**//*
	protected Object[] getBundle() {
		if(getArguments() != null) {
			System.out.println("getBundle");
			if(getArguments().containsKey(key)) {
				Object[] object = (Object[]) getArguments().getSerializable(key);
				return object;
			}
		}
		return null;
	}
	
	*//**有参页面跳转**//*
	protected void toIntent(GeneralFragment generalFragment,String object) {
		if(generalFragment != null) {
			Bundle arguments = new Bundle();
			//arguments.putString(key, object);
			arguments.putSerializable(key,object);
			generalFragment.setArguments(arguments);
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, generalFragment).commit();
		}
	}*/
	
	/**有参页面跳转**/

	protected void toIntent(GeneralFragment generalFragment,Bundle bundle) {
		if(generalFragment != null) {
			//bundle.putString(key, object);
			generalFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, generalFragment).commit();
		}
	}

	/**无参页面跳转**/
	protected void toIntent(GeneralFragment generalFragment) {
		if(generalFragment != null) {
			//bundle.putString(key, object);
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, generalFragment).commit();
		}
	}


}