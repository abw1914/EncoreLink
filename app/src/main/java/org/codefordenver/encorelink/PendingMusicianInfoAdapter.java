package org.codefordenver.encorelink;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PendingMusicianInfoAdapter extends RecyclerView.Adapter<PendingMusicianInfoAdapter.ViewHolder> {

    private ArrayList<String> musicianInfo;
    private Listener listener;


    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        Button rejectionButton;




        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;

            LayoutInflater li = LayoutInflater.from(view.getContext());
            View v = li.inflate(R.layout.activity_musician_details,cardView,false);

            rejectionButton = v.findViewById(R.id.musician_rejection_button);
            rejectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        removeAt(getAdapterPosition());
                        Toast.makeText(v.getContext(), "Removing " + OrganizerDashboardPendingTab.volunteerSmallView.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }

        void bind(final int position) {

            TextView textView = cardView.findViewById(R.id.pending_musician_info);
            textView.setText(musicianInfo.get(position));
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
            });



        }

        void removeAt(int position) {
            musicianInfo.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, musicianInfo.size());
        }

    }

    public PendingMusicianInfoAdapter(ArrayList<String> musicianInfo) {
        this.musicianInfo = musicianInfo;
    }


    //called when recyclerview instantiates new viewholder instance
    //creates the views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_musician_info, parent, false);


        return new ViewHolder(cv);
    }

    //called when recycler view wants to populate data from model for the user to see
    //after each view holder is created, recycler view calls onBindViewHolder to populate item
    //with data
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);


    }


    //returns number of items in data source
    @Override
    public int getItemCount() {
        return musicianInfo.size();
    }
}
