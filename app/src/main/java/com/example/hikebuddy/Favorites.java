package com.example.hikebuddy;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Favorites  extends AppCompatActivity {
    private ArrayList<Hike> HikeData;
    private HikeAdapter Adapter;
    private RecyclerView recyclerView;
    private Context mContext;
    private SearchView searchView;
    private Toolbar toolbar;
    private TextView textview;
    private boolean refresh = false;
    private static boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        HikeData = new ArrayList<>();
        mContext = this;

        initializeData();

        recyclerView = findViewById(R.id.rv_hike_list);
        Adapter = new HikeAdapter(this, HikeData);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textview = findViewById(R.id.textView);

        if (HikeData != null && HikeData.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            textview.setVisibility(View.VISIBLE);
        }

        Adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.trek_foreground);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                toolbar.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                filter(query);
                return true;
            }
        });
        return true;
    }

    private void filter(String text) {
        ArrayList<Hike> filteredList = new ArrayList<>();

        for (Hike item : HikeData) {
            if (item.getTitle().contains(text.toLowerCase())) {
                recyclerView = findViewById(R.id.rv_hike_list);
                textview = findViewById(R.id.textView);
                recyclerView.setVisibility(View.VISIBLE);
                textview.setVisibility(View.GONE);
                filteredList.add(item);
                Adapter.filterList(filteredList);
            } else if (filteredList.isEmpty()){
                recyclerView = findViewById(R.id.rv_hike_list);
                textview = findViewById(R.id.textView);
                recyclerView.setVisibility(View.GONE);
                textview.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent detailIntent = new Intent(mContext, HomePage.class);
            mContext.startActivity(detailIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize the Hike data from resources.
     */
    private void initializeData() {
        SharedPreferences pref = getSharedPreferences( "prefs", Context.MODE_PRIVATE);
        //Retrieve the SharedPreference string and convert it back into an arraylist of Hike objects.
        Gson gson = new Gson();
        String json = pref.getString("favs", null);
        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
        HikeData = gson.fromJson(json, type);
    }

    @Override
    public void onStart() {
        super.onStart();
        running = true;
    }

     @Override
    public void onStop() {
        super.onStop();
        running = false;
        refresh = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (refresh) {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            refresh = false;
        }
    }

    public boolean isRunning(){
        return running;
    }

}
