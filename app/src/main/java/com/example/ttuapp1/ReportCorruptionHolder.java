
package com.example.ttuapp1;

public class ReportCorruptionHolder {
    public String anonymous_username;
    public String subject;
    public String message;

    public ReportCorruptionHolder() {
    }

    public ReportCorruptionHolder(String username, String email, String age) {
        this.anonymous_username = username;
        this.subject = email;
        this.message = age;
    }

    public String getAnonymous_username() {
        return anonymous_username;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
