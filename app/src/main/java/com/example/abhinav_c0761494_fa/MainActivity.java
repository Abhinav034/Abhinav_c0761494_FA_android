package com.example.abhinav_c0761494_fa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstname , lastname , phone , address;
    Button btn;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstname = findViewById(R.id.firstNameText);
        lastname = findViewById(R.id.lastNameText);
        phone = findViewById(R.id.ptext);
        address = findViewById(R.id.atext);

        btn = findViewById(R.id.button);
        db = new DatabaseHelper(this);




    }

    public void addButtonPressed(View view) {

        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String phonenum = phone.getText().toString();
        String address1 = address.getText().toString();


        if(fname.isEmpty()){
            firstname.setError("Firstname empty");
            firstname.requestFocus();
            return;
         }
        if(lname.isEmpty()){
            lastname.setError("Lastname empty");
            lastname.requestFocus();
            return;
        }
        if(phonenum.isEmpty()){
            phone.setError("Firstname empty");
            phone.requestFocus();
            return;
        }
        if(address1.isEmpty()){
            address.setError("Firstname empty");
            address.requestFocus();
            return;
        }

          boolean inserted =   db.insert(fname , lname , phonenum , address1);
            if(inserted){
                Toast.makeText(MainActivity.this , "Inserted successfully!" , Toast.LENGTH_SHORT).show();

                firstname.setText("");
                lastname.setText("");
                phone.setText("");
                address.setText("");

                firstname.requestFocus();

            }else{

                Toast.makeText(MainActivity.this , "Failed to insert!" , Toast.LENGTH_SHORT).show();
            }



    }

    public void listButtonPressed(View view) {

        Intent intent = new Intent(MainActivity.this , ListActivity.class);

        startActivity(intent);


    }
}
