package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StuffRegister extends AppCompatActivity {

    private EditText name,employeeid,age,dob,joiningdate,phone;
    private Button uploadImage,register;
    private Spinner type;
    private RadioButton selected;
    private RadioGroup grpradio;
    private StorageReference stref;
    String picturePath,downloadUrl;
    private static int RESULT_LOAD_IMAGE = 1;
    Uri selectedImage;
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
        stref= FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        ref=FirebaseDatabase.getInstance().getReference("UserType");

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();




        }
    }

    void regStuff(String shall){

        String sname = name.getText().toString();
        String empid = employeeid.getText().toString();
        String sage = age.getText().toString();
        String sdob = dob.getText().toString();
        String sjoin = joiningdate.getText().toString();
        String sphone = phone.getText().toString();
        StorageReference riversRef = stref.child("Profile/"+emp_type+"-"+empid+".jpg");
        //  name.setText(picturePath);
        riversRef.putFile(selectedImage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                       // downloadUrl1 = taskSnapshot.getDownloadUrl();
                        //downloadUrl=downloadUrl1.toString();
                        //  Toast.makeText(getApplicationContext(),"d",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        Stuff newStuff = new Stuff(sname,empid,sage,sdob,sjoin,sphone,shall);
        UserType newType=new UserType(emp_type+"-"+empid,"123456",emp_type);
        databaseReference.child(emp_type+"-"+empid).setValue(newStuff);
        ref.child(emp_type+"-"+empid).setValue(newType);

    }

}
