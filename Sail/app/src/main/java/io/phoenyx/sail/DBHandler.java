package io.phoenyx.sail;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String CREATE_GOALS_TABLE = "CREATE TALBE "
            + TABLE_GOALS + "(" + GOALS_ID_COLUMN + " INTEGER PRIMARY KEY," + GOALS_TITLE_COLUMN + " TEXT,"
            + GOALS_DESCRIPTION_COLUMN + " TEXT,"
            + GOALS_DATE_COLUMN + " TEXT,"
            + GOALS_STARRED_COLUMN + " INTEGER,"
            + GOALS_COMPLETED_COLUMN + " INTEGER)";

    //ACHIEVEMENTS TALBE
    private static final String CREATE_ACHIEVEMENTS_TABLE = "CREATE TALBE "
            + TABLE_ACHIEVEMENTS + "(" + ACHIEVEMENTS_ID_COLUMN + " INTEGER PRIMARY KEY," + ACHIEVEMENTS_TITLE_COLUMN + " TEXT,"
            + ACHIEVEMENTS_DESCRIPTION_COLUMN + " TEXT,"
            + ACHIEVEMENTS_DATE_COLUMN + " TEXT,"
            + ACHIEVEMENTS_STARRED_COLUMN + " INTEGER)";

    //PROMISES TALBE
    private static final String CREATE_PROMISES_TABLE = "CREATE TALBE "
            + TABLE_PROMISES + "(" + PROMISES_ID_COLUMN + " INTEGER PRIMARY KEY," + PROMISES_TITLE_COLUMN + " TEXT,"
            + PROMISES_DESCRIPTION_COLUMN + " TEXT,"
            + PROMISES_DURATION_COLUMN + " TEXT,"
            + PROMISES_STARRED_COLUMN + " INTEGER,"
            + PROMISES_COMPLETED_COLUMN + " INTEGER)";

    //TIMELINE TALBE
    private static final String CREATE_TIMELINE_TABLE = "CREATE TALBE "
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

    //DATABASE ENDPOITNS
    public int createGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GOALS_TITLE_COLUMN, goal.getTitle());
        values.put(GOALS_DESCRIPTION_COLUMN, goal.getDescription());
        values.put(GOALS_DATE_COLUMN, goal.getDate());
        values.put(GOALS_STARRED_COLUMN, (goal.isStarred()) ? 1 : 0);
        values.put(GOALS_COMPLETED_COLUMN, (goal.isCompleted()) ? 1 : 0);

        long goalID = db.insert(TABLE_GOALS, null, values);

    }
}
