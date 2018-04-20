package org.codefordenver.encorelink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class AvailableEventsActivity extends AppCompatActivity{

    private RecyclerView mAvailableEventsRecyclerView;
    private AvailableEventsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_available_events);

        mAvailableEventsRecyclerView = (RecyclerView) findViewById(R.id.available_events_recycler_view);

        mAvailableEventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AvailableEvents availableEvents = AvailableEvents.get(this);
        List<Event> eventList = availableEvents.getAvailableEvents();

        mAdapter = new AvailableEventsAdapter(eventList);
        mAvailableEventsRecyclerView.setAdapter(mAdapter);

    }
}
