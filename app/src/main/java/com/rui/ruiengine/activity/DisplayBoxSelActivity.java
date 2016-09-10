package com.rui.ruiengine.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.SelViewAdapter;
import com.rui.ruiengine.model.SelInfo;

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
public class DisplayBoxSelActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private SelViewAdapter mSelViewAdapter;

    static final String API_URL = "https://ruimonitor-1080.appspot.com/sels";
    private  RetrieveBoxSelsTask mRetrieveBoxSelsTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_box_sel);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.sel_rv);

        mToolbar = (Toolbar)findViewById(R.id.display_sel_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        SelViewAdapter selViewAdapter = new SelViewAdapter(this, createList());

        recyclerView.setAdapter(selViewAdapter);

        startRetrieveBoxSelsTask();

    }

    private List<SelInfo> createList()
    {
        StringBuilder stringBuilder = new StringBuilder();
        List<SelInfo> selList = new ArrayList<>();

        SelInfo selInfo1 = new SelInfo();
        stringBuilder.append("1 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:16:59 | ");
        stringBuilder.append("Power Supply #0x23 | ");
        stringBuilder.append("Input lost or out-of-range | ");
        stringBuilder.append("Asserted");

        selInfo1.setContent(stringBuilder.toString());
        selList.add(selInfo1);

        stringBuilder.setLength(0);

        SelInfo selInfo2 = new SelInfo();
        stringBuilder.append("2 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:16:59 | ");
        stringBuilder.append("Power Supply #0x2f | ");
        stringBuilder.append("Failure detected | ");
        stringBuilder.append("Asserted");
        selInfo2.setContent(stringBuilder.toString());
        selList.add(selInfo2);

        stringBuilder.setLength(0);
        SelInfo selInfo3 = new SelInfo();
        stringBuilder.append("3 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:17:01 | ");
        stringBuilder.append("Voltage #0x1b | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 0 < Threshold 44 volts");
        selInfo3.setContent(stringBuilder.toString());
        selList.add(selInfo3);

        stringBuilder.setLength(0);
        SelInfo selInfo4 = new SelInfo();
        stringBuilder.append("4 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:17:01 | ");
        stringBuilder.append("Voltage #0x1f | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 0.16 < Threshold 3.00 volts");
        selInfo4.setContent(stringBuilder.toString());
        selList.add(selInfo4);

        stringBuilder.setLength(0);
        SelInfo selInfo5 = new SelInfo();
        stringBuilder.append("5 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:17:02 | ");
        stringBuilder.append("Voltage #0x1d | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 0.82 < Threshold 11.21 volts");
        selInfo5.setContent(stringBuilder.toString());
        selList.add(selInfo5);

        stringBuilder.setLength(0);
        SelInfo selInfo6 = new SelInfo();
        stringBuilder.append("6 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:50 | ");
        stringBuilder.append("Voltage #0x1b | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 52 < Threshold 44 volts");
        selInfo6.setContent(stringBuilder.toString());
        selList.add(selInfo6);

        stringBuilder.setLength(0);
        SelInfo selInfo7 = new SelInfo();
        stringBuilder.append("7 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:50 | ");
        stringBuilder.append("Voltage #0x1d | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 12.03 < Threshold 11.21 volts");
        selInfo7.setContent(stringBuilder.toString());
        selList.add(selInfo7);

        stringBuilder.setLength(0);
        SelInfo selInfo8 = new SelInfo();
        stringBuilder.append("8 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:50 | ");
        stringBuilder.append("Voltage #0x1f | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 3.25 < Threshold 3.00 volts");
        selInfo8.setContent(stringBuilder.toString());
        selList.add(selInfo8);

        stringBuilder.setLength(0);
        SelInfo selInfo9 = new SelInfo();
        stringBuilder.append("9 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:50 | ");
        stringBuilder.append("Power Supply #0x23 | ");
        stringBuilder.append("Input lost or out-of-range | ");
        stringBuilder.append("Deasserted");
        selInfo9.setContent(stringBuilder.toString());
        selList.add(selInfo9);

        stringBuilder.setLength(0);
        SelInfo selInfo10 = new SelInfo();
        stringBuilder.append("a | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:53 | ");
        stringBuilder.append("Power Supply #0x22 | ");
        stringBuilder.append("Input lost or out-of-range | ");
        stringBuilder.append("Asserted");
        selInfo10.setContent(stringBuilder.toString());
        selList.add(selInfo10);

        stringBuilder.setLength(0);
        SelInfo selInfo11 = new SelInfo();
        stringBuilder.append("b | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:53 | ");
        stringBuilder.append("Power Supply #0x2f | ");
        stringBuilder.append("Failure detected | ");
        stringBuilder.append("Asserted");
        selInfo11.setContent(stringBuilder.toString());
        selList.add(selInfo11);

        stringBuilder.setLength(0);
        SelInfo selInfo12 = new SelInfo();
        stringBuilder.append("c | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:55 | ");
        stringBuilder.append("Voltage #0x1a | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 0 < Threshold 44 volts");
        selInfo12.setContent(stringBuilder.toString());
        selList.add(selInfo12);

        stringBuilder.setLength(0);
        SelInfo selInfo13 = new SelInfo();
        stringBuilder.append("d | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:55 | ");
        stringBuilder.append("Voltage #0x1e | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 0.22 < Threshold 3.00 volts");
        selInfo13.setContent(stringBuilder.toString());
        selList.add(selInfo13);

        stringBuilder.setLength(0);
        SelInfo selInfo14 = new SelInfo();
        stringBuilder.append("e | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:19:57 | ");
        stringBuilder.append("Voltage #0x1c | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 0.82 < Threshold 11.21 volts");
        selInfo14.setContent(stringBuilder.toString());
        selList.add(selInfo14);

        stringBuilder.setLength(0);
        SelInfo selInfo15 = new SelInfo();
        stringBuilder.append("f | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:20:08 | ");
        stringBuilder.append("Voltage #0x1a | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 52 < Threshold 44 volts");
        selInfo15.setContent(stringBuilder.toString());
        selList.add(selInfo15);

        stringBuilder.setLength(0);
        SelInfo selInfo16 = new SelInfo();
        stringBuilder.append("10 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:20:08 | ");
        stringBuilder.append("Voltage #0x1c | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 12.10 < Threshold 11.21 volts");
        selInfo16.setContent(stringBuilder.toString());
        selList.add(selInfo16);

        stringBuilder.setLength(0);
        SelInfo selInfo17 = new SelInfo();
        stringBuilder.append("11 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:20:08 | ");
        stringBuilder.append("Voltage #0x1e | ");
        stringBuilder.append("Lower Non-recoverable going low | ");
        stringBuilder.append("Reading 3.28 < Threshold 3.00 volts");
        selInfo17.setContent(stringBuilder.toString());
        selList.add(selInfo17);

        stringBuilder.setLength(0);
        SelInfo selInfo18 = new SelInfo();
        stringBuilder.append("12 | ");
        stringBuilder.append("02/11/2016 | ");
        stringBuilder.append("08:20:08 | ");
        stringBuilder.append("Power Supply #0x22 | ");
        stringBuilder.append("Input lost or out-of-range | ");
        stringBuilder.append("Deasserted");
        selInfo18.setContent(stringBuilder.toString());
        selList.add(selInfo18);

        return selList;

    }

    private class RetrieveBoxSelsTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveBoxSelsTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxSelsTask != null)
        {
            try
            {
                mRetrieveBoxSelsTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxSelsTask = new RetrieveBoxSelsTask();
        mRetrieveBoxSelsTask.execute();

    }
}
