package com.rui.ruiengine.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rui.ruiengine.R;

/**
 * Created by rhuang on 1/2/16.
 */
public class FragmentAppHelp extends Fragment {

    public FragmentAppHelp() {

        // Empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_app_help, container, false);
    }
}
