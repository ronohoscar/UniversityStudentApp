package com.example.ttuapp1;

public class FriendlyMessage {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String mid;
    private String id;
    private String text;
    private String name;
    private String photoUrl;
    private String imageUrl;
    private String timeStamp;



    public FriendlyMessage() {
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public FriendlyMessage(String text, String name, String photoUrl, String imageUrl, String mid, String timeStamp) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
        this.mid=mid;
        this.timeStamp=timeStamp;
    }


    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
