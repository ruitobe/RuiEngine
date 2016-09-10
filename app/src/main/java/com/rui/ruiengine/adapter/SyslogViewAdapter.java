package com.rui.ruiengine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.SyslogInfo;

import java.util.List;

/**
 * Created by rhuang on 3/25/16.
 */
public class SyslogViewAdapter extends RecyclerView.Adapter<SyslogViewAdapter.SyslogViewHolder> {

    Context mContext;
    private List<SyslogInfo> mSyslogs;

    public SyslogViewAdapter(Context context, List<SyslogInfo> syslogs) {
        this.mContext = context;
        mSyslogs = syslogs;
    }

    @Override
    public void onBindViewHolder(SyslogViewAdapter.SyslogViewHolder holder, int position) {
        SyslogInfo syslogInfo = mSyslogs.get(position);

        holder.syslogTextView.setText(syslogInfo.getContent());
    }


    @Override
    public int getItemCount() {
        return mSyslogs.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SyslogViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.syslog_view_item, viewGroup, false);
        return new SyslogViewHolder(itemView);
    }


    public class SyslogViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView syslogTextView;

        public SyslogViewHolder(View view)
        {
            super(view);
            syslogTextView = (TextView)view.findViewById(R.id.syslog_content_tv);

        }
    }

}
