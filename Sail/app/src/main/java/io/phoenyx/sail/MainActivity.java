package io.phoenyx.sail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.phoenyx.sail.fragments.AchievementsFragment;
import io.phoenyx.sail.fragments.GoalsFragment;
import io.phoenyx.sail.fragments.PromisesFragment;
import io.phoenyx.sail.fragments.TimelineFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawer;
    View navHeader;
    ActionBarDrawerToggle toggle;

    DBHandler dbHandler;

    public static int navItemIndex = 0;
    private static final String TAG_GOALS = "goals";
    private static final String TAG_ACHIEVEMENTS = "achievements";
    private static final String TAG_PROMISES = "promises";
    private static final String TAG_TIMELINE = "timeline";

    public static String CURRENT_TAG = TAG_GOALS;

    String[] activityTitles = new String[]{"Goals", "Achievements", "Promises", "Timeline"};

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navHeader = navigationView.getHeaderView(0);
        handler = new Handler();
        navigationView.setNavigationItemSelectedListener(this);

        dbHandler = new DBHandler(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        loadNavHeader();

        navigate(navItemIndex);
    }

    private void loadNavHeader() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_data_sp_key), Context.MODE_PRIVATE);
        int numGoals = sharedPref.getInt("num_goals", 0);
        int numAchievements = sharedPref.getInt("num_achievements", 0);
        int numPromises = sharedPref.getInt("num_promises", 0);
        int numTimelines = sharedPref.getInt("num_timelines", 0);

        TextView  numGoalsTextView = (TextView) navHeader.findViewById(R.id.numGoalsTextView);
        TextView  numAchievementsTextView = (TextView) navHeader.findViewById(R.id.numAchievementsTextView);
        TextView  numPromisesTextView = (TextView) navHeader.findViewById(R.id.numPromisesTextView);
        TextView  numTimelinesTextView = (TextView) navHeader.findViewById(R.id.numTimelineEventsTextView);

        String numGoalsString = numGoals + " goal(s)";
        String numAchievementsString = numAchievements + " achievement(s)";
        String numPromisesString = numPromises + " promise(s)";
        String numTimelinesString = numTimelines + " timeline event(s)";

        numGoalsTextView.setText(numGoalsString);
        numAchievementsTextView.setText(numAchievementsString);
        numPromisesTextView.setText(numPromisesString);
        numTimelinesTextView.setText(numTimelinesString);
    }

    private void navigate(int navItemIndex) {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);

        Fragment fragment = null;
        Class fragmentClass;
        switch (navItemIndex) {
            case 0:
                fragmentClass = GoalsFragment.class;
                break;
            case 1:
                fragmentClass = AchievementsFragment.class;
                break;
            case 2:
                fragmentClass = PromisesFragment.class;
                break;
            case 3:
                fragmentClass = TimelineFragment.class;
                break;
            default:
                fragmentClass = GoalsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment).commitAllowingStateLoss();
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);

        int id = item.getItemId();

        if (id == R.id.nav_goals) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_GOALS;
        } else if (id == R.id.nav_achievements) {
            navItemIndex = 1;
            CURRENT_TAG = TAG_ACHIEVEMENTS;
        } else if (id == R.id.nav_promises) {
            navItemIndex = 2;
            CURRENT_TAG = TAG_PROMISES;
        } else if (id == R.id.nav_timeline) {
            navItemIndex = 3;
            CURRENT_TAG = TAG_TIMELINE;
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(navItemIndex);
            }
        }, 100);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
