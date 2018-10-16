package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditProfile extends AppCompatActivity {

    EditText name,mobile,email;
    LinearLayout maillayout;
    Button submit;
    Intent get;

    Users student;
    Stuff stufff;

    SkipActivity session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        session=new SkipActivity(this);
        name=findViewById(R.id.editName);
        mobile=findViewById(R.id.editMob);
        email=findViewById(R.id.editMail);
        maillayout=findViewById(R.id.mailLayout);

        get=getIntent();



        if(session.gettype().compareTo("Student")==0)
        {
            student=(Users) get.getSerializableExtra("student");
            name.setHint(student.getName());
            mobile.setHint(student.getPhoneno());
            email.setHint(student.getEmail());


        }
        else
        {
            maillayout.setVisibility(View.GONE);
            stufff=(Stuff) get.getSerializableExtra("stuff");
            name.setHint(stufff.getName());
            mobile.setHint(stufff.getPhone());


        }



    }
}
