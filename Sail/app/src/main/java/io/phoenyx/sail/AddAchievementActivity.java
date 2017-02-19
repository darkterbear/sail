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

import java.util.Calendar;

public class AddAchievementActivity extends AppCompatActivity {

    DBHandler dbHandler;
    EditText achievementTitleEditText, achievementDescriptionEditText;
    TextView achievementDateTextView;
    String[] months;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievement);

        getSupportActionBar().setTitle("New Achievement");

        dbHandler = new DBHandler(this);

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};

        achievementTitleEditText = (EditText) findViewById(R.id.achievementTitleEditText);
        achievementDescriptionEditText = (EditText) findViewById(R.id.achievementDescriptionEditText);
        achievementDateTextView = (TextView) findViewById(R.id.achievementDateTextView);

        achievementDateTextView.setText(months[month] + " " + day + " " + year);
        achievementDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeSince1970 = System.currentTimeMillis();

                DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        achievementDateTextView.setText(months[selectedMonth] + " " + selectedDay + " " + selectedYear);
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(AddAchievementActivity.this, datePickerListener, year, month, day);
                dialog.getDatePicker().setMinDate(timeSince1970);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.done:
                Achievement achievement = new Achievement(achievementTitleEditText.getText().toString(), achievementDescriptionEditText.getText().toString(), achievementDateTextView.getText().toString(), false);
                dbHandler.createAchievement(achievement);
                finish();
                break;
            default:
                Snackbar.make(findViewById(android.R.id.content), "Please try again", BaseTransientBottomBar.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}
