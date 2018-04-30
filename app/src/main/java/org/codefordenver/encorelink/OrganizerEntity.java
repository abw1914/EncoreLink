package org.codefordenver.encorelink;

/**
 * Created by BenMorrisRains on 3/17/18.
 */

public class OrganizerEntity {

    private int id;
    private String organizationName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String contactName;
    private String contactJobTitle;
    private String phoneNumber;
    private String emailAddress;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactJobTitle(String contactJobTitle) {
        this.contactJobTitle = contactJobTitle;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactJobTitle() {
        return contactJobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return
                "Organization Name: " + organizationName + "\n" +
                "Street Address: " + streetAddress + '\n' +
                "City: " + city + '\n' +
                "State: " + state + '\n' +
                "Zipcode:" + zipcode + '\n' +
                "Contact Name: " + contactName + '\n' +
                "Contact Job Title: " + contactJobTitle + '\n' +
                "Phone Number: " + phoneNumber + '\n' +
                "Email Address: " + emailAddress + '\n';
    }
}
