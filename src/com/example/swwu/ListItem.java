package com.example.swwu;

public class ListItem {
    private String[] mData;
    	public ListItem(String[] data ){    		
        mData = data;        
    }

    public ListItem(String M_MAJOR, String INF_YEAR, String INF_ID, String INF_NAME,
    		String TU_ADME, String TU_TUIT, String TU_IMP, String TU_SF,
    		String TU_ALF, String TU_NEWSF, String TU_BUSF, String TU_BF,
    		String TU_LF, String TU_AAF, String TU_TOTAL) {
        mData = new String[15];
        mData[0] = M_MAJOR;
        mData[1] = INF_YEAR;        
        mData[2] = INF_ID;
        mData[3] = INF_NAME;    
        mData[4] = TU_ADME;
        mData[5] = TU_TUIT;        
        mData[6] = TU_IMP;
        mData[7] = TU_SF;    
        mData[8] = TU_ALF;
        mData[9] = TU_NEWSF;        
        mData[10] = TU_BUSF;
        mData[11] = TU_BF;    
        mData[12] = TU_LF;
        mData[13] = TU_AAF;  
        mData[14] = TU_TOTAL;  
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