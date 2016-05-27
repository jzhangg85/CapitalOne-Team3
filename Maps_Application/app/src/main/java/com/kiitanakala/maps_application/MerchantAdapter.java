package com.kiitanakala.maps_application;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KiitanAkala on 5/26/16.
 */
public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.MerchantViewHolder> {
    ArrayList merchants;
    @Override
    public MerchantAdapter.MerchantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MerchantViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(MerchantAdapter.MerchantViewHolder holder, int position) {
        //set text for text views.
    }

    public MerchantAdapter(ArrayList modelObj){
        merchants = modelObj;

    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }
    public class MerchantViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView locationView;
        TextView categoryView;
        TextView popularityView;
        public MerchantViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.nameID);
            locationView = (TextView)itemView.findViewById(R.id.locationID);
            categoryView = (TextView)itemView.findViewById(R.id.categoryID);
            popularityView = (TextView)itemView.findViewById(R.id.popularityID);



        }
    }

}
