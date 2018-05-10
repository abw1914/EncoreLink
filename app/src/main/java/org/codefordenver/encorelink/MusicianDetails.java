package org.codefordenver.encorelink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

public class MusicianDetails extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "number";
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_details);

        backButton = findViewById(R.id.back_button);

        Slidr.attach(this);

        int cardNumber = (int) getIntent().getExtras().get(EXTRA_NUMBER);
        String musicianName = OrganizerDashboardTab1.volunteerDetail.get(cardNumber);
        TextView textView = findViewById(R.id.musician_details);
        textView.setText(musicianName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
