package com.sun.demo.base.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;


/**
 * @date 2018/03
 * @author szy
 * 
 */
public final class ToolsWebReq {
	
	
	//教务的接口
	public final static String TEACHER_SERVER_BASE_DETAIL_URL = "http://123.139.88.227:8989/api/labelinfo";
	
	//教务接口的秘钥
	public final static String TEACHER_SERVER_SECRET_KEY = "909090909099";
	
	
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url 发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPostWebReq(String url, Map<String, String> param) throws Exception {
		String paramStr = "";
		if (null != param) { //遍历参数Map添加到集合中
			Set<String> keySet = param.keySet();
			for (String key : keySet) {
				paramStr = paramStr + key + "=" + param.get(key) + "&";
			}
		}
		int lastAndIndex = paramStr.lastIndexOf("&");
		if (lastAndIndex != -1) {
			paramStr = paramStr.substring(0, lastAndIndex);
		}
		
		System.out.println("地址===" + url);
		System.out.println("入参===" + paramStr);
		
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setDoOutput(true);// 发送POST请求必须设置如下两行
			conn.setDoInput(true);// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));// 发送请求参数
			out.print(paramStr);// flush输出流的缓冲
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			throw e;
		} finally { // 使用finally块来关闭输出流、输入流
			try {
				if (in != null) in.close();
			} catch (IOException ex) { }
			if (out != null) out.close();
		}
		return result;
	}
	
	/**
	 * 向指定 URL 发送GET方法的请求
	 * @param url 发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @return
	 */
	public static String sendGetWebReq(String url, Map<String, String> param) throws Exception {
		String paramStr = "";
		if (null != param) { //遍历参数Map添加到集合中
			Set<String> keySet = param.keySet();
			for (String key : keySet) {
				paramStr = paramStr + key + "=" + param.get(key) + "&";
			}
		}
		int lastAndIndex = paramStr.lastIndexOf("&");
		if (lastAndIndex != -1) {
			paramStr = paramStr.substring(0, lastAndIndex);
		}
		
		System.out.println("地址===" + url);
		System.out.println("入参===" + paramStr);
		url = url + "?" + paramStr;
		
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setDoInput(true);// 获取URLConnection对象对应的输出流
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 GET 请求出现异常！" + e);
			throw e;
		} finally { // 使用finally块来关闭输出流、输入流
			try {
				if (in != null) in.close();
			} catch (IOException ex) { }
		}
		return result;
	}
	
	/**
	 * 从URL上获取网络数据
	 * @param urlString  网络地址
	 * @param maxTimeOut 最大连接超时
	 * @param reqType    请求类型
	 * @return
	 */
	public static byte[] obtainWebDataByURL(String urlString, Map<String, String> param) {
		String paramStr = "";
		if (null != param) { //遍历参数Map添加到集合中
			Set<String> keySet = param.keySet();
			for (String key : keySet) {
				paramStr = paramStr + key + "=" + param.get(key) + "&";
			}
		}
		int lastAndIndex = paramStr.lastIndexOf("&");
		if (lastAndIndex != -1) {
			paramStr = paramStr.substring(0, lastAndIndex);
		}
		
		System.out.println("地址===" + urlString);
		System.out.println("入参===" + paramStr);
		
		PrintWriter out = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(15 * 1000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
			out = new PrintWriter(conn.getOutputStream());// 发送请求参数
			out.print(paramStr);// flush输出流的缓冲
			out.flush();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();
				baos = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = is.read(buffer)) != -1) {
	                baos.write(buffer, 0, len);
	            }
	            byte[] byteArray = baos.toByteArray();
	            return byteArray;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != baos) {
					baos.close();
				}
			} catch (IOException e) {

			}
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {

			}
			if (out != null) {
				out.close();
			}
		}
		
		return null;
	}
	
	/**
	 * json请求
	 * @param url
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	  public static JSONObject sendPost(String url, JSONObject obj,Map<String, String> param)throws IOException{
		           OutputStreamWriter out = null;
		           BufferedReader reader = null;
		           String response="";
		           
		           String paramStr = "";
		           JSONObject jsStr = null;
					if (null != param) { //遍历参数Map添加到集合中
						Set<String> keySet = param.keySet();
						for (String key : keySet) {
							paramStr = paramStr + key + "=" + param.get(key) + "&";
						}
					}
					
					paramStr = paramStr + "key=" + TEACHER_SERVER_SECRET_KEY;
					System.out.println("入参===" + paramStr);
					
					String sign = ToolsMD5.Md5_32(paramStr);
					obj.put("sign", sign.toUpperCase());
					
		           try {
		               URL httpUrl = null; //HTTP URL类 用这个类来创建连接
		               //创建URL
		               httpUrl = new URL(url);
		               //建立连接
		               HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
		               conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
		               conn.setRequestProperty("Charset", "UTF-8");
		               conn.setRequestMethod("POST");
		               conn.setRequestProperty("Content-Type", "application/json");
		               conn.setRequestProperty("connection", "keep-alive");
		               conn.setUseCaches(false);//设置不要缓存
		               conn.setInstanceFollowRedirects(true);
		               conn.setDoOutput(true);
		               conn.setDoInput(true);
		               conn.connect();
		               //POST请求
		               out = new OutputStreamWriter(
		                       conn.getOutputStream());
		              
		               out.write(obj.toString());
		               out.flush();
		               //读取响应
		               reader = new BufferedReader(new InputStreamReader(
		                       conn.getInputStream()));
		               String lines;
		               while ((lines = reader.readLine()) != null) {
		                   lines = new String(lines.getBytes(), "utf-8");
		                   response+=lines;
		               }
		               reader.close();
		               // 断开连接
		               conn.disconnect();
		               
		               jsStr = (JSONObject) JSONObject.stringToValue(response);
		           } catch (Exception e) {
		           System.out.println("发送 POST 请求出现异常！"+e);
		           e.printStackTrace();
		           }
		           //使用finally块来关闭输出流、输入流
		           finally{
		           try{
		               if(out!=null){
		                   out.close();
		               }
		               if(reader!=null){
		                   reader.close();
		               }
		           }
		           catch(IOException ex){
		               ex.printStackTrace();
		           }
		       }
		   
		           return jsStr;
		       }
}
