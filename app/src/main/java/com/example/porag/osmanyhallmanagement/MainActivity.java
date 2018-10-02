package com.example.porag.osmanyhallmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
    UserType user;
    private SkipActivity session;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /// Intent demo=new Intent(getApplicationContext(),SignUp.class);
       /// startActivity(demo);
        UserID=findViewById(R.id.input_email);
        Password=findViewById(R.id.input_password);
        LogIn=findViewById(R.id.btn_login);
        session=new SkipActivity(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging In");
        db=FirebaseDatabase.getInstance();
        myref=db.getReference();
        users=myref.child("UserType");
       if(!session.getusename().isEmpty())
     {
         updateUI(session.gettype());
     }
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                userid=UserID.getText().toString();
                password=Password.getText().toString();
                usrqry=users.orderByChild("id").equalTo(userid);

                usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                            user = singleSnapshot.getValue(UserType.class);
                        }
                        if(user==null)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Invalid Username/Password",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(user.getPassword().compareTo(password)==0)
                            {
                                session.setusename(userid);
                                session.settype(user.getType());
                                updateUI(user.getType());
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Invalid Username/Password",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });




    }

    void updateUI(String type) {
        progressDialog.dismiss();
     //  Toast.makeText(getApplicationContext(),"Successfull Login"+ type,Toast.LENGTH_LONG).show();
        if(type.compareTo("Student")==0) {
            Intent i = new Intent(getApplicationContext(), StudentProfile.class);
            startActivity(i);
        }
        if(type.compareTo("Office")==0)
        {
            Intent i = new Intent(getApplicationContext(), OfficeProfile.class);
            startActivity(i);
        }
        if(type.compareTo("Mess")==0)
        {
            Intent i = new Intent(getApplicationContext(), MessProfile.class);
            startActivity(i);
        }


    }

}
