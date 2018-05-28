package org.codefordenver.encorelink;

import android.content.Intent;
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

public class CreateMusicianProfile extends AppCompatActivity {

    public static final String MUSICIAN_PROFILE = "musician_profile";
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText streetAddress;
    private EditText city;
    private EditText zipcode;
    private EditText musicalTalent;
    private EditText videoLink;

    private DatabaseReference databaseReference;
    private MusicianEntity musicianEntity = new MusicianEntity();
    public static String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_musician_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }

        firstName = findViewById(R.id.musician_first_name);
        lastName = findViewById(R.id.musician_last_name);
        phoneNumber = findViewById(R.id.musician_phonenumber);
        streetAddress = findViewById(R.id.musician_street_address);
        city = findViewById(R.id.musician_city);
        zipcode = findViewById(R.id.musician_zipcode);
        musicalTalent = findViewById(R.id.musician_talent);
        videoLink = findViewById(R.id.video_link);
        Button saveButton = findViewById(R.id.save_musician_profile);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                musicianEntity.setFirstName(firstName.getText().toString());
                musicianEntity.setLastName(lastName.getText().toString());
                musicianEntity.setPhoneNumber(phoneNumber.getText().toString());
                musicianEntity.setStreetAddress(streetAddress.getText().toString());
                musicianEntity.setCity(city.getText().toString());
                musicianEntity.setZipcode(zipcode.getText().toString());
                musicianEntity.setMusicalTalent(musicalTalent.getText().toString());
                musicianEntity.setVideoLink(videoLink.getText().toString());

                if (musicianEntity.getFirstName().length() <= 0) {
                    Toast.makeText(CreateMusicianProfile.this, "First name is required!", Toast.LENGTH_SHORT).show();
                }
                if (musicianEntity.getLastName().isEmpty()) {
                    Toast.makeText(CreateMusicianProfile.this, "Last name is required!", Toast.LENGTH_SHORT).show();
                }
                if (musicianEntity.getPhoneNumber().isEmpty()) {
                    Toast.makeText(CreateMusicianProfile.this, "Phone number is required!", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child(MUSICIAN_PROFILE).child(userId).setValue(musicianEntity);

                    Toast.makeText(CreateMusicianProfile.this, "Saving profile information.", Toast.LENGTH_SHORT).show();
                    Intent musicianDashboard = new Intent(CreateMusicianProfile.this, TestMusicianDashboard.class);
                    startActivity(musicianDashboard);
                    finish();

                }

            }
        });

    }
}
