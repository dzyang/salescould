package com.example.method;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class mymethod {

	/**
	 * Toast的封装
	 * 
	 * @param mContext
	 *            上下文，来区别哪一个activity调用的
	 * @param msg
	 *            你希望显示的值。
	 */
	public static void showMsg(Context mContext, String msg) {
		Toast toast = new Toast(mContext);
		toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);// 设置居中
		toast.show();// 显示,(缺了这句不显示)
	}
}
