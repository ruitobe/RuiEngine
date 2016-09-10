package com.rui.ruiengine.activity;

import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.ViewPagerAdapter;
import com.rui.ruiengine.fragment.FragmentBoxInfo;
import com.rui.ruiengine.fragment.FragmentBoxSensorList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rhuang on 1/4/16.
 */
public class DisplayBoxCpuAndSensorActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ArcProgress mCurrentCpuLoadingArc;
    private ProgressBar mCurrentCpuLoadingBar;
    private ProgressBar mMaxCpuLoadingBar;
    private ProgressBar mMinCpuLoadingBar;

    private TextView mCurrentCpuLoadingTestView;

    static final String API_URL = "https://ruimonitor-1080.appspot.com/cpu_sensors";
    private  RetrieveBoxCpuAndSensorTask mRetrieveBoxCpuAndSensorTask;

    private Timer mTimer;
    private int mCurrentCpuLoading = 1;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_box_cpu_sensor);

        mToolbar = (Toolbar)findViewById(R.id.display_box_stats_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mViewPager = (ViewPager)findViewById(R.id.display_box_stats_viewpager);
        setupViewPager(mViewPager);

        mCurrentCpuLoadingArc = (ArcProgress)findViewById(R.id.current_cpu_loading_arc);

        mCurrentCpuLoadingBar = (ProgressBar)findViewById(R.id.current_cpu_loading_pb);
        mMaxCpuLoadingBar = (ProgressBar)findViewById(R.id.max_cpu_loading_pb);
        mMinCpuLoadingBar = (ProgressBar)findViewById(R.id.min_cpu_loading_pb);

        mCurrentCpuLoadingTestView = (TextView)findViewById(R.id.current_cpu_loading_tv);

        mCurrentCpuLoadingBar.getProgressDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
        mMinCpuLoadingBar.getProgressDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
        mMaxCpuLoadingBar.getProgressDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
        mCurrentCpuLoadingBar.setScaleY(3F);
        mMinCpuLoadingBar.setScaleY(3F);
        mMaxCpuLoadingBar.setScaleY(3F);

        mTabLayout = (TabLayout) findViewById(R.id.display_box_stats_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        startRetrieveBoxCpuAndSensorTask();


        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCurrentCpuLoading = mCurrentCpuLoadingArc.getProgress();
                        mCurrentCpuLoadingArc.setProgress(mCurrentCpuLoading + 1);
                        mCurrentCpuLoadingBar.setProgress(mCurrentCpuLoading + 1);
                        // TODO: update it according to real reading
                        mCurrentCpuLoadingTestView.setText((int)((mCurrentCpuLoading / 100.0) * 1029) + "MHz");
                    }
                });
            }
        }, 1000, 100);

    }

    private class RetrieveBoxCpuAndSensorTask extends AsyncTask<Void, Void, String>
    {

        protected String doInBackground(Void... urls)
        {

            String result = HttpRequest(1, API_URL);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public static String HttpRequest(int way, String url)
    {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {

            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            HttpURLConnection connection;
            // POST:
            if(way == 0)
            {
                connection = (HttpURLConnection)realUrl.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                out = new PrintWriter(connection.getOutputStream());
                out.flush();

            }
            // GET:
            else
            {

                connection = (HttpURLConnection) realUrl.openConnection();
                int responseCode = connection.getResponseCode();

                if (responseCode >= 400 && responseCode <= 499)
                {

                    // Provide a more meaningful exception message
                    throw new Exception("Bad authentication status: " + responseCode);

                }
            }

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = in.readLine()) != null)
            {

                sb.append(line);
            }

            result = sb.toString();

        }
        catch (Exception e)
        {

            System.out.println("Send GET request abnormal!" + e);
            e.printStackTrace();
        }

        finally
        {

            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }

        System.out.println(result);
        return result;
    }

    public void startRetrieveBoxCpuAndSensorTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxCpuAndSensorTask != null)
        {
            try
            {
                mRetrieveBoxCpuAndSensorTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxCpuAndSensorTask = new RetrieveBoxCpuAndSensorTask();
        mRetrieveBoxCpuAndSensorTask.execute();

    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentBoxSensorList(), "SENSOR LIST");
        adapter.addFragment(new FragmentBoxInfo(), "INFO");
        viewPager.setAdapter(adapter);

    }
}
