package com.example.hikebuddy;

import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.view.LayoutInflater;
import android.content.Context;
import com.example.hikebuddy.R;
import static android.view.KeyCharacterMap.load;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        // Initialize the views.
        TextView hikeTitle = findViewById(R.id.hikename);
        ImageView hikeImage = findViewById(R.id.hikeimages);
        TextView hikeInfo = findViewById(R.id.hikeinfo);

        // Set the text and image from the Intent extra.
        hikeTitle.setText(getIntent().getStringExtra("title"));
        hikeImage.setImageResource(getIntent().getIntExtra("image_resource",0));
        hikeInfo.setText(getIntent().getStringExtra("info"));
    }
}
