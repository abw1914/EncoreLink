package org.codefordenver.encorelink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateOrganizerProfile extends AppCompatActivity {

    public static final String ORGANIZER_PROFILE = "organizer_profile";
    private EditText organizationName;
    private EditText streetAddress;
    private EditText city;
    private EditText zipCode;
    private EditText contactName;
    private EditText contactJobTitle;
    private EditText phoneNumber;
    private EditText emailAddress;
    private EditText state;

    private FirebaseAuth firebaseAuth;
    public static String userId;




    private Button btnViewDatabase;

    private OrganizerEntity organizerEntity = new OrganizerEntity();


    private Button persistButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_organizer_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }

        organizationName = findViewById(R.id.organizationName);

        streetAddress = findViewById(R.id.streetAddress);

        city = findViewById(R.id.city);

        state = findViewById(R.id.state);

        zipCode = findViewById(R.id.zipcode);

        contactName = findViewById(R.id.contactName);

        contactJobTitle = findViewById(R.id.contactJobTitle);

        phoneNumber = findViewById(R.id.phoneNumber);

        emailAddress = findViewById(R.id.emailAddress);

        persistButton = findViewById(R.id.pushToDB);




        persistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                organizerEntity.setOrganizationName(organizationName.getText().toString());
                organizerEntity.setStreetAddress(streetAddress.getText().toString());
                organizerEntity.setCity(city.getText().toString());
                organizerEntity.setState(state.getText().toString());
                organizerEntity.setZipcode(zipCode.getText().toString());
                organizerEntity.setContactName(contactName.getText().toString());
                organizerEntity.setContactJobTitle(contactJobTitle.getText().toString());
                organizerEntity.setPhoneNumber(phoneNumber.getText().toString());
                organizerEntity.setEmailAddress(emailAddress.getText().toString());



                if (organizerEntity.getOrganizationName().isEmpty()) {
                    Toast.makeText(CreateOrganizerProfile.this, "Organization Name is Required.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (organizerEntity.getContactName().isEmpty()) {

                    Toast.makeText(CreateOrganizerProfile.this, "Contact Name is Required.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (organizerEntity.getPhoneNumber().isEmpty()) {
                    Toast.makeText(CreateOrganizerProfile.this, "Phone number is Required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!PhoneNumberUtils.isWellFormedSmsAddress(organizerEntity.getPhoneNumber())) {

                    Toast.makeText(CreateOrganizerProfile.this, "Not a valid phone number.", Toast.LENGTH_SHORT).show();
                    return;

                }


//                key = databaseReference.child("organizer_profile").push().getKey();

                databaseReference.child(ORGANIZER_PROFILE).child(userId).setValue(organizerEntity);

                Toast.makeText(CreateOrganizerProfile.this, "Saving to Database", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateOrganizerProfile.this, OrganizerDashboard.class);
                startActivity(intent);
                finish();

            }


        });

    }

}
