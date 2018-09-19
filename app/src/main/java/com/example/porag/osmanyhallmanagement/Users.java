package com.example.porag.osmanyhallmanagement;


public class Users {
    String name;
    String hallid;
    String hall;
    String phoneno;
    String department;
    String email;
    String dob;
    String roll;
    String room;
    String picture;

    public Users() {
    }

    public Users(String name, String hallid, String hall, String phoneno, String department, String email, String dob, String roll, String room, String picture) {
        this.name = name;
        this.hallid = hallid;
        this.hall = hall;
        this.phoneno = phoneno;
        this.department = department;
        this.email = email;
        this.dob = dob;
        this.roll = roll;
        this.room = room;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getHallid() {
        return hallid;
    }

    public String getHall() {
        return hall;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getRoll() {
        return roll;
    }

    public String getRoom() {
        return room;
    }
}
