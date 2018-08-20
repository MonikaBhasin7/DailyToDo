package com.m.dailytodo.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m.dailytodo.Model.TaskItem;
import com.m.dailytodo.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>  {


    private List<TaskItem> taskList;
    public TaskAdapter(ArrayList<TaskItem> taskList) {
        this.taskList = taskList;
    }
    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.task_item_layout,parent,false);
        return new TaskAdapter.TaskViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int i) {
        final TaskItem taskItem=taskList.get(i);
        holder.task_name.setText(taskItem.getTask_name());
        holder.priority_name.setText(taskItem.getPriority_name());
        if(taskItem.getPriority_name().equals("Low"))
        {
            holder.ll.setBackgroundColor(Color.rgb(255, 204, 255));
        }
        else if(taskItem.getPriority_name().equals("Medium"))
        {
            holder.ll.setBackgroundColor(Color.rgb(255, 51, 255));
        }
        if(taskItem.getPriority_name().equals("High"))
        {
            holder.ll.setBackgroundColor(Color.rgb(128, 0, 128));
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll;
        TextView task_name;
        TextView priority_name;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            task_name=(TextView)itemView.findViewById(R.id.task_name);
            priority_name=(TextView)itemView.findViewById(R.id.priority_name);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
