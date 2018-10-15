package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class GuardGoingOut extends AppCompatActivity {

    private ListView listapp;
    private ArrayList<GoingOutHistory> complaints;
    DatabaseReference ref;
    GoingOutAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_going_out);
        listapp=findViewById(R.id.listgoingout);
        complaints = new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference("GoingOutHistory");
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    GoingOutHistory value = data.getValue(GoingOutHistory.class);
                    complaints.add(value);
                }
                Adapter = new GoingOutAdapter(GuardGoingOut.this,
                        complaints);
                listapp.setAdapter(Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.(" exception", "Failed to read value.", databaseError.toException());
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
