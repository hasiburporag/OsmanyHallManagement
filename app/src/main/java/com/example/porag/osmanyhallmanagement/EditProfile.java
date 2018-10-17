package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    EditText name,mobile,email;
    LinearLayout maillayout;
    Button submit;
    Intent get;
    String str,stremail,phone;
    Users student;
    Stuff stufff;
    Button confirm;
    SkipActivity session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        session=new SkipActivity(this);
        mobile=findViewById(R.id.editMob);
        email=findViewById(R.id.editMail);
        maillayout=findViewById(R.id.mailLayout);
        confirm=findViewById(R.id.confirmedit);

        get=getIntent();



        if(session.gettype().compareTo("Student")==0)
        {
            student=(Users) get.getSerializableExtra("student");
            mobile.setHint(student.getPhoneno());
            email.setHint(student.getEmail());


        }
        else
        {
            maillayout.setVisibility(View.GONE);
            stufff=(Stuff) get.getSerializableExtra("stuff");
            mobile.setHint(stufff.getPhone());

            //New


        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.gettype().compareTo("Student")==0)
                {
                   //e.getText().toString();
                   stremail=email.getText().toString();
                   phone=mobile.getText().toString();
                   if(stremail.isEmpty())
                   {
                      stremail=student.getEmail();
                   }
                   if(phone.isEmpty())
                   {
                       phone=student.getPhoneno();
                   }
                   Users updated=new Users(student.getName(),student.getHallid(),student.getHall(),phone,student.getDepartment(),student.getEmail(),student.getDob(),student.getRoll(),student.getRoom(),student.getPicture());
                    DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Users");
                    dr.child(session.getusename()).setValue(updated);
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();


                }
                else
                {
                    //str=name.getText().toString();
                   // stremail=email.getText().toString();
                    phone=mobile.getText().toString();
                    if(phone.isEmpty())
                    {
                        phone=stufff.getPhone();
                    }
                    Stuff updated=new Stuff(stufff.getName(),stufff.getEmployeeid(),stufff.getAge(),stufff.getDob(),stufff.getJoiningdate(),phone,stufff.getHall());
                    DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Users");
                    dr.child(session.getusename()).setValue(updated);
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                    //New


                }

            }
        });



    }
}
