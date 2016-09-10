package com.rui.ruiengine.chartitem;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;
import com.rui.ruiengine.R;


/**
 * Created by rhuang on 1/8/16.
 */
public class BarChartItem extends ChartItem {
    private Typeface mTypeface;

    public BarChartItem(ChartData<?> chartData, Context context) {
        super(chartData);
        mTypeface = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

    }

    private static class ViewHolder {
        BarChart barChart;
    }

    @Override
    public int getItemType() {
        return TYPE_BAR_CHART;
    }

    @Override
    public View getView(int position, View convertView, Context context) {

        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_box_sensor_list_bar_chart_item, null);
            holder.barChart = (BarChart)convertView.findViewById(R.id.box_sensor_bar_chart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        // Apply chart style
        holder.barChart.setDescription("");
        holder.barChart.setDrawGridBackground(false);
        holder.barChart.setDrawBarShadow(false);

        XAxis xAxis = holder.barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTypeface);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextColor(0xFFFFFFFF);

        YAxis leftAxis = holder.barChart.getAxisLeft();
        leftAxis.setTypeface(mTypeface);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(20f);
        leftAxis.setTextColor(0xFFFFFFFF);

        YAxis rightAxis = holder.barChart.getAxisRight();
        rightAxis.setTypeface(mTypeface);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);
        rightAxis.setTextColor(0xFFFFFFFF);

        mChartData.setValueTypeface(mTypeface);

        holder.barChart.setData((BarData) mChartData);

        holder.barChart.animateY(700);

        return convertView;
    }
}
