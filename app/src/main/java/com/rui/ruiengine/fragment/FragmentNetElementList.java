package com.rui.ruiengine.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rui.ruiengine.R;
import com.rui.ruiengine.activity.ConnectNetElementHubActivity;
import com.rui.ruiengine.activity.ShowSelectedNetElementActivity;
import com.rui.ruiengine.adapter.NetElementListAdapter;
import com.rui.ruiengine.model.NetElementInfo;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rhuang on 1/2/16.
 */
public class FragmentNetElementList extends Fragment {
    private final int SCAN_TIMEOUT = 10;
    private ConnectNetElementHubActivity mActivity = null;


    static final String API_URL = "https://ruimonitor-1080.appspot.com/netelements";

    AppCompatButton mScanButton;
    NetElementListAdapter mNetElementListAdapter;
    NetElementInfo mNetElement;
    ArrayList<NetElementInfo> mNetElementList;
    ListView mNetElementListView;
    TextView mNoNetElementInfoText;

    private  RetrieveNetElementsTask mRetrieveNetElementsTask;

    public FragmentNetElementList() {

        // Empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ne_list, container, false);
        mNetElementListView = (ListView)view.findViewById(R.id.ne_lv);

        mNetElementList = new ArrayList<>();

        mActivity = (ConnectNetElementHubActivity)getActivity();
        mNetElementListAdapter = new NetElementListAdapter(mActivity, mNetElementList);
        mNetElementListView.setAdapter(mNetElementListAdapter);

        mNetElementListView.setClickable(true);
        mNetElementListView.setOnItemClickListener(mNetElementClickListener);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        mNoNetElementInfoText = (TextView)view.findViewById(R.id.no_ne_tv);
        mScanButton = (AppCompatButton)view.findViewById(R.id.btn_scan);
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private class RetrieveNetElementsTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveNetElementsTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveNetElementsTask != null)
        {
            try
            {
                mRetrieveNetElementsTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveNetElementsTask = new RetrieveNetElementsTask();
        mRetrieveNetElementsTask.execute();

    }

    public void scan() {

        if(!checkAvailability()) {
            onScanNetElementFailed();
            return;
        }

        mScanButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(mActivity, R.style.AppTheme_Dark_Dialog);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Scanning net elements...");
        progressDialog.show();

        // TODO: Implement scan logic here

        startRetrieveNetElementsTask();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // On complete call either onScanNetElementSuccessful or onScanNetElementFailed
                onScanNetElementSuccessful();
                //onScanNetElementFailed();
                progressDialog.dismiss();
            }
        }, 3000);
    }

    public boolean checkAvailability() {
        boolean isAvailable = true;
        // TODO: implement check availability logic

        return isAvailable;
    }

    public void onScanNetElementSuccessful() {
        mScanButton.setEnabled(false);

        mNoNetElementInfoText.setVisibility(View.INVISIBLE);

        // TODO: implement list view updating logic here
        mNetElement = new NetElementInfo("mcRNC_Espoo", "Karaportti 8 02610, Espoo");
        mNetElementList.add(mNetElement);
        mNetElement = new NetElementInfo("mcRNC_Tampere", "Orivedenkatu 8 33720, Tampere");
        mNetElementList.add(mNetElement);

        mNetElementListAdapter.notifyDataSetChanged();

    }

    public void onScanNetElementFailed() {

        Toast.makeText(mActivity, "Scanning net elements failed...", Toast.LENGTH_LONG).show();
        mScanButton.setEnabled(true);
    }

    public void connectNetElement() {

        final ProgressDialog progressDialog = new ProgressDialog(mActivity, R.style.AppTheme_Dark_Dialog);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Connecting net element...");
        progressDialog.show();

        // TODO: Implement scan logic here

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // On complete call either onConnectNetElementSuccessful or onConnectNetElementFailed
                onConnectNetElementSuccessful();
                //onConnectNetElementFailed();
                progressDialog.dismiss();
            }
        }, 3000);
    }

    public void onConnectNetElementSuccessful() {

        // We start ShowSelectedNetElementActivity
        Intent intent = new Intent(mActivity, ShowSelectedNetElementActivity.class);
        startActivity(intent);
        //mActivity.finish();
    }

    public void onConnectNetElementFailed() {
        Toast.makeText(mActivity, "Connecting net element failed...", Toast.LENGTH_LONG).show();
    }

    // Create listener for net element list
    private AdapterView.OnItemClickListener mNetElementClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // TODO: here we jump to net element activity
            connectNetElement();

        }
    };


}
