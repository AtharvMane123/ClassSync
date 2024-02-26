package com.class_sync.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.class_sync.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherClass_Adapter extends RecyclerView.Adapter<TeacherClass_Adapter.ViewHolder> {

    ArrayList<String> arrayList;

    TeacherClass_Adapter.RecyclerViewItemClickListener recyclerViewItemClickListener;

    public TeacherClass_Adapter(ArrayList<String> arrayList, RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.arrayList = arrayList;
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherClass_Adapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_student_database_recylerview_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position));


            holder.imageView.setImageResource(R.drawable.audience);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewItemClickListener.ItemClick(arrayList.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface RecyclerViewItemClickListener {
        void ItemClick(String s, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,Class;
        CircleImageView imageView;
       public ViewHolder(@NonNull View itemView)
       {
           super(itemView);
           name = itemView.findViewById(R.id.ViewStudentDatabase_studentName);
           Class = itemView.findViewById(R.id.ViewStudentDatabase_studentClass);
           imageView = itemView.findViewById(R.id.avatar_image);
       }
    }

}
