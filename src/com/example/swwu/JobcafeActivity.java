package com.example.swwu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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

public class JobcafeActivity extends Activity {
   
    Button Btnlogin;
    EditText inputID, inputPW;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobcafe);
        
        Btnlogin = (Button)findViewById(R.id.job_login_button);
        inputID = (EditText)findViewById(R.id.job_id_edit);
        inputPW = (EditText)findViewById(R.id.job_password_edit);
        
        Btnlogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                 dialog = ProgressDialog.show(JobcafeActivity.this, "", 
                            "Validating user...", true);
                         //�α��ι�ư ������ ��� ��ٸ��� ���� ��µǴ� ���̾�α�
                     new Thread(new Runnable() {
                            public void run() {
                                login(); 
                            }
                          }).start();               
            }
        });
        
        Button jobcafe_back = (Button) findViewById(R.id.job_back);
        jobcafe_back.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button jobcafe_home = (Button) findViewById(R.id.job_home);
        jobcafe_home.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              finish();
           }
        });
        Button jobcafe_card = (Button) findViewById(R.id.job_card);
        jobcafe_card.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),StudentIDActivity.class);
              startActivity(intent);
           }
        });
        Button jobcafe_logout = (Button) findViewById(R.id.job_logout);
        jobcafe_logout.setOnClickListener(new OnClickListener() {
           
           public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
              startActivity(intent);
           }
        });
    }//onCreate ��
   
    void login(){
        try{            
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://swc.dothome.co.kr/DB/login.php");
            // ���⼭�� ���� AVD�� �����ϱ� ������ �� ������ �ּҸ� ���
            // ���� ������ ���� ��� ���� �ּҸ� ��� 192.168.14.185:80
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("INF_ID",inputID.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("INF_PW",inputPW.getText().toString().trim())); 
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response=httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response :" + response); //�޽��� ��û�� ����� ����� Ȯ�ο�!
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
                        Toast.makeText(JobcafeActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                        //�α��ο� �����ϸ� �佺Ʈ�޽��� ����ϰ�,
                    }
                });
                startActivity(new Intent(JobcafeActivity.this,JobcafeBActivity.class));
                 //�α��� ����� ���� ȭ������ �Ѿ!
                finish();
            }
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
      }
        public void showAlert(){
        	JobcafeActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobcafeActivity.this);
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
        
 
      //�ϴ�
        
        
   }
}