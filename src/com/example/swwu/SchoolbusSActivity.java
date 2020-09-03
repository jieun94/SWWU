package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SchoolbusSActivity extends Activity {

   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_s);
        
        //상단
        Button school_s_i = (Button) findViewById(R.id.school_s_i);
        school_s_i.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        
        Button school_s_h = (Button) findViewById(R.id.school_s_h);
        school_s_h.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
        	Intent intent = new Intent(getApplicationContext(),SchoolbusHActivity.class);
       		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
       		startActivity(intent);
       		
       		overridePendingTransition(0,0);
       	}
       });
        
        Button school_s_tab = (Button) findViewById(R.id.school_s_tab);
        school_s_tab.setOnClickListener(new OnClickListener() {
           
        	   public void onClick(View v) {
               	Intent intent = new Intent(getApplicationContext(),SchoolbusSActivity.class);
              		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              		startActivity(intent);
              		
              		overridePendingTransition(0,0);
              	}
              });
        
      //하단
        
        Button school_s_back = (Button) findViewById(R.id.school_s_back);
        school_s_back.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button school_s_home = (Button) findViewById(R.id.school_s_home);
        school_s_home.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
        	   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
          		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
          		startActivity(intent);
          		finish();
           }
        });
        Button school_s_card = (Button) findViewById(R.id.school_s_card);
        school_s_card.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),CLogin.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
      		startActivity(intent);
      		
      		overridePendingTransition(0,0);
           }
        });
        Button school_s_logout = (Button) findViewById(R.id.school_s_logout);
        school_s_logout.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
              startActivity(intent);
           }
        });
        
        
   }
}