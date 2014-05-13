package com.example.daily;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

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
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myfood.R;
import com.example.utils.ChinaAlphabetComparator;
import com.example.utils.ChinaCityUtil;
import com.example.utils.myapplication;

public class DailyKqCcAddActivity extends Activity implements AdapterView.OnItemSelectedListener{
	private myapplication myapplication1;
	private Button button1;
	private Button button2;
	private EditText srcaddr_edit;
	private EditText destaddr_edit;
	private EditText srcdate_edit	;
	private EditText enddate_edit;
	private EditText memo_edit;
	private Calendar calendar;
	private String selectdate = null;	
	
	

	 
	private ChinaAlphabetComparator src_comparator;  
	 private ChinaAlphabetComparator dest_comparator1;
	 private Spinner src_spinnerProvince;  
	 private Spinner src_spinnerCity;  
	 private Spinner src_spinnerRegion;  
	 private Hashtable<String, Hashtable<String, String[]>> src_hashtable;  
	 private Hashtable<String, Hashtable<String, String[]>> dest_hashtable;  
	 private String[] src_arrProvince, src_arrCity, src_arrRegion;  
	 private String province, city, region;  
	  
	 private Spinner dest_spinnerProvince;  
	 private Spinner dest_spinnerCity;  
	 private Spinner dest_spinnerRegion;  
	 private String[] dest_arrProvince,dest_arrCity, dest_arrRegion;  
	 private String dest_province, dest_city, dest_region;  
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dailykq_cc_new);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		calendar = Calendar.getInstance();
		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		srcaddr_edit= (EditText) findViewById(R.id.dailykq_cc_srcAddr);
		destaddr_edit= (EditText) findViewById(R.id.dailykq_cc_destAddr);
		//////////////////////////////////////
		src_comparator = new ChinaAlphabetComparator();  
		dest_comparator1 = new ChinaAlphabetComparator();  
	    src_hashtable = ChinaCityUtil.initChinaCitysHashtable();  
	    dest_hashtable = ChinaCityUtil.initChinaCitysHashtable();  
	    src_arrProvince = ChinaCityUtil.findAreaStringArr(src_hashtable, ChinaCityUtil.TYPE_PROVINCE);  
	    dest_arrProvince = ChinaCityUtil.findAreaStringArr(src_hashtable, ChinaCityUtil.TYPE_PROVINCE);  
	    
	    ArrayAdapter<String> src_adapterProvince = getSrcArrayAdapter(src_arrProvince);
	    ArrayAdapter<String> dest_adapterProvince = getSrcArrayAdapter(dest_arrProvince);
	    
	
	     src_spinnerProvince = (Spinner) findViewById(R.id.spinnerProvince);  
	     src_spinnerProvince.setAdapter(src_adapterProvince);  
	     src_spinnerProvince.setOnItemSelectedListener((OnItemSelectedListener) this);  
	        
	     src_spinnerCity = (Spinner) findViewById(R.id.spinnerCity);  
	     src_spinnerCity.setOnItemSelectedListener((OnItemSelectedListener) this);  
	        
	     src_spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);  
	     src_spinnerRegion.setOnItemSelectedListener((OnItemSelectedListener) this);  
		
	     
	     dest_spinnerProvince = (Spinner) findViewById(R.id.dest_spinnerProvince);  
	     dest_spinnerProvince.setAdapter(dest_adapterProvince);  
	     dest_spinnerProvince.setOnItemSelectedListener((OnItemSelectedListener) this);  
	        
	     dest_spinnerCity = (Spinner) findViewById(R.id. dest_spinnerCity);  
	     dest_spinnerCity.setOnItemSelectedListener((OnItemSelectedListener) this);  
	        
	     dest_spinnerRegion = (Spinner) findViewById(R.id.dest_spinnerRegion);  
	     dest_spinnerRegion.setOnItemSelectedListener((OnItemSelectedListener) this);  
	     

		srcdate_edit= (EditText) findViewById(R.id.dailykq_cc_start_date);
		srcdate_edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(DailyKqCcAddActivity.this, dateListener,
						calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		enddate_edit= (EditText) findViewById(R.id.dailykq_cc_end_date);
		enddate_edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(DailyKqCcAddActivity.this, dateListener1,
						calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		memo_edit= (EditText) findViewById(R.id.dailykq_cc_memo);
		
		
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(DailyKqCcAddActivity.this, DailyKqCcActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
		
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//updateding();
				new Thread(saveRun).start();
			}
		});
	
	
	}
	
	   /***
	 * 日期选择监听事件，注意：与其配套的有 private Calendar calendar; calendar =
	 * Calendar.getInstance(); private String selectdate = null; 调用方法 new
	 * DatePickerDialog(SubmitActivity.this, dateListener,
	 * calendar.get(Calendar.YEAR), calendar .get(Calendar.MONTH), calendar
	 * .get(Calendar.DAY_OF_MONTH)).show();
	 */
	DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month,
				int dayOfMonth) {
			// Calendar月份是从0开始,所以month要加1

			selectdate = year + "-" + (month + 1) + "-" + dayOfMonth;
			/*new TimePickerDialog(DailyKqCcAddActivity.this, timeListener,
					calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
					false).show();*/
			srcdate_edit.setText(selectdate);

		};
	};

	DatePickerDialog.OnDateSetListener dateListener1 = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month,
				int dayOfMonth) {
			// Calendar月份是从0开始,所以month要加1

			selectdate = year + "-" + (month + 1) + "-" + dayOfMonth;
			/*new TimePickerDialog(DailyKqCcAddActivity.this, timeListener,
					calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
					false).show();*/
			enddate_edit.setText(selectdate);

		};
	};
	
	TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			selectdate += " " + hourOfDay + ":" + minute + ":00";
			srcdate_edit.setText(selectdate);
		};
	};

	
