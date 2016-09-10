package com.rui.ruiengine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.BoxInfo;

import java.util.List;

/**
 * Created by rhuang on 1/6/16.
 */
public class BoxViewAdapter extends RecyclerView.Adapter<BoxViewAdapter.BoxViewHolder> {

    Context mContext;
    private List<BoxInfo> mBoxes;
    OnItemClickListener mItemClickListener;
    public BoxViewAdapter(Context context, List<BoxInfo> boxes) {
        this.mContext = context;
        mBoxes = boxes;
    }

    @Override
    public int getItemCount() {
        return mBoxes.size();
    }



    @Override
    public void onBindViewHolder(BoxViewAdapter.BoxViewHolder holder, int position) {
        BoxInfo boxInfo = mBoxes.get(position);

        holder.mName.setText(boxInfo.getName());
        holder.mNetInfo.setText(boxInfo.getNetInfo());
        holder.mSwVersionInfo.setText(boxInfo.getSwVersionInfo());
        holder.mHwVersionInfo.setText(boxInfo.getHwVersionInfo());
        holder.mHwPartNum.setText(boxInfo.getHwPartNum());
        holder.mHwSerialNum.setText(boxInfo.getHwSerialNum());
        holder.mIcon.setImageResource(R.drawable.box);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BoxViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.box_view_item, viewGroup, false);
        return new BoxViewHolder(itemView);
    }

    public interface OnItemClickListener
    {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener)
    {
        this.mItemClickListener = mItemClickListener;
    }

    public class BoxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        protected ImageView mIcon;
        protected TextView mName;
        protected TextView mNetInfo;
        protected TextView mSwVersionInfo;
        protected TextView mHwVersionInfo;
        protected TextView mHwPartNum;
        protected TextView mHwSerialNum;

        public BoxViewHolder(View view)
        {
            super(view);
            mIcon = (ImageView)view.findViewById(R.id.box_icon_iv);
            mName = (TextView)view.findViewById(R.id.box_name_tv);
            mNetInfo = (TextView)view.findViewById(R.id.box_net_info_tv);
            mSwVersionInfo = (TextView)view.findViewById(R.id.sw_version_info_tv);
            mHwVersionInfo = (TextView)view.findViewById(R.id.hw_version_info_tv);
            mHwPartNum = (TextView)view.findViewById(R.id.hw_part_num_tv);
            mHwSerialNum = (TextView)view.findViewById(R.id.hw_serial_num_tv);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(mItemClickListener != null)
            {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }

        }
    }
}
