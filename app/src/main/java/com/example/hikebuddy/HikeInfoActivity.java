package com.example.hikebuddy;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.CheckBox;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.LinearLayout;
import android.widget.Toast;

public class HikeInfoActivity extends AppCompatActivity {

    /**
     * Initializes the activity, filling in the data from the Intent.
     *
     * @param savedInstanceState Contains information about the saved state
     *                           of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hike_info_activity);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        // Initialize the views.
        final TextView hikeTitle = findViewById(R.id.hikename);
        ImageView hikeImage = findViewById(R.id.hikeimages);
        TextView hikeInfo = findViewById(R.id.hikeinfo);
        RatingBar hikeDiff = findViewById(R.id.difficulty);

        // Set the text and image from the Intent extra.
        hikeTitle.setText(getIntent().getStringExtra("title"));
        hikeImage.setImageResource(getIntent().getIntExtra("image_resource",0));
        hikeInfo.setText(getIntent().getStringExtra("info"));
        hikeDiff.setRating(getIntent().getIntExtra("diff", 0));

        //To make the checkboxes I take a string and use Regex to split up the string into an array
        //Only works with ONE word items, ex "boots", NOT "hiking boots"
        String gearString = getIntent().getStringExtra("gear");
        String[] gear = gearString.split("\\s+");
        for (int i = 0; i < gear.length; i++) {
            gear[i] = gear[i].replaceAll("[^\\w]", "");
            System.out.println(gear[i]);
        }

        LinearLayout checkboxList = findViewById(R.id.checkboxes);
        int arraylength = gear.length;

        //Make a new checkbox for each item in the gear array, so it can be different for each hike
        for (int i = 0; i < arraylength; i++)
        {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                }
            });
            checkBox.setId(i);
            checkBox.setText(gear[i]);
            checkboxList.addView(checkBox);
        }

        //for Milestone
        final String hikeTitleIntent = getIntent().getStringExtra("title");

        Button btnMile = findViewById(R.id.buttonMilestone);

        btnMile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HikeInfoActivity.this, Milestone.class);
                intent.putExtra("hikeTitleInfoActivity",hikeTitleIntent);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        menu.findItem(R.id.search).setVisible(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Toast.makeText(this, "activity started", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
