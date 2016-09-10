package com.rui.ruiengine.model;

/**
 * Created by rhuang on 1/2/16.
 */
public class NetElementInfo {
    // Data
    private String mName;
    private String mAddress;

    public NetElementInfo(String name, String address) {
        mName = name;
        mAddress = address;
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAddress(String address) { mAddress = address; }



}
