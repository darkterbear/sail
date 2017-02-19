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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddGoalActivity extends AppCompatActivity {

    DBHandler dbHandler;
    EditText goalTitleEditText, goalDescriptionEditText;
    CheckBox goalLongTermCheckBox;
    TextView goalDateTextView;
    String[] months;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        getSupportActionBar().setTitle("New Goal");

        dbHandler = new DBHandler(this);

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};

        goalTitleEditText = (EditText) findViewById(R.id.goalTitleEditText);
        goalDescriptionEditText = (EditText) findViewById(R.id.goalDescriptionEditText);
        goalDateTextView = (TextView) findViewById(R.id.goalDateTextView);
        goalLongTermCheckBox = (CheckBox) findViewById(R.id.goalLongTermCheckBox);

        goalLongTermCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    goalDateTextView.setText("Long term");
                } else {
                    goalDateTextView.setText(months[month] + " " + day + " " + year);
                }
            }
        });

        goalDateTextView.setText(months[month] + " " + day + " " + year);
        goalDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeSince1970 = System.currentTimeMillis();

                DatePickerDialog dialog = new DatePickerDialog(AddGoalActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        goalDateTextView.setText(months[selectedMonth] + " " + selectedDay + " " + selectedYear);
                    }
                }, year, month, day);

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
                Goal goal = new Goal(goalTitleEditText.getText().toString(), goalDescriptionEditText.getText().toString(), goalDateTextView.getText().toString(), false, false);
                dbHandler.createGoal(goal);
                finish();
                break;
            default:
                Snackbar.make(findViewById(android.R.id.content), "Please try again", BaseTransientBottomBar.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}
