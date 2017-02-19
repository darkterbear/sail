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

public class AddTimelineEventActivity extends AppCompatActivity {

    DBHandler dbHandler;
    EditText timelineEventTitleEditText, timelineEventDescriptionEditText;
    TextView timelineEventDateTextView;
    String[] months;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timeline_event);

        getSupportActionBar().setTitle("New Event");

        dbHandler = new DBHandler(this);

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};

        timelineEventTitleEditText = (EditText) findViewById(R.id.timelineEventTitleEditText);
        timelineEventDescriptionEditText = (EditText) findViewById(R.id.timelineEventDescriptionEditText);
        timelineEventDateTextView = (TextView) findViewById(R.id.timelineEventDateTextView);

        timelineEventDateTextView.setText(months[month] + " " + day + " " + year);
        timelineEventDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeSince1970 = System.currentTimeMillis();

                DatePickerDialog dialog = new DatePickerDialog(AddTimelineEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        timelineEventDateTextView.setText(months[selectedMonth] + " " + selectedDay + " " + selectedYear);
                    }
                }, year, month, day);

                dialog.getDatePicker().setMaxDate(timeSince1970);
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
                TimelineEvent timelineEvent = new TimelineEvent(timelineEventTitleEditText.getText().toString(), timelineEventDescriptionEditText.getText().toString(), timelineEventDateTextView.getText().toString());
                dbHandler.createTimelineEvent(timelineEvent);
                finish();
                break;
            default:
                Snackbar.make(findViewById(android.R.id.content), "Please try again", BaseTransientBottomBar.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}
