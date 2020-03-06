package com.example.hikebuddy;

import android.os.Bundle;
import android.content.res.TypedArray;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Hike> HikeData;
    private HikeAdapter Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        HikeData = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_hike_list);
        Adapter = new HikeAdapter(this, HikeData);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializeData();
        HikeData.add(new Hike("Koko head", "info on koko head", R.drawable.img_koko_head));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
     *
    private void initializeData() {
        // Get the resources from the XML file.
        String[] HikeList = getResources()
                .getStringArray(R.array.hike_names);
        String[] HikeInfo = getResources()
                .getStringArray(R.array.hike_info);
        TypedArray HikeImageResources = getResources()
                .obtainTypedArray(R.array.hike_images);

        // Clear the existing data (to avoid duplication).
        HikeData.clear();

        // Create the ArrayList of Hike objects with the titles and
        // information about each Hike
        for (int i = 0; i < HikeList.length; i++) {
            HikeData.add(new Hike(HikeList[i], HikeInfo[i],
                    HikeImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        HikeImageResources.recycle();

        // Notify the adapter of the change.
        Adapter.notifyDataSetChanged();
    }
     */
}
