package com.example.porag.osmanyhallmanagement;

public class InsertGuest {
    String name1,info,room,phone,guest_type,currentDateandTime,status,gkeyid;
    int check;

    public InsertGuest() {
    }

    public InsertGuest(String keyid,String name1, String info, String room, String phone, String guest_type,String currentDateandTime,int check,String sts) {
        this.name1 = name1;
        this.info = info;
        this.room = room;
        this.phone = phone;
        this.guest_type = guest_type;
        this.currentDateandTime =  currentDateandTime;
        this.check=check;
        status=sts;
        this.gkeyid=keyid;
    }

    public String getStatus() {
        return status;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getName1() {
        return name1;
    }

    public String getInfo() {
        return info;
    }

    public String getRoom() {
        return room;
    }

    public String getPhone() {
        return phone;
    }

    public String getGuest_type() {
        return guest_type;
    }

    public String getCurrentDateandTime() {
        return currentDateandTime;
    }
}
