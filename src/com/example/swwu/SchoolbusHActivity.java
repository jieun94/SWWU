package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SchoolbusHActivity extends Activity {

   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_h);
        
        //상단
        Button school_h_i = (Button) findViewById(R.id.school_h_i);
        school_h_i.setOnClickListener(new OnClickListener() {
           
        	public void onClick(View v) {
               	Intent intent = new Intent(getApplicationContext(),SchoolbusIActivity.class);
              		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              		startActivity(intent);
              		
              		overridePendingTransition(0,0);
              	}
              });
        
        Button school_h_tab = (Button) findViewById(R.id.school_h_tab);
        school_h_tab.setOnClickListener(new OnClickListener() {
           
        	public void onClick(View v) {
                finish();
             }
          });
        
        Button school_h_s = (Button) findViewById(R.id.school_h_s);
        school_h_s.setOnClickListener(new OnClickListener() {
           
        	   public void onClick(View v) {
               	Intent intent = new Intent(getApplicationContext(),SchoolbusSActivity.class);
              		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              		startActivity(intent);
              		
              		overridePendingTransition(0,0);
              	}
              });
        
      //하단
        
        Button school_h_back = (Button) findViewById(R.id.school_h_back);
        school_h_back.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button school_h_home = (Button) findViewById(R.id.school_h_home);
        school_h_home.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
        	    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
          		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
          		startActivity(intent);
          		finish();
           }
        });
        Button school_h_card = (Button) findViewById(R.id.school_h_card);
        school_h_card.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),CLogin.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
      		startActivity(intent);
      		
      		overridePendingTransition(0,0);
           }
        });
        Button school_h_logout = (Button) findViewById(R.id.school_h_logout);
        school_h_logout.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
              startActivity(intent);
           }
        });
        
        
   }
}