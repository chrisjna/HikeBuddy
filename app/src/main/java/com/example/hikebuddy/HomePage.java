package com.example.hikebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;

public class HomePage extends AppCompatActivity {

    private MainActivity main;
    private Context mContext;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        mContext = this;
        searchView = findViewById(R.id.searchButton);
        searchView.setOnQueryTextListener(queryTextListener);
    }

    public void callVisitors(View view){
        Intent detailIntent = new Intent(mContext, Visitors.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
    }

    public void callResidents(View view){
        Intent detailIntent = new Intent(mContext, Residents.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
    }

    public void callFavorites(View view){
        Intent detailIntent = new Intent(mContext, Favorites.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
    }


    public void callInformation(View view){
        Intent detailIntent = new Intent(mContext, InformationActivity.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
    }

    final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("passedQuery", query);
            startActivity(intent);
            searchView.clearFocus();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };


}
