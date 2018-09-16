package com.example.porag.osmanyhallmanagement;

public class register {
    String name,depertment,studentid,hallid,room,phone,email;

    register(){

    }

    public register(String name, String depertment, String studentid, String hallid, String room, String phone, String email) {
        this.name = name;
        this.depertment = depertment;
        this.studentid = studentid;
        this.hallid = hallid;
        this.room = room;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDepertment() {
        return depertment;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getHallid() {
        return hallid;
    }

    public String getRoom() {
        return room;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
