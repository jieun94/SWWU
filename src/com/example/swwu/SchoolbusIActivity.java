package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SchoolbusIActivity extends Activity {

   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_i);
        
        //상단
        Button school_i_tab = (Button) findViewById(R.id.school_i_tab);
        school_i_tab.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        
        Button school_i_h = (Button) findViewById(R.id.school_i_h);
        school_i_h.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
        	Intent intent = new Intent(getApplicationContext(),SchoolbusHActivity.class);
       		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
       		startActivity(intent);
       		
       		overridePendingTransition(0,0);
       	}
       });
        
        Button school_i_s = (Button) findViewById(R.id.school_i_s);
        school_i_s.setOnClickListener(new OnClickListener() {
           
        	   public void onClick(View v) {
               	Intent intent = new Intent(getApplicationContext(),SchoolbusSActivity.class);
              		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
              		startActivity(intent);
              		
              		overridePendingTransition(0,0);
              	}
              });
        
      //하단
        
        Button school_i_back = (Button) findViewById(R.id.school_i_back);
        school_i_back.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button school_i_home = (Button) findViewById(R.id.school_i_home);
        school_i_home.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
        	   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
          		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
          		startActivity(intent);
          		finish();
           }
        });
        Button school_i_card = (Button) findViewById(R.id.school_i_card);
        school_i_card.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),CLogin.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
      		startActivity(intent);
      		
      		overridePendingTransition(0,0);
           }
        });
        Button school_i_logout = (Button) findViewById(R.id.school_i_logout);
        school_i_logout.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
              startActivity(intent);
           }
        });
        
        
   }
}