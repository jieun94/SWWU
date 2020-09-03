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

public class FoodActivity  extends Activity {
	
	TextView m_mon;
	TextView m_tue;
	TextView m_wed;
	TextView m_thu;
	TextView m_fri;
	TextView hb_mon;
	TextView hb_tue;
	TextView hb_wed;
	TextView hb_thu;
	TextView hb_fri;
	TextView hs_mon;
	TextView hs_tue;
	TextView hs_wed;
	TextView hs_thu;
	TextView hs_fri;
	TextView hd_mon;
	TextView hd_tue;
	TextView hd_wed;
	TextView hd_thu;
	TextView hd_fri;
	
	phpDown task;
	//back task;
    ArrayList<MListItem> mlistItem= new ArrayList<MListItem>();
	MListItem mItem;
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

    	task = new phpDown();    
    	m_mon = (TextView)findViewById(R.id.m_mon);
    	m_tue = (TextView)findViewById(R.id.m_tue);
    	m_wed = (TextView)findViewById(R.id.m_wed);
    	m_thu = (TextView)findViewById(R.id.m_thu);
    	m_fri = (TextView)findViewById(R.id.m_fri);
    	hb_mon = (TextView)findViewById(R.id.hb_mon);
    	hb_tue = (TextView)findViewById(R.id.hb_tue);
    	hb_wed = (TextView)findViewById(R.id.hb_wed);
    	hb_thu = (TextView)findViewById(R.id.hb_thu);
    	hb_fri = (TextView)findViewById(R.id.hb_fri);
    	hs_mon = (TextView)findViewById(R.id.hs_mon);
    	hs_tue = (TextView)findViewById(R.id.hs_tue);
    	hs_wed = (TextView)findViewById(R.id.hs_wed);
    	hs_thu = (TextView)findViewById(R.id.hs_thu);
    	hs_fri = (TextView)findViewById(R.id.hs_fri);
	    hd_mon = (TextView)findViewById(R.id.hd_mon);
    	hd_tue = (TextView)findViewById(R.id.hd_tue);
    	hd_wed = (TextView)findViewById(R.id.hd_wed);
    	hd_thu = (TextView)findViewById(R.id.hd_thu);
    	hd_fri = (TextView)findViewById(R.id.hd_fri);
    	//imView = (ImageView) findViewById(R.id.imageView1);
        
        task.execute("http://swc.dothome.co.kr/DB/food.php");
        
        Button food_map= (Button) findViewById(R.id.food_map);
        food_map.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MapActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button food_major = (Button) findViewById(R.id.food_major);
        food_major.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MajorActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button food_tab = (Button) findViewById(R.id.food_tab);
        food_tab.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
      //�ϴ�
        Button food_back = (Button) findViewById(R.id.food_back);
        food_back.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button food_home = (Button) findViewById(R.id.food_home);
        food_home.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		finish();
        	}
        });
        Button food_card = (Button) findViewById(R.id.food_card);
        food_card.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),CLogin.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button food_logout = (Button) findViewById(R.id.food_logout);
        food_logout.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
        		startActivity(intent);
        	}
        });
	}
	private class phpDown extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{

                      // 연결 url 설정
                      URL url = new URL(urls[0]);
                      // 커넥션 객체 생성
                      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                      // 연결되었으면.
                      if(conn != null){
                         conn.setConnectTimeout(10000);
                         conn.setUseCaches(false);
                         // 연결되었음 코드가 리턴되면.
                         if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                            for(;;){
                                // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.  
                                String line = br.readLine();
                                if(line == null) break;
                                // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
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
        	
        	
            String M_MON;
            String M_TUE;
            String M_WED;
            String M_THU;
            String M_FRI;
            String HB_MON;
            String HB_TUE;
            String HB_WED;
            String HB_THU;
            String HB_FRI;            
            String HS_MON;
            String HS_TUE;
            String HS_WED;
            String HS_THU;
            String HS_FRI;
            String HD_MON;
            String HD_TUE;
            String HD_WED;
            String HD_THU;
            String HD_FRI;
            
            try{
            	
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                
                for(int i=0; i<ja.length(); i++){
                	
                    JSONObject jo = ja.getJSONObject(i);
                    
                    M_MON = jo.getString("M_MON");
                    M_TUE = jo.getString("M_TUE");
                    M_WED = jo.getString("M_WED");
                    M_THU = jo.getString("M_THU");
                    M_FRI = jo.getString("M_FRI");                    
                    HB_MON = jo.getString("HB_MON");
                    HB_TUE = jo.getString("HB_TUE");
                    HB_WED = jo.getString("HB_WED");
                    HB_THU = jo.getString("HB_THU");
                    HB_FRI = jo.getString("HB_FRI");                    
                    HS_MON = jo.getString("HS_MON");
                    HS_TUE = jo.getString("HS_TUE");
                    HS_WED = jo.getString("HS_WED");
                    HS_THU = jo.getString("HS_THU");
                    HS_FRI = jo.getString("HS_FRI");
                    HD_MON = jo.getString("HD_MON");
                    HD_TUE = jo.getString("HD_TUE");
                    HD_WED = jo.getString("HD_WED");
                    HD_THU = jo.getString("HD_THU");
                    HD_FRI = jo.getString("HD_FRI");
                    
                    mlistItem.add(new MListItem(
                            M_MON,
                            M_TUE,
                            M_WED,
                            M_THU,
                            M_FRI,                            
                            HB_MON,
                            HB_TUE,
                            HB_WED,
                            HB_THU,
                            HB_FRI,                            
                            HS_MON,
                            HS_TUE,
                            HS_WED,
                            HS_THU,
                            HS_FRI,
                            HD_MON,
                            HD_TUE,
                            HD_WED,
                            HD_THU,
                            HD_FRI));
                }
            } catch(JSONException e){
                e.printStackTrace();
            }
            m_mon.setText(mlistItem.get(0).getData(0));
            m_tue.setText(mlistItem.get(0).getData(1));
            m_wed.setText(mlistItem.get(0).getData(2));
            m_thu.setText(mlistItem.get(0).getData(3));
            m_fri.setText(mlistItem.get(0).getData(4));            
            hb_mon.setText(mlistItem.get(0).getData(5));
            hb_tue.setText(mlistItem.get(0).getData(6));
            hb_wed.setText(mlistItem.get(0).getData(7));
            hb_thu.setText(mlistItem.get(0).getData(8));
            hb_fri.setText(mlistItem.get(0).getData(9));            
            hs_mon.setText(mlistItem.get(0).getData(10));
            hs_tue.setText(mlistItem.get(0).getData(11));
            hs_wed.setText(mlistItem.get(0).getData(12));
            hs_thu.setText(mlistItem.get(0).getData(13));
            hs_fri.setText(mlistItem.get(0).getData(14));
            hd_mon.setText(mlistItem.get(0).getData(15));
            hd_tue.setText(mlistItem.get(0).getData(16));
            hd_wed.setText(mlistItem.get(0).getData(17));
            hd_thu.setText(mlistItem.get(0).getData(18));
            hd_fri.setText(mlistItem.get(0).getData(19));
        }
    }
}