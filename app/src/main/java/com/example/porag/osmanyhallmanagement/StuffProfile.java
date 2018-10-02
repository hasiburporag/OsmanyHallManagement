package com.example.porag.osmanyhallmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessaging;

public class StuffProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_profile);
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
    }
}
