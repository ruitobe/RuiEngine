package com.rui.ruiengine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.CfgInfo;

import java.util.List;

/**
 * Created by rhuang on 3/28/16.
 */
public class CfgViewAdapter extends RecyclerView.Adapter<CfgViewAdapter.CfgViewHolder> {

    Context mContext;
    private List<CfgInfo> mCfgs;

    public CfgViewAdapter(Context context, List<CfgInfo> cfgs) {
        this.mContext = context;
        mCfgs = cfgs;
    }

    @Override
    public int getItemCount() {
        return mCfgs.size();
    }

    @Override
    public void onBindViewHolder(CfgViewAdapter.CfgViewHolder holder, int position) {
        CfgInfo cfgInfo = mCfgs.get(position);

        holder.mSlotName.setText(cfgInfo.getSlotName());
        holder.mContent.setText(cfgInfo.getContent());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public CfgViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cfg_view_item, viewGroup, false);
        return new CfgViewHolder(itemView);
    }


    public class CfgViewHolder extends RecyclerView.ViewHolder
    {

        protected TextView mSlotName;
        protected TextView mContent;

        public CfgViewHolder(View view)
        {
            super(view);
            mSlotName = (TextView)view.findViewById(R.id.box_slot_name_tv);
            mContent = (TextView)view.findViewById(R.id.box_cfg_content_tv);


        }

    }
}
