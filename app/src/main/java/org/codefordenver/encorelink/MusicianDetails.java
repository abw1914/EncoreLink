package org.codefordenver.encorelink;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

public class MusicianDetails extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "number";
//
    public static ArrayList<String> approvedMusicians = new ArrayList<>();
    private TextView closeButton;
    public static int cardNumber;
    private String talentURL;
    private TextView musicalTalentLink;
    private Button approvalButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String userId;
    public static boolean approved;
    public Button rejection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_details);
        rejection = findViewById(R.id.musician_rejection_button);
        closeButton = findViewById(R.id.x_button);
        TextView textView = findViewById(R.id.musician_details);
        musicalTalentLink = findViewById(R.id.musical_talent_link);

        approvalButton = findViewById(R.id.musician_approval);


        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }




        cardNumber = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_NUMBER);
        String musicianDetails = OrganizerDashboardPendingTab.volunteerDetail.get(cardNumber);

        findURL(OrganizerDashboardPendingTab.volunteerLink);
        musicalTalentLink.setText(talentURL);
        textView.setText(musicianDetails);

        musicalTalentLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(talentURL));
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    Toast.makeText(MusicianDetails.this, "Bad URL!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        approvalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("approved_musicians").child(userId).child(String.valueOf(cardNumber)).
                        setValue(OrganizerDashboardPendingTab.volunteerDetail.get(cardNumber));
                Toast.makeText(MusicianDetails.this, "Musician approved!", Toast.LENGTH_SHORT - 3).show();
                Toast.makeText(MusicianDetails.this, "Moving musician to In Progress...", Toast.LENGTH_SHORT).show();
                approvedMusicians.add(OrganizerDashboardPendingTab.volunteerDetail.get(cardNumber));



            }
        });



        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void findURL(ArrayList<String> data) {

        Matcher m = Patterns.WEB_URL.matcher(data.get(cardNumber));
        while (m.find()) {
            String url = m.group();
            talentURL = url;
        }
    }




}
