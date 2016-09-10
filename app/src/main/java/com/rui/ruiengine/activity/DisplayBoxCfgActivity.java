package com.rui.ruiengine.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.CfgViewAdapter;
import com.rui.ruiengine.model.CfgInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhuang on 3/28/16.
 */
public class DisplayBoxCfgActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    static final String API_URL = "https://ruimonitor-1080.appspot.com/cfg";
    private  RetrieveBoxCfgTask mRetrieveBoxCfgTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_box_cfg);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cfg_rv);

        mToolbar = (Toolbar)findViewById(R.id.display_cfg_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        CfgViewAdapter cfgViewAdapter = new CfgViewAdapter(this, createList());
        recyclerView.setAdapter(cfgViewAdapter);

        startRetrieveBoxCfgTask();
    }

    private List<CfgInfo> createList()
    {

        List<CfgInfo> cfgInfoList = new ArrayList<CfgInfo>();
        StringBuilder stringBuilder = new StringBuilder();
        CfgInfo cfg1 = new CfgInfo();

        cfg1.setSlotName("Add-in card 1");

        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg1.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg1);

        CfgInfo cfg2 = new CfgInfo();
        stringBuilder.setLength(0);
        cfg2.setSlotName("Add-in card 2");
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg2.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg2);

        CfgInfo cfg3 = new CfgInfo();
        cfg3.setSlotName("Add-in card 3");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg3.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg3);

        CfgInfo cfg4 = new CfgInfo();
        cfg4.setSlotName("Add-in card 4");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg4.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg4);

        CfgInfo cfg5 = new CfgInfo();

        cfg5.setSlotName("Add-in card 5");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg5.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg5);

        CfgInfo cfg6 = new CfgInfo();
        cfg6.setSlotName("Add-in card 6");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg6.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg6);

        CfgInfo cfg7 = new CfgInfo();
        cfg7.setSlotName("Add-in card 7");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg7.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg7);

        CfgInfo cfg8 = new CfgInfo();
        cfg8.setSlotName("Add-in card 8");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 2.8.0\n");
        stringBuilder.append("Part Number: C112573.A2B\n");
        stringBuilder.append("BMPP2-B PCPL Version: 2.4.0\n");
        stringBuilder.append("BMPP2-B MMBL Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B OCTF Version: 2.8.0\n");
        stringBuilder.append("BMPP2-B FRUD Version: 2.8.0");
        cfg8.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg8);

        CfgInfo cfg9 = new CfgInfo();
        cfg9.setSlotName("AMC card 1");
        stringBuilder.setLength(0);
        stringBuilder.append("MMC Version: 1.10.0\n");
        stringBuilder.append("Part Number: C110598.B3A\n");
        stringBuilder.append("hdsam-a_ad_frud Version: 2.4.0");
        cfg9.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg9);


        CfgInfo cfg10 = new CfgInfo();
        cfg10.setSlotName("PSU 1");

        stringBuilder.setLength(0);
        stringBuilder.append("Board Mfg: EMERSON\n");
        stringBuilder.append("Board Product: BAFE-B\n");
        stringBuilder.append("Board Serial: TR12270196\n");
        stringBuilder.append("Board Part Number: C112156.C1A\n");
        stringBuilder.append("Board Extra: 1f01\n");
        stringBuilder.append("Product Name: DS1200-3-007\n");

        stringBuilder.append("Product Part Number :PSU\n");
        stringBuilder.append("Product Manufacturer: EMERSON\n");
        stringBuilder.append("Product Version :04\n");
        stringBuilder.append("Product Serial :I510KH000X04P\n");

        cfg10.setContent(stringBuilder.toString());
        cfgInfoList.add(cfg10);

        return cfgInfoList;
    }

    private class RetrieveBoxCfgTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveBoxCfgTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxCfgTask != null)
        {
            try
            {
                mRetrieveBoxCfgTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxCfgTask = new RetrieveBoxCfgTask();
        mRetrieveBoxCfgTask.execute();

    }
}
