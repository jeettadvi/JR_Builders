package com.example.jeett.jr_builders;

/**
 * Created by jeett on 09-Apr-18.
 */

public class property {
    public String tital;
    public String discription;
   public String url;
    public String mLat;
    public String mLon;


    public property(){

    }

    public property(String title, String description, String mLat, String mLon,String url) {
        this.tital=title;
        this.discription=description;
        this.mLat = mLat;
        this.mLon=mLon;
        this.url = url;
    }

    public String getTital() {
        return tital;
    }

    public String getDiscription() {
        return discription;
    }

    public String getmLat() {
        return mLat;
    }

    public String getmLon() {
        return mLon;
    }

    public String getUrl() {
        return url;
    }
}

