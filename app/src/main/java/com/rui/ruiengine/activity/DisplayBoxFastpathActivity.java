package com.rui.ruiengine.activity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.CardViewAdapter;
import com.rui.ruiengine.model.FastpathInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhuang on 3/27/16.
 */
public class DisplayBoxFastpathActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Bitmap mTopBitmap;

    static final String API_URL = "https://ruimonitor-1080.appspot.com/fastpath";
    private  RetrieveBoxFastpathTask mRetrieveBoxFastpathTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_box_fastpath);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.fastpath_rv);

        mToolbar = (Toolbar)findViewById(R.id.display_fastpath_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        CardViewAdapter cardViewAdapter = new CardViewAdapter(createList(2));
        recyclerView.setAdapter(cardViewAdapter);

        startRetrieveBoxFastpathTask();
    }

    private List<FastpathInfo> createList(int size)
    {

        List<FastpathInfo> result = new ArrayList<FastpathInfo>();

        FastpathInfo fastpath56512 = new FastpathInfo();

        mTopBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cardview_image_top1);
        fastpath56512.setIamge(mTopBitmap);
        fastpath56512.setDescription("Broadcom FASTPATH Switching");
        fastpath56512.setType("Broadcom FIREBOLT-2 56514 Development System - 24 GE, 4 TENGIG");
        fastpath56512.setModel("BCM-56514-24, Maintenance Level A");
        fastpath56512.setManufacturer("0xbc00");
        fastpath56512.setMac("00:D0:C9:DA:EA:EF");
        fastpath56512.setSystem("Linux 2.6.21.7-hrt1-WR2.0aLinux 2.6.");
        result.add(fastpath56512);

        FastpathInfo fastpath56820 = new FastpathInfo();
        mTopBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cardview_image_top2);
        fastpath56820.setIamge(mTopBitmap);
        fastpath56820.setDescription("Broadcom FASTPATH Switching");
        fastpath56820.setType("Broadcom Scorpion 56820 Development System - 24 TENGIG, 4 GE");
        fastpath56820.setModel("BCM-56820, Maintenance Level A");
        fastpath56820.setManufacturer("0xbc00");
        fastpath56820.setMac("00:D0:C9:DA:EA:B3");
        fastpath56820.setSystem("Linux 2.6.21.7-hrt1-WR2.0aLinux 2.6.");

        result.add(fastpath56820);


        return result;
    }

    private class RetrieveBoxFastpathTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveBoxFastpathTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxFastpathTask != null)
        {
            try
            {
                mRetrieveBoxFastpathTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxFastpathTask = new RetrieveBoxFastpathTask();
        mRetrieveBoxFastpathTask.execute();

    }


}
