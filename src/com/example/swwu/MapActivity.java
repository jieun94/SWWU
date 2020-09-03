package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MapActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        //���
        Button map_tab= (Button) findViewById(R.id.map_tab);
        map_tab.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        
        Button map_major = (Button) findViewById(R.id.map_major);
        map_major.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MajorActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button map_food = (Button) findViewById(R.id.map_food);
        map_food.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),FoodActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        
      //�ϴ�
        Button map_back = (Button) findViewById(R.id.map_back);
        map_back.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button map_home = (Button) findViewById(R.id.map_home);
        map_home.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		finish();
        	}
        });
        Button map_card = (Button) findViewById(R.id.map_card);
        map_card.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),CLogin.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button map_logout = (Button) findViewById(R.id.map_logout);
        map_logout.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
        		startActivity(intent);
        	}
        });
	}
}