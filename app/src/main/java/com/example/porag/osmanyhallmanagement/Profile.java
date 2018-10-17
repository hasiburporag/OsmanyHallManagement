package com.example.porag.osmanyhallmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        edit=findViewById(R.id.edit);
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

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),EditProfile.class);
                i.putExtra("student", user);
                startActivity(i);
            }
        });
        StorageReference mImageRef =
                FirebaseStorage.getInstance().getReference("Profile").child(session.getusename()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024 *5;
        mImageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        img.setMinimumHeight(dm.heightPixels);
                        img.setMinimumWidth(dm.widthPixels);
                        img.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        progressDialog.dismiss();

    }
}
