package com.example.hikebuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.res.TypedArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Favorites  extends AppCompatActivity {
    private ArrayList<Hike> HikeData;
    private HikeAdapter Adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        HikeData = new ArrayList<>();

        initializeData();

        recyclerView = findViewById(R.id.rv_hike_list);
        Adapter = new HikeAdapter(this, HikeData);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Initialize the Hike data from resources.
     */
    private void initializeData() {
        SharedPreferences pref = getSharedPreferences( "prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = pref.getString("favs", null);
        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
        HikeData = gson.fromJson(json, type);

    }

}
