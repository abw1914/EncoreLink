package org.codefordenver.encorelink;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class OrganizerCreateEvent extends AppCompatActivity {

    private EditText eventTitle;
    private EditText streetAddress;
    private EditText city;
    private EditText zipcode;
    private EditText startTime;
    private EditText endTime;
    private TextInputEditText notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_create_event);

        eventTitle = findViewById(R.id.addEventTitleEditText);
        streetAddress = findViewById(R.id.addEventAddressEditText);
        city = findViewById(R.id.addEventCityEditText)

    }
}
