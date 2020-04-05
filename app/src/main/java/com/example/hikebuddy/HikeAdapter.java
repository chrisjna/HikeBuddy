package com.example.hikebuddy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the Hike data.
 */
public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder> {

    private ArrayList<Hike> mHikeData;
    private Context mContext;

    /**
     * Constructor that passes in the Hike data and the context.
     *
     * @param HikeData ArrayList containing the Hike data.
     * @param context  Context of the application.
     */
    HikeAdapter(Context context, ArrayList<Hike> HikeData) {
        this.mHikeData = HikeData;
        this.mContext = context;

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

        holder.mTitleText.setText(currentHike.getTitle());
        holder.mInfoText.setText(currentHike.getInfo());
        holder.mHikeImage.setImageResource(mHikeData.get(position).getImageResource());
        holder.mHikeDiff.setRating(currentHike.getDiff());
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
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
            mContext.startActivity(detailIntent);
        }
    }
}