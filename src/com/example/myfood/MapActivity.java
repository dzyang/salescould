package com.example.myfood;









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
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.utils.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MapActivity extends Activity {
	private myapplication myapplication1;
	BMapManager mBMapMan = null;
	MapView mMapView = null;
	MapController mMapController = null;
	private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 5000;
	private static int LOCATION_COUTNS = 0;
	public Double nLatitude; // 经度 给gps定位用
	public Double nLontitude; // 纬度 给gps定位用
	private Bundle Bundle1;// 传值
	private Button button;
	private PopupOverlay   pop  = null;
	private OverlayItem mCurItem = null;
	private MyOverlay mOverlay = null;
	private Button mapbutton = null;
	
	/*private PopupOverlay   pop  = null;
	private OverlayItem mCurItem = null;
	
	private TextView  popupText = null;
	private View viewCache = null;
	private View popupInfo = null;
	private View popupLeft = null;
	private View popupRight = null;*/
	/**
	 * overlay 位置坐标
	 */
	double mLon1 = 116.400244 ;
	double mLat1 = 39.963175 ;
	double mLon2 = 116.369199;
	double mLat2 = 39.942821;
	double mLon3 = 116.425541;
	double mLat3 = 39.939723;
	double mLon4 = 116.401394;
	double mLat4 = 39.906965;
	
	
	private View viewCache = null;
	private View popupInfo = null;
	private View popupLeft = null;
	private View popupRight = null;
	private TextView  popupText = null;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错,这句话太经典了
		mBMapMan.start();
		setContentView(R.layout.activity_map);
		Bundle1 = this.getIntent().getExtras();
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		
		/**
         * 创建一个popupoverlay
         */
        PopupClickListener popListener = new PopupClickListener(){
			@Override
			public void onClickedPopup(int index) {
				if ( index == 0){
					//更新item位置
				      pop.hidePop();
				      GeoPoint p = new GeoPoint(mCurItem.getPoint().getLatitudeE6()+5000,
				    		  mCurItem.getPoint().getLongitudeE6()+5000);
				      mCurItem.setGeoPoint(p);
				      mOverlay.updateItem(mCurItem);
				      mMapView.refresh();
				}
				else if(index == 2){
					//更新图标
					mCurItem.setMarker(getResources().getDrawable(R.drawable.nav_turn_via_1));
					mOverlay.updateItem(mCurItem);
				    mMapView.refresh();
				}
			}
        };
		pop = new PopupOverlay(mMapView,popListener);
		
		 //启用卫星视图
       // mMapView.setSatellite(true);
        
        //myLocationOverlay.enableMyLocation(); // 启用定位
        //myLocationOverlay.enableCompass(); // 启用指南针
        //mapView.getOverlays().add(myLocationOverlay);
        mMapView.setTraffic(true);// 交通地图
        mMapView.invalidate();// 刷新地图
		
		  /**
         * 获取地图控制器
         */
        mMapController = mMapView.getController();
        /**
         *  设置地图是否响应点击事件  .
         */
        mMapController.enableClick(true);
        /**
         * 设置地图缩放级别
         */
        mMapController.setZoom(12);
        /**
         * 显示内置缩放控件
         */
        mMapView.setBuiltInZoomControls(true);
        
       // initOverlay();
        /**
         * 设定地图中心点
         */
     // 得到mMapView的控制权,可以用它控制和驱动平移和缩放
        /*GeoPoint point =new GeoPoint((int)(39.915* 1E6),(int)(116.404* 1E6));
         //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
         mMapController.setCenter(point);//设置地图中心点
	*/
		
		// 设置启用内置的缩放控件
		mMapController = mMapView.getController();
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		button = (Button) findViewById(R.id.map_button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gpsdw();// 显示gps定位
			}
		});
		ptdw();// 给出酒店坐标
		 gpsdw();
	}
	
	
	public void ptdw() {
		
		/**
    	 * 创建自定义overlay
    	 */
         mOverlay = new MyOverlay(getResources().getDrawable(R.drawable.icon_marka),mMapView);	
         /**
          * 准备overlay 数据
          */
         GeoPoint p1 = new GeoPoint ((int)(mLat1*1E6),(int)(mLon1*1E6));
         OverlayItem item1 = new OverlayItem(p1,"位置1","");
         /**
          * 设置overlay图标，如不设置，则使用创建ItemizedOverlay时的默认图标.
          */
         item1.setMarker(getResources().getDrawable(R.drawable.icon_marka));
         
         GeoPoint p2 = new GeoPoint ((int)(mLat2*1E6),(int)(mLon2*1E6));
         OverlayItem item2 = new OverlayItem(p2,"位置2","");
         item2.setMarker(getResources().getDrawable(R.drawable.icon_markb));
         
         GeoPoint p3 = new GeoPoint ((int)(mLat3*1E6),(int)(mLon3*1E6));
         OverlayItem item3 = new OverlayItem(p3,"位置3","");
         item3.setMarker(getResources().getDrawable(R.drawable.icon_markc));

         GeoPoint p4 = new GeoPoint ((int)(mLat4*1E6),(int)(mLon4*1E6));
         OverlayItem item4 = new OverlayItem(p4,"位置4","");
         item4.setMarker(getResources().getDrawable(R.drawable.icon_markc));
         /**
          * 将item 添加到overlay中
          * 注意： 同一个itme只能add一次
          */
         mOverlay.addItem(item1);
         mOverlay.addItem(item2);
         mOverlay.addItem(item3);
         mOverlay.addItem(item4);
		
		
		
		String[] spStr = Bundle1.getStringArrayList("map").get(0).toString().split(",");
		GeoPoint point = new GeoPoint(

		(int) (Double.parseDouble(spStr[1]) * 1E6),
		(int) (Double.parseDouble(spStr[0]) * 1E6));
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		//Drawable marker = getResources().getDrawable(R.drawable.icon_marka); // 得到需要标在地图上的资源
		//mMapView.getOverlays().add(new OverItemT(marker, MapActivity.this, Bundle1.getStringArrayList("map"))); // 添加ItemizedOverlay实例到mMapView
		//mMapView.getOverlays().add(new OverItemT(marker, MapActivity.this))
		
		//MyOverlaymOverlay = new MyOverlay(getResources().getDrawable(R.drawable.icon_marka),mMapView);	
			/* GeoPoint p1 = new GeoPoint ((int)(10*1E6),(int)(10*1E6));
	       OverlayItem item1 = new OverlayItem(p1,"覆盖物1","");
	       MapView.getOverlays().add();*/
		
		MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mMapView);
		LocationData locData = new LocationData();
		locData.latitude = Double.parseDouble(spStr[1]);
		locData.longitude = Double.parseDouble(spStr[0]);	
		// 设置定位数据  
		myLocationOverlay.setData(locData);
		// 设置中心图片  
		// locationOverlay.setMarker(getResources().getDrawable(R.drawable.ic_launcher));  
		// 设置定位的模式（NORMAL， FOLLOWING，COMPASS ）三种  

		
		mMapView.getOverlays().add(mOverlay);
		mMapView.getOverlays().add(myLocationOverlay);

			
		mMapView.refresh();
		//mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别		
		
		 mapbutton = new Button(this);
         mapbutton.setBackgroundResource(R.drawable.popup);
		
		/**
         * 向地图添加自定义View.
         */
        viewCache = getLayoutInflater().inflate(R.layout.custom_text_view, null);
        popupInfo = (View) viewCache.findViewById(R.id.popinfo);
        popupLeft = (View) viewCache.findViewById(R.id.popleft);
        popupRight = (View) viewCache.findViewById(R.id.popright);
        popupText =(TextView) viewCache.findViewById(R.id.textcache);


	}
	/**
	 * 显示gps定位
	 */
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
				/*
				 * StringBuffer sb = new StringBuffer(256);
				 * sb.append("Time : "); sb.append(location.getTime());
				 * sb.append("\nError code : ");
				 * sb.append(location.getLocType()); sb.append("\nLatitude : ");
				 * sb.append(location.getLatitude());
				 * sb.append("\nLontitude : ");
				 * sb.append(location.getLongitude()); sb.append("\nRadius : ");
				 * sb.append(location.getRadius()); if (location.getLocType() ==
				 * BDLocation.TypeGpsLocation) { sb.append("\nSpeed : ");
				 * sb.append(location.getSpeed()); sb.append("\nSatellite : ");
				 * sb.append(location.getSatelliteNumber()); } else if
				 * (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				 * sb.append("\nAddress : "); sb.append(location.getAddrStr());
				 * } LOCATION_COUTNS++; sb.append("\n检查位置更新次数：");
				 * sb.append(String.valueOf(LOCATION_COUTNS));
				 * Toast.makeText(MapActivity.this, sb.toString(),
				 * Toast.LENGTH_SHORT).show(); 这个是用来做测试的，现在不用来显示了
				 */
				nLatitude = location.getLatitude();
				nLontitude = location.getLongitude();
				MyLocationOverlay myLocationOverlay = new MyLocationOverlay(mMapView);
				LocationData locData = new LocationData();
				// 手动将位置源置为天安门，在实际应用中，请使用百度定位SDK获取位置信息，要在SDK中显示一个位置，需要
				// 使用百度经纬度坐标（bd09ll）
				myLocationOverlay.enableCompass();//打开指南针  
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
	
	
	 public class MyOverlay extends ItemizedOverlay{

			public MyOverlay(Drawable defaultMarker, MapView mapView) {
				super(defaultMarker, mapView);
			}
			

			@Override
			public boolean onTap(int index){
				OverlayItem item = getItem(index);
				mCurItem = item ;
				if (index == 3){
					mapbutton.setText("这是一个系统控件");
					GeoPoint pt = new GeoPoint((int) (mLat4 * 1E6),
							(int) (mLon4 * 1E6));
					// 弹出自定义View
					pop.showPopup(mapbutton, pt, 32);
				}
				else{
				   popupText.setText(getItem(index).getTitle());
				   Bitmap[] bitMaps={
					    BMapUtil.getBitmapFromView(popupLeft), 		
					    BMapUtil.getBitmapFromView(popupInfo), 		
					    BMapUtil.getBitmapFromView(popupRight) 		
				    };
				    pop.showPopup(bitMaps,item.getPoint(),32);
				}
				return true;
			}
			
			@Override
			public boolean onTap(GeoPoint pt , MapView mMapView){
				if (pop != null){
	                pop.hidePop();
	                mMapView.removeView(mapbutton);
				}
				return false;
			}
	    	
	    }
}
