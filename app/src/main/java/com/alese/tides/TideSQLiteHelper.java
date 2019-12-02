package com.alese.tides;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TideSQLiteHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "tides.sqlite";
    public static final int DATABASE_VERSION = 1;

    // DB Fields
    public static final String TIDEFORECAST = "tide_forecast";
    public static final String TIDEID = "TideID";
    public static final String DATE = "Date";
    public static final String DAY = "Day";
    public static final String TIME = "Time";
    public static final String HEIGHT = "Height";

    private Context context = null;
    // Constructor
    public TideSQLiteHelper(Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = c;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TIDEFORECAST +
                "(" + TIDEID + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DATE + "TEXT,"
                    + DAY + "TEXT,"
                    + TIME + "TEXT,"
                    + HEIGHT + "TEXT"
                    + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
