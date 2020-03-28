package com.example.hikebuddy;

import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

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
        final TextView hikeTitle = findViewById(R.id.hikename);
        ImageView hikeImage = findViewById(R.id.hikeimages);
        TextView hikeInfo = findViewById(R.id.hikeinfo);

        // Set the text and image from the Intent extra.
        hikeTitle.setText(getIntent().getStringExtra("title"));
        hikeImage.setImageResource(getIntent().getIntExtra("image_resource",0));
        hikeInfo.setText(getIntent().getStringExtra("info"));
        final String hikeTitleIntent = getIntent().getStringExtra("title");

        Button btnMile = findViewById(R.id.buttonMilestone);

        btnMile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HikeInfoActivity.this, Milestone.class);
                startActivity(new Intent(HikeInfoActivity.this, Milestone.class));
                intent.putExtra("hikeTitleInfoActivity", hikeTitleIntent);
            }
        });
    }
}
