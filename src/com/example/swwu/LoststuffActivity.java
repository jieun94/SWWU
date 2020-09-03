package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class LoststuffActivity extends Activity {
	
	WebView web;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loststuff);
        
        web = (WebView) this.findViewById(R.id.loststuffview);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        
        web.loadUrl("http://swc.dothome.co.kr/loststuff.php");
        
      //���
        Button loststuff_tab= (Button) findViewById(R.id.loststuff_tab);
        loststuff_tab.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
      
        
      //�ϴ�
        Button loststuff_back = (Button) findViewById(R.id.loststuff_back);
        loststuff_back.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button loststuff_home = (Button) findViewById(R.id.loststuff_home);
        loststuff_home.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button loststuff_card = (Button) findViewById(R.id.loststuff_card);
        loststuff_card.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),CLogin.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button loststuff_logout = (Button) findViewById(R.id.loststuff_logout);
        loststuff_logout.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
        		startActivity(intent);
        	}
        });
	}
}