package com.example.hikebuddy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Data model for each row of the RecyclerView
 */
class Hike implements Parcelable {

    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private final int imageResource;
    private int diff;
    private boolean favStatus;


    public Hike(String title, String info, int imageResource, int diff, boolean favStatus) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.diff = diff;
        this.favStatus = favStatus;
    }

    /**
     * Gets the title of the sport.
     *
     * @return The title of the sport.
     */
    String getTitle() {
        return title;
    }

    /**
     * Gets the info about the sport.
     *
     * @return The info about the sport.
     */
    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    int getDiff() {return diff;}

    public boolean getFavStatus(){
        return favStatus;
    }

    public void setFavStatus(boolean favStatus){
        this.favStatus = favStatus;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.info);
        dest.writeInt(this.imageResource);
        dest.writeInt(this.diff);
        dest.writeBoolean(this.favStatus);
    }

    public static final Parcelable.Creator<Hike> CREATOR = new Parcelable.Creator<Hike>() {
        public Hike createFromParcel(Parcel in) {
            return new Hike(in);
        }

        public Hike[] newArray(int size) {
            return new Hike[size];
        }
    };

    public Hike(Parcel in){
        this.title = in.readString();
        this.info = in.readString();
        this.imageResource = in.readInt();
        this.diff = in.readInt();
        this.favStatus = in.readBoolean();
    }
}
