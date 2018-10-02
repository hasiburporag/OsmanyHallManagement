package com.example.porag.osmanyhallmanagement;

public class MessManage {
    String id;
    boolean breakfast,lunch,dinner;

    public MessManage() {
    }

    public MessManage(String id,boolean breakfast, boolean lunch, boolean dinner) {
        this.id=id;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getId() {
        return id;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public boolean isLunch() {
        return lunch;
    }

    public boolean isDinner() {
        return dinner;
    }
}
