package com.example.abhinav_c0761494_fa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<Persons> persons;
    DatabaseHelper db;




    public CustomAdapter(@NonNull Context context, int resource , List<Persons> persons , DatabaseHelper db ) {
        super(context, resource , persons);
        this.context = context;
        this.resource = resource;
        this.persons = persons;
        this.db = db;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource , null);


        TextView fname = view.findViewById(R.id.fnameText);
        TextView lname = view.findViewById(R.id.lnameText);
        TextView phone = view.findViewById(R.id.ptext);
        TextView address = view.findViewById(R.id.atext);


        Button updateButton = view.findViewById(R.id.updateButton);
        Button deleteButton = view.findViewById(R.id.deleteButton);


            final Persons persons1 = persons.get(position);
            fname.setText(persons1.getFname());
            lname.setText(persons1.getLname());
            phone.setText(persons1.getPhone());
            address.setText(persons1.getAddress());

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePerson(persons1);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteData(persons1.getFname());
                            loadEmployees();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
            return view;
        }


    private void updatePerson(final Persons persons) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.update_layout , null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText updatefname = view.findViewById(R.id.updateFname);
        final EditText updatelname = view.findViewById(R.id.updatelname);
        final EditText updatephone = view.findViewById(R.id.updatePhone);
        final EditText updateaddress = view.findViewById(R.id.updateAddress);

        Button updateLayout = view.findViewById(R.id.updateLayoutButton);

        updatefname.setText(persons.getFname());
        updatelname.setText(persons.getLname());
        updatephone.setText(persons.getPhone());
        updateaddress.setText(persons.getAddress());

        updateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newFname = updatefname.getText().toString();
                String newLname = updatelname.getText().toString();
                String newPhone = updatephone.getText().toString();
                String newAdd = updateaddress.getText().toString();


                if (newFname.isEmpty()){

                    updatefname.setError("Firstname empty");
                    updatefname.requestFocus();
                    return;
                }

                if(newLname.isEmpty()){

                    updatelname.setError("Lastname empty");
                    updatelname.requestFocus();
                    return;
                }
                if(newPhone.isEmpty()){

                    updatephone.setError("Lastname empty");
                    updatephone.requestFocus();
                    return;
                }
                if(newAdd.isEmpty()){

                    updateaddress.setError("Lastname empty");
                    updateaddress.requestFocus();
                    return;
                }

                boolean updated = db.updateData(newFname , newLname , newPhone , newAdd , persons.getFname());

                if(updated){
                    Toast.makeText(context , "Updated successfully" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context , "Failed to update" , Toast.LENGTH_SHORT).show();
                }

                alertDialog.dismiss();
                loadEmployees();

            }
        });
    }

    private void loadEmployees() {

        Cursor cursor = db.getData();
        persons.clear();
        while (cursor.moveToNext()){

            persons.add(new Persons(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));

        }
        notifyDataSetChanged();

    }


}































