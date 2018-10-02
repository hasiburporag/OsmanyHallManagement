package com.example.porag.osmanyhallmanagement;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StudentReport extends AppCompatActivity {
    Spinner type;
    String complaint_type;
    Button submit;
    EditText detail_complaint;
    DatabaseReference ref;
    SkipActivity session;
    String[] report={"Select the type","Mess Complaint","Room Complaint","Hall Complaints","Application"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_report);
        type=findViewById(R.id.typereport);
        detail_complaint=findViewById(R.id.detail_complaint);
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
        submit=findViewById(R.id.report_submit);
        session=new SkipActivity(this);
        type.setOnItemSelectedListener(new CustomListener());
        ref= FirebaseDatabase.getInstance().getReference("Complaint");
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,report);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(aa);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                complaint_type=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                Complaint newcomplaint=new Complaint(complaint_type,detail_complaint.getText().toString(),session.getusename(),currentDateandTime);
                ref.push().setValue(newcomplaint);
                detail_complaint.setText("");
                Toast.makeText(getApplicationContext(),"Sent",Toast.LENGTH_LONG).show();
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


}
