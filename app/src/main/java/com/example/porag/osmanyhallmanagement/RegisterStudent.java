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

public class RegisterStudent extends AppCompatActivity {

    private EditText name,depertment,studentid,hallid,room,phone,email;
    private Button btnregister;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIView();
        databaseReference = FirebaseDatabase.getInstance().getReference("Register");

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    regStudent();
                    Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegisterStudent.this,RegisterStudent.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setupUIView(){
        name = (EditText)findViewById(R.id.name);
        depertment = (EditText)findViewById(R.id.depertment);
        studentid = (EditText)findViewById(R.id.studentid);
        hallid = (EditText)findViewById(R.id.hallid);
        room = (EditText)findViewById(R.id.room);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        btnregister = (Button)findViewById(R.id.register);
    }

    private Boolean validate(){
        Boolean result = false;

        String sname = name.getText().toString();
        String sdepertment = depertment.getText().toString();
        String sid = studentid.getText().toString();
        String shall = hallid.getText().toString();
        String sroom = room.getText().toString();
        String sphone = phone.getText().toString();
        String semail = email.getText().toString();

        if(sname.isEmpty() || sdepertment.isEmpty() || sid.isEmpty() || shall.isEmpty() || sroom.isEmpty() || sphone.isEmpty() || semail.isEmpty()){
            Toast.makeText(this,"Enter All Values",Toast.LENGTH_LONG).show();
        }
        else{
            result = true;
        }

        return result;
    }

    public void regStudent(){
        String ssname = name.getText().toString().trim();
        String ssdepertment = depertment.getText().toString().trim();
        String ssid = studentid.getText().toString().trim();
        String sshall = hallid.getText().toString().trim();
        String ssroom = room.getText().toString().trim();
        String ssphone = phone.getText().toString().trim();
        String ssemail = email.getText().toString().trim();
        String id = databaseReference.push().getKey();

        register reg = new register(ssname,ssdepertment,ssid,sshall,ssroom,ssphone,ssemail);
        databaseReference.child(id).setValue(reg);
    }
}
