package org.codefordenver.encorelink.EntityClasses;

public class EventEntity {

    private String eventTitle;
    private String streetAddress;
    private String city;
    private String zipcode;
    private String startTime;
    private String endTime;
    private String notes;
    private String addEvent;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAddEvent(String addEvent) {
        this.addEvent = addEvent;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNotes() {
        return notes;
    }

    public String getAddEvent() {
        return addEvent;
    }
}
