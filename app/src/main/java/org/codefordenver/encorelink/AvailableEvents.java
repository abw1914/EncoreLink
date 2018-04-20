package org.codefordenver.encorelink;

import android.content.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvailableEvents {
    private static AvailableEvents sAvailableEvents;

    private List<Event> mAvailableEventList;

    public static AvailableEvents get(Context context) {
        if (sAvailableEvents == null) {
            sAvailableEvents = new AvailableEvents(context);
        }
        return sAvailableEvents;
    }

    private AvailableEvents(Context context)
    {
        mAvailableEventList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            Event event = new Event.Builder(i)
                    .withName("Event #" + i)
                    .atLocation("Location #" + i)
                    .startingOn(LocalDateTime.now())
                    .build();
            mAvailableEventList.add(event);
        }
    }

    public List<Event> getAvailableEvents() {
        return mAvailableEventList;
    }

    public Event getEvent(int id) {
        for (Event event: mAvailableEventList) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
}
