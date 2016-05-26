package com.kiitanakala.maps_application;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by KiitanAkala on 5/26/16.
 */
public class MerchantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class MerchantViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView locationView;
        TextView categoryView;
        TextView popularityView;
        public MerchantViewHolder(View itemView) {
            super(itemView);

        }
    }

}
