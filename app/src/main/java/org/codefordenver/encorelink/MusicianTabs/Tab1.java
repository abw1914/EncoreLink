package org.codefordenver.encorelink.MusicianTabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import org.codefordenver.encorelink.EntityClasses.EventEntity;
import org.codefordenver.encorelink.R;
import org.codefordenver.encorelink.UpcomingEventsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tab1 extends Fragment {


    private String eventTitle;
    public static String organizerId;
    private EventEntity eventInfo;
    private FirebaseAuth firebaseAuth;


    public static List<String> upcomingEventsList;
    public static List<String> eventTitleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        upcomingEventsList = new ArrayList<>();
        eventTitleList = new ArrayList<>();
        eventInfo = new EventEntity();
        final RecyclerView upcomingEventsRecyler = (RecyclerView) inflater.inflate(R.layout.upcoming_events, container, false);



        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

        final UpcomingEventsAdapter upcomingEventsAdapter = new UpcomingEventsAdapter(upcomingEventsList);

        upcomingEventsList.clear();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().equals("city")) {
                        eventInfo.setCity(Objects.requireNonNull(ds.getValue()).toString());
                    }

                    if (ds.getKey().equals("endTime")) {
                        eventInfo.setEndTime(ds.getValue().toString());
                    }

                    if (ds.getKey().equals("eventTitle")) {
                        eventInfo.setEventTitle(ds.getValue().toString());
                        eventTitleList.add(ds.getValue().toString());

                    }

                    if (ds.getKey().equals("notes")) {
                        eventInfo.setNotes(ds.getValue().toString());
                    }

                    if (ds.getKey().equals("startTime")) {
                        eventInfo.setStartTime(ds.getValue().toString());
                    }

                    if (ds.getKey().equals("streetAddress")) {
                        eventInfo.setStreetAddress(ds.getValue().toString());
                    }

                    if (ds.getKey().equals("uid")) {
                        eventInfo.setUid(ds.getValue().toString());
                        organizerId = eventInfo.getUid();
                    }

                    if (ds.getKey().equals("zipcode")) {
                        eventInfo.setZipcode(ds.getValue().toString());

                    }
                }
                upcomingEventsList.add(eventInfo.toString());


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


        setEventTitle();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        upcomingEventsRecyler.setLayoutManager(linearLayoutManager);

        return upcomingEventsRecyler;

    }

    public String setEventTitle() {
        eventTitle = eventInfo.getEventTitle();
        return eventTitle;
    }

    public String getEventTitle() {
        return eventTitle;
    }

}
