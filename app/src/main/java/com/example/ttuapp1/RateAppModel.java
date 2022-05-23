package com.example.ttuapp1;

public class RateAppModel {
    public String rating,email,uid;

    public RateAppModel(){

    }

    public RateAppModel(String rating,String email, String uid){
        this.rating= rating;
        this.email= email;
        this.uid= uid;

    }

    public String getRating() {
        return rating;
    }
    public String getEmail() {
        return email;
    }
    public String getUid() {
        return uid;
    }
}
