package com.rui.ruiengine.chartitem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.rui.ruiengine.R;

/**
 * Created by rhuang on 1/8/16.
 */
public class LineChartItem extends ChartItem {

    private Typeface mTypeface;
    private LineChart mLineChart;
    public  LineChartItem(ChartData<?> chartData, Context context) {
        super(chartData);
        mTypeface = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    private static class ViewHolder {
        LineChart lineChart;
    }

    public LineChart getLineChart() {

        return mLineChart;

    }

    @Override
    public int getItemType() {
        return TYPE_LINE_CHART;
    }

    @Override
    public View getView(int position, View convertView, Context context) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_box_sensor_list_line_chart_item, null);
            holder.lineChart = (LineChart)convertView.findViewById(R.id.box_sensor_line_chart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Legend legend = holder.lineChart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(15f);
        // Apply chart style
        holder.lineChart.setDescription("");
        holder.lineChart.setDrawGridBackground(false);

        XAxis xAxis = holder.lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTypeface);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextColor(0xFFFFFFFF);

        YAxis leftAxis = holder.lineChart.getAxisLeft();
        leftAxis.setTypeface(mTypeface);
        leftAxis.setLabelCount(5, false);
        leftAxis.setTextColor(0xFFFFFFFF);

        YAxis rightAxis = holder.lineChart.getAxisRight();
        rightAxis.setTypeface(mTypeface);
        rightAxis.setLabelCount(5, false);
        rightAxis.setTextColor(0xFFFFFFFF);

        holder.lineChart.setData((LineData) mChartData);

        holder.lineChart.animateX(1000);
        mLineChart = holder.lineChart;

        return convertView;
    }


}
