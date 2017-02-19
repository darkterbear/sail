package io.phoenyx.sail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by terrance on 2/19/17.
 */

public class SplashActivity extends AppCompatActivity {
    DBHandler dbHandler;
    int numGoals, numAchievements, numPromises, numTimelineEvents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHandler = new DBHandler(this);

        numGoals = dbHandler.getAllGoals().size();
        numAchievements = dbHandler.getAllAchievements().size();
        numPromises = dbHandler.getAllPromises().size();
        numTimelineEvents = dbHandler.getAllTimelineEvents().size();

        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startIntent.putExtra("item_sizes", new int[]{numGoals, numAchievements, numPromises, numTimelineEvents});
        startActivity(startIntent);
        finish();
    }
}
