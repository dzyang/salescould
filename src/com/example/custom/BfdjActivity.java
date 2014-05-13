package com.example.custom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.Thumbnails;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.daily.DailyFeeNewActivity;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BfdjActivity extends Activity {
	private myapplication myapplication1;
	private Button button1;
	private Button button2;
	
	private Button button4;
	private Button button5;
	private EditText bfdj_pic_edittext;
	private final String TAG = "UploadActivity";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bfdj);
		myapplication1 = (myapplication) getApplication();
		myapplication1.getInstance().addActivity(this);
		
		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		bfdj_pic_edittext= (EditText) findViewById(R.id.bfdj_pic_edittext);
		
		button4 = (Button) findViewById(R.id.button4);
		button5= (Button) findViewById(R.id.button5);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(BfdjActivity.this, MainFragment.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				}
		});
		
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				new Thread(saveRun).start();
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
			/*	File file=new File("/mnt/extSdCard/Images/test.jpg");
				Intent takephoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);    
				takephoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(takephoto, CAPTURE_CODE);*/
				
				Intent takephoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				 startActivityForResult(takephoto,CAPTURE_CODE);

			}
		});
		
		
		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				Intent album = new Intent(Intent.ACTION_GET_CONTENT);   
				album.setType("video/*,image/*"); 				
				//album.setType(IMAGE_TYPE);   
				startActivityForResult(album, IMAGE_CODE);

			}
		});
		
	}

	Bitmap bm=null;
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			ContentResolver resolver = getContentResolver();
			if (resultCode != RESULT_OK) {
				return;
			} else if (requestCode == IMAGE_CODE) {
				Uri originalUri = data.getData();
				if (originalUri != null) {
					bm = MediaStore.Images.Media.getBitmap(resolver,originalUri);
					//iv.setImageBitmap(bm);
				}
			} else if (requestCode == CAPTURE_CODE && resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				if (bundle != null) {
					bm = (Bitmap) bundle.get("data");
					//iv.setImageBitmap(bm);
			        java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
			        String filename = format2.format(new Date())+".jpg";			         
					srcPath=saveBitmap(bm,filename);
					bfdj_pic_edittext.setText(srcPath);
					Log.i(TAG, srcPath);  
					Log.i(TAG, srcPath);  
				}
			}
		} catch (Exception e) {
			Toast.makeText(this, "选择图片错误，图片只能为jpg格式", Toast.LENGTH_SHORT).show();
		}
	}
	
    private String saveBitmap(Bitmap imgThumb, String fileName) {
		// TODO Auto-generated method stub
    	FileOutputStream out = null;
    	try {	    		 
    	       out = new FileOutputStream("/mnt/sdcard/"+fileName);
    	       imgThumb.compress(Bitmap.CompressFormat.PNG, 90, out);
    	} catch (Exception e) {
    	    e.printStackTrace();
    	} finally {
    	       try{
    	           out.close();
    	       } catch(Throwable ignore) {}
    	}
		return "/mnt/sdcard/"+fileName;
	}

	
	private final int IMAGE_CODE = 101; //標志從相冊中取圖片

	private final int CAPTURE_CODE = 100;//標志照相后取圖片

	
