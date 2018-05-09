package org.codefordenver.encorelink;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is an empty activity for now.
 */
public class OrganizerDashboardTab1 extends Fragment {


    private String userId;

    //Arraylist to hold our list of volunteer musicians
    private ArrayList<String> volunteers = new ArrayList<>();

    //private String field members to hold temp String data
    private String tempFirst;
    private String tempTalent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        final RecyclerView musicianInfoRecycler = (RecyclerView) inflater.inflate(R.layout.tab1, container, false);


        //Checking to make sure user is logged in and is not null
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }

        //setting DatabaseReference variable so we can search through the correct node in our DB
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CreateMusicianProfile.MUSICIAN_PROFILE);

        //Instantiating and declaring our Adapter object for our Recycler View
        final PendingMusicianInfoAdapter adapter = new PendingMusicianInfoAdapter(volunteers);

        //this clear is a must so we aren't getting duplicated data in the cardview
        volunteers.clear();


        //Adding child event listener to our database object
        mDatabase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //iterate through each dataSnapshot inside mDatabase
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //if we get a first name, add it to temp string
                    if (dataSnapshot1.getKey().equals("firstName")) {
                        tempFirst = Objects.requireNonNull("First name: " + dataSnapshot1.getValue(String.class));
                    }

                    //if we get a musical talent, save it also into a temp string
                    if (dataSnapshot1.getKey().equals("musicalTalent")) {
                        tempTalent = Objects.requireNonNull("Talent: " + dataSnapshot1.getValue(String.class));

                        //in order to display all the string data together in one card,
                        //we have to add each temp string to the array list.
                        volunteers.add(tempFirst + "\n" + tempTalent);
                    }


                }
                //set adapater equal to our adapater object
                musicianInfoRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        musicianInfoRecycler.setLayoutManager(linearLayoutManager);

        return musicianInfoRecycler;

    }


}
