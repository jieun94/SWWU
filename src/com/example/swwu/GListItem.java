package com.example.swwu;

public class GListItem {
    private String[] mData;
    	public GListItem(String[] data ){    		
        mData = data;        
    }

    public GListItem(String G_CHOOSE, String G_GET, String G_T_AVER)
    		 {
        mData = new String[3];
        mData[0] = G_CHOOSE; 
        mData[1] = G_GET;
        mData[2] = G_T_AVER;
    }

    public String[] getData(){    
        return mData;
    }

    public String getData(int index){
        return mData[index];
    }

    public void setData(String[] data){
        mData = data;
    }
}