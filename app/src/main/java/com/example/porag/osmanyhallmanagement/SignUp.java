package com.example.porag.osmanyhallmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText a,b,c,d,e,f,g;
    String h,i,j,k,l,m,n,o;
    Button sign;
    DatabaseReference signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        a=findViewById(R.id.input_name);
        b=findViewById(R.id.input_hall);
        c=findViewById(R.id.input_hallid);
        d=findViewById(R.id.input_roll);
        e=findViewById(R.id.input_dob);
        f=findViewById(R.id.input_phoneno);
        g=findViewById(R.id.input_position);
        sign=findViewById(R.id.btn_sign);
        signup= FirebaseDatabase.getInstance().getReference("UserType");

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addValue();

            }
        });

    }

     void addValue() {
        h=a.getText().toString();
         i=b.getText().toString();
         j=c.getText().toString();
         k=d.getText().toString();
         l=e.getText().toString();
         m=f.getText().toString();
         n=g.getText().toString();

        // Users user=new Users(n+k,h,j,i,m,n,l,h);
         UserType user=new UserType(h,i,j);
         signup.child(h).setValue(user);


    }
}
