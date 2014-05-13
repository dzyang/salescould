package com.example.daily;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.fragments.MainFragment;
import com.example.jsonservices.jsonusers;
import com.example.model.submit;
import com.example.model.users;
import com.example.myfood.DiquActivity;
import com.example.myfood.MainActivity;
import com.example.myfood.R;
import com.example.utils.GoodsDBManager;
import com.example.utils.SubmitDBManager;
import com.example.utils.dingnumDBManager;
import com.example.utils.myapplication;
import com.example.view.DailyView.MyAdapter;
import com.example.view.DailyView.ViewHolder;

public class DailyKqDkMapActivity extends Activity {
	private myapplication myapplication1;	
	MapView mMapView = null;
	private Button button1;
	private Button button2;
	private Button button3;
	private TextView kq_dk_map_address;
	Bundle Bundle1=null;
	String type="";
	BMapManager mBMapMan = null;	
	MapController mMapController = null;
	private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 5000;
	private static int LOCATION_COUTNS = 0;
	public Double nLatitude; // 经度 给gps定位用
	public Double nLontitude; // 纬度 给gps定位用
	public String address; // 纬度 给gps定位用
	MKSearch mSearch = null;	// 搜索模块，也可去掉地图模块独立使用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle1 = this.getIntent().getExtras();
		type = Bundle1.getString("type");
		Log.i("打卡类型:",type);
		
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
				
	
		/////////////////////////////////////////////////////////////
		mBMapMan = new BMapManager(getApplication());
		//mBMapMan.init("dUaQMCwajfYB7fgbsoAyjhIw", null);
		// 必须要加载key
		mBMapMan.init("kZDuYiOHna1tle6GGhSY6Pqg", new MKGeneralListener() {
			public void onGetPermissionState(int arg0) {
					if (arg0 == 300) {
						//Toast.makeText(MainActivity.this, R.string.key_error,
						//Toast.LENGTH_LONG).show();
					}
			}

			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity.this, R.string.net_error,Toast.LENGTH_LONG).show();

			}
		});
		mBMapMan.start();
		setContentView(R.layout.dailykq_dk_map);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		kq_dk_map_address=(TextView)findViewById(R.id.kq_dk_map_address);
		mMapView = (MapView) findViewById(R.id.daily_kq_dk_bmapsView);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setTraffic(true);// 交通地图
	    mMapView.invalidate();// 刷新地图
        mMapController = mMapView.getController();
        mMapController.enableClick(true);
        mMapController.setZoom(12);
        mMapView.setBuiltInZoomControls(true);
		mMapController = mMapView.getController();
		 mSearch = new MKSearch();
		 mSearch.init(mBMapMan, new MKSearchListener() {
	            @Override
	            public void onGetPoiDetailSearchResult(int type, int error) {
	            }
	            
				public void onGetAddrResult(MKAddrInfo res, int error) {
					if (error != 0) {
						String str = String.format("错误号：%d", error);
						Toast.makeText(DailyKqDkMapActivity.this, str, Toast.LENGTH_LONG).show();
						return;
					}
					//地图移动到该点
					mMapView.getController().animateTo(res.geoPt);	
					if (res.type == MKAddrInfo.MK_GEOCODE){
						//地理编码：通过地址检索坐标点
						String strInfo = String.format("纬度：%f 经度：%f", res.geoPt.getLatitudeE6()/1e6, res.geoPt.getLongitudeE6()/1e6);
						Toast.makeText(DailyKqDkMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
					}
					if (res.type == MKAddrInfo.MK_REVERSEGEOCODE){
						//反地理编码：通过坐标点检索详细地址及周边poi
						String strInfo = res.strAddr;
						Toast.makeText(DailyKqDkMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
						address=res.strAddr;
						kq_dk_map_address.setText(address);
					}
					//生成ItemizedOverlay图层用来标注结果点
					ItemizedOverlay<OverlayItem> itemOverlay = new ItemizedOverlay<OverlayItem>(null, mMapView);
					//生成Item
					OverlayItem item = new OverlayItem(res.geoPt, "", null);
					//得到需要标在地图上的资源
					Drawable marker = getResources().getDrawable(R.drawable.icon_markf);  
					//为maker定义位置和边界
					marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());
					//给item设置marker
					item.setMarker(marker);
					//在图层上添加item
					itemOverlay.addItem(item);
					
					//清除地图其他图层
					mMapView.getOverlays().clear();
					//添加一个标注ItemizedOverlay图层
					mMapView.getOverlays().add(itemOverlay);
					//执行刷新使生效
					mMapView.refresh();
				}
				public void onGetPoiResult(MKPoiResult res, int type, int error) {
					
				}
				public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error) {
				}
				public void onGetTransitRouteResult(MKTransitRouteResult res, int error) {
				}
				public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error) {
				}
				public void onGetBusDetailResult(MKBusLineResult result, int iError) {
				}
				@Override
				public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {
				}

				@Override
				public void onGetShareUrlResult(MKShareUrlResult result, int type,
						int error) {
					// TODO Auto-generated method stub
					
				}

	        });
	        
		////////////////////////////////////////////////////////////
		gpsdw();
     	
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(DailyKqDkMapActivity.this, DailyKqDkActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
		button2.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				gpsdw();// 显示gps定位
 			}
 		});

		button3.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				new Thread(saveRun).start();
 			}
 		});
		
	}
	
	
