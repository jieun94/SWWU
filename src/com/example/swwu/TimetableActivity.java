package com.example.swwu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TimetableActivity  extends Activity {

	ImageView imView;
	Bitmap bmImg;
	//phpDown task;
	//back imgaeLoadTask;  //추가
	//back task;
	ArrayList<TListItem> tlistItem= new ArrayList<TListItem>();
	TListItem tItem;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);

		//task = new phpDown();
		imView = (ImageView) findViewById(R.id.timetableimg);


		(new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					Util util = new Util();

					String response = util.submitWithParameters("/DB/timetable.php", null);

					System.out.println("timetable Response :" + response); //메시지 요청이 제대로 됬는지 확인용!
					JSONObject jsonObject = new JSONObject(response);
					JSONArray results = jsonObject.getJSONArray("results");
					JSONObject result = (JSONObject) results.get(0);
					String imageUrl = result.getString("T_IMG");

					
					new DownloadImageTask(imView).execute(imageUrl);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		}).execute(null, null, null);


		//imgaeLoadTask = new back();  //추가

		Button timetable_tab = (Button) findViewById(R.id.timetable_tab);
		timetable_tab.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

		Button timetable_grade = (Button) findViewById(R.id.timetable_grade);
		timetable_grade.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),GradeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);

				overridePendingTransition(0,0);
			}
		});

		Button timetable_bill = (Button) findViewById(R.id.timetable_bill);
		timetable_bill.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),BillActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);

				overridePendingTransition(0,0);
			}
		}); 


		// ϴ

		Button timetable_back = (Button) findViewById(R.id.timetable_back);
		timetable_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		Button timetable_home = (Button) findViewById(R.id.timetable_home);
		timetable_home.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
				finish();
			}
		});
		Button timetable_card = (Button) findViewById(R.id.timetable_card);
		timetable_card.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),StudentIDActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);

				overridePendingTransition(0,0);
			}
		});
		Button timetable_logout = (Button) findViewById(R.id.timetable_logout);
		timetable_logout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
				startActivity(intent);
			}
		});
	}
	
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}

}