package com.example.priya.dbdisplaydata;

/**
 * Created by Priya on 11/19/2017.
 */

public class User{

    //private variables
    int _id;
    String _first_name;
    String _last_name;

    // Empty constructor
    public User(){

    }
    // constructor
    public User(int id, String name, String _last_name){
        this._id = id;
        this._first_name = _first_name;
        this._last_name = _last_name;
    }

    // constructor
    public User(String _first_name, String _last_name){
        this._first_name = _first_name;
        this._last_name = _last_name;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting first name
    public String getFirstName(){
        return this._first_name;
    }

    // setting first name
    public void setFirstName(String first_name){
        this._first_name = first_name;
    }

    // getting Last name
    public String getLastName(){
        return this._last_name;
    }

    // setting last name
    public void setLastName(String last_name){
        this._last_name = last_name;
    }
}