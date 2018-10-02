package com.example.porag.osmanyhallmanagement;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MessCount extends AppCompatActivity {

    DatabaseReference ref;
    int a=0;
    TextView cnt;
    Button count;
    Query qry;
    RadioGroup meals;
    RadioButton selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_count);
        cnt=findViewById(R.id.cnt);
        count=findViewById(R.id.count);
        meals=findViewById(R.id.meals);
        ref= FirebaseDatabase.getInstance().getReference("Mess");
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=0;

                int id=meals.getCheckedRadioButtonId();
                selected=findViewById(id);
                if(selected.getText().toString().compareTo("Breakfast")==0)
                {
                    qry=ref.orderByChild("breakfast").equalTo(true);
                }
                else if(selected.getText().toString().compareTo("Lunch")==0)
                {
                    qry=ref.orderByChild("lunch").equalTo(true);
                }
                else
                {
                    qry=ref.orderByChild("dinner").equalTo(true);
                }


                qry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot value:dataSnapshot.getChildren())
                        {
                            a++;
                        }
                        cnt.setText(Integer.toString(a));
                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


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
