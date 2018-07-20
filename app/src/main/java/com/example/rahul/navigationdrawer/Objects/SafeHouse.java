package com.example.rahul.navigationdrawer.Objects;

public class SafeHouse {
    String place_name;
    int accommodation;
    String description;
    double lat;
    double lon;
    int contact;

    public SafeHouse(String place_name, int accommodation, String description, double lat, double lon, int contact) {
        this.place_name = place_name;
        this.accommodation = accommodation;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.contact = contact;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public int getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(int accommodation) {
        this.accommodation = accommodation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
}
