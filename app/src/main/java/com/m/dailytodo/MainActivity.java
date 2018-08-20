
package com.m.dailytodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.m.dailytodo.Adapter.CategoryAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CategoryAdapter mAdapter;
    RecyclerView recyclerView;
    ArrayList<String> categoryList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        categoryList= (ArrayList<String>) new DatabaseHelper(this).getCarts();

        mAdapter = new CategoryAdapter(categoryList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //FoodListItem foodListItem = foodList.get(position);
                String category_name=categoryList.get(position);
                //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),TaskDetail.class);
                Bundle bundle=new Bundle();
                bundle.putString("name",category_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void fab_click(View view)
    {
        Intent intent=new Intent(getApplicationContext(),AddTask.class);
        startActivity(intent);
    }
}
