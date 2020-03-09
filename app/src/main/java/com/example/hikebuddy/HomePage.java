package com.example.hikebuddy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;

import android.view.View;

public class HomePage extends AppCompatActivity {

    private MainActivity main;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;



    }

    public void callVisitors(View view){
        Intent detailIntent = new Intent(mContext, Visitors.class);
        mContext.startActivity(detailIntent);
    }

    public void callResidents(View view){
        Intent detailIntent = new Intent(mContext, Residents.class);
        mContext.startActivity(detailIntent);
    }

    public void callFavorites(View view){
        Intent detailIntent = new Intent(mContext, Favorites.class);
        mContext.startActivity(detailIntent);
    }

    public void callInformation(View view){
        //Intent detailIntent = new Intent(mContext, Information.class);
        //mContext.startActivity(detailIntent);
    }

}
