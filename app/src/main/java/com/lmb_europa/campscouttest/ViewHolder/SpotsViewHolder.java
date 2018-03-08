package com.lmb_europa.campscouttest.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmb_europa.campscouttest.Interface.ItemClickListener;
import com.lmb_europa.campscouttest.R;

/**
 * Created by AleksandraPC on 27-Feb-18.
 */

public class SpotsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtSpotName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public SpotsViewHolder(View itemView) {
        super(itemView);

        txtSpotName = (TextView) itemView.findViewById(R.id.spot_name);
        imageView = (ImageView) itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}
