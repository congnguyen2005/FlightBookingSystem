package com.example.flightbookingsystem.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FlightsBookingSystem.db";
    private static final int DATABASE_VERSION = 1;

    // Table creation query
    private static final String CREATE_USERS_TABLE = "CREATE TABLE Users (" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT UNIQUE NOT NULL, " +
            "password TEXT NOT NULL, " +
            "email TEXT UNIQUE NOT NULL, " +
            "phone TEXT NOT NULL, " +
            "full_name TEXT NOT NULL, " +
            "birth_date DATE, " +
            "gender TEXT CHECK (gender IN ('Nam', 'Nữ', 'Khác')), " +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ");";

    private static final String CREATE_FLIGHTS_TABLE = "CREATE TABLE Flights (" +
            "flight_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "departure TEXT NOT NULL, " +
            "destination TEXT NOT NULL, " +
            "departure_time DATETIME NOT NULL, " +
            "arrival_time DATETIME NOT NULL, " +
            "airline TEXT NOT NULL, " +
            "price REAL NOT NULL" +
            ");";

    private static final String CREATE_BOOKINGS_TABLE = "CREATE TABLE Bookings (" +
            "booking_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER NOT NULL, " +
            "flight_id INTEGER NOT NULL, " +
            "booking_date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "status TEXT CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending', " +
            "total_price REAL NOT NULL, " +
            "FOREIGN KEY (user_id) REFERENCES Users(user_id), " +
            "FOREIGN KEY (flight_id) REFERENCES Flights(flight_id)" +
            ");";

    private static final String CREATE_PASSENGERS_TABLE = "CREATE TABLE Passengers (" +
            "passenger_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "booking_id INTEGER NOT NULL, " +
            "full_name TEXT NOT NULL, " +
            "passport_number TEXT, " +
            "age INTEGER NOT NULL, " +
            "type TEXT CHECK (type IN ('Người lớn', 'Trẻ em', 'Em bé')) NOT NULL, " +
            "FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)" +
            ");";

    private static final String CREATE_PAYMENTS_TABLE = "CREATE TABLE Payments (" +
            "payment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "booking_id INTEGER NOT NULL, " +
            "payment_method TEXT CHECK (payment_method IN ('Credit Card', 'E-Wallet', 'Bank Transfer')) NOT NULL, " +
            "payment_date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "amount REAL NOT NULL, " +
            "FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)" +
            ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables when the database is created
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_FLIGHTS_TABLE);
        db.execSQL(CREATE_BOOKINGS_TABLE);
        db.execSQL(CREATE_PASSENGERS_TABLE);
        db.execSQL(CREATE_PAYMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old tables if database version is upgraded
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Flights");
        db.execSQL("DROP TABLE IF EXISTS Bookings");
        db.execSQL("DROP TABLE IF EXISTS Passengers");
        db.execSQL("DROP TABLE IF EXISTS Payments");
        onCreate(db);
    }


    // Method to authenticate the user during login
    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isValidUser = cursor.getCount() > 0; // If the username and password match
        cursor.close();
        db.close();

        return isValidUser;
    }
}