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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class OrganizerDashboardPendingTab extends Fragment {


    private String userId;


    //Arraylist to hold our list of volunteer musicians
    public static ArrayList<String> volunteerSmallView = new ArrayList<>();
    public static ArrayList<String>  volunteerDetail = new ArrayList<>();
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


        final RecyclerView musicianInfoRecycler = (RecyclerView) inflater.inflate(R.layout.tab1, container, false);


        //Checking to make sure user is logged in and is not null
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }
        //setting DatabaseReference variable so we can search through the correct node in our DB
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CreateOrganizerProfile.ORGANIZER_PROFILE).child(userId).child("pending_musicians");

        //Instantiating and declaring our Adapter object for our Recycler View
        final PendingMusicianInfoAdapter adapter = new PendingMusicianInfoAdapter(volunteerDetail);

        //this clear is a must so we aren't getting duplicated data in the cardview
        volunteerDetail.clear();
        //Adding child event listener to our database object
        mDatabase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //iterate through each dataSnapshot inside mDatabase
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //if we get a first name, add it to temp string
                    if (dataSnapshot1.getKey().equals("firstName")) {
                        tempFirst = Objects.requireNonNull(dataSnapshot1.getValue(String.class));
                    }

                    if (dataSnapshot1.getKey().equals("lastName")) {
                        tempLastName = Objects.requireNonNull(dataSnapshot1.getValue(String.class));
                    }

                    //if we get a musical talent, save it also into a temp string
                    if (dataSnapshot1.getKey().equals("musicalTalent")) {
                        tempTalent = Objects.requireNonNull("Talent: " + dataSnapshot1.getValue(String.class));

                        //in order to display all the string data together in one card,
                        //we have to add each temp string to the array list.

                            volunteerSmallView.add(tempFirst + " " + tempLastName + tempTalent);

                    }

                    if (dataSnapshot1.getKey().equals("phoneNumber")) {
                        tempPhoneNumber = Objects.requireNonNull( dataSnapshot1.getValue(String.class));
                    }

                    if (dataSnapshot1.getKey().equals("streetAddress")) {
                        tempStreetAddress = Objects.requireNonNull(dataSnapshot1.getValue(String.class));
                    }

                    if (dataSnapshot1.getKey().equals("city")) {
                        tempCity = Objects.requireNonNull(dataSnapshot1.getValue(String.class));
                    }

                    if (dataSnapshot1.getKey().equals("zipcode")) {
                        tempZipcode = Objects.requireNonNull("Zipcode: " + dataSnapshot1.getValue(String.class));
                    }

                    if (dataSnapshot1.getKey().equals("videoLink")) {
                        tempVideoLink = Objects.requireNonNull(dataSnapshot1.getValue(String.class));
                        volunteerLink.add(tempVideoLink);

                        volunteerDetail.add(tempFirst + " " + tempLastName +
                                "\n" + tempPhoneNumber +
                                "\n" + tempStreetAddress +
                                "\n" + tempCity + ", " + tempZipcode + "\n" + tempTalent);

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
