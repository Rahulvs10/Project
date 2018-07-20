package com.example.rahul.navigationdrawer.Objects;

import java.io.Serializable;

public class Resource implements Serializable {
    String name;
    String res_name;
    String quantity;
    int supply_for;
    double lat;
    double lon;
    long contact;
    String status;

    public Resource(String name, String res_name, String quantity, int supply_for, String status, double lat, double lon, long contact) {
        this.name = name;
        this.res_name = res_name;
        this.quantity = quantity;
        this.supply_for = supply_for;
        this.status = status;
        this.lat = lat;
        this.lon = lon;
        this.contact = contact;
    }

    public Resource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getSupply_for() {
        return supply_for;
    }

    public void setSupply_for(int supply_for) {
        this.supply_for = supply_for;
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

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
