package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;

public class StreamTool {

	/***
	 * 从数据流中读取数据，主要用在adapter
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] read(InputStream inStream)
			throws Exception {

		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			inStream.close();
			// ProgressDialog1.dismiss();
			return outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	/***
	 * 获得网络数据
	 * 
	 * @param urlstr
	 * @return
	 */
	public InputStream getis(String urlstr) {
		try {
			URL url = new URL(urlstr);
			URLConnection connection = url.openConnection();
			connection.setReadTimeout(50000);
			connection.connect();
			InputStream isInputStream = connection.getInputStream();
			return isInputStream;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 读取文件流 try { // String urlString =
	 * "http://172.198.1.50/upload/myfood.jpg"; // URL url = new URL(urlString);
	 * // HttpURLConnection conn = (HttpURLConnection) url // .openConnection();
	 * // //conn.setRequestMethod("GET"); // //conn.setConnectTimeout(6000); //
	 * conn.connect(); // InputStream inputStream = conn.getInputStream(); //
	 * StreamTool streamTool = new StreamTool(); // byte[] data =
	 * streamTool.readinstream(inputStream); // File file = new
	 * File("/sdcard/123.jpg"); // FileOutputStream outputStream = new
	 * FileOutputStream(file); // outputStream.write(data); //
	 * outputStream.close(); // } catch (Exception e) { // e.printStackTrace();
	 * // }
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public byte[] readinstream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, length);
		}
		;
		byteArrayOutputStream.close();
		inputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
}
