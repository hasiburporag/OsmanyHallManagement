package com.example.porag.osmanyhallmanagement;

public class UserType {
    String id,password,type;

    public UserType() {
    }

    public UserType(String id, String password, String type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}
