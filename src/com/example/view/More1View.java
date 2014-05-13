package com.example.view;

import com.example.fragments.GeneralFragment;
import com.example.myfood.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 更多页面
 * @author ZHF
 *
 */
public class More1View extends GeneralFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setTitle("更多duo");
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.more, container, false);
		if(getArguments() != null) {
			String title=(String) getArguments().getSerializable("title");
			super.setTitle(title);
			/*System.out.println("getBundle");
			if(getArguments().containsKey(key)) {
				String object = (String) getArguments().getSerializable(key);
				System.out.println("======"+object);
			}*/
		}
		return view;
	}
}
