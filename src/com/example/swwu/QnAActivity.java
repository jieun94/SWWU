package com.example.swwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class QnAActivity extends Activity {

	WebView web;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna);
        
        web = (WebView) this.findViewById(R.id.qnaview);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        
        web.loadUrl("http://swc.dothome.co.kr/qna.php");
        
        //���
        Button qna_tab = (Button) findViewById(R.id.qna_tab);
        qna_tab.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });

      //�ϴ�
        Button qna_back = (Button) findViewById(R.id.qna_back);
        qna_back.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button qna_home = (Button) findViewById(R.id.qna_home);
        qna_home.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button qna_card = (Button) findViewById(R.id.qna_card);
        qna_card.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),CLogin.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button qna_logout = (Button) findViewById(R.id.qna_logout);
        qna_logout.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
        		startActivity(intent);
        	}
        });
        
	}
}