package com.example.porag.osmanyhallmanagement;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

public class StudentProfile extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private SkipActivity session;
    ProgressDialog progressDialog;

    CardView Gout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging Out");
        dl = (DrawerLayout) findViewById(R.id.activity_student_profile);
        Gout=findViewById(R.id.gout);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        session=new SkipActivity(this);
        dl.addDrawerListener(t);
        t.syncState();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);

        Gout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(StudentProfile.this)
                                    .setTitle("Going Out")
                                    .setMessage("Stay Outside at Night?")
                                    .setCancelable(false)
                                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                        }
                    }
                });
            }
        });

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.profile:
                        Intent profile=new Intent(getApplicationContext(),Profile.class);
                        startActivity(profile);
                        break;
                    case R.id.notifications:
                        Toast.makeText(StudentProfile.this, "Notifications", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Sign_Out:
                        progressDialog.show();
                        session.clearAll();
                        Intent logout=new Intent(getApplicationContext(),MainActivity.class);
                        progressDialog.dismiss();
                        startActivity(logout);
                        break;
                    case R.id.contact:
                        Toast.makeText(StudentProfile.this, "Contact Us", Toast.LENGTH_SHORT).show();
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
