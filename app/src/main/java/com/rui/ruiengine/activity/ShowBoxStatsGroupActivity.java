package com.rui.ruiengine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.graphics.BitmapFactory;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.GridViewAdapter;
import com.rui.ruiengine.model.BoxStatsGroupItem;

import java.util.ArrayList;

/**
 * Created by rhuang on 1/11/16.
 */
public class ShowBoxStatsGroupActivity extends AppCompatActivity {
    GridView mGridView;
    private Toolbar mToolbar;
    ArrayList<BoxStatsGroupItem> mBoxStatsGroupItems = new ArrayList<BoxStatsGroupItem>();
    GridViewAdapter mGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_box_stats_group);

        mToolbar = (Toolbar)findViewById(R.id.show_box_stats_group_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // TODO: set grid view item
        Bitmap cpuIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.cpu);
        Bitmap memIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ram);
        Bitmap syslogIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.syslog);
        Bitmap systemEventLogIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.sel);
        Bitmap fastpathIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.switch_log);
        Bitmap configIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.config);

        mBoxStatsGroupItems.add(new BoxStatsGroupItem(cpuIcon, "CPU & Sensors"));
        mBoxStatsGroupItems.add(new BoxStatsGroupItem(memIcon, "Memory"));
        mBoxStatsGroupItems.add(new BoxStatsGroupItem(syslogIcon, "System Log"));
        mBoxStatsGroupItems.add(new BoxStatsGroupItem(systemEventLogIcon, "SEL"));
        mBoxStatsGroupItems.add(new BoxStatsGroupItem(fastpathIcon, "Fastpath"));
        mBoxStatsGroupItems.add(new BoxStatsGroupItem(configIcon, "Configuration"));


        mGridView = (GridView)findViewById(R.id.box_stats_group_gv);
        mGridViewAdapter = new GridViewAdapter(this, R.layout.grid_row_item, mBoxStatsGroupItems);
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemTextView = (TextView)view.findViewById(R.id.grid_item_tv);
                Intent intent;
                switch (itemTextView.getText().toString()) {
                    case "CPU & Sensors":
                        intent = new Intent(getApplicationContext(), DisplayBoxCpuAndSensorActivity.class);
                        startActivity(intent);
                        //finish();
                        break;
                    case "Memory":
                        intent = new Intent(getApplicationContext(), DisplayBoxMemActivity.class);
                        startActivity(intent);
                        //finish();
                        break;
                    case "System Log":
                        //Toast.makeText(getApplicationContext(), "System Log", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplicationContext(), DisplayBoxSyslogActivity.class);
                        startActivity(intent);
                        break;
                    case "SEL":
                        //Toast.makeText(getApplicationContext(), "SEL", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplicationContext(), DisplayBoxSelActivity.class);
                        startActivity(intent);
                        break;
                    case "Fastpath":
                        //Toast.makeText(getApplicationContext(), "Fastpath", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplicationContext(), DisplayBoxFastpathActivity.class);
                        startActivity(intent);
                        break;
                    case "Configuration":
                        // TODO: jump to the relevant activity
                        //Toast.makeText(getApplicationContext(), "Configuration", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplicationContext(), DisplayBoxCfgActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
