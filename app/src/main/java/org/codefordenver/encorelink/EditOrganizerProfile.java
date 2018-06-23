package org.codefordenver.encorelink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.Objects;

public class EditOrganizerProfile extends AppCompatActivity {


    ListView editOrganizerProfileListView;
    private ArrayList<String> userInfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        setContentView(R.layout.activity_edit_organizer_profile);
        editOrganizerProfileListView = findViewById(R.id.editOrganizerProfileView);

        userInfo.add(LoginFragment.organizerInfo.toString());


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userInfo);
        editOrganizerProfileListView.setAdapter(arrayAdapter);



    }
}