package com.divyacollege.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  String DATABASE_NAME="customer_db";
    private static String TABLE_NAME = "customer_table";
    private static int VERSION = 2;




    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME +"(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int prevVersion, int currentVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertCustomer(Customer customer) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", customer.getName());
        contentValues.put("address", customer.getAddress());

        long rowsInserted = db.insert(TABLE_NAME, null, contentValues);
        if (rowsInserted > 0) {
            return true;
        } else {
            return false;

        }

    }


    public List<Customer> fetchAllData(){
        //Initialize List
        List<Customer> customerList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from "+TABLE_NAME ;
        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            Customer customer = new Customer(name,address);

            customerList.add(customer);
        }
        return customerList;
    }





}



