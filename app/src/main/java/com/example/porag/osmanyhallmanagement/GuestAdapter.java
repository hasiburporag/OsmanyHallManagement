package com.example.porag.osmanyhallmanagement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuestAdapter extends BaseAdapter {
    private Context activity;
    private ArrayList<InsertGuest> allStudent = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public GuestAdapter(Context activity, ArrayList<InsertGuest> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        Collections.reverse(this.allStudent);;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView name,info,phone,type,date,time;
        Button accept,decline;
      //  CircleImageView crc;
    }
    private GuestAdapter.ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public InsertGuest getItem(int position) {
        return allStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final int pos = position;
        if(view == null){
            viewHolder = new GuestAdapter.ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_guest,null);
            viewHolder.name = view.findViewById(R.id.guestname);
            viewHolder.phone=view.findViewById(R.id.guestphn);
            viewHolder.info = view.findViewById(R.id.guestinfo);
            viewHolder.type = view.findViewById(R.id.guesttype);
            viewHolder.accept=view.findViewById(R.id.accept);
            viewHolder.decline=view.findViewById(R.id.decline);
            //  viewHolder.reason = view.findViewById(R.id.reasongoingout);
            //viewHolder.time = view.findViewById(R.id.timegoingout);
            //viewHolder.date=view.findViewById(R.id.dategoingout);



            view.setTag(viewHolder);
        }else {
            viewHolder= (GuestAdapter.ViewHolder) view.getTag();
        }
        String[] parts = allStudent.get(pos).getCurrentDateandTime().split(" ");
        String part1 = parts[0]; // 004
        String part2 = parts[1];

        viewHolder.name.setText(allStudent.get(pos).getName1());
        viewHolder.phone.setText(allStudent.get(pos).getPhone());
         viewHolder.info.setText(allStudent.get(pos).getInfo());
         if(allStudent.get(pos).getCheck()==1)
         {
             viewHolder.accept.setVisibility(View.GONE);
             viewHolder.decline.setVisibility(View.GONE);
         }
         viewHolder.accept.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Guest");
                 InsertGuest gst=new InsertGuest(allStudent.get(pos).gkeyid,allStudent.get(pos).name1,allStudent.get(pos).info,allStudent.get(pos).room,allStudent.get(pos).phone,allStudent.get(pos).guest_type,allStudent.get(pos).currentDateandTime,1,"Accept");
                 dr.child(allStudent.get(pos).name1+allStudent.get(pos).currentDateandTime).setValue(gst);

             }
         });
         viewHolder.decline.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Guest");
                 InsertGuest gst=new InsertGuest(allStudent.get(pos).gkeyid,allStudent.get(pos).name1,allStudent.get(pos).info,allStudent.get(pos).room,allStudent.get(pos).phone,allStudent.get(pos).guest_type,allStudent.get(pos).currentDateandTime,1,"Decline");
                 dr.child(allStudent.get(pos).name1+allStudent.get(pos).currentDateandTime).setValue(gst);

             }
         });



        //   viewHolder.time.setText(part2);

        viewHolder.type.setText(allStudent.get(pos).getGuest_type());
        // viewHolder.date.setText(part1);
        return view;
    }
}
