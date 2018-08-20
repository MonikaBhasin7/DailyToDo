package com.m.dailytodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.m.dailytodo.Model.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName="food_cart.db";
    public static final String TableName1 = "category_details";
    public static final String TableName2 = "task_details";
    private static final int DB_VER=1;
    public static final String CategoryName = "CategoryName";
    public static final String Task = "Task";
    public static final String PriorityName = "PriorityName";


    public DatabaseHelper(Context context) {
        super(context,DatabaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableName1 +" (CategoryName TEXT)");
        db.execSQL("create table " + TableName2 +" (Task TEXT,PriorityName TEXT,CategoryName TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName1);
        db.execSQL("DROP TABLE IF EXISTS "+TableName2);
        onCreate(db);
    }

    public boolean addToTask(String task,String category,String priority)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(Task,task);
        contentValues1.put(PriorityName,priority);
        contentValues1.put(CategoryName,category);
        long result = db1.insert(TableName2,null ,contentValues1);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addToCart(String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryName,category);
        long result = db.insert(TableName1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean check(String category_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName1,null);

        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(CategoryName));

                if(category_name.equals(a))
                {
                    return true;
                }
            }while (res.moveToNext());

        }
        return false;
    }

    /*public void update(String name, String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Quantity,number);
        String whereArgs[] = {name};
        db.update(TableName,contentValues,"ProductName=?", whereArgs);
    }*/
    public List<String> getCarts()
    {
        List<String> result;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName1,null);
        result = new ArrayList<>();


        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(CategoryName));
                result.add(a);

            }while (res.moveToNext());

        }
        return result;
    }
    public List<TaskItem> getTasks(String name_category)
    {
        List<TaskItem> result;

        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("select * from "+TableName2 + " where "+ CategoryName + "=" +name_category,null);
        Cursor res = db.rawQuery("select * from "+TableName2,null);
        result = new ArrayList<>();


        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(Task));
                String b=res.getString(res.getColumnIndex(PriorityName));
                String c=res.getString(res.getColumnIndex(CategoryName));
                if(c.equals(name_category))
                {
                    TaskItem taskItem=new TaskItem(a,b);
                    result.add(taskItem);
                }


            }while (res.moveToNext());

        }
        return result;
    }
}

