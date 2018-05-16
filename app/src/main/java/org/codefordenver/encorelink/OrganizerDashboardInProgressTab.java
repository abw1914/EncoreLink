package org.codefordenver.encorelink;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This is an empty activity for now.
 */
public class OrganizerDashboardInProgressTab extends Fragment {

    private String userId;


    //Arraylist to hold our list of volunteer musicians
    public static ArrayList<String> volunteerSmallView = new ArrayList<>();
    public static ArrayList<String> volunteerDetail = new ArrayList<>();
    public static ArrayList<String> volunteerLink = new ArrayList<>();

    //private String field members to hold temp String data
    private String tempFirst;
    private String tempTalent;
    private String tempLastName;
    private String tempPhoneNumber;
    private String tempStreetAddress;
    private String tempCity;
    private String tempZipcode;
    public static String tempVideoLink;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        final RecyclerView musicianInfoRecycler = (RecyclerView) inflater.inflate(R.layout.tab3, container, false);


        //Checking to make sure user is logged in and is not null
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }
        //setting DatabaseReference variable so we can search through the correct node in our DB
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("approved_musicians");

        //Instantiating and declaring our Adapter object for our Recycler View
        final InProcessMusicianAdapter adapter = new InProcessMusicianAdapter(volunteerSmallView);

        //this clear is a must so we aren't getting duplicated data in the cardview
        volunteerSmallView.clear();
        //Adding child event listener to our database object
        mDatabase.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //iterate through each dataSnapshot inside mDatabase
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //make sure we are getting correct user data
                    if (dataSnapshot.getKey().equals(userId)) {
                        Pattern pattern = Pattern.compile(".*[a-z-A-Z]+(.*)");
                        Matcher matcher = pattern.matcher(Objects.requireNonNull(dataSnapshot1.getValue()).toString());
                        if (matcher.find()) {
                            volunteerSmallView.add(matcher.group());
                        } else {
                            System.out.println("No match found");
                        }
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
