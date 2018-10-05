package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StuffRegister extends AppCompatActivity {

    private EditText name,employeeid,age,dob,joiningdate,phone;
    private Button uploadImage,register;
    private Spinner type;
    private RadioButton selected;
    private RadioGroup grpradio;
    String emp_type;
    DatabaseReference databaseReference,ref;
    String[] report={"Select the type","Mess","Office","Guard"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_register);
        setUpUIview();
        type.setOnItemSelectedListener(new CustomListener());
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,report);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(aa);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        ref=FirebaseDatabase.getInstance().getReference("UserType");

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

               // startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emp_type=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = grpradio.getCheckedRadioButtonId();
                selected=findViewById(id);
                regStuff(selected.getText().toString());
                Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(StuffRegister.this,StuffRegister.class);
                startActivity(intent);
                finish();
            }
        });
    }



    void setUpUIview(){

        name = findViewById(R.id.name);
        employeeid = findViewById(R.id.employeeid);
        age = findViewById(R.id.age);
        dob = findViewById(R.id.dob);
        joiningdate = findViewById(R.id.joiningdate);
        phone = findViewById(R.id.phone);
        uploadImage = findViewById(R.id.uploadImage);
        register = findViewById(R.id.register);
        type = findViewById(R.id.selectemp);
        grpradio = findViewById(R.id.grp_radio);

    }

    void regStuff(String shall){

        String sname = name.getText().toString();
        String empid = employeeid.getText().toString();
        String sage = age.getText().toString();
        String sdob = dob.getText().toString();
        String sjoin = joiningdate.getText().toString();
        String sphone = phone.getText().toString();

        Stuff newStuff = new Stuff(sname,empid,sage,sdob,sjoin,sphone,shall);
        UserType newType=new UserType(emp_type+"-"+empid,"123456",emp_type);
        databaseReference.child(emp_type+"-"+empid).setValue(newStuff);
        ref.child(emp_type+"-"+empid).setValue(newType);

    }

}
