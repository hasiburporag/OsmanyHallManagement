package com.example.porag.osmanyhallmanagement;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

public class OfficeProfile extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private LinearLayout layout;
    private SkipActivity session;
    private CardView notice,registerStudent,applications,emp_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_profile);
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
         getApplicationContext().startService(new Intent(getApplicationContext(), com.example.porag.osmanyhallmanagement.Notification.class));
        session=new SkipActivity(this);
        layout=findViewById(R.id.parenlayout1);
        dl = (DrawerLayout) findViewById(R.id.activity_office_profile);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv1);
        notice=findViewById(R.id.notice);
        registerStudent=findViewById(R.id.registerStudent);
        applications=findViewById(R.id.applications);
        applications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),OfficeApplications.class);
                startActivity(i);

            }
        });
        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterStudent.class);
                startActivity(i);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),PublishNotice.class);
                startActivity(i);
            }
        });
        emp_reg=findViewById(R.id.emp_reg);
        emp_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.profile:
                       // Intent profile=new Intent(getApplicationContext(),Profile.class);
                       // startActivity(profile);
                        break;
                    case R.id.notifications:
                        Toast.makeText(OfficeProfile.this, "Notifications", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Sign_Out:

                        session.clearAll();
                        Intent logout=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(logout);
                        break;
                    case R.id.contact:
                        Toast.makeText(OfficeProfile.this, "Contact Us", Toast.LENGTH_SHORT).show();
                        break;

                    default:

                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:

                moveTaskToBack(true);

                return true;
        }
        return false;
    }
}
