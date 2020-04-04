package com.example.hikebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;

public class HomePage extends AppCompatActivity {

    private MainActivity main;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
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

        Button filterVisitor = findViewById(R.id.button2);
        Button informationButton = findViewById(R.id.button6);
        SearchView searchView = findViewById(R.id.searchButton);

        filterVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, MainActivity.class));
            }
        });

        searchView.setOnQueryTextListener(queryTextListener);

        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, InformationActivity.class));
            }
        });

    }

    final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("passedQuery", query);
            startActivity(intent);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}
