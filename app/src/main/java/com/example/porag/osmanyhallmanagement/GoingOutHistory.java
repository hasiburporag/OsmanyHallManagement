package com.example.porag.osmanyhallmanagement;

public class GoingOutHistory {
    String reason;
    String location;
    String timing;
    String id;

    public GoingOutHistory(String reason, String location, String dateTime,String id) {
        this.reason = reason;
        this.location = location;
        this.timing = dateTime;
        this.id=id;
    }

    public GoingOutHistory() {
    }

    public String getReason() {
        return reason;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public String getTiming() {
        return timing;
    }
}
