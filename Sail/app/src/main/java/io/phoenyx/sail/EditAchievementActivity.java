package io.phoenyx.sail;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class EditAchievementActivity extends AppCompatActivity {

    DBHandler dbHandler;
    EditText achievementTitleEditText, achievementDescriptionEditText;
    TextView achievementDateTextView;
    String[] months;
    int achievementID;
    Achievement achievement;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievement);

        Bundle extras = getIntent().getExtras();
        achievementID = extras.getInt("achievement_id");

        getSupportActionBar().setTitle("Edit Achievement");

        dbHandler = new DBHandler(this);
        achievement = dbHandler.getAchievement(achievementID);

        months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};

        achievementTitleEditText = (EditText) findViewById(R.id.achievementTitleEditText);
        achievementDescriptionEditText = (EditText) findViewById(R.id.achievementDescriptionEditText);
        achievementDateTextView = (TextView) findViewById(R.id.achievementDateTextView);

        achievementTitleEditText.setText(achievement.getTitle());
        achievementDescriptionEditText.setText(achievement.getDescription());
        achievementDateTextView.setText(achievement.getDate());

        achievementDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeSince1970 = System.currentTimeMillis();

                DatePickerDialog dialog = new DatePickerDialog(EditAchievementActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        achievementDateTextView.setText(months[selectedMonth] + " " + selectedDay + " " + selectedYear);
                    }
                }, year, month, day);

                dialog.getDatePicker().setMaxDate(timeSince1970);
                String[] dateParams = achievement.getDate().split(" ");
                dialog.updateDate(Integer.parseInt(dateParams[2]), Arrays.asList(months).indexOf(dateParams[0]), Integer.parseInt(dateParams[1]));
                dialog.setTitle("");
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                dbHandler.deleteAchievement(achievementID);
                finish();
                break;
            default:
                Snackbar.make(findViewById(android.R.id.content), "Please try again", BaseTransientBottomBar.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Achievement newAchievement = new Achievement(achievementID, achievementTitleEditText.getText().toString(), achievementDescriptionEditText.getText().toString(), achievementDateTextView.getText().toString(), false);
        dbHandler.updateAchievement(newAchievement);
        finish();
    }
}
