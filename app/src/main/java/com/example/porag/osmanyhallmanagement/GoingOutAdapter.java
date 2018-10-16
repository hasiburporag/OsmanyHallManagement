package com.example.porag.osmanyhallmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

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
            viewHolder.location = view.findViewById(R.id.locationgoingout);
            viewHolder.reason = view.findViewById(R.id.reasonegoingout);
            viewHolder.time = view.findViewById(R.id.timegoingout);
            viewHolder.date=view.findViewById(R.id.dategoingout);



            view.setTag(viewHolder);
        }else {
            viewHolder= (GoingOutAdapter.ViewHolder) view.getTag();
        }
        String[] parts = allStudent.get(pos).getTiming().split(" ");
        String part1 = parts[0]; // 004
        String part2 = parts[1];

        viewHolder.id.setText(allStudent.get(pos).getId());
        viewHolder.location.setText(allStudent.get(pos).getLocation());
        viewHolder.reason.setText(allStudent.get(pos).getReason());



        viewHolder.time.setText(part2);

        //viewHolder.type.setText(allStudent.get(pos).getType());
        viewHolder.date.setText(part1);
        return view;
    }
}
