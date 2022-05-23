package com.example.ttuapp1;

public class User {
    public String username, email, age,reg_no,course,imageUrl;

    public User(){
    }

    public User(String username, String email, String age, String reg_no, String course, String imageUrl){
        this.username= username;
        this.email = email;
        this.age= age;
        this.course= course;
        this.reg_no= reg_no;
        this.imageUrl= imageUrl;
    }

    public String getUsername() {
        return username;
    }
    public String getReg_no() {
        return reg_no;
    }
    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }
    public String getImageUrl() {
        return imageUrl;
    }


    public String getAge() {
        return age;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
