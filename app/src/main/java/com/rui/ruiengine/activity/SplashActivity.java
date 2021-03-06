package com.rui.ruiengine.activity;


import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.rui.ruiengine.util.AppConstants;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import com.rui.ruiengine.R;

/**
 * Created by rhuang on 3/7/16.
 */
public class SplashActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        getSupportActionBar().hide();

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.primary_darker); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(5000); //int ms

        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(AppConstants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(200); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(200); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.accent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(5000);
        configSplash.setPathSplashFillColor(R.color.accent); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("Rui Engine");
        configSplash.setTitleTextColor(R.color.accent);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("OpenSans-Regular.ttf");

    }

    @Override
    public void animationsFinished() {

        // We start MainActivity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }
}
