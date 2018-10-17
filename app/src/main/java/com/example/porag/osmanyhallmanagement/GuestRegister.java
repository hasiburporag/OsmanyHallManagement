package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GuestRegister extends AppCompatActivity {

    private Spinner type;
    private EditText name1,info,room,phone;
    private Button btnregister;
    String guest_type;
    String[] report={"Select the type","Outsider","MISTian","Hall Residence"};
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_register);

        setupUIView();

        type=findViewById(R.id.gsp);

        databaseReference = FirebaseDatabase.getInstance().getReference("Guest");

        type.setOnItemSelectedListener(new CustomListener());
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,report);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(aa);



        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                guest_type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegGuest(guest_type);
                Toast.makeText(getApplicationContext(),"Sucesfull",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(GuestRegister.this,GuestRegister.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void setupUIView(){
        name1 = (EditText)findViewById(R.id.name1);
        info = (EditText)findViewById(R.id.info);
        room = (EditText)findViewById(R.id.room);
        phone = (EditText)findViewById(R.id.phone);
        btnregister = (Button)findViewById(R.id.register1);
    }

    void RegGuest(String guest_type){
        String gname = name1.getText().toString().trim();
        String ginfo = info.getText().toString().trim();
        String groom = room.getText().toString().trim();
        String gphone = phone.getText().toString().trim();
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


        String key=new StringBuilder().append(gname).append(currentDateandTime).toString();
        InsertGuest reg = new InsertGuest(key,gname,ginfo,groom,gphone,guest_type,currentDateandTime,0,"Decline");
        databaseReference.child(key).setValue(reg);
    }
}
