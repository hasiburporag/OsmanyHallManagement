package com.example.porag.osmanyhallmanagement;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PublishNotice extends AppCompatActivity {

    EditText notice;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_notice);
        notice=findViewById(R.id.notice_publish);
        Submit=findViewById(R.id.notice_submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=notice.getText().toString();

                if (text.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Field can't be empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference db=FirebaseDatabase.getInstance().getReference("Notice");

                    String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                    String time=new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                    Notice notice1= new Notice(text,date,time);
                    db.push().setValue(notice1);
                    notice.setText("");
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                }
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
