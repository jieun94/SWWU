package com.example.swwu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class JobcafeBActivity extends Activity {

	TextView inf_job;
	
	phpDown task;
	//back task;
    ArrayList<JListItem> jlistItem= new ArrayList<JListItem>();
	JListItem jItem;
	
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobcafe_balance);
        
        task = new phpDown();    
        inf_job = (TextView)findViewById(R.id.inf_job);
    	
        task.execute("http://swc.dothome.co.kr/DB/job.php");
        
        Button jobcafe_back = (Button) findViewById(R.id.job_back);
        jobcafe_back.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button jobcafe_home = (Button) findViewById(R.id.job_home);
        jobcafe_home.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button jobcafe_card = (Button) findViewById(R.id.job_card);
        jobcafe_card.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),StudentIDActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
      		startActivity(intent);
      		
      		overridePendingTransition(0,0);
           }
        });
        
   }
	private class phpDown extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{

                      // �곌껐 url �ㅼ젙
                      URL url = new URL(urls[0]);
                      // 而ㅻ꽖��媛앹껜 �앹꽦
                      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                      // �곌껐�섏뿀�쇰㈃.
                      if(conn != null){
                         conn.setConnectTimeout(10000);
                         conn.setUseCaches(false);
                         // �곌껐�섏뿀��肄붾뱶媛�由ы꽩�섎㈃.
                         if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                            for(;;){
                                // �뱀긽��蹂댁뿬吏�뒗 �띿뒪�몃� �쇱씤�⑥쐞濡��쎌뼱 ��옣.  
                                String line = br.readLine();
                                if(line == null) break;
                                // ��옣���띿뒪���쇱씤��jsonHtml��遺숈뿬�ｌ쓬
                                jsonHtml.append(line + "\n");
                             }
                          br.close();
                       }
                        conn.disconnect();
                     }
                   } catch(Exception ex){
                      ex.printStackTrace();
                   }
                   return jsonHtml.toString();
                 }
        
  
        
        protected void onPostExecute(String str){
        	
        	
            String INF_JOB;
            
            try{
            	
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                
                for(int i=0; i<ja.length(); i++){
                	
                    JSONObject jo = ja.getJSONObject(i);
                    
                    INF_JOB = jo.getString("INF_JOB");
                    
                    jlistItem.add(new JListItem(
                            INF_JOB));
                }
            } catch(JSONException e){
                e.printStackTrace();
            }
            inf_job.setText(jlistItem.get(0).getData(0)+"원");
        }
    }
}