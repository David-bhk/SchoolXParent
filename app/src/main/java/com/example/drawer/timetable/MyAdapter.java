package com.example.drawer.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

//    public MyAdapter(Fragment ftab1, ArrayList<User> list) {
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.start.setText(user.getStart().toString().trim());
        holder.end.setText(user.getEnd().toString().trim());
        holder.cN.setText(user.getcN().toString().trim());

    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView start, end, cN;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            start = itemView.findViewById(R.id.tvfirstName);
            end = itemView.findViewById(R.id.tvlastName);
            cN = itemView.findViewById(R.id.tvage);
        }
    }
}
