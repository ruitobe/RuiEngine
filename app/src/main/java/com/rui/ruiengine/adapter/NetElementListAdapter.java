package com.rui.ruiengine.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.NetElementInfo;

import java.util.ArrayList;


/**
 * Created by rhuang on 1/2/16.
 */
public class NetElementListAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<NetElementInfo> mNetElements;

    public NetElementListAdapter(Context context, ArrayList<NetElementInfo> netElements) {
        this.mContext = context;
        mNetElements = netElements;
    }

    public static class ViewHolder {

        public TextView mName;
        public TextView mAddress;
        public ImageView mIcon;
    }

    @Override
    public int getCount() { return mNetElements.size();}

    @Override
    public Object getItem(int position) {
        return mNetElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            // Inflate fragment_ne_item.xml for each row
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_ne_item, null);

            viewHolder.mName = (TextView)convertView.findViewById(R.id.ne_name_tv);
            viewHolder.mAddress = (TextView)convertView.findViewById(R.id.ne_address_tv);
            viewHolder.mIcon = (ImageView)convertView.findViewById(R.id.ne_icon_iv);

            NetElementInfo netElementInfo = mNetElements.get(position);
            String name = netElementInfo.getName();
            String address = netElementInfo.getAddress();
            viewHolder.mName.setText(name);
            viewHolder.mAddress.setText(address);
            viewHolder.mIcon.setImageResource(R.drawable.ne);

        }

        return convertView;
    }
}
