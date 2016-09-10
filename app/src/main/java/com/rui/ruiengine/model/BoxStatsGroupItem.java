package com.rui.ruiengine.model;

import android.graphics.Bitmap;

/**
 * Created by rhuang on 1/11/16.
 */
public class BoxStatsGroupItem {
    Bitmap mIcon;
    String mTitle;

    public BoxStatsGroupItem(Bitmap icon, String title) {
        super();
        mIcon = icon;
        mTitle = title;
    }

    public Bitmap getIcon() {
        return mIcon;
    }

    public void setIcon(Bitmap icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

}
