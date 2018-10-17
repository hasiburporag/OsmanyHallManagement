package com.example.porag.osmanyhallmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuardActivity extends AppCompatActivity {

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
    CardView guest,list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard);
        dl = (DrawerLayout) findViewById(R.id.activity_student_profile1);
        session=new SkipActivity(this);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        context=getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nvg);
        guest=findViewById(R.id.guest);
        list=findViewById(R.id.list);
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
                        Intent profile=new Intent(getApplicationContext(),StuffProfile.class);
                        startActivity(profile);
                        break;

                    case R.id.changepass:
                       // Toast.makeText(getApplicationContext(), "Change", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),ChangePass.class);
                        startActivity(i);
                        break;
                    case R.id.Sign_Out:
                       // progressDialog.show();
                        session.clearAll();
                        Intent logout=new Intent(getApplicationContext(),MainActivity.class);
                       // progressDialog.dismiss();
                        startActivity(logout);
                        break;

                    default:

                }
                return true;
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),GuestRegister.class);
                startActivity(m);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),GuardGoingOut.class);
                startActivity(m);
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
