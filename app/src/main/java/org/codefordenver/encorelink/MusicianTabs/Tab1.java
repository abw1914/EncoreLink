package org.codefordenver.encorelink.MusicianTabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.codefordenver.encorelink.CreateOrganizerProfile;
import org.codefordenver.encorelink.R;
import org.codefordenver.encorelink.UpcomingEventsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tab1 extends Fragment {

    private String userId;
    private String city;
    private String endTime;
    private String eventTitle;
    private String notes;
    private String startTime;
    private String streetAddress;
    private String zipcode;







    private List<String> upcomingEventsList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        final RecyclerView upcomingEventsRecyler = (RecyclerView) inflater.inflate(R.layout.upcoming_events, container, false);

        //Checking to make sure user is logged in and is not null
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

        final UpcomingEventsAdapter upcomingEventsAdapter = new UpcomingEventsAdapter(upcomingEventsList);

        upcomingEventsList.clear();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().equals("city")) {
                        city = Objects.requireNonNull(ds.getValue()).toString();
                    }

                    if(ds.getKey().equals("endTime")) {
                        endTime = ds.getValue().toString();
                    }

                    if (ds.getKey().equals("eventTitle")) {
                        eventTitle = ds.getValue().toString();
                    }

                    if(ds.getKey().equals("notes")) {
                        notes = ds.getValue().toString();
                    }

                    if(ds.getKey().equals("startTime")) {
                        startTime = ds.getValue().toString();
                    }

                    if(ds.getKey().equals("streetAddress")) {
                        streetAddress = ds.getValue().toString();
                    }

                    if(ds.getKey().equals("zipcode")) {
                        zipcode = ds.getValue().toString();
                        upcomingEventsList.add(String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s", eventTitle,
                                startTime, endTime, streetAddress, city, zipcode, notes));
                    }
                }

                upcomingEventsRecyler.setAdapter(upcomingEventsAdapter);
                upcomingEventsAdapter.notifyDataSetChanged();
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        upcomingEventsRecyler.setLayoutManager(linearLayoutManager);

        return upcomingEventsRecyler;

    }
}
