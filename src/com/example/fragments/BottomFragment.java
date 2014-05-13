package com.example.fragments;


import com.example.myfood.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


/**
 * 页面底部导航栏
 * 
 * @author ZHF
 * 
 */
public class BottomFragment extends Fragment {
    //默认回调接口实现类的对象	
	private Callbacks callbacks = defaultCallbacks; 

	/** Fragment和Activity建立关联的时候调用 **/
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		//当前类是否实现了底部导航栏点击事件回调接口
		if(!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Activity must implements fragment's callbacks !");
		}
		callbacks = (Callbacks) activity;
	}

	/** 为Fragment加载布局时调用 **/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		RadioGroup radioGroup = (RadioGroup) inflater.inflate(R.layout.fragment_bottom, container, false);
		//绑定监听器
		radioGroup.setOnCheckedChangeListener(changeListener);
		return radioGroup;
	}
	
	/**RadioGroup监听器**/
	private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			System.out.println(checkedId);
			callbacks.onItemSelected(checkedId); //调用接口中方法
		}
	};

	public interface Callbacks{
		/**导航栏回调接口**/
		public void onItemSelected(int item);
	}
	/**默认回调实现类的对象**/
	private static Callbacks defaultCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(int item) {
		//什么都不干
		}
	};
	
	/**Fragment和Activity解除关联的时候调用**/
    @Override
    public void onDetach() {
        super.onDetach();
       callbacks = defaultCallbacks;
    }
}
