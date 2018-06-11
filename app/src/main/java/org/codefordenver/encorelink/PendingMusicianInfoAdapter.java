package org.codefordenver.encorelink;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.codefordenver.encorelink.OrganizerTabs.OrganizerDashboardPendingTab;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PendingMusicianInfoAdapter extends RecyclerView.Adapter<PendingMusicianInfoAdapter.ViewHolder> {

    private ArrayList<String> musicianInfo;
    private String talentURL;
    public static ArrayList<String> approvedMusicians = new ArrayList<>();
    public static String musicianPosition;


    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        Button rejectionButton;
        private Button approvalButton;
        private DatabaseReference databaseReference;
        private String userId;
        private FirebaseAuth firebaseAuth;
        private FirebaseUser firebaseUser;
        private String musicianId;


        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;


            cardView.setCardElevation(2);
            cardView.setPadding(1,1,1,1);

        }

        void bind(final int position) {


            databaseReference = FirebaseDatabase.getInstance().getReference();

            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                userId = user.getUid();
            }



//            Matcher m = Patterns.WEB_URL.matcher(OrganizerDashboardPendingTab.volunteerLink.get(position));
//            while (m.find()) {
//                talentURL = m.group();
//            }
//
//            TextView textView = cardView.findViewById(R.id.pending_musician_info);
//            TextView userURL = cardView.findViewById(R.id.musical_talent_link);
//            textView.setText(musicianInfo.get(position));
//            userURL.setText(talentURL);
//
//            userURL.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        if (!talentURL.substring(0,4).equals("http")) {
//                            talentURL = "http://" + talentURL;
//                        }
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse(talentURL));
//                        v.getContext().startActivity(intent);
//                    } catch (ActivityNotFoundException e){
//                        Toast.makeText(v.getContext(), "Bad URL!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

            approvalButton = cardView.findViewById(R.id.musician_approval);

            approvalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseReference.child("approved_musicians").child(userId).child(String.valueOf(position)).
                            setValue(OrganizerDashboardPendingTab.volunteerDetail.get(position));
                    Toast.makeText(v.getContext(), "Musician approved!", Toast.LENGTH_SHORT - 3).show();
                    Toast.makeText(v.getContext(), "Moving musician to In Progress...", Toast.LENGTH_SHORT).show();
                    approvedMusicians.add(OrganizerDashboardPendingTab.volunteerDetail.get(position));



                }
            });

            rejectionButton = cardView.findViewById(R.id.musician_rejection_button);
            rejectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                      final DatabaseReference rejectReference = FirebaseDatabase.getInstance().getReference().child("pending_musicians")
                                .child(userId);
                        rejectReference.removeValue();
                        removeAt(getLayoutPosition());
                        notifyItemRemoved(getLayoutPosition());
                        Toast.makeText(v.getContext(), "Removing " + OrganizerDashboardPendingTab.volunteerSmallView.get(getAdapterPosition()),
                                Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            musicianPosition = String.valueOf(position);
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
