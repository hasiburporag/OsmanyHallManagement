package com.example.porag.osmanyhallmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class StuffProfile extends AppCompatActivity {

    private TextView name,name1,loacatin,designation,employeeid,age,dob,joiningdate,phone,workplace;
    FirebaseDatabase db;
    DatabaseReference ref,myref;
    Query query;
    private SkipActivity session;
    String username,a;
    Stuff stuff;
    ProgressDialog progressDialog;
    ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_profile);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
        edit=findViewById(R.id.editStuff);
        setUpUIview();
        session=new SkipActivity(this);
        username=session.getusename();
        //name.setText(username);
        db=FirebaseDatabase.getInstance();
        ref=db.getReference();
        myref=ref.child("Users");


        query=myref.orderByKey().equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    stuff = singleSnapshot.getValue(Stuff.class);
                }


                // a = user.getName();
                name1.setText(stuff.getName());
                designation.setText(stuff.getEmployeeid());
                loacatin.setText(stuff.getHall());
                name.setText(stuff.getName());
                employeeid.setText(stuff.getEmployeeid());
                age.setText(stuff.getAge());
                joiningdate.setText(stuff.getJoiningdate());
                dob.setText(stuff.getDob());
                phone.setText(stuff.getPhone());
                workplace.setText(stuff.getHall());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

            }
        });

        progressDialog.dismiss();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),EditProfile.class);
                i.putExtra("stuff", stuff);
                startActivity(i);
            }
        });


    }

    void setUpUIview(){

        name1 = findViewById(R.id.name);
        loacatin = findViewById(R.id.location);
        designation = findViewById(R.id.designation);
        name = findViewById(R.id.ename);
        employeeid = findViewById(R.id.employeeid);
        age = findViewById(R.id.age);
        dob = findViewById(R.id.dob);
        joiningdate = findViewById(R.id.jdate);
        phone = findViewById(R.id.mobile);
        workplace = findViewById(R.id.workplace);

    }

}
