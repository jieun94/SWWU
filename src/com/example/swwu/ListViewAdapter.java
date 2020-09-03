package com.example.swwu;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

   // Declare Variables
   Context context;
   LayoutInflater inflater;
   ArrayList<HashMap<String, String>> data;
   HashMap<String, String> resultp = new HashMap<String, String>();

   public ListViewAdapter(Context context,
         ArrayList<HashMap<String, String>> arraylist) {
      this.context = context;
      data = arraylist;
   }

   @Override
   public int getCount() {
      return data.size();
   }

   @Override
   public Object getItem(int position) {
      return null;
   }

   @Override
   public long getItemId(int position) {
      return 0;
   }

   public View getView(final int position, View convertView, ViewGroup parent) {
      // Declare Variables
      TextView cname;
      TextView cgrade;
      TextView gaver;
      TextView grate;

      inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      View itemView = inflater.inflate(R.layout.listview_item, parent, false);
      // Get the position
      resultp = data.get(position);

      // Locate the TextViews in listview_item.xml
      cname = (TextView) itemView.findViewById(R.id.cname);
      cgrade = (TextView) itemView.findViewById(R.id.cgrade);
      gaver = (TextView) itemView.findViewById(R.id.gaver);
      grate = (TextView) itemView.findViewById(R.id.grate);


      // Capture position and set results to the TextViews
      cname.setText(resultp.get(GradeActivity.C_NAME));
      cgrade.setText(resultp.get(GradeActivity.C_GRADE));
      gaver.setText(resultp.get(GradeActivity.G_AVER));
      grate.setText(resultp.get(GradeActivity.G_RATE));
      // Capture position and set results to the ImageView
      // Passes flag images URL into ImageLoader.class
      // Capture ListView item click
      
      return itemView;
   }
}