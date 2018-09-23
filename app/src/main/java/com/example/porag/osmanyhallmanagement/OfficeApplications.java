package com.example.porag.osmanyhallmanagement;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OfficeApplications extends AppCompatActivity {
    private ListView listapp;
    private ArrayList <Complaint> complaints;
    DatabaseReference ref;
    ItemComplaintAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_applications);
        listapp=findViewById(R.id.listview1);
        complaints=new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference("Complaint");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Complaint value = data.getValue(Complaint.class);
                    complaints.add(value);
                }
                 Adapter = new ItemComplaintAdapter(OfficeApplications.this,
                        complaints);
                listapp.setAdapter(Adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.(" exception", "Failed to read value.", databaseError.toException());
            }
        });

        listapp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Complaint selected=Adapter.getItem(position);
                Intent i=new Intent(getApplicationContext(),OfficeApplicationDetails.class);
                i.putExtra("sampleObject", selected);
                startActivity(i);
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
