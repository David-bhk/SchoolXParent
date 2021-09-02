package com.example.drawer.Result;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    Context context;
    List<ResultModel> resultModelList;

    public ResultAdapter(Context context, List<ResultModel> resultModelList) {
        this.context = context;
        this.resultModelList = resultModelList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ResultAdapter.ViewHolder holder, int position) {

            ResultModel resultModel = resultModelList.get(position);
            if (holder.getPosition()==0){
                holder.itemView.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            }
        else if (holder.getPosition()%2!=0)holder.itemView.setVisibility(View.VISIBLE);
        else holder.itemView.setVisibility(View.GONE);

//GETTING THE SubjectName, CourseUnits, Marks.
            holder.SubjectName.setText(resultModel.getSub().toString().trim());
            holder.CourseUnits.setText(resultModel.getCu().toString().trim());
            holder.Marks.setText(resultModel.getMarks().toString().trim());


    }

    @Override
    public int getItemCount() {
        return resultModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SubjectName, CourseUnits, Marks;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //FINDIND SubjectName, CourseUnits, Marks BY IDs
            SubjectName = (itemView).findViewById(R.id.sub_id);
            CourseUnits = (itemView).findViewById(R.id.cu_id);
            Marks = (itemView).findViewById(R.id.marks_id);

        }
    }
}