Runnable saveRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
		
			
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_kq/cc_add.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号


			paramsList.add(new BasicNameValuePair("_srcaddr",srcaddr_edit.getText().toString()));//工作ID
			
			
			paramsList.add(new BasicNameValuePair("_destaddr",destaddr_edit.getText().toString()));//完成日期
			
			
			paramsList.add(new BasicNameValuePair("_startdate",srcdate_edit.getText().toString()));//完成目标
			
			
			paramsList.add(new BasicNameValuePair("_enddate",enddate_edit.getText().toString()));//完成目标
			
			
			
			paramsList.add(new BasicNameValuePair("_memo",memo_edit.getText().toString()));//完成目标
			
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
				
		        	Toast.makeText(DailyKqCcAddActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(DailyKqCcAddActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};



	private ArrayAdapter<String> getSrcArrayAdapter(String[] arr) {  
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  
	            android.R.layout.simple_spinner_item, arr);  
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	    adapter.sort(src_comparator);  
	    return adapter;  
	}

	private void modifySrcCity(String src_province) {  
	    src_arrCity = ChinaCityUtil.findAreaStringArr(src_hashtable, ChinaCityUtil.TYPE_CITY, src_province);  
	    ArrayAdapter<String> adapterCity = getSrcArrayAdapter(src_arrCity);  
	    src_spinnerCity.setAdapter(adapterCity);  
	}

	private void modifySrcRegion(String src_province, String src_city) {  
	    src_arrRegion = ChinaCityUtil.findAreaStringArr(src_hashtable, ChinaCityUtil.TYPE_REGION, src_province,  
	            src_city);  
	    ArrayAdapter<String> adapterRegion = getSrcArrayAdapter(src_arrRegion);  
	    src_spinnerRegion.setAdapter(adapterRegion);  
	}

	
	private ArrayAdapter<String> getDestArrayAdapter(String[] arr) {  
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  
	            android.R.layout.simple_spinner_item, arr);  
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	    adapter.sort(dest_comparator1);  
	    return adapter;  
	}
	private void modifyDestCity(String dets_province) {  
	   dest_arrCity = ChinaCityUtil.findAreaStringArr(dest_hashtable, ChinaCityUtil.TYPE_CITY, dets_province);  
	    ArrayAdapter<String> dest_adapterCity = getDestArrayAdapter(dest_arrCity);  
	    dest_spinnerCity.setAdapter(dest_adapterCity);  
	}

	private void modifyDestRegion(String dest_province, String dest_city) {  
	    dest_arrRegion = ChinaCityUtil.findAreaStringArr(dest_hashtable, ChinaCityUtil.TYPE_REGION, dest_province,  
	            dest_city);  
	    ArrayAdapter<String> dest_adapterRegion = getDestArrayAdapter(dest_arrRegion);  
	    dest_spinnerRegion.setAdapter(dest_adapterRegion);  
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  
	    if (parent == src_spinnerProvince) {  
	        province = src_arrProvince[position];  
	        modifySrcCity(province);  
	    } else if (parent == src_spinnerCity) {  
	        city = src_arrCity[position];  
	        modifySrcRegion(province, city);  
	    } else if (parent == src_spinnerRegion) {  
	        region = src_arrRegion[position];  
	        //txtInfo.setText(province + " " + city + " " + region);  
	       // srcaddr_edit.setText( parent.getItemAtPosition(position).toString());
	        srcaddr_edit.setText( province + " " + city + " " + region);
	    } else if (parent == dest_spinnerProvince) {  
	        dest_province = dest_arrProvince[position];  
	        modifyDestCity(dest_province);  
	    }
	    else if (parent == dest_spinnerCity) {  
	        dest_city = dest_arrCity[position];  
	        modifyDestRegion(dest_province, dest_city);  
	    } else if (parent == dest_spinnerRegion) {  
	        dest_region = dest_arrRegion[position];  
	        destaddr_edit.setText( dest_province + " " + dest_city + " " + dest_region);
	    }
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

		 
	 

	
}
