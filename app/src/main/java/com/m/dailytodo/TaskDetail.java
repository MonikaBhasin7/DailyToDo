package com.m.dailytodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.m.dailytodo.Adapter.CategoryAdapter;
import com.m.dailytodo.Adapter.TaskAdapter;
import com.m.dailytodo.Model.TaskItem;

import java.util.ArrayList;

public class TaskDetail extends AppCompatActivity {

    TaskAdapter mAdapter;
    RecyclerView recyclerView;
    ArrayList<TaskItem> taskList=new ArrayList<>();
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("name");
        data=data.toString().trim();

        DatabaseHelper dbs=new DatabaseHelper(getBaseContext());
        taskList= (ArrayList<TaskItem>) dbs.getTasks(data);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mAdapter = new TaskAdapter(taskList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
