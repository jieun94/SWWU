package com.example.swwu;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BillActivity  extends Activity {
   

   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	   
	   // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy);
		
		
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);
        
        final TextView bill_major = (TextView)findViewById(R.id.bill_major);
		final TextView bill_year = (TextView)findViewById(R.id.bill_year);
		final TextView bill_id = (TextView)findViewById(R.id.bill_id);
		final TextView bill_name = (TextView)findViewById(R.id.bill_name);
		final TextView bill_adme = (TextView)findViewById(R.id.bill_adme);
		final TextView bill_tuit = (TextView)findViewById(R.id.bill_tuit);
		final TextView bill_imp = (TextView)findViewById(R.id.bill_imp);
		final TextView bill_sf = (TextView)findViewById(R.id.bill_sf);
		final TextView bill_alf = (TextView)findViewById(R.id.bill_alf);
		final TextView bill_newsf = (TextView)findViewById(R.id.bill_newsf);
		final TextView bill_busf = (TextView)findViewById(R.id.bill_busf);
		final TextView bill_bf = (TextView)findViewById(R.id.bill_bf);
		final TextView bill_lf = (TextView)findViewById(R.id.bill_lf);
		final TextView bill_aaf = (TextView)findViewById(R.id.bill_aaf);
		final TextView bill_sum = (TextView)findViewById(R.id.bill_sum);

				System.out.println("==================bill================");
				(new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                    	try {
                    		Util util = new Util();
                    		
                    		String response = util.submitWithParameters("/DB/bill.php", null);
                    		
                    		System.out.println("bill Response :" + response); //메시지 요청이 제대로 됬는지 확인용!
        					JSONObject jsonObject = new JSONObject(response);
					
        					JSONArray results = jsonObject.getJSONArray("results");
        					for (int i = 0; i < results.length(); i++) {
								JSONObject result = (JSONObject) results.get(i);
						
								final String bMAJOR = result.getString("M_MAJOR"); 
								final String bYEAR = result.getString("INF_YEAR");
								final String bID = result.getString("INF_ID");
								final String bNAME = result.getString("INF_NAME");
								final String bADME = result.getString("TU_ADME");
								final String bTUIT = result.getString("TU_TUIT");
								final String bIMP = result.getString("TU_IMP");
								final String bSF = result.getString("TU_SF");
								final String bALF = result.getString("TU_ALF");
								final String bNEWSF = result.getString("TU_NEWSF");
								final String bBUSF = result.getString("TU_BUSF");
								final String bBF = result.getString("TU_BF");
								final String bLF = result.getString("TU_LF");
								final String bAAF = result.getString("TU_AAF");
								final String bTOTAL = result.getString("TU_TOTAL");
								
		    					System.out.println("bM_MAJOR = "+ bMAJOR);
		    					System.out.println("bINF_YEAR = "+ bYEAR);
		    					System.out.println("bINF_ID = "+ bID);
		    					System.out.println("bINF_NAME = "+ bNAME);
		    					System.out.println("bTU_ADME = "+ bADME);
		    					System.out.println("bTU_TUIT = "+ bTUIT);
		    					System.out.println("bTU_IMP = "+ bIMP);
		    					System.out.println("bTU_SF = "+ bSF);
		    					System.out.println("bTU_ALF = "+ bALF);
		    					System.out.println("bTU_NEWSF = "+ bNEWSF);
		    					System.out.println("bTU_BUSF = "+ bBUSF);
		    					System.out.println("bTU_BF = "+ bBF);
		    					System.out.println("bTU_LF = "+ bLF);
		    					System.out.println("bTU_AAF = "+ bAAF);
		    					System.out.println("bTU_TOTAL = "+ bTOTAL);
		    				
		    					runOnUiThread(new Runnable() {
		    	                    public void run() {
		    	                    	bill_major.setText(bMAJOR);
		    	                    	bill_year.setText(bYEAR);
		    	                    	bill_id.setText(bID);
		    	                    	bill_name.setText(bNAME);
		    	                    	bill_adme.setText(bADME);
		    	                    	bill_tuit.setText(bTUIT);
		    	                    	bill_imp.setText(bIMP);
		    	                    	bill_sf.setText(bSF);
		    	                    	bill_alf.setText(bALF);
		    	                    	bill_newsf.setText(bNEWSF);
		    	                    	bill_busf.setText(bBUSF);
		    	                    	bill_bf.setText(bBF);
		    	                    	bill_lf.setText(bLF);
		    	                    	bill_aaf.setText(bAAF);
		    	                    	bill_sum.setText(bTOTAL);
		    	                    	
		    	                    }
		    	                });
		    					
							}
        					
                    	} catch (Exception e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
    					return null;
                    }
                    
                }).execute(null, null, null);
			
		

        
        
        Button bill_timetable = (Button) findViewById(R.id.bill_timetable);
        bill_timetable.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),TimetableActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              startActivity(intent);
              
              overridePendingTransition(0,0);
           }
        }); 
        
        Button bill_grade = (Button) findViewById(R.id.bill_grade);
        bill_grade.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {       		
       		Intent intent = new Intent(getApplicationContext(),GradeActivity.class);
       		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
       		startActivity(intent);
       		
       		overridePendingTransition(0,0);
       	}
       }); 
        
        Button bill_tab = (Button) findViewById(R.id.bill_tab);
        bill_tab.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        }); 
        

        
        //하단 
        
        Button bill_back = (Button) findViewById(R.id.bill_back);
        bill_back.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button bill_home = (Button) findViewById(R.id.bill_home);
        bill_home.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
   				
        	Intent intent = new Intent(getApplicationContext(),MainActivity.class);
       		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
       		startActivity(intent);
       		finish();
           }
        });
        Button bill_card = (Button) findViewById(R.id.bill_card);
        bill_card.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),StudentIDActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              startActivity(intent);
              
              overridePendingTransition(0,0);
           }
        });
        Button bill_logout = (Button) findViewById(R.id.bill_logout);
        bill_logout.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
              startActivity(intent);
           }
        });

   }
   
   
}