package com.rui.ruiengine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.SelInfo;

import java.util.List;

/**
 * Created by rhuang on 3/25/16.
 */
public class SelViewAdapter extends RecyclerView.Adapter<SelViewAdapter.SelViewHolder> {

    Context mContext;
    private List<SelInfo> mSels;

    public SelViewAdapter(Context context, List<SelInfo> sels) {
        this.mContext = context;
        mSels = sels;
    }

    @Override
    public int getItemCount() {
        return mSels.size();
    }

    @Override
    public void onBindViewHolder(SelViewAdapter.SelViewHolder holder, int position) {
        SelInfo selInfo = mSels.get(position);

        holder.selTextView.setText(selInfo.getContent());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SelViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sel_view_item, viewGroup, false);
        return new SelViewHolder(itemView);
    }

    public class SelViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView selTextView;

        public SelViewHolder(View view)
        {
            super(view);
            selTextView = (TextView)view.findViewById(R.id.sel_content_tv);

        }
    }

}
