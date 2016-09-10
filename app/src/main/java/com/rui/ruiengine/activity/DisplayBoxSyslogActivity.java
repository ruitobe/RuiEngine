package com.rui.ruiengine.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.SyslogViewAdapter;
import com.rui.ruiengine.model.SyslogInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhuang on 3/25/16.
 */
public class DisplayBoxSyslogActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private SyslogViewAdapter mSyslogViewAdapter;

    private ListView mSyslogListView;

    static final String API_URL = "https://ruimonitor-1080.appspot.com/syslogs";
    private  RetrieveBoxSyslogsTask mRetrieveBoxSyslogTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_box_syslog);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.syslog_rv);

        mToolbar = (Toolbar)findViewById(R.id.display_syslog_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        SyslogViewAdapter syslogViewAdapter = new SyslogViewAdapter(this, createList());

        recyclerView.setAdapter(syslogViewAdapter);

        startRetrieveBoxSyslogTask();

    }

    private List<SyslogInfo> createList()
    {

        StringBuilder stringBuilder = new StringBuilder();
        List<SyslogInfo> syslogList = new ArrayList<>();

        SyslogInfo syslogInfo1 = new SyslogInfo();
        stringBuilder = new StringBuilder();
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B syslogd: ");
        stringBuilder.append("1.4.1: restart (remote reception).");

        syslogInfo1.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo1);

        SyslogInfo syslogInfo2 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("Remote syslog working, push local log to syslog");

        syslogInfo2.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo2);

        SyslogInfo syslogInfo3 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090058 ");
        stringBuilder.append("INFO: Remote syslog not working yet, using local log only");

        syslogInfo3.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo3);

        SyslogInfo syslogInfo4 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090058 ");
        stringBuilder.append("INFO: *** Starting hook script for LMP-1-1-1 (Sun Oct 11 09:00:58 IST 2015) ***");

        syslogInfo4.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo4);

        SyslogInfo syslogInfo5 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090058 ");
        stringBuilder.append("INFO: *** The ntp might not be configured yet - time stamps might not match reality ***");

        syslogInfo5.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo5);

        SyslogInfo syslogInfo6 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090058 ");
        stringBuilder.append("INFO: Configure syslog");

        syslogInfo6.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo6);

        SyslogInfo syslogInfo7 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090059 ");
        stringBuilder.append("INFO: Remote syslog not working yet, using local log only");

        syslogInfo7.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo7);

        SyslogInfo syslogInfo8 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090059 ");
        stringBuilder.append("INFO: Configure ntp");

        syslogInfo8.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo8);

        SyslogInfo syslogInfo9 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090059 ");
        stringBuilder.append("INFO: Change command prompt");

        syslogInfo9.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo9);

        SyslogInfo syslogInfo10 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090059 ");
        stringBuilder.append("INFO: Copy SSH public keys");

        syslogInfo10.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo10);

        SyslogInfo syslogInfo11 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090059 ");
        stringBuilder.append("INFO: Copy hosts file");

        syslogInfo11.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo11);

        SyslogInfo syslogInfo12 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090100 ");
        stringBuilder.append("INFO: Copy nsn_recovery file");

        syslogInfo12.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo12);

        SyslogInfo syslogInfo13 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090100 ");
        stringBuilder.append("INFO: Configure backplane resiliency tar");

        syslogInfo13.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo13);

        SyslogInfo syslogInfo14 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090100 ");
        stringBuilder.append("INFO: Configure backplane resiliency setup script");

        syslogInfo14.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo14);

        SyslogInfo syslogInfo15 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090100 ");
        stringBuilder.append("INFO: Configure xmeshd service - mode (reconfigure)");

        syslogInfo15.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo15);

        SyslogInfo syslogInfo16 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090100 ");
        stringBuilder.append("INFO: Configure backplane.sh service - mode ()");

        syslogInfo16.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo16);

        SyslogInfo syslogInfo17 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090100 ");
        stringBuilder.append("INFO: Configure main switch startup configuration");

        syslogInfo17.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo17);

        SyslogInfo syslogInfo18 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090102 ");
        stringBuilder.append("INFO: Configure extension switch startup configuration");

        syslogInfo18.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo18);

        SyslogInfo syslogInfo19 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090102 ");
        stringBuilder.append("INFO: Configure port monitor");

        syslogInfo19.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo19);

        SyslogInfo syslogInfo20 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090103 ");
        stringBuilder.append("INFO: PCIe RC location is Slot1");

        syslogInfo20.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo20);

        SyslogInfo syslogInfo21 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090103 ");
        stringBuilder.append("INFO: Configure MMCs");

        syslogInfo21.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo21);

        SyslogInfo syslogInfo22 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090103 ");
        stringBuilder.append("INFO: Change IPMB 0x72 active configuration");

        syslogInfo22.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo22);


        SyslogInfo syslogInfo23 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090104 ");
        stringBuilder.append("INFO: Change IPMB 0x74 active configuration");

        syslogInfo23.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo23);

        SyslogInfo syslogInfo24 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090105 ");
        stringBuilder.append("INFO: Change IPMB 0x76 active configuration");

        syslogInfo24.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo24);

        SyslogInfo syslogInfo25 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090106 ");
        stringBuilder.append("INFO: Change IPMB 0x78 active configuration");

        syslogInfo25.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo25);

        SyslogInfo syslogInfo26 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090107 ");
        stringBuilder.append("INFO: Change IPMB 0x7a active configuration");

        syslogInfo26.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo26);

        SyslogInfo syslogInfo27 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090107 ");
        stringBuilder.append("INFO: Change IPMB 0x7c active configuration");

        syslogInfo27.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo27);

        SyslogInfo syslogInfo28 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090108 ");
        stringBuilder.append("INFO: Change IPMB 0x7e active configuration");

        syslogInfo28.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo28);

        SyslogInfo syslogInfo29 = new SyslogInfo();
        stringBuilder.setLength(0);
        stringBuilder.append("Oct 11 14:35:41 ");
        stringBuilder.append("BCNMB-B dhcphook: ");
        stringBuilder.append("20151011/090109 ");
        stringBuilder.append("INFO: Change IPMB 0x80 active configuration");

        syslogInfo29.setContent(stringBuilder.toString());
        syslogList.add(syslogInfo29);

        return syslogList;
    }

    private class RetrieveBoxSyslogsTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveBoxSyslogTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxSyslogTask != null)
        {
            try
            {
                mRetrieveBoxSyslogTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxSyslogTask = new RetrieveBoxSyslogsTask();
        mRetrieveBoxSyslogTask.execute();

    }
}