Runnable saveRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
		
			
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_kq/dk_add.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号

			paramsList.add(new BasicNameValuePair("_type",type));//类型			
			paramsList.add(new BasicNameValuePair("_address",address));//地址			
			paramsList.add(new BasicNameValuePair("_addresspoint",nLatitude+"_"+nLontitude));//经纬度
			if(type.equals("1"))
			{
				paramsList.add(new BasicNameValuePair("_memo","签到"));//完成目标
			}else
			{
				paramsList.add(new BasicNameValuePair("_memo","早退"));//完成目标
			}
			try {
				httprequest.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
				HttpClient HttpClient1 = new DefaultHttpClient();
				HttpResponse httpResponse = HttpClient1.execute(httprequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					result = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity()));
				} else {
					result = 0;
				}
				Message msg = new Message();
		        Bundle data = new Bundle();
		        data.putInt("result",result);
		        msg.setData(data);
		        savehandler.sendMessage(msg);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
	 };  

	 Handler savehandler = new Handler(){
		    @Override
		    public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		        Bundle data = msg.getData();
		        int result = data.getInt("result");
		        Log.i("mylog","请求结果-->" + result);
		        if (result == 1) {
				
		        	Toast.makeText(DailyKqDkMapActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(DailyKqDkMapActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};
	
	
	
	public void gpsdw() {
		locationClient = new LocationClient(this);
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		option.setProdName("快销APP"); // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setScanSpan(UPDATE_TIME); // 设置定时定位的时间间隔。单位毫秒
		locationClient.setLocOption(option);

		// 注册位置监听器
		locationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				nLatitude = location.getLatitude();
				nLontitude = location.getLongitude();
				
				GeoPoint ptCenter = new GeoPoint((int)(nLatitude*1e6), (int)(nLontitude*1e6));
				mSearch.reverseGeocode(ptCenter);
				
				
				MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mMapView);
				myLocationOverlay.setMarker(getResources().getDrawable(R.drawable.icon_marka));
				// 手动将位置源置为天安门，在实际应用中，请使用百度定位SDK获取位置信息，要在SDK中显示一个位置，需要
				// 使用百度经纬度坐标（bd09ll）
				//myLocationOverlay.enableCompass();//打开指南针  
				LocationData locData = new LocationData();
				locData.latitude = nLatitude;
				locData.longitude = nLontitude;
				locData.direction = 2.0f;
				myLocationOverlay.setData(locData);
				mMapView.getOverlays().add(myLocationOverlay);
				mMapView.refresh();
				mMapController.animateTo(new GeoPoint((int) (nLatitude * 1e6),(int) (nLontitude * 1e6)));
				locationClient.stop();
			}

			@Override
			public void onReceivePoi(BDLocation location) {
			}

		});

		locationClient.start();
		locationClient.requestLocation();
	}
	
	
	
	
}
