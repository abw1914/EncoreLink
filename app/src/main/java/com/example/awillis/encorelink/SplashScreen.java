/**
 * Created by awillis on 2/6/18.
 * Modified by rmacias on 2/15/18.
 */

package com.example.awillis.encorelink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class SplashScreen extends AppCompatActivity {

  private String mMusician;
  private String mFacility;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Set the content of the activity to use the activity_main.xml layout file
    setContentView(R.layout.splash);

    // Find the View that shows the musician category
    TextView mMusician = (TextView) findViewById(R.id.tv_musician);

    // Set a click listener on that View
    mMusician.setOnClickListener(new View.OnClickListener() {
      // The code in this method will be executed when the musician category is clicked on.
      @Override
      public void onClick(View view) {
        // Create a new intent to open the {@link MusicianAuthentication}
        Intent musicianIntent = new Intent(SplashScreen.this, MusicianAuthentication.class);

        // Start the new activity
        startActivity(musicianIntent);
      }

//            ArrayList<String> list = newArrayList("a", "b", "c");
    });

    // Find the View that shows the facility category
    TextView mFacility = (TextView) findViewById(R.id.tv_facility);

    // Set a click listener on that View
    mFacility.setOnClickListener(new View.OnClickListener() {
      // The code in this method will be executed when the family category is clicked on.
      @Override
      public void onClick(View view) {
        // Create a new intent to open the {@link FacilityAuthentication}
        Intent facilityIntent = new Intent(SplashScreen.this, FacilityAuthentication.class);

        // Start the new activity
        startActivity(facilityIntent);
      }
    });
  }
}
//Select if you are musician or a facility manager
