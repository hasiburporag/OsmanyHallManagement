package com.example.porag.osmanyhallmanagement;

import java.io.Serializable;

public class Complaint implements Serializable {
    String type;
    String detail;
    String student;
    String datetime;

    public Complaint() {
    }

    public Complaint(String type, String detail, String student, String datetime) {
        this.type = type;
        this.detail = detail;
        this.student = student;
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public String getStudent() {
        return student;
    }

    public String getDatetime() {
        return datetime;
    }
}
