package com.class_sync.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.class_sync.R;

import java.util.ArrayList;

public class ViewStudentDatabase_RecyclerAdapter extends RecyclerView.Adapter<ViewStudentDatabase_RecyclerAdapter.ViewHolder> {
    ArrayList<ViewStudentDatabase_RecyclerViewModelClass> arrayList;

    RecyclerViewItemClickListener1 recyclerViewItemClickListener;

    public ViewStudentDatabase_RecyclerAdapter(ArrayList<ViewStudentDatabase_RecyclerViewModelClass> arrayList, RecyclerViewItemClickListener1 recyclerViewItemClickListener) {
        this.arrayList = arrayList;
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_student_database_recylerview_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.Class.setText(arrayList.get(position).getS_Class());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,Class;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.ViewStudentDatabase_studentName);
            Class = itemView.findViewById(R.id.ViewStudentDatabase_studentClass);
        }
    }

    public interface RecyclerViewItemClickListener1 {
        public void ItemClick(String Name, String Class);
    }
}
