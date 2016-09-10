package com.rui.ruiengine.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.BoxViewAdapter;
import com.rui.ruiengine.model.BoxInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhuang on 1/5/16.
 */
public class ShowSelectedNetElementActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    static final String API_URL = "https://ruimonitor-1080.appspot.com/boxes";
    private  RetrieveBoxesTask mRetrieveBoxesTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selected_net_element);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.box_rv);

        mToolbar = (Toolbar)findViewById(R.id.show_ne_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        BoxViewAdapter boxViewAdapter = new BoxViewAdapter(this, createList());

        recyclerView.setAdapter(boxViewAdapter);

        startRetrieveBoxesTask();

        recyclerView.setClickable(true);

        // Create listener for net element recycler view
        boxViewAdapter.SetOnItemClickListener(new BoxViewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position) {

                System.out.println(position);
                // TODO: here we jump to box activity
                connectBox();

            }
        });

    }

    private List<BoxInfo> createList()
    {

        List<BoxInfo> boxList = new ArrayList<BoxInfo>();

        BoxInfo box1 = new BoxInfo();

        box1.setName("LMP-1-1-1");
        box1.setNetInfo("192.168.0.10");
        box1.setSwVersionInfo("7.1.1");
        box1.setHwVersionInfo("A104-3");
        box1.setHwPartNum("C111721.A3A");
        box1.setHwSerialNum("83104700101");
        boxList.add(box1);

        BoxInfo box2 = new BoxInfo();
        box2.setName("LMP-1-2-1");
        box2.setNetInfo("192.168.0.20");
        box2.setSwVersionInfo("7.1.1");
        box2.setHwVersionInfo("A104-3");
        box2.setHwPartNum("C111721.A3A");
        box2.setHwSerialNum("83104700102");
        boxList.add(box2);

        BoxInfo box3 = new BoxInfo();
        box3.setName("LMP-1-3-1");
        box3.setNetInfo("192.168.0.30");
        box3.setSwVersionInfo("7.1.1");
        box3.setHwVersionInfo("A104-3");
        box3.setHwPartNum("C111721.A3A");
        box3.setHwSerialNum("83104700103");
        boxList.add(box3);

        BoxInfo box4 = new BoxInfo();
        box4.setName("LMP-1-4-1");
        box4.setNetInfo("192.168.0.40");
        box4.setSwVersionInfo("7.1.1");
        box4.setHwVersionInfo("A104-3");
        box4.setHwPartNum("C111721.A3A");
        box4.setHwSerialNum("83104700104");
        boxList.add(box4);

        BoxInfo box5 = new BoxInfo();
        box5.setName("LMP-1-5-1");
        box5.setNetInfo("192.168.0.50");
        box5.setSwVersionInfo("7.1.1");
        box5.setHwVersionInfo("A104-3");
        box5.setHwPartNum("C111721.A3A");
        box5.setHwSerialNum("83104700105");
        boxList.add(box5);

        BoxInfo box6 = new BoxInfo();
        box6.setName("LMP-1-6-1");
        box6.setNetInfo("192.168.0.60");
        box6.setSwVersionInfo("7.1.1");
        box6.setHwVersionInfo("A104-3");
        box6.setHwPartNum("C111721.A3A");
        box6.setHwSerialNum("83104700106");
        boxList.add(box6);

        BoxInfo box7 = new BoxInfo();
        box7.setName("LMP-1-7-1");
        box7.setNetInfo("192.168.0.70");
        box7.setSwVersionInfo("7.1.1");
        box7.setHwVersionInfo("A104-3");
        box7.setHwPartNum("C111721.A3A");
        box7.setHwSerialNum("83104700107");
        boxList.add(box7);

        BoxInfo box8 = new BoxInfo();
        box8.setName("LMP-1-8-1");
        box8.setNetInfo("192.168.0.80");
        box8.setSwVersionInfo("7.1.1");
        box8.setHwVersionInfo("A104-3");
        box8.setHwPartNum("C111721.A3A");
        box8.setHwSerialNum("83104700108");
        boxList.add(box8);

        return boxList;
    }

    private class RetrieveBoxesTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveBoxesTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxesTask != null)
        {
            try
            {
                mRetrieveBoxesTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxesTask = new RetrieveBoxesTask();
        mRetrieveBoxesTask.execute();

    }

    public void connectBox() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Connecting box...");
        progressDialog.show();

        // TODO: implement connect box logic here:

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // On complete call either onConnectBoxSuccessful or onConnectBoxFailed
                onConnectBoxSuccessful();
                //onConnectBoxFailed();
                progressDialog.dismiss();
            }
        }, 3000);
    }

    public void onConnectBoxSuccessful() {
        // We start ShowBoxStatsGroupActivity
        Intent intent = new Intent(this, ShowBoxStatsGroupActivity.class);
        startActivity(intent);
        //this.finish();
    }

    public void onConnectBoxFailed() {
        Toast.makeText(this, "Connecting box failed...", Toast.LENGTH_LONG).show();
    }


}
