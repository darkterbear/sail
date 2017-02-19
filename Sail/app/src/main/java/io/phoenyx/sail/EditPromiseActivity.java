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

public class EditPromiseActivity extends AppCompatActivity {

    DBHandler dbHandler;
    EditText promiseTitleEditText, promiseDescriptionEditText, promisePersonEditText;
    CheckBox promiseLongTermCheckBox;
    TextView promiseDateTextView;
    int promiseID;
    Promise promise;
    String[] months;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promise);

        Bundle extras = getIntent().getExtras();
        promiseID = extras.getInt("promise_id");

        getSupportActionBar().setTitle("Edit Promise");

        dbHandler = new DBHandler(this);
        promise = dbHandler.getPromise(promiseID);

        /*
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        */

        months = new String[]{"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};

        promiseTitleEditText = (EditText) findViewById(R.id.promiseTitleEditText);
        promiseDescriptionEditText = (EditText) findViewById(R.id.promiseDescriptionEditText);
        promisePersonEditText = (EditText) findViewById(R.id.promisePersonEditText);
        promiseDateTextView = (TextView) findViewById(R.id.promiseDateTextView);
        promiseLongTermCheckBox = (CheckBox) findViewById(R.id.promiseLongTermCheckBox);

        promiseTitleEditText.setText(promise.getTitle());
        promiseDescriptionEditText.setText(promise.getDescription());
        promiseDateTextView.setText(promise.getDate());

        if (promise.getDate().equals("Long term")) {
            promiseLongTermCheckBox.setChecked(true);
        }

        promiseLongTermCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    promiseDateTextView.setText("Long term");
                } else {
                    promiseDateTextView.setText(promise.getDate());
                }
            }
        });

        promiseDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeSince1970 = System.currentTimeMillis();

                DatePickerDialog dialog = new DatePickerDialog(EditPromiseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        promiseDateTextView.setText(months[selectedMonth] + " " + selectedDay + " " + selectedYear);
                    }
                }, year, month, day);

                dialog.getDatePicker().setMinDate(timeSince1970);
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
                dbHandler.deletePromise(promiseID);
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
        Promise newPromise = new Promise(promiseID, promiseTitleEditText.getText().toString(), promiseDescriptionEditText.getText().toString(), promiseDateTextView.getText().toString(), promisePersonEditText.getText().toString(), false, false);
        dbHandler.updatePromise(newPromise);
        finish();
    }
}
