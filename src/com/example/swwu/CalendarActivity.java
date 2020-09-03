package com.example.swwu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity extends Activity {
	
	TextView today_day;
	TextView today_content;
	
	phpDown task;
	//back task;
    ArrayList<CListItem> clistItem= new ArrayList<CListItem>();
	CListItem cItem;
	
	
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
        	
        	
            String CAL_DAY;
            String CAL_CONTENT;

            try{
            	
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                
                for(int i=0; i<ja.length(); i++){
                	
                    JSONObject jo = ja.getJSONObject(i);
                    
                    CAL_DAY = jo.getString("CAL_DAY");
                    CAL_CONTENT = jo.getString("CAL_CONTENT");
                    
                }
                for(int i = 0; i < ja.length(); i++) {
              	  CListItem cItem = new CListItem();
              	JSONObject jo = ja.getJSONObject(i);
              	
              	  cItem.mData = jo.getString("CAL_DAY");
              	  cItem.mContent = jo.getString("CAL_CONTENT");

              	  clistItem.add(cItem);
              	}
            } catch(JSONException e){
                e.printStackTrace();
            }
            
            
        }
    }
	
	public GregorianCalendar month, itemmonth;// calendar instances.

   public CalendarAdapter adapter;// adapter instance
   public Handler handler;// for grabbing some event values for showing the dot
                     // marker.
   public ArrayList<String> items; // container to store calendar items which
                           // needs showing the event marker

   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.calendar);
      
      task = new phpDown();    
      today_day = (TextView)findViewById(R.id.today_day);
      today_content = (TextView)findViewById(R.id.today_content);
      
      task.execute("http://swc.dothome.co.kr/DB/calendar.php");
      
    //�ϴ�
	   Button calendar_back = (Button) findViewById(R.id.calendar_back);
	   calendar_back.setOnClickListener(new OnClickListener() {
	   	
	   	public void onClick(View v) {
	   		finish();
	   	}
	   });
	   Button calendar_home = (Button) findViewById(R.id.calendar_home);
	   calendar_home.setOnClickListener(new OnClickListener() {
	   	
	   	public void onClick(View v) {
	   		finish();
	   	}
	   });
	   Button calendar_card = (Button) findViewById(R.id.calendar_card);
	   calendar_card.setOnClickListener(new OnClickListener() {
	   	
	   	public void onClick(View v) {
	   		Intent intent = new Intent(getApplicationContext(),CLogin.class);
	   		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    		startActivity(intent);
    		
    		overridePendingTransition(0,0);
	   	}
	   });
	   Button calendar_logout = (Button) findViewById(R.id.calendar_logout);
	   calendar_logout.setOnClickListener(new OnClickListener() {
	   	
	   	public void onClick(View v) {
	   		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
	   		startActivity(intent);
	   	}
	   });
	   
	   //Ķ����
       Locale.setDefault( Locale.KOREA );
      month = (GregorianCalendar) GregorianCalendar.getInstance();
      itemmonth = (GregorianCalendar) month.clone();

      items = new ArrayList<String>();
      adapter = new CalendarAdapter(this, month);

      GridView gridview = (GridView) findViewById(R.id.gridview);
      gridview.setAdapter(adapter);

      handler = new Handler();
      handler.post(calendarUpdater);

      TextView title = (TextView) findViewById(R.id.title);
      title.setText(android.text.format.DateFormat.format("yyyy - MM", month));

      RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

      previous.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            setPreviousMonth();
            refreshCalendar();
         }
      });

      RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
      next.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            setNextMonth();
            refreshCalendar();

         }
      });


      gridview.setOnItemClickListener(new OnItemClickListener() {


		public void onItemClick(AdapterView<?> parent, View v,
               int position, long id) {

            ((CalendarAdapter) parent.getAdapter()).setSelected(v);
            String selectedGridDate = CalendarAdapter.dayString.get(position);
            String[] separatedTime = selectedGridDate.split("-");
            String gridvalueString = separatedTime[2].replaceFirst("^0*",
                  "");// taking last part of date. ie; 2 from 2012-12-02.
            int gridvalue = Integer.parseInt(gridvalueString);
            // navigate to next or previous month on clicking offdays.
            if ((gridvalue > 10) && (position < 8)) {
               setPreviousMonth();
               refreshCalendar();
            } else if ((gridvalue < 7) && (position > 28)) {
               setNextMonth();
               refreshCalendar();
            }
            ((CalendarAdapter) parent.getAdapter()).setSelected(v);

            for(int i = 0; i < clistItem.size(); i++) {
                if( CalendarAdapter.dayString.get(position).equals(clistItem.get(i).mData) ) {
                   today_day.setText(clistItem.get(i).mData);
                     today_content.setText(clistItem.get(i).mContent);
                     break;
                }
            }

       }});
   }
 
   protected void setNextMonth() {
      if (month.get(GregorianCalendar.MONTH) == month
            .getActualMaximum(GregorianCalendar.MONTH)) {
         month.set((month.get(GregorianCalendar.YEAR) + 1),
               month.getActualMinimum(GregorianCalendar.MONTH), 1);
      } else {
         month.set(GregorianCalendar.MONTH,
               month.get(GregorianCalendar.MONTH) + 1);
      }

   }

   protected void setPreviousMonth() {
      if (month.get(GregorianCalendar.MONTH) == month
            .getActualMinimum(GregorianCalendar.MONTH)) {
         month.set((month.get(GregorianCalendar.YEAR) - 1),
               month.getActualMaximum(GregorianCalendar.MONTH), 1);
      } else {
         month.set(GregorianCalendar.MONTH,
               month.get(GregorianCalendar.MONTH) - 1);
      }

   }


   public void refreshCalendar() {
      TextView title = (TextView) findViewById(R.id.title);

      adapter.refreshDays();
      adapter.notifyDataSetChanged();
      handler.post(calendarUpdater); // generate some calendar items

      title.setText(android.text.format.DateFormat.format("yyyy - MM", month));
   }

   public Runnable calendarUpdater = new Runnable() {

      @Override
      public void run() {
         
         adapter.setItems(items);
         adapter.notifyDataSetChanged();
      }
   };
   
}