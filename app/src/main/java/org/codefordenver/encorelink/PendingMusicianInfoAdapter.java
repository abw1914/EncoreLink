package org.codefordenver.encorelink;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PendingMusicianInfoAdapter extends RecyclerView.Adapter<PendingMusicianInfoAdapter.ViewHolder>{

    private String[] musicianInfo;

     class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder (CardView view) {
            super(view);
            cardView = view;
        }

        void bind(int position) {
            TextView textView = cardView.findViewById(R.id.pending_musician_info);
            textView.setText(musicianInfo[position]);
        }
    }

    public PendingMusicianInfoAdapter(String[] musicianInfo) {
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
        return musicianInfo.length;
    }
}
