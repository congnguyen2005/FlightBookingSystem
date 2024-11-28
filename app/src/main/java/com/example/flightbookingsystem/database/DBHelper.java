package com.example.flightbookingsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flightBookingSystem.db";  // Tên cơ sở dữ liệu
    private static final int DATABASE_VERSION = 2;  // Tăng version khi cập nhật DB

    // Bảng người dùng
    private static final String CREATE_TABLE_USERS = "CREATE TABLE Users ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "username TEXT NOT NULL, "
            + "password TEXT NOT NULL, "
            + "fullname TEXT, "
            + "phone TEXT, "
            + "email TEXT, "
            + "birthday TEXT, "
            + "gender TEXT"
            + ");";

    // Bảng chuyến bay
    private static final String CREATE_TABLE_FLIGHTS = "CREATE TABLE Flights ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "flight_number TEXT NOT NULL, "
            + "departure TEXT NOT NULL, "
            + "destination TEXT NOT NULL, "
            + "departure_time DATETIME NOT NULL, "
            + "arrival_time DATETIME NOT NULL, "
            + "price DECIMAL(10, 2) NOT NULL, "
            + "available_seats INTEGER NOT NULL"
            + ");";
    // Bảng vé
    private static final String CREATE_TABLE_TICKETS = "CREATE TABLE Tickets ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "user_id INTEGER NOT NULL, "
            + "flight_id INTEGER NOT NULL, "
            + "ticket_type TEXT NOT NULL, "
            + "quantity INTEGER NOT NULL, "
            + "total_price DECIMAL(10, 2) NOT NULL, "
            + "booking_time DATETIME NOT NULL, "
            + "FOREIGN KEY (user_id) REFERENCES Users(id), "
            + "FOREIGN KEY (flight_id) REFERENCES Flights(id)"
            + ");";

    // Bảng thông báo
    private static final String CREATE_TABLE_NOTIFICATIONS = "CREATE TABLE UserNotifications ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "user_id INTEGER NOT NULL, "
            + "notification TEXT NOT NULL, "
            + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "is_read BOOLEAN DEFAULT FALSE, "
            + "FOREIGN KEY (user_id) REFERENCES Users(id)"
            + ");";

    // Bảng lịch sử tìm kiếm
    private static final String CREATE_TABLE_SEARCH_HISTORY = "CREATE TABLE SearchHistory ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "user_id INTEGER NOT NULL, "
            + "departure VARCHAR(255) NOT NULL, "
            + "destination VARCHAR(255) NOT NULL, "
            + "search_time DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (user_id) REFERENCES Users(id)"
            + ");";

    // Bảng thông tin người dùng
    private static final String CREATE_TABLE_USER_PROFILE = "CREATE TABLE UserProfile ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "user_id INTEGER NOT NULL, "
            + "full_name TEXT NOT NULL, "
            + "passport_number TEXT, "
            + "contact_phone TEXT, "
            + "contact_email TEXT, "
            + "FOREIGN KEY (user_id) REFERENCES Users(id)"
            + ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_FLIGHTS);
        db.execSQL(CREATE_TABLE_TICKETS);
        db.execSQL(CREATE_TABLE_NOTIFICATIONS);
        db.execSQL(CREATE_TABLE_SEARCH_HISTORY);
        db.execSQL(CREATE_TABLE_USER_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Flights");
        db.execSQL("DROP TABLE IF EXISTS Tickets");
        db.execSQL("DROP TABLE IF EXISTS UserNotifications");
        db.execSQL("DROP TABLE IF EXISTS SearchHistory");
        db.execSQL("DROP TABLE IF EXISTS UserProfile");
        onCreate(db);
    }
}
