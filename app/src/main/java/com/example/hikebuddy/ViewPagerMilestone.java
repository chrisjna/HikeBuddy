package com.example.hikebuddy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.net.URISyntaxException;

import static android.content.Intent.getIntent;


public class ViewPagerMilestone extends PagerAdapter {

    private Context context;
    private Integer [] images = {R.drawable.img_koko_head,R.drawable.mainpageimage,R.drawable.img_makapuu};
    private String[] texts = {"text1", "text2", "text3"};
    private String[] titleArray;
    private String[] hikeMilestones;
    private String currentHike = "";

    public ViewPagerMilestone(Context context, String hikeTitleMilestone) {
        this.context = context;

        currentHike = hikeTitleMilestone;
        titleArray = context.getResources().getStringArray(R.array.hike_names);
        hikeMilestones = context.getResources().getStringArray(R.array.hike_milestone);
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        TextView textView = (TextView) view.findViewById(R.id.milestoneDetail);
        textView.setText(texts[position]);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(getCount() - 1);
        progressBar.setProgress(position);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
