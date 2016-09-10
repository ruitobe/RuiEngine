package com.rui.ruiengine.model;

/**
 * Created by rhuang on 1/6/16.
 */
public class BoxInfo {
    private String mName;
    private String mNetInfo;
    private String mSwVersionInfo;
    private String mHwVersionInfo;
    private String mHwPartNum;
    private String mHwSerialNum;


    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

    public String getNetInfo() {
        return mNetInfo;
    }
    public void setNetInfo(String netInfo) {
        mNetInfo = netInfo;
    }

    public String getSwVersionInfo() {
        return mSwVersionInfo;
    }
    public void setSwVersionInfo(String swVersionInfo) {
        mSwVersionInfo = swVersionInfo;
    }

    public String getHwVersionInfo() {
        return mHwVersionInfo;
    }
    public void setHwVersionInfo(String hwVersionInfo) {
        mHwVersionInfo = hwVersionInfo;
    }

    public String getHwPartNum() {
        return mHwPartNum;
    }
    public void setHwPartNum(String hwPartNum) {
        mHwPartNum = hwPartNum;
    }

    public void setHwSerialNum(String serialNum) {
        mHwSerialNum = serialNum;
    }
    public String getHwSerialNum() {
        return mHwSerialNum;
    }

}
