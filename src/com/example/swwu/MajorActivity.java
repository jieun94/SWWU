package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MajorActivity extends Activity {

	WebView web;
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.major);
        
        web = (WebView) this.findViewById(R.id.major);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        
        web.loadUrl("http://swc.dothome.co.kr/DB/search.html");
        
        Button major_map= (Button) findViewById(R.id.major_map);
        major_map.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MapActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        
        
        Button major_tab = (Button) findViewById(R.id.major_tab);
        major_tab.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button major_food = (Button) findViewById(R.id.major_food);
        major_food.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),FoodActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        
        
        //�ϴ�
        Button major_back = (Button) findViewById(R.id.major_back);
        major_back.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button major_home = (Button) findViewById(R.id.major_home);
        major_home.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		finish();
        	}
        });
        Button major_card = (Button) findViewById(R.id.major_card);
        major_card.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),CLogin.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button major_logout = (Button) findViewById(R.id.major_logout);
        major_logout.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
        		startActivity(intent);
        	}
        });
	}
}