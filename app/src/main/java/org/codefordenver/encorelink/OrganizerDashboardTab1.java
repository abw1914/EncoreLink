package org.codefordenver.encorelink;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is an empty activity for now.
 */
public class OrganizerDashboardTab1 extends Fragment {


    private String userId;
    private ArrayList<String> volunteers = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        RecyclerView musicianInfoRecycler = (RecyclerView) inflater.inflate(R.layout.tab1, container, false);

        String[] testInfo = new String[3];

        for(int i = 0; i < testInfo.length; i++) {
            testInfo[i] = "This is a test";
        }

        PendingMusicianInfoAdapter adapter = new PendingMusicianInfoAdapter(testInfo);
        musicianInfoRecycler.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        musicianInfoRecycler.setLayoutManager(linearLayoutManager);

        return musicianInfoRecycler;

    }

//        View view = inflater.inflate(R.layout.tab1, container, false);
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//            userId = user.getUid();
//        }
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CreateMusicianProfile.MUSICIAN_PROFILE);
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, volunteers);
//        ListView volunteersList = view.findViewById(R.id.pending_organizer_tab);
//
//        volunteersList.setAdapter(arrayAdapter);
//
//
//        mDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                        volunteers.add(Objects.requireNonNull(dataSnapshot1.getKey() + ": " +
//                                dataSnapshot1.getValue()));
//                }
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//        return view;
//
//    }


}
