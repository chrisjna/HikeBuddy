package com.example.hikebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class Milestone extends AppCompatActivity {

    ViewPager viewPager;
    String hikeTitleMilestone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);

        //get string
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            hikeTitleMilestone = extras.getString("hikeTitleInfoActivity");
        }

        ViewPagerMilestone viewPagerAdapter = new ViewPagerMilestone(this, hikeTitleMilestone);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
