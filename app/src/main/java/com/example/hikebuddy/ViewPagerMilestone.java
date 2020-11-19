package com.example.hikebuddy;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ViewPagerMilestone extends PagerAdapter {

    private final Context context;
    private String [] texts;
    private TypedArray images;

    public ViewPagerMilestone(Context context,  String hike) {
        this.context = context;
        String hiker = hike + " Milestone";
        hiker = hiker.replaceAll(" ", "_");
        String hikerPictures = hiker + "_Images";

        for (Field field : R.array.class.getDeclaredFields())
        {
            if (Modifier.isStatic(field.getModifiers()) && !Modifier.isPrivate(field.getModifiers()) && field.getType().equals(int.class))
            {
                try
                {
                    if (field.getName().matches(hiker))
                    {
                        int id = field.getInt(R.array.class);
                        texts = context.getResources().getStringArray(id);
                    }

                    if (field.getName().matches(hikerPictures)){
                        int id = field.getInt(R.array.class);
                        images = context.getResources().obtainTypedArray(id);
                    }
                } catch (IllegalArgumentException ignored)
                {
                } catch (IllegalAccessException ignored)
                {
                }
            }
        }
    }

    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.content_milestone_adapter, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images.getResourceId(position, -1));
        
        TextView textView = (TextView) view.findViewById(R.id.milestoneDetail);
        textView.setText(texts[position]);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(getCount());
        progressBar.setProgress(position + 1);

        String maxNum = Integer.toString(getCount());
        String current = Integer.toString(position + 1);
        TextView textViewNumber = (TextView) view.findViewById(R.id.textViewNumber) ;
        textViewNumber.setText(current + " of " + maxNum);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

}
