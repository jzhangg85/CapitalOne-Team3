package com.kiitanakala.maps_application;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by KiitanAkala on 5/26/16.
 */
public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.MerchantViewHolder> {
    ArrayList<Merchant> merchants;
    Context context;
    @Override
    public MerchantAdapter.MerchantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlayout,parent,false);
        return new MerchantViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MerchantAdapter.MerchantViewHolder holder, int position) {
        //set text for text views.
        Merchant mer = merchants.get(position);
        holder.categoryView.setText(mer.getCat());
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocation(mer.mLatitude, mer.mLongitude, 1);
        }catch(java.io.IOException e){
            Log.d("geo",e.toString());
        }
        holder.locationView.setText(addresses.get(0).getAddressLine(0));
        holder.nameView.setText(mer.mName);

    }

    public MerchantAdapter(ArrayList<Merchant> modelObj, Context cont){
        merchants = modelObj;
        context = cont;

    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }
    public static class MerchantViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView locationView;
        TextView categoryView;
      //  TextView popularityView;
        public MerchantViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView)itemView.findViewById(R.id.nameID);
            locationView = (TextView)itemView.findViewById(R.id.locationID);
            categoryView = (TextView)itemView.findViewById(R.id.categoryID);
         //   popularityView = (TextView)itemView.findViewById(R.id.popularityID);



        }
    }

}
