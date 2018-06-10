package org.codefordenver.encorelink;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder> {

    private List<String> upcomingEventsList;



    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private DatabaseReference databaseReference;
        private String userId;
        private FirebaseAuth firebaseAuth;
        private FirebaseUser firebaseUser;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            cardView.setCardElevation(2);
            cardView.setPadding(1,1,1,1);
        }

        public void bind(int position) {

            TextView textView = cardView.findViewById(R.id.upcoming_events_for_card);
            textView.setText(upcomingEventsList.get(position));

        }
    }


    public UpcomingEventsAdapter(List<String> upcomingEventsList) {
        this.upcomingEventsList = upcomingEventsList;
    }
    @Override
    public UpcomingEventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_card, parent, false);


        return new UpcomingEventsAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(UpcomingEventsAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
