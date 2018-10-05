package com.example.porag.osmanyhallmanagement;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    TextView name,hallid,Hall1,Hall2,studentid,dept,mobile,email,dob;
    CircleImageView img;
    FirebaseDatabase db;
    DatabaseReference ref,myref;
    Query query;
    private SkipActivity session;
    String username,a;
    Users user;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        name=findViewById(R.id.name);
        hallid=findViewById(R.id.designation);
        img=findViewById(R.id.profile_pic);
        Hall1=findViewById(R.id.location);
        Hall2=findViewById(R.id.hall);
        studentid=findViewById(R.id.studentid);
        dept=findViewById(R.id.department);
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        dob=findViewById(R.id.dob);
        session=new SkipActivity(this);
        username=session.getusename();
        //name.setText(username);
        db=FirebaseDatabase.getInstance();
        ref=db.getReference();
        myref=ref.child("Users");

       // user=new Users();

        query=myref.orderByKey().equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(Users.class);
                }


               // a = user.getName();
                name.setText(user.getName());
                hallid.setText(user.getHallid());
                Hall2.setText(user.getHall());
                Hall1.setText(user.getHall());
                dept.setText(user.getDepartment());
                mobile.setText(user.getPhoneno());
                email.setText(user.getEmail());
                dob.setText(user.getDob());
                studentid.setText(user.getRoll());
                img.setImageURI(user.getPicture());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

            }
        });

        progressDialog.dismiss();

    }
}
