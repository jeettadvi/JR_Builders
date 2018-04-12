package com.example.jeett.jr_builders;

/**
 * Created by jeett on 08-Apr-18.
 */

public class Upload {

    public String name;
    public String url;

    public Upload() {
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url= url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

