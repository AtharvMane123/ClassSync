package com.class_sync.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.class_sync.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        if(arrayList.get(position).getGender().equals("Male"))
        {
            holder.imageView.setImageResource(R.drawable.avatar);
        }else {
            holder.imageView.setImageResource(R.drawable.female_student);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewItemClickListener.ItemClick(arrayList.get(position).getName(),arrayList.get(position).getS_Class(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,Class;
        CircleImageView imageView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.ViewStudentDatabase_studentName);
            Class = itemView.findViewById(R.id.ViewStudentDatabase_studentClass);
            imageView = itemView.findViewById(R.id.avatar_image);
        }
    }

    public interface RecyclerViewItemClickListener1 {
        public void ItemClick(String Name, String Class,int position);
    }
}
