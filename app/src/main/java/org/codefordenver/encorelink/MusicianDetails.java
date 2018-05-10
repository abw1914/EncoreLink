package org.codefordenver.encorelink;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class MusicianDetails extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "number";
    private TextView closeButton;
    private TextView musicalTalentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_details);

        closeButton = findViewById(R.id.x_button);
        musicalTalentText = findViewById(R.id.musical_talent_link);

        int cardNumber = (int) getIntent().getExtras().get(EXTRA_NUMBER);
        String musicianDetails = OrganizerDashboardTab1.volunteerDetail.get(cardNumber);
        TextView textView = findViewById(R.id.musician_details);
        textView.setText(musicianDetails);

        musicalTalentText.setText(OrganizerDashboardTab1.musicalTalentLink);

        musicalTalentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                try {
                    intent.setData(Uri.parse(OrganizerDashboardTab1.musicalTalentLink));
                    startActivity(intent);
                } catch (ActivityNotFoundException exception) {
                    Toast.makeText(v.getContext(), "Error text", Toast.LENGTH_SHORT).show();
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


}
