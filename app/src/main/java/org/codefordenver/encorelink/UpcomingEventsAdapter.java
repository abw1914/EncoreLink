package org.codefordenver.encorelink;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.codefordenver.encorelink.EntityClasses.MusicianEntity;
import org.codefordenver.encorelink.MusicianTabs.Tab1;

import java.util.ArrayList;
import java.util.List;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder> {

    private List<String> upcomingEventsList;
    public static int pendingMusicianIndex;
    public static MusicianEntity musician;


    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private DatabaseReference databaseReference;
        private String userId;
        private FirebaseAuth firebaseAuth;
        private FirebaseUser firebaseUser;
        private Button requestToPlay;
        private boolean requestedEvent;



        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            cardView.setCardElevation(2);
            cardView.setPadding(1,1,1,1);
            getMusicianInfo();
        }

        public void bind(final int position) {
            pendingMusicianIndex = position;
            getMusicianInfo();
            checkPosition();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            if(firebaseUser != null) {
                userId = firebaseUser.getUid();
            }

            TextView textView = cardView.findViewById(R.id.upcoming_events_for_card);
            textView.setText(upcomingEventsList.get(position));

            requestToPlay = cardView.findViewById(R.id.request_to_play);

            requestToPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestedEvent = true;
                    databaseReference.child(CreateOrganizerProfile.ORGANIZER_PROFILE).child(Tab1.organizerId).child("pending_musicians").child(String.valueOf(pendingMusicianIndex))
                    .setValue(musician.toString());
                    Toast.makeText(v.getContext(), "Request Pending", Toast.LENGTH_SHORT).show();

                    databaseReference.child(CreateMusicianProfile.MUSICIAN_PROFILE).child(userId).child("pending_events").setValue(upcomingEventsList.get(position));
                }
            });

        }

        public void checkPosition() {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            if(firebaseUser != null) {
                userId = firebaseUser.getUid();
            }
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference().child(CreateOrganizerProfile.ORGANIZER_PROFILE).child(Tab1.organizerId).child("pending_musicians");

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(ds.getKey().equals(String.valueOf(pendingMusicianIndex))) {
                            pendingMusicianIndex++;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        public void getMusicianInfo() {

            musician = new MusicianEntity();
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            if(firebaseUser != null) {
                userId = firebaseUser.getUid();
            }
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference().child(CreateMusicianProfile.MUSICIAN_PROFILE).child(userId);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(ds.getKey().equals("firstName")) {
                            musician.setFirstName(ds.getValue(String.class));
                        }
                        if(ds.getKey().equals("lastName")) {
                            musician.setLastName(ds.getValue(String.class));
                        }
                        if(ds.getKey().equals("musicalTalent")) {
                            musician.setMusicalTalent(ds.getValue(String.class));
                        }
                        if(ds.getKey().equals("videoLink")) {
                            musician.setVideoLink(ds.getValue(String.class));
                        }
                        if(ds.getKey().equals("phoneNumber")) {
                            musician.setPhoneNumber(ds.getValue(String.class));
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }


    public UpcomingEventsAdapter(List<String> upcomingEventsList) {
        this.upcomingEventsList = upcomingEventsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_card, parent, false);


        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(UpcomingEventsAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return upcomingEventsList.size();
    }
}
