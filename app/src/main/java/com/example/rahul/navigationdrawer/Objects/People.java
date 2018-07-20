package com.example.rahul.navigationdrawer.Objects;

import java.io.Serializable;

public class People implements Serializable {
    String name;
    String description;
    int age;
    String gender;
    long contact;
    String photo_url;

    public People() {
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

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public People(String name, String description, int age, String gender, long contact, String photo_url) {
        this.name = name;
        this.description = description;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.photo_url = photo_url;
    }
}
