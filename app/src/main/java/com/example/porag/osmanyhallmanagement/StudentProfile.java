package com.example.porag.osmanyhallmanagement;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentProfile extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private SkipActivity session;
    private Context context;
    private PopupWindow pw;
    private LinearLayout layout;
    Button submit,close;
    EditText reason,where1;
    ProgressDialog progressDialog;
    DatabaseReference ref;
    TextView test;

    CardView Gout,mess,payment,complaint,guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        FirebaseMessaging.getInstance().subscribeToTopic("Notices");
        getApplicationContext().startService(new Intent(getApplicationContext(), com.example.porag.osmanyhallmanagement.Notification.class));

        // getApplicationContext().startService(new Intent(getApplicationContext(),NoticeService.class));
        Gout=findViewById(R.id.gout);
        guest=findViewById(R.id.guestapprv);

        mess=findViewById(R.id.mess); progressDialog=new ProgressDialog(this);
        layout=findViewById(R.id.parenlayout);

        progressDialog.setMessage("Logging Out");
        dl = (DrawerLayout) findViewById(R.id.activity_student_profile);
        payment=findViewById(R.id.billing);
        complaint=findViewById(R.id.report);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        session=new SkipActivity(this);
        dl.addDrawerListener(t);
        t.syncState();
        context=getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),MessStudent.class);
                startActivity(m);
            }
        });

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),StudentReport.class);
                startActivity(m);
            }
        });


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),BillPayStudent.class);
                startActivity(m);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),GuestApprove.class);
                startActivity(i);

            }
        });

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

                                LayoutInflater inf= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                                View Custom=inf.inflate(R.layout.pop_up_window_1,null);
                                    pw = new PopupWindow(
                                            Custom,
                                            LinearLayout.LayoutParams.WRAP_CONTENT,
                                            LinearLayout.LayoutParams.WRAP_CONTENT
                                    );

                                    if(Build.VERSION.SDK_INT>=21){
                                        pw.setElevation(5.0f);
                                    }
                                    pw.showAtLocation(layout, Gravity.CENTER,0,0);
                                    pw.setFocusable(true);
                                    pw.update();
                                    submit=Custom.findViewById(R.id.submit_reason);
                                    close=Custom.findViewById(R.id.close);
                                    reason=Custom.findViewById(R.id.input_reason);
                                    where1=Custom.findViewById(R.id.input_location);
                                    ref= FirebaseDatabase.getInstance().getReference("GoingOutHistory");

                                    submit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            String karon=reason.getText().toString();
                                            String kothai=where1.getText().toString();
                                            String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                            GoingOutHistory New=new GoingOutHistory(karon,kothai,currentDateandTime,session.getusename());
                                            ref.child(session.getusename()+currentDateandTime).setValue(New);
                                            pw.dismiss();
                                            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            pw.dismiss();
                                        }
                                    });





                                }
                            }).show();
                        }
                    }
                });
            }
        });

        //nv.getHeaderView()
        View header=nv.getHeaderView(0);
        final CircleImageView imageView=header.findViewById(R.id.header_pic);
        StorageReference mImageRef =
                FirebaseStorage.getInstance().getReference("Profile").child(session.getusename()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024 *5;
        mImageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        imageView.setMinimumHeight(dm.heightPixels);
                        imageView.setMinimumWidth(dm.widthPixels);
                        imageView.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
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

                    case R.id.changepass:
                        Intent i=new Intent(getApplicationContext(),ChangePass.class);
                        startActivity(i);
                        Toast.makeText(StudentProfile.this, "Change", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Sign_Out:
                        progressDialog.show();
                        session.clearAll();
                        Intent logout=new Intent(getApplicationContext(),MainActivity.class);
                        progressDialog.dismiss();
                        startActivity(logout);
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
