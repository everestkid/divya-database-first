package com.divyacollege.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mSaveBtn;
    EditText mNameEt;
    EditText mAddressEt;
    ListView mListView;


    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize our database helper class
        dbHelper = new DatabaseHelper(this);

        //Initialize our views
        mNameEt = findViewById(R.id.name_et);
        mAddressEt = findViewById(R.id.address_et);
        mSaveBtn = findViewById(R.id.save_btn);
        mListView = findViewById(R.id.list_view);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameEt.getText().toString();
                String address= mAddressEt.getText().toString();
                Customer customer = new Customer(name,address);

               Boolean success = dbHelper.insertCustomer(customer);
               if (success){
                   Toast.makeText(MainActivity.this, "Succesfully Inserted", Toast.LENGTH_SHORT).show();
                   updateListView();

                   //clear text from input
                   mNameEt.setText("");
                   mAddressEt.setText("");
               }

            }
        });


    }

    public void updateListView(){
        ArrayAdapter<Customer> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,dbHelper.fetchAllData());
        mListView.setAdapter(arrayAdapter);
    }

}