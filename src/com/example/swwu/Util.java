package com.example.swwu;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class Util {
	private static DefaultHttpClient client;
	/*public static DefaultHttpClient getThreadSafeClient()  {

		DefaultHttpClient client = new DefaultHttpClient();
		ClientConnectionManager mgr = client.getConnectionManager();
		HttpParams params = client.getParams();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, 
				mgr.getSchemeRegistry()), params);
		return client;
	}*/
	/*
	public static DefaultHttpClient getHttpClient(){
		if(client ==null){
			client = new DefaultHttpClient();
		}
		return client;
	}*/
	static {
		if(client ==null){
			client = new DefaultHttpClient();
			HttpParams params = client.getParams();  

			//타임아웃 30초..
			HttpConnectionParams.setConnectionTimeout(params, 30000);
			HttpConnectionParams.setSoTimeout(params, 30000);
		}
	}
	private final String domain ="http://swc.dothome.co.kr";
	public String submitWithParameters(String urlPath,Map<String,String> paramMap){


		String path=domain + urlPath;

		List parameters = new ArrayList();


		if(paramMap!=null){

			Iterator<String> iter=paramMap.keySet().iterator();

			String key=null;
			String value=null;
			while(iter.hasNext()){
				key=iter.next();
				value=paramMap.get(key);
				parameters.add(new BasicNameValuePair(key,value));

				Log.i("Request key,value",key+","+value);
			}
		}
		BufferedReader reader=null;
		String resultString=null;
		try {
			
			HttpPost httpPost = new HttpPost(path); 

			if(paramMap!=null){
				//파라미터셋팅..
				//UrlEncodedFormEntity entity =new UrlEncodedFormEntity(parameters, "UTF-8");
				UrlEncodedFormEntity entity =new UrlEncodedFormEntity(parameters, "ISO-8859-1");
				httpPost.setEntity(entity);
			}
			//실행후 응답받음..
			Log.i("Request","execute start");

			HttpResponse response = client.execute(httpPost);

			Log.i("Request","execute end");
			HttpEntity entityResponse = response.getEntity();
			reader = new BufferedReader(new InputStreamReader(entityResponse.getContent(), "UTF-8"), 8);

			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			resultString = sb.toString();

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try{reader.close();}catch(Exception e){}

		}
		return resultString;
	}
}
