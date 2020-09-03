package com.example.swwu;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GradeActivity extends Activity {

   TextView gradechoose;
   TextView gradeget;
   TextView gradeaverage;
   // back task;
   ArrayList<GListItem> glistItem = new ArrayList<GListItem>();
   GListItem Item;

   // list
   JSONObject jsonobject;
   JSONArray jsonarray;
   ListView listview;
   ListViewAdapter adapter;
   ProgressDialog mProgressDialog;
   ArrayList<HashMap<String, String>> arraylist ;
   static String C_NAME = "cname";
   static String C_GRADE = "cgrade";
   static String G_AVER = "gaver";
   static String G_RATE = "grate";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.grade);

      final TextView gradechoose = (TextView)findViewById(R.id.gradechoose);
      final TextView gradeget = (TextView)findViewById(R.id.gradeget);
      final TextView gradeaverage = (TextView)findViewById(R.id.gradeaverage);

      System.out.println("==================grade================");
      (new AsyncTask<Void, Void, Void>() {
         @Override
         protected Void doInBackground(Void... params) {
            try {
               Util util = new Util();

               String response = util.submitWithParameters("/DB/grade.php", null);

               System.out.println("grade Response :" + response); //메시지 요청이 제대로 됬는지 확인용!
               JSONObject jsonObject = new JSONObject(response);

               JSONArray results = jsonObject.getJSONArray("results");
               
               //size 1보다 크면에러
               JSONObject result = (JSONObject) results.get(0);

               final String gGCHOOSE = result.getString("G_CHOOSE"); 
               final String gGGET = result.getString("G_GET");
               final String gGTAVER = result.getString("G_T_AVER");

               System.out.println("gG_CHOOSE = "+ gGCHOOSE);
               System.out.println("gG_GET = "+ gGGET);
               System.out.println("gG_T_AVER = "+ gGTAVER);

               runOnUiThread(new Runnable() {
                  public void run() {
                     gradechoose.setText(gGCHOOSE);
                     gradeget.setText(gGGET);
                     gradeaverage.setText(gGTAVER);

                  }
               });
               
               
               String gradetResponse = util.submitWithParameters("/DB/gradet.php", null);

               System.out.println("gradet Response :" + gradetResponse); //메시지 요청이 제대로 됬는지 확인용!
               JSONObject gradetJsonObject = new JSONObject(gradetResponse);

               arraylist = new ArrayList<HashMap<String, String>>();

               JSONArray gradetResults = gradetJsonObject.getJSONArray("results");
               for (int i = 0; i < gradetResults.length(); i++) {
                  JSONObject gradetTesult = (JSONObject) gradetResults.get(i);
                  HashMap<String, String> map = new HashMap<String, String>();
                  map.put("cname", gradetTesult.getString("C_NAME"));
                  map.put("cgrade", gradetTesult.getString("C_GRADE"));
                  map.put("gaver", gradetTesult.getString("G_AVER"));
                  map.put("grate", gradetTesult.getString("G_RATE"));
                  // Set the JSON Objects into the array
                  arraylist.add(map);
               }
               runOnUiThread(new Runnable() {
                  public void run() {
                      listview = (ListView) findViewById(R.id.gradeview);
                        // Pass the results into ListViewAdapter.java
                        adapter = new ListViewAdapter(GradeActivity.this, arraylist);
                        System.out.println(arraylist.size());
                        // Set the adapter to the ListView
                        listview.setAdapter(adapter);
                        // Close the progressdialog
                        //mProgressDialog.dismiss();

                  }
               });

            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            return null;
         }

      }).execute(null, null, null);

      // list
      /*(new AsyncTask<Void, Void, Void>() {
         @Override
         protected Void doInBackground(Void... params) {
            try {
               Util util = new Util();

               String response = util.submitWithParameters("/DB/gradet.php", null);

               System.out.println("grade t Response :" + response); //메시지 요청이 제대로 됬는지 확인용!
               JSONObject jsonObject = new JSONObject(response);

               arraylist = new ArrayList<HashMap<String, String>>();

               JSONArray results = jsonObject.getJSONArray("results");
               for (int i = 0; i < results.length(); i++) {
                  JSONObject result = (JSONObject) results.get(i);
                  HashMap<String, String> map = new HashMap<String, String>();
                  map.put("cname", jsonobject.getString("C_NAME"));
                  map.put("cgrade", jsonobject.getString("C_GRADE"));
                  map.put("gaver", jsonobject.getString("G_AVER"));
                  map.put("grate", jsonobject.getString("G_RATE"));
                  // Set the JSON Objects into the array
                  arraylist.add(map);
               }
               runOnUiThread(new Runnable() {
                  public void run() {
                      listview = (ListView) findViewById(R.id.gradeview);
                        // Pass the results into ListViewAdapter.java
                        adapter = new ListViewAdapter(GradeActivity.this, arraylist);
                        System.out.println(arraylist.size());
                        // Set the adapter to the ListView
                        listview.setAdapter(adapter);
                        // Close the progressdialog
                        mProgressDialog.dismiss();

                  }
               });

            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            return null;
         }

      }).execute(null, null, null);*/
      // GridView

      Button grade_timetable = (Button) findViewById(R.id.grade_timetable);
      grade_timetable.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),
                  TimetableActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            overridePendingTransition(0, 0);
         }
      });

      Button grade_tab = (Button) findViewById(R.id.grade_tab);
      grade_tab.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            finish();
         }
      });

      Button grade_bill = (Button) findViewById(R.id.grade_bill);
      grade_bill.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),
                  BillActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            overridePendingTransition(0, 0);
         }
      });

      // 占싹댐옙

      Button grade_back = (Button) findViewById(R.id.grade_back);
      grade_back.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            finish();
         }
      });
      Button grade_home = (Button) findViewById(R.id.grade_home);
      grade_home.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
         }
      });
      Button grade_card = (Button) findViewById(R.id.grade_card);
      grade_card.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),
                  StudentIDActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            overridePendingTransition(0, 0);
         }
      });
      Button grade_logout = (Button) findViewById(R.id.grade_logout);
      grade_logout.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),
                  LogoutActivity.class);
            startActivity(intent);
         }
      });
   }


}