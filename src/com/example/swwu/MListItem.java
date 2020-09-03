package com.example.swwu;

public class MListItem {
    private String[] mData;
    	public MListItem(String[] data ){    		
        mData = data;        
    }

    public MListItem(String M_MON, String M_TUE, String M_WED, String M_THU, String M_FRI,
    				String HB_MON, String HB_TUE, String HB_WED, String HB_THU, String HB_FRI,
    				String HS_MON, String HS_TUE, String HS_WED, String HS_THU, String HS_FRI,
    				String HD_MON, String HD_TUE, String HD_WED, String HD_THU, String HD_FRI) {
    	
        mData = new String[20];
        mData[0] = M_MON;
        mData[1] = M_TUE;        
        mData[2] = M_WED;
        mData[3] = M_THU;    
        mData[4] = M_FRI;        
        mData[5] = HB_MON;
        mData[6] = HB_TUE;        
        mData[7] = HB_WED;
        mData[8] = HB_THU;    
        mData[9] = HB_FRI;        
        mData[10] = HS_MON;
        mData[11] = HS_TUE;        
        mData[12] = HS_WED;
        mData[13] = HS_THU;    
        mData[14] = HS_FRI;
        mData[15] = HD_MON;
        mData[16] = HD_TUE;        
        mData[17] = HD_WED;
        mData[18] = HD_THU;    
        mData[19] = HD_FRI;

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