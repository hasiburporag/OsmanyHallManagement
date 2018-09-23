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
        private TextView name,type,date_time,detail;
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
            viewHolder.name = view.findViewById(R.id.student_name);
            viewHolder.type = view.findViewById(R.id.com_type);
            viewHolder.date_time = view.findViewById(R.id.date_time);
            viewHolder.detail=view.findViewById(R.id.detail_com);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(allStudent.get(pos).getStudent());
        viewHolder.type.setText(allStudent.get(pos).getType());
        viewHolder.detail.setText(allStudent.get(pos).getDetail());
        viewHolder.date_time.setText(allStudent.get(pos).getDatetime());
        return view;
    }
}
