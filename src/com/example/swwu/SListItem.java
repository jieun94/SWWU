package com.example.swwu;

public class SListItem {
    private String[] mData;
    	public SListItem(String[] data ){    		
        mData = data;        
    }

    public SListItem(String INF_IMG, String M_MAJOR, String INF_ID, String INF_NAME) {
        mData = new String[4];
        mData[0] = INF_IMG; 
        mData[1] = M_MAJOR; 
        mData[2] = INF_ID;
        mData[3] = INF_NAME;
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