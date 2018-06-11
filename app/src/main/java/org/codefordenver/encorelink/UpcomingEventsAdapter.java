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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.codefordenver.encorelink.MusicianTabs.Tab1;

import java.util.List;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder> {

    private List<String> upcomingEventsList;



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
        }

        public void bind(final int position) {

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
                    databaseReference.child(CreateOrganizerProfile.ORGANIZER_PROFILE).child(Tab1.organizerId).child("pending_musicians").child(Tab1.eventTitle)
                    .setValue(firebaseUser.getEmail() + "\nWould like to play your event, " + Tab1.eventTitle);
                    Toast.makeText(v.getContext(), "Request Pending", Toast.LENGTH_SHORT).show();

                    databaseReference.child(CreateMusicianProfile.MUSICIAN_PROFILE).child(userId).child("pending_events").setValue(upcomingEventsList.get(position));
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