package org.codefordenver.encorelink;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InProcessMusicianAdapter extends RecyclerView.Adapter<InProcessMusicianAdapter.ViewHolder> {

    private ArrayList<String> musicianInfo;
    private InProcessMusicianAdapter.Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(InProcessMusicianAdapter.Listener listener) {
        this.listener = listener;
    }

    public InProcessMusicianAdapter(ArrayList<String> musicianInfo) {
        this.musicianInfo = musicianInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_musician_info, parent, false);


        return new InProcessMusicianAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return musicianInfo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        Button rejectionButton;




        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            cardView.setCardElevation(2);
            cardView.setPadding(1,1,1,1);

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

    }



}
