package org.codefordenver.encorelink;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static org.codefordenver.encorelink.R.layout.musician_event_list_item;

public class AvailableEventsAdapter extends RecyclerView.Adapter<AvailableEventsAdapter.ViewHolder>{
    private List<Event> mAvailableEventList;

    public AvailableEventsAdapter(List<Event> eventList)
    {
        mAvailableEventList = eventList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.musician_event_list_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mAvailableEventList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAvailableEventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItemDate;
        TextView listItemTime;
        TextView listItemLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            listItemDate = (TextView) itemView.findViewById(R.id.date);
            listItemTime = (TextView) itemView.findViewById(R.id.time);
            listItemLocation = (TextView) itemView.findViewById(R.id.location);
        }

        void bind (Event event) {
            String date = event.getDate().getMonth().toString() + " "
                            + event.getDate().getDayOfMonth() + " "
                            + event.getDate().getYear();
            listItemDate.setText(date);

            String time = Integer.toString(event.getDate().getHour()) + ":"
                            + Integer.toString(event.getDate().getMinute());

            listItemTime.setText(time);

            listItemLocation.setText(event.getLocation());
        }
    }
}
