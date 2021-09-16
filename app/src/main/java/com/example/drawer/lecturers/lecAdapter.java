package com.example.drawer.lecturers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.R;
import com.example.drawer.Result.ResultAdapter;
import com.example.drawer.Result.ResultModel;
import com.example.drawer.timetable.MyAdapter;
import com.example.drawer.timetable.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class lecAdapter extends RecyclerView.Adapter<lecAdapter.MyHolderView>{
    Context context;
    List<LecModel> list;


    public lecAdapter(Context context, List<LecModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lecmodel,parent,false);
        return  new MyHolderView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
        LecModel lecModel = list.get(position);
        holder.name.setText(lecModel.getName().toString().trim());
        holder.g.setText(lecModel.getG().toString().trim());
        holder.qual.setText(lecModel.getQual().toString().trim());
        holder.num.setText(lecModel.getNum().toString().trim());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MobNumber = lecModel.getNum();
                String call = "tel:" +MobNumber.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(call));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return (list == null) ? 0 : list.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder{
        TextView name, g, qual,num;
        private Button button;

        public MyHolderView(@NonNull @NotNull View itemView) {
            super(itemView);
            qual = itemView.findViewById(R.id.qual);
            g = itemView.findViewById(R.id.gender);
           name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.num);
            button = itemView.findViewById(R.id.contact);


        }
    }
}
