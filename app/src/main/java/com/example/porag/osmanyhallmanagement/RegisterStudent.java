package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class RegisterStudent extends AppCompatActivity {

    private EditText name,depertment,studentid,hallid,room,phone,email,dob;
    private RadioGroup grp;
    private RadioButton selected;
    private Button btnregister, uploadImg;
    DatabaseReference databaseReference,ref,refMess;
    private SkipActivity session;
    private static int RESULT_LOAD_IMAGE = 1;
    private StorageReference stref;
    String picturePath,downloadUrl;
    Uri selectedImage,downloadUrl1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        setupUIView();
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        ref=FirebaseDatabase.getInstance().getReference("UserType");
        refMess=FirebaseDatabase.getInstance().getReference("Mess");
        stref= FirebaseStorage.getInstance().getReference();
        session=new SkipActivity(this);
        uploadImg=findViewById(R.id.uploadImage);

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    int id=grp.getCheckedRadioButtonId();
                    selected=findViewById(id);
                    regStudent(selected.getText().toString());
                    Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegisterStudent.this,RegisterStudent.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void setupUIView(){
        name = (EditText)findViewById(R.id.name);
        depertment = (EditText)findViewById(R.id.depertment);
        studentid = (EditText)findViewById(R.id.studentid);
        hallid = (EditText)findViewById(R.id.hallid);
        room = (EditText)findViewById(R.id.room);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        dob=findViewById(R.id.dob);
        grp=findViewById(R.id.grp_radio);
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
        String sdob=dob.getText().toString();

        if(sname.isEmpty() || sdepertment.isEmpty() || sid.isEmpty() || shall.isEmpty() || sroom.isEmpty() || sphone.isEmpty() || semail.isEmpty()){
            Toast.makeText(this,"Enter All Values",Toast.LENGTH_LONG).show();
        }
        else{
            result = true;
        }

        return result;
    }

    public void regStudent(String shall){
        String ssname = name.getText().toString();
        String ssdepertment = depertment.getText().toString();
        String ssid = studentid.getText().toString();
        String sshall = hallid.getText().toString();
        String ssroom = room.getText().toString();
        String ssphone = phone.getText().toString();
        String ssemail = email.getText().toString();
        String sdob=dob.getText().toString();

        //  Uri file = Uri.fromFile(new File(picturePath));
        StorageReference riversRef = stref.child("Profile/"+"Student-"+ssid+"-"+sshall+".jpg");
        //  name.setText(picturePath);
        riversRef.putFile(selectedImage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        downloadUrl1 = taskSnapshot.getDownloadUrl();
                        downloadUrl=downloadUrl1.toString();
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

        Users newStudent=new Users(ssname,sshall,shall,ssphone,ssdepertment,ssemail,sdob,ssid,ssroom,downloadUrl1);
       UserType newType=new UserType("Student-"+ssid+"-"+sshall,"123456","Student");
       MessManage newMess=new MessManage("Student-"+ssid+"-"+sshall,false,false,false);
       databaseReference.child("Student-"+ssid+"-"+sshall).setValue(newStudent);
       ref.child("Student-"+ssid+"-"+sshall).setValue(newType);
       refMess.child("Student-"+ssid+"-"+sshall).setValue(newMess);


    }



}
