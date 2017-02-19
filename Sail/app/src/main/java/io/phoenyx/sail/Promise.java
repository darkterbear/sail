package io.phoenyx.sail;

/**
 * Created by terrance on 2/18/17.
 */

public class Promise {
    int id;
    String title, description, date, person;
    boolean starred, completed;

    public Promise(int id, String title, String description, String date, String person, boolean starred, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.person = person;
        this.starred = starred;
        this.completed = completed;
    }

    public Promise(String title, String description, String date, String person, boolean starred, boolean completed) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.person = person;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
