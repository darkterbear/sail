package io.phoenyx.sail;

/**
 * Created by terrance on 2/18/17.
 */

public class Promise {
    int id;
    String title, description, duration;
    boolean starred, completed;

    public Promise(int id, String title, String description, String duration, boolean starred, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.starred = starred;
        this.completed = completed;
    }

    public Promise(String title, String description, String duration, boolean starred, boolean completed) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.starred = starred;
        this.completed = completed;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
