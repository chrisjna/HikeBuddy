package com.example.hikebuddy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;

public class HomePage extends AppCompatActivity {

    private MainActivity main;
    private Context mContext;
    private SearchView searchView;
    private PopupWindow popupWindow;


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
        onButtonShowPopupWindowClick(view);
        searchView.clearFocus();
    }

    public void callResidents(View view){
        Intent detailIntent = new Intent(mContext, Residents.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
        popupWindow.dismiss();
    }

    public void callFavorites(View view){
        Intent detailIntent = new Intent(mContext, Favorites.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
        popupWindow.dismiss();

    }


    public void callInformation(View view){
        Intent detailIntent = new Intent(mContext, InformationActivity.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
        popupWindow.dismiss();
    }

    public void callDifficulty(View view){
        Intent detailIntent = new Intent(mContext, Difficulty.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
        popupWindow.dismiss();
    }

    public void callDistance(View view){
        Intent detailIntent = new Intent(mContext, Distance.class);
        mContext.startActivity(detailIntent);
        searchView.clearFocus();
        popupWindow.dismiss();
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(20);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
