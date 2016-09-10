package com.rui.ruiengine.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.rui.ruiengine.R;
import com.rui.ruiengine.activity.DisplayBoxCpuAndSensorActivity;
import com.rui.ruiengine.adapter.ChartDataAdapter;
import com.rui.ruiengine.chartitem.ChartItem;
import com.rui.ruiengine.chartitem.LineChartItem;

import java.util.ArrayList;

/**
 * Created by rhuang on 1/4/16.
 */
public class FragmentBoxSensorList extends Fragment {

    private ListView mSensorChartListView;
    private DisplayBoxCpuAndSensorActivity mActivity = null;
    private ArrayList<ChartItem> mSensorChartList;
    private LineChart mLineChart;
    private BarChart mBarChart;

    public FragmentBoxSensorList() {

        // Empty public constructor
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> months = new ArrayList<String>();
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Okt");
        months.add("Nov");
        months.add("Dec");

        return months;
    }

    private ArrayList<String> getNothing() {
        ArrayList<String> nothing = new ArrayList<String>();
        for (int index = 0; index < 16; index++) {
            nothing.add("");
        }
        return nothing;
    }

    private BarData generateDataBar() {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < 12; index++) {
            entries.add(new BarEntry((int)(Math.random() * 70) + 30, index));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "Fixed Data Set");
        barDataSet.setBarSpacePercent(20f);
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setHighLightAlpha(255);
        barDataSet.setValueTextColor(0xFFFFFFFF);

        BarData barData = new BarData(getMonths(), barDataSet);
        return  barData;

    }

    private LineData generateDataLine(String dataSetName) {

        ArrayList<Entry> entries = new ArrayList<Entry>();


        for (int index = 0; index < 16; index++) {
            entries.add(new Entry((int)(Math.random() * 65) + 40, index));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, dataSetName);
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleSize(4.5f);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(true);

        LineData lineData = new LineData(getNothing(), lineDataSet);
        return lineData;

    }

    private void addLineChartEntry(int position) {
        if(mSensorChartListView != null) {

            LineChartItem lineChartItem = (LineChartItem)mSensorChartListView.getItemAtPosition(position);

            if(lineChartItem != null) {
                mLineChart = lineChartItem.getLineChart();
                if(mLineChart != null) {
                    LineData lineData = mLineChart.getData();
                    if(lineData != null) {
                        LineDataSet lineDataSet = lineData.getDataSetByIndex(0);

                        if(lineDataSet == null) {
                            lineDataSet = createLineDataSet();
                            lineData.addDataSet(lineDataSet);
                        }

                        lineData.addXValue("");
                        lineData.addEntry(new Entry((int) (Math.random() * 65) + 40, lineDataSet.getEntryCount()), 0);
                        mLineChart.notifyDataSetChanged();
                        mLineChart.setVisibleXRangeMaximum(16);
                        mLineChart.moveViewToX(lineData.getXValCount() - 17);
                    }
                }
            }

        }

    }

    private LineDataSet createLineDataSet() {

        LineDataSet lineDataSet = new LineDataSet(null, "Dynamic Data Set");
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleSize(4.5f);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(true);
        return lineDataSet;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (DisplayBoxCpuAndSensorActivity)getActivity();
        mSensorChartList = new ArrayList<ChartItem>();
        // For demo, add 12 sensors data sets
        mSensorChartList.add(new LineChartItem(generateDataLine("PEX Temp"), getContext()));
        mSensorChartList.add(new LineChartItem(generateDataLine("3.3 SB"), getContext()));
        mSensorChartList.add(new LineChartItem(generateDataLine("PSU1 Out Current"), getContext()));
        mSensorChartList.add(new LineChartItem(generateDataLine("FRU Data Integrity"), getContext()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_box_sensor_list, container, false);
        mSensorChartListView = (ListView)view.findViewById(R.id.box_sensor_chart_lv);
        ChartDataAdapter chartDataAdapter = new ChartDataAdapter(mActivity, mSensorChartList);
        mSensorChartListView.setAdapter(chartDataAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // add 100 entries more
                for (int index = 0; index < 100; index++) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addLineChartEntry(0);
                            addLineChartEntry(1);
                            addLineChartEntry(2);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();

    }

}
