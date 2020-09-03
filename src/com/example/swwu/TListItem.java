package com.example.swwu;

public class TListItem {
    private String[] mData;
    	public TListItem(String[] data ){    		
        mData = data;        
    }

    public TListItem(String INF_CODE, String T_IMG) {
        mData = new String[2];
        mData[0] = INF_CODE; 
        mData[1] = T_IMG; 
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