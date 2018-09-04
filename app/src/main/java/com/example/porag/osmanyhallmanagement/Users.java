package com.example.porag.osmanyhallmanagement;

public class Users {
    String id;
    String name;
    String hallid;
    String hall;
    String phoneno;
    String position;
    String dob;
    String password;
    String roll;

    public Users()
    {

    }

    public Users(String id, String name, String hallid, String hall, String phoneno, String position, String dob,String Password,String Roll) {
        this.id = id;
        this.name = name;
        this.hallid = hallid;
        this.hall = hall;
        this.phoneno = phoneno;
        this.position = position;
        this.dob = dob;
        this.password=Password;
        this.roll=roll;
    }

    public String getRoll() {
        return roll;
    }

    public String getId() {
        return id;
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

    public String getPosition() {
        return position;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {

        return dob;
    }
}
