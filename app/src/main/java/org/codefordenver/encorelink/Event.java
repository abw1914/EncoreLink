package org.codefordenver.encorelink;

import java.time.LocalDateTime;

public class Event {

    private String mName;
    private String mLocation;
    private LocalDateTime mDate;
    private LocalDateTime mEndDate;
    private LocalDateTime mCreatedDate;
    private String mNotes;
    private int mId;
    private int mOwnerId;

    private Event(Builder builder) {
        mName = builder.mName;
        mLocation = builder.mLocation;
        mDate = builder.mDate;
        mEndDate = builder.mEndDate;
        mCreatedDate = builder.mCreatedDate;
        mNotes = builder.mNotes;
        mId = builder.mId;
        mOwnerId = builder.mOwnerId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public LocalDateTime getDate() {
        return mDate;
    }

    public void setDate(LocalDateTime date) {
        this.mDate = date;
    }

    public LocalDateTime getEndDate() {
        return mEndDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.mEndDate = endDate;
    }

    public LocalDateTime getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.mCreatedDate = createdDate;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        this.mNotes = notes;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        this.mOwnerId = ownerId;
    }

    public static class Builder {

        private String mName;
        private String mLocation;
        private LocalDateTime mDate;
        private LocalDateTime mEndDate;
        private LocalDateTime mCreatedDate;
        private String mNotes;
        private int mId;
        private int mOwnerId;

        public Builder(int eventId) {
            this.mId = eventId;
        }

        public Builder withName(String eventName) {
            this.mName = eventName;

            return this;
        }

        public Builder atLocation(String eventLocation) {
            this.mLocation = eventLocation;

            return this;
        }

        public Builder startingOn(LocalDateTime eventDate) {
            this.mDate = eventDate;

            return this;
        }

        public Builder endingOn(LocalDateTime endingDate) {
            this.mEndDate = endingDate;

            return this;
        }

        public Builder createdOn(LocalDateTime createDate) {
            this.mCreatedDate = createDate;

            return this;
        }

        public Builder notes(String eventNotes) {
            this.mNotes = eventNotes;

            return this;
        }

        public Builder theOwnerID(int ownerID) {
            this.mOwnerId = ownerID;

            return this;
        }

        public Event build() {
            Event event = new Event(this);

            return event;
        }
    }

}
