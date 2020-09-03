package com.example.swwu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CLogin extends Activity {
   
    Button Btnlogin;
    EditText INF_ID, INF_PW;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Btnlogin = (Button)findViewById(R.id.logout_login_button);
        INF_ID = (EditText)findViewById(R.id.login_id_edit);
        INF_PW = (EditText)findViewById(R.id.login_password_edit);
        
        
        Btnlogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                 dialog = ProgressDialog.show(CLogin.this, "", 
                            "Validating user...", true);
                         //�α��ι�ư ������ ��� ��ٸ��� ���� ��µǴ� ���̾�α�
                     new Thread(new Runnable() {
                            public void run() {
                                login(); 
                            }
                          }).start();               
            }
        });
    }//onCreate ��
   
    void login(){
        try{  
        	
        	String infId = ((EditText)findViewById(R.id.login_id_edit)).getText().toString();
			String infPw = ((EditText)findViewById(R.id.login_password_edit)).getText().toString();
    		Util util = new Util();
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("INF_ID", infId);
    		map.put("INF_PW", infPw);
    		
    		String response = util.submitWithParameters("/DB/login.php", map);
            
			System.out.println("Login Response :" + response); //메시지 요청이 제대로 됬는지 확인용!
			JSONObject jsonObject = new JSONObject(response);
			String dataInfId = jsonObject.getString("INF_ID"); 
			String dataInfName = jsonObject.getString("INF_NAME");
			System.out.println("dataInfId = "+ dataInfId);
			System.out.println("dataInfName = "+ dataInfName);
            runOnUiThread(new Runnable() {
                public void run() {
                    dialog.dismiss(); 
                }
            });
             
            if(response.equalsIgnoreCase("No Such User Found")){
               showAlert();  
            }else{
               runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(CLogin.this,"Login Success", Toast.LENGTH_SHORT).show();
                        //�α��ο� �����ϸ� �佺Ʈ�޽��� ����ϰ�,
                    }
                });
                startActivity(new Intent(CLogin.this,StudentIDActivity.class));
                 //�α��� ����� ���� ȭ������ �Ѿ!
                finish();
            }
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
      }
    
        public void showAlert(){
           CLogin.this.runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CLogin.this);
                    builder.setTitle("Login Error.");
                    builder.setMessage("User not Found.")  
                           .setCancelable(false)
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                               }
                           });                     
                    AlertDialog alert = builder.create();
                    alert.show();               
                }
            });
        }
}