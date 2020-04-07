package com.example.hikebuddy;

/**
 * Data model for each row of the RecyclerView
 */
class Hike {

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

    int getDiff() {
        return diff;
    }

    public boolean getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(boolean favStatus) {
        this.favStatus = favStatus;
    }
}