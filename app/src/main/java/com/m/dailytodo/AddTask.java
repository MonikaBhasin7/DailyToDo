package com.m.dailytodo;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddTask extends AppCompatActivity{

    private boolean isReached = false;
    Boolean check_add_task;
    Spinner spinner;
    Spinner spinner_priority;
    EditText task;
    Boolean choose_category;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task=(EditText)findViewById(R.id.task);
        //task.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
        /*task.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(task.getText().length() == 20 && isReached==false) {
                    task.append("\n");
                    //isReached = true;
                }
                if(task.getText().length() < 10 && isReached)
                {
                    isReached = false;
                }
            //}
        })*/

        List<String> priority_list = new ArrayList<String>();
        priority_list.add("Low");
        priority_list.add("Medium");
        priority_list.add("High");


        List<String> categories = new ArrayList<String>();
        categories= (ArrayList<String>) new DatabaseHelper(this).getCarts();

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner_priority = (Spinner) findViewById(R.id.spinner_priority);




        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter_priority = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priority_list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter_priority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner_priority.setAdapter(dataAdapter_priority);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddTask.this);
                builder1.setMessage("Add Category");
                builder1.setCancelable(true);

                final EditText input = new EditText(AddTask.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder1.setView(input);
                builder1.setPositiveButton(
                        "Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                DatabaseHelper dbs=new DatabaseHelper(getBaseContext());
                                choose_category=dbs.check(input.getText().toString().trim());
                                if(choose_category.equals(true))
                                {
                                    Toast.makeText(getApplicationContext(),"Category Already Exists",Toast.LENGTH_LONG).show();
                                }
                                else if(choose_category.equals(false))
                                {
                                    Boolean check_add=dbs.addToCart(input.getText().toString().trim());
                                    if(check_add.equals(true))
                                    {
                                        Toast.makeText(getApplicationContext(),"Category Added",Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(getApplicationContext(),AddTask.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Error in Adding Category",Toast.LENGTH_LONG).show();
                                    }
                                }
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                builder1.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void add_click(View view)
    {
        DatabaseHelper dbs=new DatabaseHelper(getBaseContext());


        check_add_task = dbs.addToTask(task.getText().toString().trim(),spinner.getSelectedItem().toString().trim(),spinner_priority.getSelectedItem().toString().trim());
        if(check_add_task.equals(true))
        {
            Toast.makeText(getApplicationContext(),"Category Added",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),AddTask.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error in Adding Category",Toast.LENGTH_SHORT).show();
        }

    }
}
