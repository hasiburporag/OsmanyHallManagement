package com.example.porag.osmanyhallmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ItemComplaintAdapter extends BaseAdapter {
    private Context activity;
    private ArrayList <Complaint> allStudent = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public ItemComplaintAdapter(Context activity, ArrayList<Complaint> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        Collections.reverse(this.allStudent);;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView type,date_time,time1;
    }
    private ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public Complaint getItem(int position) {
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
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_complaint,null);
            viewHolder.type = view.findViewById(R.id.com_type);
            viewHolder.date_time = view.findViewById(R.id.date1);
            viewHolder.time1=view.findViewById(R.id.time1);



            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        String[] parts = allStudent.get(pos).getDatetime().split(" ");
        String part1 = parts[0]; // 004
        String part2 = parts[1];

        viewHolder.time1.setText(part2);

        viewHolder.type.setText(allStudent.get(pos).getType());
        viewHolder.date_time.setText(part1);
        return view;
    }
}
