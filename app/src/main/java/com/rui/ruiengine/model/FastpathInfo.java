package com.rui.ruiengine.model;

import android.graphics.Bitmap;

/**
 * Created by rhuang on 3/27/16.
 */
public class FastpathInfo {

    Bitmap mImage;
    String mDescription;
    String mType;
    String mModel;
    String mManufacturer;
    String mMac;
    String mSystem;

    public FastpathInfo() {
        super();

    }

    public Bitmap getIamge() {
        return mImage;
    }
    public void setIamge(Bitmap image) {
        mImage = image;
    }

    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }

    public String getType() {
        return mType;
    }
    public void setType(String type) {
        mType = type;
    }

    public String getModel() {
        return mModel;
    }
    public void setModel(String model) {
        mModel = model;
    }

    public String getManufacturer() {
        return mManufacturer;
    }
    public void setManufacturer(String manufacturer) {
        mManufacturer = manufacturer;
    }

    public String getMac() {
        return mMac;
    }
    public void setMac(String mac) {
        mMac = mac;
    }

    public String getSystem() {
        return mSystem;
    }
    public void setSystem(String system) {
        mSystem = system;
    }


}
