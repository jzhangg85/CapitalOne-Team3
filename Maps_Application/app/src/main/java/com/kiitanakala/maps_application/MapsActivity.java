package com.kiitanakala.maps_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        MerchantAdapter ma = new MerchantAdapter(merch,getApplicationContext());//modelOBJ
        rv.setAdapter(ma);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        Merchant m1 = new Merchant("a",38.21232112,40,"abc","b");
        Merchant m2 = new Merchant("a",38.20232112,40,"abc","b");
        Merchant m3 = new Merchant("a",38.23232112,40,"abc","b");


        merch.add(m1);
        merch.add(m2);
        merch.add(m3);
        //ArrayList<LatLng> listLng = getLatLong(merch);


//         Add a marker in Sydney and move the camera
        for(Merchant a:merch){
            MarkerOptions mo = new MarkerOptions();
            mo.position(new LatLng(a.mLatitude,a.mLongitude));
            mo.title(a.mName);

            mMap.addMarker(mo);
        }
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(merch.get(0).mLatitude,merch.get(0).mLongitude)));
       // addHeatMap(merch);


    }
    private void addHeatMap(ArrayList<LatLng> list) {


        // Get the data: latitude/longitude positions of police stations.

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }
}
