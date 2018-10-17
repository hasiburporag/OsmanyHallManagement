package com.example.porag.osmanyhallmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePass extends AppCompatActivity {
    EditText pass,confirmpass;
    Button chng,skip;
    SkipActivity session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        pass=findViewById(R.id.passwordnew1);
        confirmpass=findViewById(R.id.cnfpass1);
        chng=findViewById(R.id.change1);

        session=new SkipActivity(this);

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
                       // updateUI(session.gettype());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Passwords didn't match",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
