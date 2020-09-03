package com.example.swwu;
 
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
 
import org.json.JSONArray;
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
import android.widget.TextView;
 
public class StudentIDActivity  extends Activity {
 
	ImageView imView;
    Bitmap bmImg;
	back imgaeLoadTask;  //추가
	//back task;
    ArrayList<SListItem> slistItem= new ArrayList<SListItem>();
	SListItem sItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentid);
        
        final TextView studentid_major2 = (TextView)findViewById(R.id.studentid_major2);
        final TextView studentid_number2 = (TextView)findViewById(R.id.studentid_number2);
        final TextView studentid_name2 = (TextView)findViewById(R.id.studentid_name2);
 
        imView = (ImageView) findViewById(R.id.studentimg);
 
		
		
		
		System.out.println("==================card================");
		(new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
            	try {
            		Util util = new Util();
            		
            		
            		String response = util.submitWithParameters("/DB/card.php", null);
 
            		System.out.println("card Response :" + response); //메시지 요청이 제대로 됬는지 확인용!
            		JSONObject jsonObject = new JSONObject(response);
					
					JSONArray results = jsonObject.getJSONArray("results");
					
					
					for (int i = 0; i < results.length(); i++) {
						JSONObject result = (JSONObject) results.get(i);
				
						final String sMAJOR = result.getString("M_MAJOR"); 
						final String sINFID = result.getString("INF_ID");
						final String sINFNAME = result.getString("INF_NAME");
						String imageUrl = result.getString("INF_IMG");
						
    					System.out.println("sMAJOR = "+ sMAJOR);
    					System.out.println("sINFID = "+ sINFID);
    					System.out.println("sINFNAME = "+ sINFNAME);
    				
    					new DownloadImageTask(imView).execute(imageUrl);
    					
    					runOnUiThread(new Runnable() {
    	                    public void run() {
    	                    	
    	                    	studentid_major2.setText(sMAJOR);
    	                    	studentid_number2.setText(sINFID);
    	                    	studentid_name2.setText(sINFNAME);
    	                    }
    	                });
    					
					}
					
            	} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
            }
            
        }).execute(null, null, null);
		
		
		
 
		//imgaeLoadTask = new back();  //추가
 
		Button studentid_tab = (Button) findViewById(R.id.studentid_tab);
		studentid_tab.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {
				finish();
			}
		});
        
        //하단 
        
        Button studentid_back = (Button) findViewById(R.id.studentid_back);
        studentid_back.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button studentid_home = (Button) findViewById(R.id.studentid_home);
        studentid_home.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		finish();
        	}
        });
        Button studentid_card = (Button) findViewById(R.id.studentid_card);
        studentid_card.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),StudentIDActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        		
        		overridePendingTransition(0,0);
        	}
        });
        Button studentid_logout = (Button) findViewById(R.id.studentid_logout);
        studentid_logout.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(),LogoutActivity.class);
        		startActivity(intent);
        	}
        });
	}
	
	private class back extends AsyncTask<String, Integer, Bitmap>{
 
        @Override
        protected Bitmap doInBackground(String... urls) {
            // TODO Auto-generated method stub
            try{
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();             
                bmImg = BitmapFactory.decodeStream(is);
            }catch(IOException e){
                e.printStackTrace();
            }
            return bmImg;
        }
 
        protected void onPostExecute(Bitmap img){
            imView.setImageBitmap(bmImg);
        }
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