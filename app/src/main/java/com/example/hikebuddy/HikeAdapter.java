package com.example.hikebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/***
 * The adapter class for the RecyclerView, contains the Hike data.
 */
public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder> {

    private ArrayList<Hike> mHikeData;
    private Context mContext;
    private int index;
    private ArrayList<Hike> FavHikes = new ArrayList<Hike>();
    private Residents residents = new Residents();
    private Visitors visitors = new Visitors();
    private Favorites favorites = new Favorites();

    /**
     * Constructor that passes in the Hike data and the context.
     *
     * @param HikeData ArrayList containing the Hike data.
     * @param context  Context of the application.
     */
    HikeAdapter(Context context, ArrayList<Hike> HikeData) {
        this.mHikeData = HikeData;
        this.mContext = context;
        index = 0;
    }

    public void filterList(ArrayList<Hike> filteredList) {
        mHikeData = filteredList;
        notifyDataSetChanged();
    }
    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public HikeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.hike_item, parent, false));

    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(HikeAdapter.ViewHolder holder, int position) {
        // Get current Hike.
        Hike currentHike = mHikeData.get(position);
        final ViewHolder viewHolder = holder;

        holder.mTitleText.setText(currentHike.getTitle());
        holder.mInfoText.setText(currentHike.getInfo());
        holder.mHikeImage.setImageResource(mHikeData.get(position).getImageResource());
        holder.mHikeDiff.setRating(currentHike.getDiff());

        if(currentHike.getFavStatus()) {
            viewHolder.mFav.setPressed(true);
            viewHolder.mFav.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        }

        if(!currentHike.getFavStatus()){
            viewHolder.mFav.setPressed(false);
            viewHolder.mFav.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
        }

        viewHolder.mFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                //Use Sharedpreferences to store the state of fav button AND to store the actual Hike object that got favorited
                SharedPreferences pref = mContext.getSharedPreferences( "prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                //getAdapterPosition gets the current clicked on hike
                Hike currentHike = mHikeData.get(viewHolder.getAdapterPosition());
                Gson gson = new Gson();
                String json = new String();

                if (currentHike.getFavStatus() == false) {
                    currentHike.setFavStatus(true);
                    viewHolder.mFav.setPressed(true);
                    editor.putBoolean("favbutton", true);
                    editor.apply();

                    if(residents.isRunning()) {
                        json = pref.getString("favs", null);
                        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
                        if(gson.fromJson(json, type) == null) {
                            Hike addedHike = mHikeData.get(viewHolder.getAdapterPosition());
                            FavHikes.add(addedHike);
                            json = gson.toJson(FavHikes);
                            editor.putString("favs", json);
                            editor.apply();
                        }
                        else{
                            FavHikes = gson.fromJson(json, type);
                            Hike addedHike = mHikeData.get(viewHolder.getAdapterPosition());
                            FavHikes.add(addedHike);
                            json = gson.toJson(FavHikes);
                            editor.putString("favs", json);
                            editor.apply();
                        }
                    }

                    if(visitors.isRunning()) {
                        json = pref.getString("favs", null);
                        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
                        if(gson.fromJson(json, type) == null) {
                            Hike addedHike = mHikeData.get(viewHolder.getAdapterPosition());
                            FavHikes.add(addedHike);
                            json = gson.toJson(FavHikes);
                            editor.putString("favs", json);
                            editor.apply();
                        }
                        else{
                            FavHikes = gson.fromJson(json, type);
                            Hike addedHike = mHikeData.get(viewHolder.getAdapterPosition());
                            FavHikes.add(addedHike);
                            json = gson.toJson(FavHikes);
                            editor.putString("favs", json);
                            editor.apply();
                        }
                }


                    Toast.makeText(mContext, "Hike Favorited", Toast.LENGTH_SHORT).show();
                    viewHolder.mFav.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    notifyDataSetChanged();
                }
                else{
                    currentHike.setFavStatus(false);
                    editor.putBoolean("favbutton", false);
                    editor.apply();

                    if(residents.isRunning()) {
                        json = pref.getString("favs", null);
                        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
                        FavHikes = gson.fromJson(json, type);
                        Hike removedHike = mHikeData.get(viewHolder.getAdapterPosition());
                        FavHikes.remove(removedHike);
                        json = gson.toJson(FavHikes);
                        editor.putString("favs", json);
                        editor.apply();
                    }
                    if(visitors.isRunning()) {
                        json = pref.getString("favs", null);
                        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
                        FavHikes = gson.fromJson(json, type);
                        Hike removedHike = mHikeData.get(viewHolder.getAdapterPosition());
                        FavHikes.remove(removedHike);
                        json = gson.toJson(FavHikes);
                        editor.putString("favs", json);
                        editor.apply();
                    }
                    if(favorites.isRunning()){
                        json = pref.getString("favs", null);
                        Type type = new TypeToken<ArrayList<Hike>>() {}.getType();
                        FavHikes = gson.fromJson(json, type);
                        Hike removedHike = FavHikes.get(viewHolder.getAdapterPosition());
                        FavHikes.remove(removedHike);
                        json = gson.toJson(FavHikes);
                        editor.putString("favs", json);
                        editor.apply();
                    }

                    Toast.makeText(mContext, "Hike Unfavorited", Toast.LENGTH_SHORT).show();
                    viewHolder.mFav.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                    notifyDataSetChanged();

                }
            }

        });
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        if(mHikeData == null)
            return 0;
        return mHikeData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mHikeImage;
        private RatingBar mHikeDiff;
        private Button mFav;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.hikename);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mHikeImage = itemView.findViewById(R.id.hikeimages);
            mHikeDiff = itemView.findViewById(R.id.difficulty);
            mFav = itemView.findViewById(R.id.favorite);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        /**
         * Handle click to show HikeInfoActivity.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
            Hike currentHike = mHikeData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, HikeInfoActivity.class);
            detailIntent.putExtra("title", currentHike.getTitle());
            detailIntent.putExtra("image_resource",
                    currentHike.getImageResource());
            detailIntent.putExtra("info", currentHike.getInfo());
            detailIntent.putExtra("diff", currentHike.getDiff());
            detailIntent.putExtra("gear", currentHike.getGear());
            mContext.startActivity(detailIntent);
        }
    }
}