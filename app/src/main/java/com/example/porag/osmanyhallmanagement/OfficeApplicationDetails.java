package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OfficeApplicationDetails extends AppCompatActivity {
    DatabaseReference ref;
    String name,id;
    TextView student,detail,id1,time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_application_details);
        student=findViewById(R.id.student);
        detail=findViewById(R.id.complaint);
        id1=findViewById(R.id.id1);
        time=findViewById(R.id.timear);
        date=findViewById(R.id.datear);
        Intent i = getIntent();
        Complaint selected = (Complaint) i.getSerializableExtra("sampleObject");

        ref= FirebaseDatabase.getInstance().getReference().child("Users");
        Query qry=ref.orderByKey().equalTo(selected.getStudent());
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Users value = data.getValue(Users.class);
                    student.setText(value.getName());
                    id1.setText(value.getHallid());
                   // name=value.getName();
                   // id=value.getHallid();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String[] parts = selected.getDatetime().split(" ");
        String part1 = parts[0]; // 004
        String part2 = parts[1];

        student.setText(name);
        detail.setText(selected.getDetail());
        id1.setText(id);
        time.setText(part2);
        date.setText(part1);

    }
}
