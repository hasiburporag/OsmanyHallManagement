package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference myref,users;
    EditText UserID,Password;
    Button LogIn,sig;
    Query usrqry;
    String userid,password;
    Users user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserID=findViewById(R.id.input_email);
        Password=findViewById(R.id.input_password);
        LogIn=findViewById(R.id.btn_login);

        db=FirebaseDatabase.getInstance();
        myref=db.getReference();
        users=myref.child("Users");


        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid=UserID.getText().toString();
                password=Password.getText().toString();
                usrqry=users.orderByChild("id").equalTo(userid);

                usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                            user = singleSnapshot.getValue(Users.class);
                        }
                        if(user==null)
                        {
                            Toast.makeText(getApplicationContext(),"Invalid Username/Password",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(user.getPassword().compareTo(password)==0)
                            {
                                updateUI();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Invalid Username/Password",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });




    }

    void updateUI() {
        Toast.makeText(getApplicationContext(),"Successfull Login",Toast.LENGTH_LONG).show();
        Intent i=new Intent(getApplicationContext(),StudentProfile.class);
        startActivity(i);

    }
}
