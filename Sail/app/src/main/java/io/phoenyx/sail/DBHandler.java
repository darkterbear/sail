package io.phoenyx.sail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by terrance on 2/18/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    //DATABASE VERSION & NAME
    private static final int DATABASE_VERSION = 1;
    private static final String DATABSE_NAME = "data.db";

    //TABLE NAMES
    private static final String TABLE_GOALS = "goals";
    private static final String TABLE_ACHIEVEMENTS = "achievements";
    private static final String TABLE_PROMISES = "promises";
    private static final String TABLE_TIMELINE = "timeline";

    //GOALS TABLE COLUMN NAMES
    private static final String GOALS_ID_COLUMN = "id";
    private static final String GOALS_TITLE_COLUMN = "title";
    private static final String GOALS_DESCRIPTION_COLUMN = "description";
    private static final String GOALS_DATE_COLUMN = "date";
    private static final String GOALS_STARRED_COLUMN = "starred";
    private static final String GOALS_COMPLETED_COLUMN = "completed";

    //ACHIEVEMENTS TABLE COLUMN NAMES
    private static final String ACHIEVEMENTS_ID_COLUMN = "id";
    private static final String ACHIEVEMENTS_TITLE_COLUMN = "title";
    private static final String ACHIEVEMENTS_DESCRIPTION_COLUMN = "description";
    private static final String ACHIEVEMENTS_DATE_COLUMN = "date";
    private static final String ACHIEVEMENTS_STARRED_COLUMN = "starred";

    //PROMISES TABLE COLUMN NAMES
    private static final String PROMISES_ID_COLUMN = "id";
    private static final String PROMISES_TITLE_COLUMN = "title";
    private static final String PROMISES_DESCRIPTION_COLUMN = "description";
    private static final String PROMISES_DURATION_COLUMN = "duration";
    private static final String PROMISES_STARRED_COLUMN = "starred";
    private static final String PROMISES_COMPLETED_COLUMN = "completed";

    //TIMELINE TABLE COLUMN NAMES
    private static final String TIMELINE_EVENT_ID_COLUMN = "id";
    private static final String TIMELINE_EVENT_TITLE_COLUMN = "title";
    private static final String TIMELINE_EVENT_DESCRIPTION_COLUMN = "description";
    private static final String TIMELINE_EVENT_DATE_COLUMN = "date";

    //TABLE INIT STATEMENTS
    //GOALS TALBE
    private static final String CREATE_GOALS_TABLE = "CREATE TABLE "
            + TABLE_GOALS + "(" + GOALS_ID_COLUMN + " INTEGER PRIMARY KEY," + GOALS_TITLE_COLUMN + " TEXT,"
            + GOALS_DESCRIPTION_COLUMN + " TEXT,"
            + GOALS_DATE_COLUMN + " TEXT,"
            + GOALS_STARRED_COLUMN + " INTEGER,"
            + GOALS_COMPLETED_COLUMN + " INTEGER)";

    //ACHIEVEMENTS TALBE
    private static final String CREATE_ACHIEVEMENTS_TABLE = "CREATE TABLE "
            + TABLE_ACHIEVEMENTS + "(" + ACHIEVEMENTS_ID_COLUMN + " INTEGER PRIMARY KEY," + ACHIEVEMENTS_TITLE_COLUMN + " TEXT,"
            + ACHIEVEMENTS_DESCRIPTION_COLUMN + " TEXT,"
            + ACHIEVEMENTS_DATE_COLUMN + " TEXT,"
            + ACHIEVEMENTS_STARRED_COLUMN + " INTEGER)";

    //PROMISES TALBE
    private static final String CREATE_PROMISES_TABLE = "CREATE TABLE "
            + TABLE_PROMISES + "(" + PROMISES_ID_COLUMN + " INTEGER PRIMARY KEY," + PROMISES_TITLE_COLUMN + " TEXT,"
            + PROMISES_DESCRIPTION_COLUMN + " TEXT,"
            + PROMISES_DURATION_COLUMN + " TEXT,"
            + PROMISES_STARRED_COLUMN + " INTEGER,"
            + PROMISES_COMPLETED_COLUMN + " INTEGER)";

    //TIMELINE TALBE
    private static final String CREATE_TIMELINE_TABLE = "CREATE TABLE "
            + TABLE_TIMELINE+ "(" + TIMELINE_EVENT_ID_COLUMN + " INTEGER PRIMARY KEY," + TIMELINE_EVENT_TITLE_COLUMN + " TEXT,"
            + TIMELINE_EVENT_DESCRIPTION_COLUMN + " TEXT,"
            + TIMELINE_EVENT_DATE_COLUMN + " TEXT)";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE TABLES
        sqLiteDatabase.execSQL(CREATE_GOALS_TABLE);
        sqLiteDatabase.execSQL(CREATE_ACHIEVEMENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_PROMISES_TABLE);
        sqLiteDatabase.execSQL(CREATE_TIMELINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMISES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMELINE);

        onCreate(sqLiteDatabase);
    }

    //******DATABASE ENDPOITNS******

    //CREATERS
    public int createGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GOALS_TITLE_COLUMN, goal.getTitle());
        values.put(GOALS_DESCRIPTION_COLUMN, goal.getDescription());
        values.put(GOALS_DATE_COLUMN, goal.getDate());
        values.put(GOALS_STARRED_COLUMN, (goal.isStarred()) ? 1 : 0);
        values.put(GOALS_COMPLETED_COLUMN, (goal.isCompleted()) ? 1 : 0);

        long goalID = db.insert(TABLE_GOALS, null, values);
        return safeLongToInt(goalID);
    }

    public int createAchievement(Achievement achievement) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACHIEVEMENTS_TITLE_COLUMN, achievement.getTitle());
        values.put(ACHIEVEMENTS_DESCRIPTION_COLUMN, achievement.getDescription());
        values.put(ACHIEVEMENTS_DATE_COLUMN, achievement.getDate());
        values.put(ACHIEVEMENTS_STARRED_COLUMN, (achievement.isStarred()) ? 1 : 0);

        long achievementID = db.insert(TABLE_ACHIEVEMENTS, null, values);
        return safeLongToInt(achievementID);
    }

    public int createPromise(Promise promise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROMISES_TITLE_COLUMN, promise.getTitle());
        values.put(PROMISES_DESCRIPTION_COLUMN, promise.getDescription());
        values.put(PROMISES_DURATION_COLUMN, promise.getDuration());
        values.put(PROMISES_STARRED_COLUMN, (promise.isStarred()) ? 1 : 0);
        values.put(PROMISES_COMPLETED_COLUMN, (promise.isCompleted()) ? 1 : 0);

        long goalID = db.insert(TABLE_GOALS, null, values);
        return safeLongToInt(goalID);
    }

    public int createTimelineEvent(TimelineEvent timelineEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TIMELINE_EVENT_TITLE_COLUMN, timelineEvent.getTitle());
        values.put(TIMELINE_EVENT_DESCRIPTION_COLUMN, timelineEvent.getDescription());
        values.put(TIMELINE_EVENT_DATE_COLUMN, timelineEvent.getDate());

        long goalID = db.insert(TABLE_GOALS, null, values);
        return safeLongToInt(goalID);
    }

    //FETCHERS
    public Goal getGoal(int goalID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_GOALS + " WHERE " + GOALS_ID_COLUMN + " = " + goalID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Goal goal = new Goal(cursor.getInt(cursor.getColumnIndex(GOALS_ID_COLUMN)),
                cursor.getString(cursor.getColumnIndex(GOALS_TITLE_COLUMN)),
                cursor.getString(cursor.getColumnIndex(GOALS_DESCRIPTION_COLUMN)),
                cursor.getString(cursor.getColumnIndex(GOALS_DATE_COLUMN)),
                (cursor.getInt(cursor.getColumnIndex(GOALS_STARRED_COLUMN)) != 0),
                (cursor.getInt(cursor.getColumnIndex(GOALS_COMPLETED_COLUMN)) != 0));

        return goal;
    }

    public Achievement getAchievement(int achievementID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ACHIEVEMENTS + " WHERE " + ACHIEVEMENTS_ID_COLUMN + " = " + achievementID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Achievement achievement = new Achievement(cursor.getInt(cursor.getColumnIndex(ACHIEVEMENTS_ID_COLUMN)),
                cursor.getString(cursor.getColumnIndex(ACHIEVEMENTS_TITLE_COLUMN)),
                cursor.getString(cursor.getColumnIndex(ACHIEVEMENTS_DESCRIPTION_COLUMN)),
                cursor.getString(cursor.getColumnIndex(ACHIEVEMENTS_DATE_COLUMN)),
                (cursor.getInt(cursor.getColumnIndex(ACHIEVEMENTS_STARRED_COLUMN)) != 0));

        return achievement;
    }

    public Promise getPromise(int promiseID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PROMISES + " WHERE " + PROMISES_ID_COLUMN + " = " + promiseID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Promise promise = new Promise(cursor.getInt(cursor.getColumnIndex(PROMISES_ID_COLUMN)),
                cursor.getString(cursor.getColumnIndex(PROMISES_TITLE_COLUMN)),
                cursor.getString(cursor.getColumnIndex(PROMISES_DESCRIPTION_COLUMN)),
                cursor.getString(cursor.getColumnIndex(PROMISES_DURATION_COLUMN)),
                (cursor.getInt(cursor.getColumnIndex(PROMISES_STARRED_COLUMN)) != 0),
                (cursor.getInt(cursor.getColumnIndex(PROMISES_COMPLETED_COLUMN)) != 0));

        return promise;
    }

    public TimelineEvent getTimelineEvent(int timelineEventID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TIMELINE + " WHERE " + TIMELINE_EVENT_ID_COLUMN + " = " + timelineEventID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        TimelineEvent timelineEvent = new TimelineEvent(cursor.getInt(cursor.getColumnIndex(TIMELINE_EVENT_ID_COLUMN)),
                cursor.getString(cursor.getColumnIndex(TIMELINE_EVENT_TITLE_COLUMN)),
                cursor.getString(cursor.getColumnIndex(TIMELINE_EVENT_DESCRIPTION_COLUMN)),
                cursor.getString(cursor.getColumnIndex(TIMELINE_EVENT_DATE_COLUMN)));

        return timelineEvent;
    }

    //LIST FETCHERS
    public List<Goal> getAllGoals() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Goal> goals = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_GOALS;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Goal goal = new Goal(cursor.getInt(cursor.getColumnIndex(GOALS_ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(GOALS_TITLE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(GOALS_DESCRIPTION_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(GOALS_DATE_COLUMN)),
                        (cursor.getInt(cursor.getColumnIndex(GOALS_STARRED_COLUMN)) != 0),
                        (cursor.getInt(cursor.getColumnIndex(GOALS_COMPLETED_COLUMN)) != 0));
                goals.add(goal);
            } while (cursor.moveToNext());
        }

        return goals;
    }

    public List<Achievement> getAllAchievements() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Achievement> achievements = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ACHIEVEMENTS;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Achievement achievement = new Achievement(cursor.getInt(cursor.getColumnIndex(ACHIEVEMENTS_ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ACHIEVEMENTS_TITLE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ACHIEVEMENTS_DESCRIPTION_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ACHIEVEMENTS_DATE_COLUMN)),
                        (cursor.getInt(cursor.getColumnIndex(ACHIEVEMENTS_STARRED_COLUMN)) != 0));
                achievements.add(achievement);
            } while (cursor.moveToNext());
        }

        return achievements;
    }

    public List<Promise> getAllPromises() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Promise> promises = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PROMISES;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Promise promise = new Promise(cursor.getInt(cursor.getColumnIndex(PROMISES_ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(PROMISES_TITLE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(PROMISES_DESCRIPTION_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(PROMISES_DURATION_COLUMN)),
                        (cursor.getInt(cursor.getColumnIndex(PROMISES_STARRED_COLUMN)) != 0),
                        (cursor.getInt(cursor.getColumnIndex(PROMISES_COMPLETED_COLUMN)) != 0));
                promises.add(promise);
            } while (cursor.moveToNext());
        }

        return promises;
    }

    public List<TimelineEvent> getAllTimelineEvents() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<TimelineEvent> timelineEvents = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TIMELINE;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                TimelineEvent timelineEvent = new TimelineEvent(cursor.getInt(cursor.getColumnIndex(TIMELINE_EVENT_ID_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TIMELINE_EVENT_TITLE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TIMELINE_EVENT_DESCRIPTION_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TIMELINE_EVENT_DATE_COLUMN)));
                timelineEvents.add(timelineEvent);
            } while (cursor.moveToNext());
        }

        return timelineEvents;
    }

    //UPDATERS
    public int updateGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GOALS_TITLE_COLUMN, goal.getTitle());
        values.put(GOALS_DESCRIPTION_COLUMN, goal.getDescription());
        values.put(GOALS_DATE_COLUMN, goal.getDate());
        values.put(GOALS_STARRED_COLUMN, (goal.isStarred()) ? 1 : 0);
        values.put(GOALS_COMPLETED_COLUMN, (goal.isCompleted()) ? 1 : 0);

        return db.update(TABLE_GOALS, values, GOALS_ID_COLUMN + " = ?", new String[]{String.valueOf(goal.getId())});
    }

    public int updateAchievement(Achievement achievement) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACHIEVEMENTS_TITLE_COLUMN, achievement.getTitle());
        values.put(ACHIEVEMENTS_DESCRIPTION_COLUMN, achievement.getDescription());
        values.put(ACHIEVEMENTS_DATE_COLUMN, achievement.getDate());
        values.put(ACHIEVEMENTS_STARRED_COLUMN, (achievement.isStarred()) ? 1 : 0);

        return db.update(TABLE_ACHIEVEMENTS, values, ACHIEVEMENTS_ID_COLUMN + " = ?", new String[]{String.valueOf(achievement.getId())});
    }

    public int updatePromise(Promise promise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROMISES_TITLE_COLUMN, promise.getTitle());
        values.put(PROMISES_DESCRIPTION_COLUMN, promise.getDescription());
        values.put(PROMISES_DURATION_COLUMN, promise.getDuration());
        values.put(PROMISES_STARRED_COLUMN, (promise.isStarred()) ? 1 : 0);
        values.put(PROMISES_COMPLETED_COLUMN, (promise.isCompleted()) ? 1 : 0);

        return db.update(TABLE_PROMISES, values, PROMISES_ID_COLUMN + " = ?", new String[]{String.valueOf(promise.getId())});
    }

    public int updateTimelineEvent(TimelineEvent timelineEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TIMELINE_EVENT_TITLE_COLUMN, timelineEvent.getTitle());
        values.put(TIMELINE_EVENT_DESCRIPTION_COLUMN, timelineEvent.getDescription());
        values.put(TIMELINE_EVENT_DATE_COLUMN, timelineEvent.getDate());

        return db.update(TABLE_TIMELINE, values, TIMELINE_EVENT_ID_COLUMN + " = ?", new String[]{String.valueOf(timelineEvent.getId())});
    }

    //DELETERS
    public void deleteGoal(int goalID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GOALS, GOALS_ID_COLUMN + " = ?", new String[]{String.valueOf(goalID)});
    }

    public void deleteAchievement(int achievementID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACHIEVEMENTS, ACHIEVEMENTS_ID_COLUMN + " = ?", new String[]{String.valueOf(achievementID)});
    }

    public void deletePromise(int promiseID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROMISES, PROMISES_ID_COLUMN + " = ?", new String[]{String.valueOf(promiseID)});
    }

    public void deleteTimelineEvent(int timelineEventID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TIMELINE, TIMELINE_EVENT_ID_COLUMN + " = ?", new String[]{String.valueOf(timelineEventID)});
    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new ArithmeticException(l + " cannot be cast to int");
        } else {
            return (int) l;
        }
    }
}

