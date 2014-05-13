package com.example.myfood;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.utils.UsersDBManager;
import com.example.utils.myapplication;















import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	

	protected static final String TAG = "MyService";
	LocationListener mLocationListener = null;
	BMapManager mBMapMan = null;
	private LocationClient locationClient = null;
	private myapplication myapplication1;
	private Handler handler1 = new Handler() {
		public void handleMessage(Message msg) {
	
		}
	};
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		
		myapplication1 = (myapplication) getApplication();
		mBMapMan = new BMapManager(getApplication());
		// 必须要加载key
		mBMapMan.init("kZDuYiOHna1tle6GGhSY6Pqg", new MKGeneralListener() {
			public void onGetPermissionState(int arg0) {
			}
			@Override
			public void onGetNetworkState(int arg0) {
			}
		});
		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错,这句话太经典了
		mBMapMan.start();
		
		locationClient = new LocationClient(this);
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		option.setProdName("快销APP"); // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setScanSpan(5000); // 设置定时定位的时间间隔。单位毫秒
		locationClient.setLocOption(option);

		// 注册位置监听器
		locationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				//UsersDBManager ud=new UsersDBManager();
		
					Log.i(TAG, location.getLatitude()+","+ location.getLongitude());
					//String username=myapplication.getInstance().getusername();
					String username="test";
					String point=location.getLatitude()+","+ location.getLongitude();
					
					int result;
					String target = myapplication1.getlocalhost()+ "/android/json_submit/adduserpoint.php";
					HttpPost httprequest = new HttpPost(target);
					List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
					paramsList.add(new BasicNameValuePair("username",username));
					paramsList.add(new BasicNameValuePair("point", point));
					try {
							httprequest.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
							HttpClient HttpClient1 = new DefaultHttpClient();
							HttpResponse httpResponse = HttpClient1.execute(httprequest);
							if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
								result = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity()));
							} else {
								result = 0;
							}
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
				
				//locationClient.stop();
			}
			@Override
			public void onReceivePoi(BDLocation location) {
			}
		});
		locationClient.start();
		locationClient.requestLocation();
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}