Runnable saveRun = new Runnable(){  
		
		@Override  
		public void run() {  
		    // TODO Auto-generated method stub  
			int result;
			String target = myapplication1.getlocalhost()+ "/android/json_bfdj/add.php";
			HttpPost httprequest = new HttpPost(target);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			paramsList.add(new BasicNameValuePair("_userid", myapplication1.getUserid()));//用户
			paramsList.add(new BasicNameValuePair("_companycode",myapplication1.getCompanycode()));//公司代号
			
			EditText customerid_edit= (EditText) findViewById(R.id.bfdj_customerid_edittext);
			paramsList.add(new BasicNameValuePair("_customerid",customerid_edit.getText().toString()));//拜访顾客
			
			EditText latlng_edit= (EditText) findViewById(R.id.bfdj_latlng_edittext);
			paramsList.add(new BasicNameValuePair("_latlng",latlng_edit.getText().toString()));//地点
			
			EditText pic_edit= (EditText) findViewById(R.id.bfdj_pic_edittext);
			paramsList.add(new BasicNameValuePair("_pic",pic_edit.getText().toString()));//照片
			
			
			EditText bz__edit= (EditText) findViewById(R.id.bfdj_memo_edittext);
			paramsList.add(new BasicNameValuePair("_bz",bz__edit.getText().toString()));//备注
		
	
	
			try {
				httprequest.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
				HttpClient HttpClient1 = new DefaultHttpClient();
				HttpResponse httpResponse = HttpClient1.execute(httprequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					result = Integer.parseInt(EntityUtils.toString(httpResponse.getEntity()));
				} else {
					result = 0;
				}
				String actionUrl =myapplication1.getlocalhost()+ "/android/json_bfdj/upload.php"; 
				//upLoadByCommonPost(actionUrl);
				upLoadByAsyncHttpClient(actionUrl);
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

	 //private String uploadFile ="/mnt/sdcard/test.png";//本地文件  
	 //private String srcPath = "/mnt/sdcard/a735e706.png";  	   
	 private String srcPath = "/mnt/extSdCard/Images/a735e706.jpg";
	  
	 /** 
	      * upLoadByAsyncHttpClient:由人造post上传 
	      *  
	      * @return void 
	      * @throws IOException 
	      * @throws 
	      * @since CodingExample　Ver 1.1 
	      */  
	     private void upLoadByCommonPost(String uploadUrl) throws IOException {  
	         String end = "\r\n";  
	         String twoHyphens = "--";  
	         String boundary = "******";  
	         URL url = new URL(uploadUrl);  
	         HttpURLConnection httpURLConnection = (HttpURLConnection) url  
	                 .openConnection();  
	         httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K  
	         // 允许输入输出流  
	         httpURLConnection.setDoInput(true);  
	         httpURLConnection.setDoOutput(true);  
	         httpURLConnection.setUseCaches(false);  
	         // 使用POST方法  
	         httpURLConnection.setRequestMethod("POST");  
	         httpURLConnection.setRequestProperty("Connection", "Keep-Alive");  
	         httpURLConnection.setRequestProperty("Charset", "UTF-8");  
	         httpURLConnection.setRequestProperty("Content-Type",  
	                 "multipart/form-data;boundary=" + boundary);  
	   
	         DataOutputStream dos = new DataOutputStream(  
	                 httpURLConnection.getOutputStream());  
	         dos.writeBytes(twoHyphens + boundary + end);  
	         dos.writeBytes("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""  
	                 + srcPath.substring(srcPath.lastIndexOf("/") + 1) + "\"" + end);  
	         dos.writeBytes(end);  
	   
	         FileInputStream fis = new FileInputStream(srcPath);  
	         byte[] buffer = new byte[8192]; // 8k  
	         int count = 0;  
	         // 读取文件  
	         while ((count = fis.read(buffer)) != -1) {  
	             dos.write(buffer, 0, count);  
	         }  
	         fis.close();  
	         dos.writeBytes(end);  
	         dos.writeBytes(twoHyphens + boundary + twoHyphens + end);  
	         dos.flush();  
	         InputStream is = httpURLConnection.getInputStream();  
	         InputStreamReader isr = new InputStreamReader(is, "utf-8");  
	         BufferedReader br = new BufferedReader(isr);  
	         String result = br.readLine();  
	         Log.i(TAG, result);  
	         dos.close();  
	         is.close();  
	     }  

	  
	     private AsyncHttpClient client;  
	     private void upLoadByAsyncHttpClient(String uploadUrl) throws FileNotFoundException {  
	    	         RequestParams params = new RequestParams();  
	    	         client = new AsyncHttpClient();  
	    	         params.put("uploadfile", new File(srcPath));  
	    	         client.post(uploadUrl, params, new AsyncHttpResponseHandler() {  
	    	             @Override  
	    	             public void onSuccess(int arg0, String arg1) {  
	    	                 super.onSuccess(arg0, arg1);  
	    	                 Log.i(TAG, arg1);  
	    	             }  
	    	         });  
	    	     }  
	    	   
	    	 


	 Handler savehandler = new Handler(){
		    @Override
		    public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		        Bundle data = msg.getData();
		        int result = data.getInt("result");
		        Log.i("mylog","请求结果-->" + result);
		        if (result == 1) {
				
		        	Toast.makeText(BfdjActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
					//finish();
				}else
				{
					Toast.makeText(BfdjActivity.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
		    }
		};
	
		/*int MAX_SIZES=200;

		*//** 创建缩略图,返回缩略图文件路径 *//*
	    public String createThumbnail(Bitmap source, String fileName){
	        int oldW = source.getWidth();
	        int oldH = source.getHeight();
	        
	        int w = Math.round((float)oldW/MAX_SIZES);  //MAX_SIZE为缩略图最大尺寸
	        int h = Math.round((float)oldH/MAX_SIZES);
	        
	        int newW = 0;
	        int newH = 0;
	        
	        if(w <= 1 && h <= 1){
	            return saveBitmap(source, fileName);
	        }
	        
	        int i = w > h ? w : h;  //获取缩放比例
	        
	        newW = oldW/i;
	        newH = oldH/i;
	        
	        Bitmap imgThumb = ThumbnailUtils.extractThumbnail(source, newW, newH);  //关键代码！！
	        
	        return saveBitmap(imgThumb, fileName);  //注：saveBitmap方法为保存图片并返回路径的private方法
	    }
	    

		*//** 创建视频缩略图，返回缩略图文件路径 *//*
	    public String createVideoThumbnail(String filePath, String fileName){
	        Bitmap videoThumb = ThumbnailUtils.createVideoThumbnail(filePath, Thumbnails.MINI_KIND);  //关键代码！！
	        return saveBitmap(videoThumb, fileName);  //注：saveBitmap方法为保存图片并返回路径的private方法

	    }
	    
		*//** 把Uri转化成文件路径 *//*
	    private String uri2filePath(Uri uri){
	        String[] proj = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri,proj,null,null,null);
	        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	        String path = cursor.getString(index);
	        return path;
	    }
	    */
	    
}
