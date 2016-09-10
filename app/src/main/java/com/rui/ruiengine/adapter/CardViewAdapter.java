package com.rui.ruiengine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rui.ruiengine.R;
import com.rui.ruiengine.model.FastpathInfo;

import java.util.List;

/**
 * Created by rhuang on 3/27/16.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private List<FastpathInfo> fastpathList;

    public CardViewAdapter(List<FastpathInfo> fastpathList)
    {
        this.fastpathList = fastpathList;
    }

    @Override
    public int getItemCount()
    {
        return fastpathList.size();
    }

    @Override
    public void onBindViewHolder(CardViewAdapter.CardViewHolder holder, int position) {

        FastpathInfo fastpathInfo = fastpathList.get(position);

        holder.vImageView.setImageBitmap(fastpathInfo.getIamge());
        holder.vDescription.setText(fastpathInfo.getDescription());
        holder.vType.setText(fastpathInfo.getType());
        holder.vModel.setText(fastpathInfo.getModel());
        holder.vManufacturer.setText(fastpathInfo.getManufacturer());
        holder.vMac.setText(fastpathInfo.getMac());
        holder.vSystem.setText(fastpathInfo.getSystem());

    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_fastpath, viewGroup, false);
        return new CardViewHolder(itemView);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView vImageView;
        protected TextView vDescription;
        protected TextView vType;
        protected TextView vModel;
        protected TextView vManufacturer;
        protected TextView vMac;
        protected TextView vSystem;

        public CardViewHolder(View view)
        {
            super(view);
            vImageView = (ImageView)view.findViewById(R.id.fastpath_iv);
            vDescription = (TextView)view.findViewById(R.id.description_tv);
            vType = (TextView)view.findViewById(R.id.type_tv);
            vModel = (TextView)view.findViewById(R.id.model_tv);
            vManufacturer = (TextView)view.findViewById(R.id.manufacturer_tv);
            vMac = (TextView)view.findViewById(R.id.mac_tv);
            vSystem = (TextView)view.findViewById(R.id.system_tv);
        }
    }
}
