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
    private String userId;
    private ArrayList<String> userInfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_organizer_profile);
        editOrganizerProfileListView = findViewById(R.id.editOrganizerProfileView);

        Slidr.attach(this);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CreateOrganizerProfile.ORGANIZER_PROFILE);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userInfo);
        editOrganizerProfileListView.setAdapter(arrayAdapter);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals(userId))
                        userInfo.add(Objects.requireNonNull(dataSnapshot1.getKey() + ": " +
                                dataSnapshot1.getValue()));
                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if (userId == null) {

            Toast.makeText(this, "No user found!", Toast.LENGTH_SHORT).show();
        }


    }
}