package com.example.porag.osmanyhallmanagement;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class GuestApprove extends AppCompatActivity {
    private ListView listapp;
    private ArrayList<InsertGuest> complaints;
    DatabaseReference ref;
    GuestAdapter Adapter;
    String room;
    SkipActivity session;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_approve);
        listapp=findViewById(R.id.guestlist);
        complaints = new ArrayList<>();
        session=new SkipActivity(this);
       // test=findViewById(R.id.test);
        DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Users");
        Query qry=dr.orderByKey().equalTo(session.getusename());
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot val:dataSnapshot.getChildren())
                {
                    Users std=val.getValue(Users.class);
                    room=std.getRoom();
                }
               // test.setText(room+"TEST");
                ref= FirebaseDatabase.getInstance().getReference("Guest");
                Query qry1=ref.orderByChild("room").equalTo(room);
                FirebaseMessaging.getInstance().subscribeToTopic("Notices");
                qry1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data: dataSnapshot.getChildren()) {
                            InsertGuest value = data.getValue(InsertGuest.class);
                            complaints.add(value);
                        }
                        Adapter = new GuestAdapter(GuestApprove.this,
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
            public void onCancelled(DatabaseError databaseError) {

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