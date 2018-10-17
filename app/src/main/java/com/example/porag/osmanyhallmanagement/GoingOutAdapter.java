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
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class GoingOutAdapter extends BaseAdapter {

    private Context activity;
    private ArrayList<GoingOutHistory> allStudent = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public GoingOutAdapter(Context activity, ArrayList<GoingOutHistory> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        Collections.reverse(this.allStudent);;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView id,location,reason,time,date;
        CircleImageView crc;
    }
    private GoingOutAdapter.ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public GoingOutHistory getItem(int position) {
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
            viewHolder = new GoingOutAdapter.ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_going_out,null);
            viewHolder.id = view.findViewById(R.id.idgoingout);
            viewHolder.crc=view.findViewById(R.id.crcimg);
            viewHolder.location = view.findViewById(R.id.locationgoingout);
          //  viewHolder.reason = view.findViewById(R.id.reasongoingout);
            //viewHolder.time = view.findViewById(R.id.timegoingout);
            //viewHolder.date=view.findViewById(R.id.dategoingout);



            view.setTag(viewHolder);
        }else {
            viewHolder= (GoingOutAdapter.ViewHolder) view.getTag();
        }
        String[] parts = allStudent.get(pos).getTiming().split(" ");
        String part1 = parts[0]; // 004
        String part2 = parts[1];

        viewHolder.id.setText(allStudent.get(pos).getId());
        viewHolder.location.setText(allStudent.get(pos).getLocation());
      //  viewHolder.reason.setText(allStudent.get(pos).getReason());
        SkipActivity session=new SkipActivity(activity);
        StorageReference mImageRef =
                FirebaseStorage.getInstance().getReference("Profile").child(session.getusename()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024 *5;
        mImageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        WindowManager wm = (WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
                        wm.getDefaultDisplay().getMetrics(dm);

                        viewHolder.crc.setMinimumHeight(dm.heightPixels);
                        viewHolder.crc.setMinimumWidth(dm.widthPixels);
                        viewHolder.crc.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


     //   viewHolder.time.setText(part2);

        //viewHolder.type.setText(allStudent.get(pos).getType());
       // viewHolder.date.setText(part1);
        return view;
    }
}
