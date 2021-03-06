package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PasswordChangeFirst extends AppCompatActivity {
    EditText pass,confirmpass;
    Button chng,skip;
    SkipActivity session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change_first);
        pass=findViewById(R.id.passwordnew);
        confirmpass=findViewById(R.id.cnfpass);
        chng=findViewById(R.id.change);
        skip=findViewById(R.id.skip);
        session=new SkipActivity(this);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI(session.gettype());
            }
        });
        chng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=pass.getText().toString().trim();
                String confirm=confirmpass.getText().toString().trim();
                if(password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Empty Password is not accepted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.compareTo(confirm)==0)
                    {
                        DatabaseReference dr=FirebaseDatabase.getInstance().getReference("UserType");
                        UserType newType=new UserType(session.getusename(),password,session.gettype());
                        dr.child(session.getusename()).setValue(newType);
                        Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();
                        updateUI(session.gettype());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Passwords didn't match",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
    void updateUI(String type) {
       // progressDialog.dismiss();
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
        if(type.compareTo("Guard")==0)
        {
            Intent i = new Intent(getApplicationContext(), GuardActivity.class);
            startActivity(i);
        }

    }
}
