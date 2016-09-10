package com.rui.ruiengine.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rui.ruiengine.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rhuang on 1/12/16.
 */
public class DisplayBoxMemActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private PieChart mPieChart;

    private Typeface mTypeFace;

    private Toolbar mToolbar;

    static final String API_URL = "https://ruimonitor-1080.appspot.com/mem";
    private  RetrieveBoxMemTask mRetrieveBoxMemTask;

    protected String[] mParties = new String[] {
            "Active", "Wired", "Inactive", "Free", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private void setData(int count, float range) {

        float multi = range;

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for(int index = 0; index < count + 1; index++) {
            yVals.add(new Entry((float) (Math.random() * multi) + multi / 5, index));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for(int index = 0; index < count + 1; index++)
            xVals.add(mParties[index % mParties.length]);

        PieDataSet pieDataSet = new PieDataSet(yVals, "Memory Usage");
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int color : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.JOYFUL_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.COLORFUL_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.LIBERTY_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.PASTEL_COLORS)
            colors.add(color);

        colors.add(ColorTemplate.getHoloBlue());

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(xVals, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(14f);
        pieData.setValueTextColor(Color.RED);
        pieData.setValueTypeface(mTypeFace);

        mPieChart.setData(pieData);
        // undo all highlights
        mPieChart.highlightValues(null);
        mPieChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString str = new SpannableString("Box Memory Usage Chart\n" // index: 0 - 22
                + "demo by " // index: 23 - 30
                + "Rui Huang" // index: 31 - 40
                );
        str.setSpan(new RelativeSizeSpan(1.7f), 0, 22, 0);
        str.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 22, 0);
        str.setSpan(new StyleSpan(Typeface.NORMAL), 23, 30, 0);
        str.setSpan(new ForegroundColorSpan(Color.GRAY), 23, 30, 0);
        str.setSpan(new RelativeSizeSpan(.8f), 31, 40, 0);
        str.setSpan(new StyleSpan(Typeface.ITALIC), 31, 40, 0);
        str.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 31, 40, 0);
        return str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_box_mem);

        mToolbar = (Toolbar)findViewById(R.id.display_box_mem_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mPieChart = (PieChart) findViewById(R.id.box_mem_pie_chart);
        mPieChart.setUsePercentValues(true);
        mPieChart.setDescription("Memory");
        mPieChart.setDescriptionColor(Color.WHITE);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mTypeFace = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        mPieChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        mPieChart.setCenterText(generateCenterSpannableText());

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColorTransparent(true);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        // add a selection listener
        mPieChart.setOnChartValueSelectedListener(this);

        startRetrieveBoxMemTask();

        setData(3, 100);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend legend = mPieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(14f);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private class RetrieveBoxMemTask extends AsyncTask<Void, Void, String>
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

    public void startRetrieveBoxMemTask()
    {
        // Cancel previously running tasks.
        if (mRetrieveBoxMemTask != null)
        {
            try
            {
                mRetrieveBoxMemTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        mRetrieveBoxMemTask = new RetrieveBoxMemTask();
        mRetrieveBoxMemTask.execute();

    }
}
