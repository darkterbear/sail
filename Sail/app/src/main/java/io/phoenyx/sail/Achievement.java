package io.phoenyx.sail;

/**
 * Created by terrance on 2/18/17.
 */

public class Achievement {
    int id;
    String title, description, date;
    boolean starred;

    public Achievement(int id, String title, String description, String date, boolean starred) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.starred = starred;
    }

    public Achievement(String title, String description, String date, boolean starred) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.starred = starred;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }
}
