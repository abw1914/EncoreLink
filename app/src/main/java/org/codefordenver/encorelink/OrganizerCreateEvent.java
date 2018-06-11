package org.codefordenver.encorelink;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.codefordenver.encorelink.EntityClasses.EventEntity;

public class OrganizerCreateEvent extends AppCompatActivity {

    private EditText eventTitle;
    private EditText streetAddress;
    private EditText city;
    private EditText zipcode;
    private EditText startTime;
    private EditText endTime;
    private TextInputEditText notes;
    private Button addEvent;
    private EventEntity eventEntity = new EventEntity();


    private FirebaseAuth firebaseAuth;
    public static String userId;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_create_event);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }


        eventTitle = findViewById(R.id.addEventTitleEditText);
        streetAddress = findViewById(R.id.addEventAddressEditText);
        city = findViewById(R.id.addEventCityEditText);
        zipcode = findViewById(R.id.addEventCityZipText);
        startTime = findViewById(R.id.addEventStartTimeText);
        endTime = findViewById(R.id.addEventEndTimeText);
        notes = findViewById(R.id.addEventNotesText);
        addEvent = findViewById(R.id.addEventButton);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventEntity.setEventTitle(eventTitle.getText().toString());
                eventEntity.setStreetAddress(streetAddress.getText().toString());
                eventEntity.setCity(city.getText().toString());
                eventEntity.setZipcode(zipcode.getText().toString());
                eventEntity.setStartTime(startTime.getText().toString());
                eventEntity.setEndTime(endTime.getText().toString());
                eventEntity.setNotes(notes.getText().toString());
                eventEntity.setUid(userId);



                databaseReference.child(CreateOrganizerProfile.ORGANIZER_PROFILE).child(userId).child("Events").child(eventTitle.getText().toString()).setValue(eventEntity);
                databaseReference.child("Events").child(eventTitle.getText().toString()).setValue(eventEntity);
                Toast.makeText(OrganizerCreateEvent.this, "Saving to Database", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrganizerCreateEvent.this, OrganizerDashboard.class);
                startActivity(intent);
                finish();
            }
        });








    }
}
