package com.example.porag.osmanyhallmanagement;

import java.io.Serializable;

public class Stuff implements Serializable {

    private String name,employeeid,age,dob,joiningdate,phone,hall;

    public Stuff() {
    }

    public Stuff(String name, String employeeid, String age, String dob, String joiningdate, String phone ,String hall) {
        this.name = name;
        this.employeeid = employeeid;
        this.age = age;
        this.dob = dob;
        this.joiningdate = joiningdate;
        this.phone = phone;
        this.hall = hall;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public String getAge() {
        return age;
    }

    public String getDob() {
        return dob;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public String getPhone() {
        return phone;
    }

    public String getHall(){
        return hall;
    }
}
