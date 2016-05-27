package com.kiitanakala.sdads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, ArrayList<Merhcant>> result = ( HashMap<String, ArrayList<Merhcant>> )
                getIntent().getSerializableExtra("map");

        entBtn = (Button) findViewById(R.id.ent);
        entBtn.setOnClickListener(this);

        resBtn = (Button) findViewById(R.id.res);
        resBtn.setOnClickListener(this);

        retBtn = (Button) findViewById(R.id.ret);
        retBtn.setOnClickListener(this);

        serBtn = (Button) findViewById(R.id.ser);
        serBtn.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ent:
                Intent nextScreen_ent = new Intent(getApplicationContext(), EntertainmentActivity.class);
                startActivity(nextScreen_ent);
                break;

            case R.id.res:
                Intent nextScreen_res = new Intent(getApplicationContext(), RestaurantActivity.class);
                startActivity(nextScreen_res);
                break;

            case R.id.ret:
                Intent nextScreen_ret = new Intent(getApplicationContext(), RetailActivity.class);
                startActivity(nextScreen_ret);
                break;

            case R.id.ser:
                Intent nextScreen_ser = new Intent(getApplicationContext(), ServicesActivity.class);
                startActivity(nextScreen_ser);
                break;
        }
    }
}
