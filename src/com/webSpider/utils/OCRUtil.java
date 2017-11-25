package com.webSpider.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import net.sf.json.JSONObject;

/**
 * OCR识别工具类
 * @author pibigstar
 *
 */
public class OCRUtil {
	
	private static final String orcUrl = "http://139.199.183.119/?img=true";
	
	/**
	 * 上传图片进行识别
	 * @param url
	 * @return
	 */
	public static String ocr(String url) {
		
		String code = "";
		try {
			byte[] img=getImgInputStream(url);
			//--上传识别
			HttpURLConnection conn = null; 
			URL orc = new URL(orcUrl); 
			conn = (HttpURLConnection) orc.openConnection(); 
			conn.setRequestMethod("POST"); 
			conn.setRequestProperty("Connection", "Keep-Alive"); 
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(img);
			out.flush(); 
			out.close(); 
			// 上传验证码
			StringBuffer strBuf = new StringBuffer(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			String line = null; 
			while ((line = reader.readLine()) != null) { 
				strBuf.append(line).append("\n"); 
			} 
			String res = strBuf.toString(); 
			JSONObject obj = JSONObject.fromObject(res);  
			code = "";
			if (obj.has("code")) {  
				code=obj.getString("code");
				System.out.println("识别结果:" + code);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	
	/**
	 * 得到图片字节
	 * @param imgUrl
	 * @return
	 */
	private static byte[] getImgInputStream(String imgUrl) {
	    InputStream inputStream = null;  
		HttpURLConnection httpURLConnection = null;
		byte [] data = null;
		try {
			 URL url = new URL(imgUrl);  
	            httpURLConnection = (HttpURLConnection) url.openConnection();  
	            // 设置网络连接超时时间  
	            httpURLConnection.setConnectTimeout(3000);  
	            // 设置应用程序要从网络连接读取数据  
	            httpURLConnection.setDoInput(true);  
	            httpURLConnection.setRequestMethod("GET"); 
	            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
	            int responseCode = httpURLConnection.getResponseCode();  
	            if (responseCode == 200) {  
	                // 从服务器返回一个输入流  
	                inputStream = httpURLConnection.getInputStream(); 
	                data=new byte[inputStream.available()];
	                inputStream.read(data);
	            }  
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
