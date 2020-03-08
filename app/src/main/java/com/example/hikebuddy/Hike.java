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

    /**
     * Constructor for the Sport data model.
     *
     * @param title The name if the sport.
     * @param info Information about the sport.
     */
    public Hike(String title, String info, int imageResource, int diff) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.diff = diff;
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

}
