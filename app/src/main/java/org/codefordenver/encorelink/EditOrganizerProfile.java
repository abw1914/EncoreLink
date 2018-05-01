package org.codefordenver.encorelink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditOrganizerProfile extends AppCompatActivity {


ListView editOrganizerProfileListView;
ArrayAdapter<String> adapter;

DatabaseReference databaseReference;
FirebaseUser firebaseUser;
UserInfo userInfo;

List<String> itemlist;

String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_organizer_profile);

        editOrganizerProfileListView = findViewById(R.id.editOrganizerProfileView);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = userInfo.getUid();
        itemlist = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemlist.clear();




                String organizationName = dataSnapshot.child(userId).child("organizationName").getValue(String.class);
//                String streetAddress = dataSnapshot.child(userId).child("streetAddress").getValue(String.class);
//                String city = dataSnapshot.child(userId).child("city").getValue(String.class);
//                String zipCode = dataSnapshot.child(userId).child("zipCode").getValue(String.class);
//                String contactName = dataSnapshot.child(userId).child("contactName").getValue(String.class);
//                String contactJobTitle = dataSnapshot.child(userId).child("contactJobTitle").getValue(String.class);
//                String phoneNumber = dataSnapshot.child(userId).child("phoneNumber").getValue(String.class);
//                String email = dataSnapshot.child(userId).child("emailAddress").getValue(String.class);
//                String state = dataSnapshot.child(userId).child("state").getValue(String.class);

                itemlist.add(organizationName);
//                itemlist.add(streetAddress);
//                itemlist.add(city);
//                itemlist.add(zipCode);
//                itemlist.add(contactName);
//                itemlist.add(contactJobTitle);
//                itemlist.add(phoneNumber);
//                itemlist.add(email);
//                itemlist.add(state);

                adapter = new ArrayAdapter<>(EditOrganizerProfile.this, android.R.layout.simple_list_item_1, itemlist);
                editOrganizerProfileListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(EditOrganizerProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });






    }
}
