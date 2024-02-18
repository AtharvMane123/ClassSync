package com.class_sync.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.class_sync.R;

import java.util.ArrayList;

public class Notification_RecyclerViewAdapter extends RecyclerView.Adapter<Notification_RecyclerViewAdapter.VieHolder> {

     ArrayList<Notification_ModelClass> arrayList;

    public Notification_RecyclerViewAdapter(ArrayList<Notification_ModelClass> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public VieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imp_notication_recyclerview_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VieHolder holder, int position) {
        holder.NotificationName.setText(arrayList.get(position).getNotificationName());
        holder.NotificationDescription.setText(arrayList.get(position).getNotificationDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VieHolder extends RecyclerView.ViewHolder {
        TextView NotificationName,NotificationDescription;
        public VieHolder(@NonNull View ItemView)
        {
            super(ItemView);
            NotificationName = ItemView.findViewById(R.id.Notification_name);
            NotificationDescription = ItemView.findViewById(R.id.Notification_description);
        }
    }
}
