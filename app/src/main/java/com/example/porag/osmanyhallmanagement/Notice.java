package com.example.porag.osmanyhallmanagement;

public class Notice {
    String notice,date,time;

    public Notice(String notice, String date, String time) {
        this.notice = notice;
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Notice() {
    }

    public String getNotice() {
        return notice;
    }

    public String getDate() {
        return date;
    }
}
