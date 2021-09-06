package com.example.drawer.Not;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    Context context;
    ArrayList<NotificationModel> notificationModelList;
    private DatabaseReference database;
    FirebaseAuth auth;


    public NotificationAdapter(Context context, ArrayList<NotificationModel> notificationModelList) {
        this.context = context;
        this.notificationModelList = notificationModelList;
        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout, parent, false);

        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationAdapter.viewHolder holder, int position) {

        NotificationModel notificationModel=notificationModelList.get(position);
        Log.d("TAGdfdfdf", notificationModel.getTitle());
        holder.notification_title.setText(notificationModel.title);
        holder.notification_description.setText(notificationModel.content);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications");
                notificationModelList.remove(NotificationAdapter.this.toString());
                notifyDataSetChanged();
                notificationModelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, notificationModelList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView notification_title ,notification_description;
        ImageView imageView;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            notification_title=itemView.findViewById(R.id.notification_title);
            notification_description=itemView.findViewById(R.id.notification_description);
            imageView=itemView.findViewById(R.id.delete);
        }
    }
}
