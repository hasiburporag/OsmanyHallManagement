package com.example.porag.osmanyhallmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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

public class OfficeProfile extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private LinearLayout layout;
    private SkipActivity session;
    private CardView notice,registerStudent,applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_profile);
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
