package com.example.rahul.navigationdrawer.Objects;

import java.io.Serializable;

public class Rescue implements Serializable{

    String name;
    String description;
    int age;
    int how_may;
    String gender;
    double lat;
    double lon;
    long contact;

    public Rescue(String name, String description, int age, int how_may, String gender, double lat, double lon, Long contact) {
        this.name = name;
        this.description = description;
        this.age = age;
        this.how_may = how_may;
        this.gender = gender;
        this.lat = lat;
        this.lon = lon;
        this.contact = contact;
    }

    public Rescue() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHow_may() {
        return how_may;
    }

    public void setHow_may(int how_may) {
        this.how_may = how_may;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
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
}
