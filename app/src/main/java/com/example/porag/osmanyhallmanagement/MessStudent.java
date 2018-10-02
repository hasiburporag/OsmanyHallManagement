package com.example.porag.osmanyhallmanagement;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MessStudent extends AppCompatActivity {
    Switch breakfast,lunch,dinner;
    MessManage user;
    DatabaseReference dbref;
    SkipActivity session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        breakfast=findViewById(R.id.breakfast);
        lunch=findViewById(R.id.lunch);
        dinner=findViewById(R.id.dinner);
        session=new SkipActivity(this);
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");

        dbref= FirebaseDatabase.getInstance().getReference().child("Mess");
        Query qry=dbref.orderByKey().equalTo(session.getusename());
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(MessManage.class);
                }
                breakfast.setChecked(user.isBreakfast());
                lunch.setChecked(user.isLunch());
                dinner.setChecked(user.isDinner());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        breakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                MessManage change=new MessManage(session.getusename(),breakfast.isChecked(),lunch.isChecked(),dinner.isChecked());

                dbref.child(session.getusename()).setValue(change);

            }
        });
        lunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MessManage change=new MessManage(session.getusename(),breakfast.isChecked(),lunch.isChecked(),dinner.isChecked());

                dbref.child(session.getusename()).setValue(change);



            }
        });
        dinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MessManage change=new MessManage(session.getusename(),breakfast.isChecked(),lunch.isChecked(),dinner.isChecked());

                dbref.child(session.getusename()).setValue(change);



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
