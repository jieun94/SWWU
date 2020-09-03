package com.example.swwu;

public class JListItem {
    private String[] mData;
    	public JListItem(String[] data ){    		
        mData = data;        
    }

    public JListItem(String INF_JOB) {
        mData = new String[1];
        mData[0] = INF_JOB; 
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