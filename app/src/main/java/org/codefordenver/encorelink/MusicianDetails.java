package org.codefordenver.encorelink;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

public class MusicianDetails extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "number";

    private TextView closeButton;
    private int cardNumber;
    private String talentURL;
    private TextView musicalTalentLink;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_details);

        closeButton = findViewById(R.id.x_button);
        TextView textView = findViewById(R.id.musician_details);
        musicalTalentLink = findViewById(R.id.musical_talent_link);



        cardNumber = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_NUMBER);
        String musicianDetails = OrganizerDashboardTab1.volunteerDetail.get(cardNumber);

        findURL(OrganizerDashboardTab1.volunteerLink);
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



        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void findURL(ArrayList<String> data) {

        Matcher m = Patterns.WEB_URL.matcher(data.get (cardNumber));
        while (m.find()) {
            String url = m.group();
            talentURL = url;
        }
    }



}
