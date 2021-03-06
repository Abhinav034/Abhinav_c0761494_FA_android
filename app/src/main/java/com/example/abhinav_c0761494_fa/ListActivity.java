package com.example.abhinav_c0761494_fa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.Person;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    ListView listView;
    List<Persons>persons = new ArrayList<>();
    List<Persons> searchList = new ArrayList<>();

    DatabaseHelper db;
    CustomAdapter customAdapter;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        searchText = findViewById(R.id.searchText);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = s.toString();
                searchList.clear();
                if(!text.isEmpty()){

                    for (Persons person:persons) {
                        if(person.getFname().contains(text)){
                            searchList.add(person);
                        }
                    }
                } else{
                    searchList.addAll(persons);

                }

                customAdapter = new CustomAdapter(ListActivity.this , R.layout.list_layout , searchList , db);
                listView.setAdapter(customAdapter);



            }



            @Override
            public void afterTextChanged(Editable s) {





            }
        });




        Cursor cursor = db.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(ListActivity.this , "No data available", Toast.LENGTH_SHORT).show();
        }else{

            while (cursor.moveToNext()){

                persons.add(new Persons(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));



            }

        }

        customAdapter = new CustomAdapter(this , R.layout.list_layout , persons , db);
        listView.setAdapter(customAdapter);



    }
}
