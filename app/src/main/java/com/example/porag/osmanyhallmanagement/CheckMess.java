package com.example.porag.osmanyhallmanagement;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class CheckMess extends AppCompatActivity {
    private TextView lunch,breakfast,dinner,name;
    private EditText hallid;
    private Button check;
    DatabaseReference ref1,ref2;
    String student;
    Users user;
    MessManage mess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mess);
        hallid=findViewById(R.id.check_hall);
        check=findViewById(R.id.check);
        breakfast=findViewById(R.id.st_breakfast);
        lunch=findViewById(R.id.st_lunch);
        dinner=findViewById(R.id.st_dinner);
        name=findViewById(R.id.st_name);
        ref1= FirebaseDatabase.getInstance().getReference().child("Users");
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query qry=ref1.orderByChild("hallid").equalTo(hallid.getText().toString());
                qry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot value:dataSnapshot.getChildren())
                        {
                            user=value.getValue(Users.class);
                            student=value.getKey();
                        }

                      name.setText(user.getName());
                       //
                        Messcheck(student);


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

    void Messcheck(String student1)
    {
        ref2=FirebaseDatabase.getInstance().getReference().child("Mess");
        Query qry1=ref2.orderByChild("id").equalTo(student1);
        qry1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    mess=value.getValue(MessManage.class);
                }

                if(mess.isBreakfast())
                {
                    breakfast.setText("Breakfast is ON");
                }
                else
                {
                    breakfast.setText("Breakfast is OFF");
                }

                if(mess.isLunch())
                {
                    lunch.setText("Lunch is ON");
                }
                else
                {
                    lunch.setText("Lunch is OFF");
                }

                if(mess.isDinner())
                {
                    dinner.setText("Dinner is ON");
                }
                else
                {
                    dinner.setText("Dinner is OFF");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
