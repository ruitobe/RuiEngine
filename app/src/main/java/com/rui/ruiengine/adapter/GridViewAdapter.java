package com.rui.ruiengine.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.BoxStatsGroupItem;

import java.util.ArrayList;

/**
 * Created by rhuang on 1/11/16.
 */
public class GridViewAdapter extends ArrayAdapter<BoxStatsGroupItem> {
    Context mContext;
    int mLayoutSourceId;
    ArrayList<BoxStatsGroupItem> mBoxStatsGroupItems = new ArrayList<BoxStatsGroupItem>();

    public GridViewAdapter(Context context, int layoutSourceId, ArrayList<BoxStatsGroupItem> boxStatsGroupItems) {
        super(context, layoutSourceId, boxStatsGroupItems);

        mLayoutSourceId = layoutSourceId;
        mContext = context;
        mBoxStatsGroupItems = boxStatsGroupItems;
    }

    public static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder = null;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayoutSourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView)view.findViewById(R.id.grid_item_tv);
            viewHolder.mImageView = (ImageView)view.findViewById(R.id.grid_item_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        BoxStatsGroupItem item = mBoxStatsGroupItems.get(position);
        viewHolder.mTextView.setText(item.getTitle());
        viewHolder.mImageView.setImageBitmap(item.getIcon());

        return view;
    }
}
