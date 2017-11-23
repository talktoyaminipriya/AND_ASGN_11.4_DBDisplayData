package com.example.priya.dbdisplaydata;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UsersManager";

    // users table name
    private static final String TABLE_USERS = "users";

    // users Table Columns names
    private static final String KEY_ID = "id1";
    private static final String KEY_FIRSTNAME = "first_name";
    private static final String KEY_LASTNAME = "last_name";
    public static String TAG = "tag";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"
                + KEY_LASTNAME + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new users
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, user.getFirstName()); // Contact Name
        values.put(KEY_LASTNAME, user.getLastName()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // Getting single users
    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery= "SELECT FROM"+ TABLE_USERS +"WHERE"
                +KEY_ID+"="+id;
        Log.d(TAG,selectQuery);

        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user._id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
        user._first_name = cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME));
        user._last_name = cursor.getString(cursor.getColumnIndex(KEY_LASTNAME));

        // return users
        return user;
    }

    // Getting All users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                // Adding users to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return users list
        return userList;
    }

    // Updating single user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, user.getFirstName());
        values.put(KEY_LASTNAME, user.getLastName());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getID())});
    }

    // Deleting single user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getID())});
        db.close();
    }

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}