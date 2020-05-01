package com.example.hikebuddy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class HomePage extends AppCompatActivity {

    private MainActivity main;
    private Context mContext;
    private SearchView searchView;
    private PopupWindow popupWindow;
    int PERMISSION_ID = 44;

    String API = "aa6de6253900fd1126c0882059f9e591";



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.weather) {
            if(checkPermissions()){
                if(isLocationEnabled())
                    new weatherTask().execute();
            } else{
                requestPermissions();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class weatherTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... args) {
            String result = "please change";
            try {
                URL url= new URL("https://api.openweathermap.org/data/2.5/weather?q=honolulu,usa&appid=" + API);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream response = new BufferedInputStream(con.getInputStream());
                java.util.Scanner scanner = new java.util.Scanner(response,"UTF-8").useDelimiter("//A");
                result = scanner.hasNext() ? scanner.next() : "";
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            String weatherDescription = "test";
            String location = "test";
            String temp = "test";
            String humidity = "test";
            String ALLweather = "test";
            try {
                JSONObject jsn = new JSONObject(result);
                JSONObject main = jsn.getJSONObject("main");
                JSONObject weather = jsn.getJSONArray("weather").getJSONObject(0);

                location = jsn.getString("name");
                temp = main.getString("temp");
                double i = Double.parseDouble(temp);
                i = (i/10 * 9/5) + 32;
                i = Math.floor(i * 1e2) / 1e2;
                System.out.println(i);
                temp = Double.toString(i) + "°F";

                weatherDescription = weather.getString("description");
                humidity = main.getString("humidity");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ALLweather = location + "\n" + "Temp: "  + temp + "\n" + "Humidity: " + humidity + "\n" + weatherDescription;
            System.out.println(ALLweather);
            Toast toast = Toast.makeText(mContext, ALLweather, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 250);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(20);
            toast.show();
        }
    }

        public void callFilter(View view) {
            onButtonShowPopupWindowClick(view);
            searchView.clearFocus();

        }

        public void callMain(View view) {
            Intent detailIntent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(detailIntent);
            searchView.clearFocus();
            popupWindow.dismiss();
        }

        public void callVisitors(View view) {
            Intent detailIntent = new Intent(mContext, Visitors.class);
            mContext.startActivity(detailIntent);
            searchView.clearFocus();
            popupWindow.dismiss();
        }

        public void callResidents(View view) {
            Intent detailIntent = new Intent(mContext, Residents.class);
            mContext.startActivity(detailIntent);
            searchView.clearFocus();
            popupWindow.dismiss();
        }

        public void callDifficulty(View view) {
            Intent detailIntent = new Intent(mContext, Difficulty.class);
            mContext.startActivity(detailIntent);
            searchView.clearFocus();
            popupWindow.dismiss();
        }

        public void callDistance(View view) {
            Intent detailIntent = new Intent(mContext, Distance.class);
            mContext.startActivity(detailIntent);
            searchView.clearFocus();
            popupWindow.dismiss();
        }

        public void callFavorites(View view) {
            Intent detailIntent = new Intent(mContext, Favorites.class);
            mContext.startActivity(detailIntent);
            searchView.clearFocus();
            popupWindow.dismiss();
        }

        public void callInformation(View view) {
            Intent detailIntent = new Intent(mContext, InformationActivity.class);
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

        public void onButtonShowPopupWindowClick(View view) {

            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            final View popupView = inflater.inflate(R.layout.window, null);

            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;
            boolean focusable = true;
            popupWindow = new PopupWindow(popupView, width, height, focusable);
            popupView.getBackground().setAlpha(175);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.setElevation(20);
            }

            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    popupView.getBackground().setAlpha(0); // restore
                    return true;
                }
            });
        }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
}
