package com.example.priya.dbdisplaydata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    DatabaseHandler db = new DatabaseHandler(this);
    TextView text;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.tv);


        // DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting users
        Log.d("Insert: ", "Inserting ..");
        db.addUser(new User("X", "X"));
        db.addUser(new User("Y", "Y"));
        db.addUser(new User("Z", "Z"));
        db.addUser(new User("W", "W"));

        // Reading all users
        Log.d("Reading: ", "Reading all users..");
        List<User> users = db.getAllUsers();
        text.setText("");

        for (User cn : users) {
            String log = "Id: " + cn.getID() + " ,FirstName: " + cn.getFirstName() + " ,LastName: " + cn.getLastName() +"\n";
            text.append(log);
            // Writing users to log
            Log.d("Name: ", log);
        }
    }

}
