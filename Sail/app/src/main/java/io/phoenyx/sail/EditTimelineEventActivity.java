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

public class EditTimelineEventActivity extends AppCompatActivity {

    DBHandler dbHandler;
    EditText timelineEventTitleEditText, timelineEventDescriptionEditText;
    TextView timelineEventDateTextView;
    String[] months;
    TimelineEvent timelineEvent;
    int timelineEventID;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timeline_event);

        Bundle extras = getIntent().getExtras();
        timelineEventID = extras.getInt("timeline_event_id");

        getSupportActionBar().setTitle("Edit Event");

        dbHandler = new DBHandler(this);
        timelineEvent = dbHandler.getTimelineEvent(timelineEventID);

        months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};

        timelineEventTitleEditText = (EditText) findViewById(R.id.timelineEventTitleEditText);
        timelineEventDescriptionEditText = (EditText) findViewById(R.id.timelineEventDescriptionEditText);
        timelineEventDateTextView = (TextView) findViewById(R.id.timelineEventDateTextView);

        timelineEventTitleEditText.setText(timelineEvent.getTitle());
        timelineEventDescriptionEditText.setText(timelineEvent.getDescription());
        timelineEventDateTextView.setText(timelineEvent.getDate());

        timelineEventDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeSince1970 = System.currentTimeMillis();

                DatePickerDialog dialog = new DatePickerDialog(EditTimelineEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
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
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                dbHandler.deleteTimelineEvent(timelineEventID);
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

        TimelineEvent newTimelineEvent = new TimelineEvent(timelineEventID, timelineEventTitleEditText.getText().toString(), timelineEventDescriptionEditText.getText().toString(), timelineEventDateTextView.getText().toString());
        dbHandler.updateTimelineEvent(newTimelineEvent);
        finish();
    }
}
