package com.kiitanakala.maps_application;

public class Merchant implements Comparable {
    
    public String mName, mCategory, mID;
    public double mLongitude, mLatitude;
    public long mTotalDollar;
    public int mTotalTrans;
    
    
    public Merchant(String name, double longitude, double latitude, String category, String ID){
        mName = name;
        mLongitude = longitude ;
        mLatitude = latitude ;
        mTotalDollar = 0 ;
        mCategory= category;
        mID = ID;
        mTotalTrans = 0;
    }
    
    public Merchant() {}
    
    public void addToTrans() {
        mTotalTrans++;
    }
    
    public void addTo(double x){
        mTotalDollar += x;
    }
    public String getCat(){
        return mCategory.substring(0, 3);
    }
    
    public String toString(){
        return mName + " " + " " + mCategory + " " + mID + " " + mTotalDollar + " " + mTotalTrans;
    }
    
    @Override
    public int compareTo(Object other) {
        Merchant temp = (Merchant) other;
        if (this.mTotalDollar < temp.mTotalDollar) {
            return -1;
        } else if (this.mTotalDollar > temp.mTotalDollar) {
            return 1;
        } else {
            return 0;
        }
    }
}
