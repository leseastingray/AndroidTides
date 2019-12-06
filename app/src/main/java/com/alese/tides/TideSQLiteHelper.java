package com.alese.tides;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class TideSQLiteHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "tides.sqlite";
    public static final int DATABASE_VERSION = 1;

    // DB Fields
    public static final String TIDEFORECAST = "tides";
    public static final String DATE = "Date";
    public static final String DAY = "Day";
    public static final String TIME = "Time";
    public static final String HIGHLOW = "Height";
    public static final String LOCATION = "Location";

    // ArrayLists for TideItems
    private ArrayList<TideItem> astoriaTideItems = null;
    private ArrayList<TideItem> florenceTideItems = null;
    private ArrayList<TideItem> southBeachTideItems = null;

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
        db.execSQL("CREATE TABLE " + TIDEFORECAST
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LOCATION + " TEXT,"
                + DATE + " TEXT,"
                + DAY + " TEXT,"
                + TIME + " TEXT,"
                + HIGHLOW + " TEXT"
                + ");");

        // Get the tide data and put it in the new table
        loadDatabaseFromXmlFile(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Stub
    }
    // Get data from xml files
    private void loadDatabaseFromXmlFile(SQLiteDatabase db) {
        Dal dal = new Dal(context);
        // Three different ArrayLists, one for each location
        astoriaTideItems = dal.parseXmlFile("Astoria_2019_tide_predictions.xml");
        florenceTideItems = dal.parseXmlFile("Florence_2019_tide_predictions.xml");
        southBeachTideItems = dal.parseXmlFile("SouthBeach_2019_tide_predictions.xml");

        // Put tide forecasts in the database
        ContentValues cv = new ContentValues();
        // Astoria TideItems
        for (TideItem item : astoriaTideItems)
        {
            cv.put(LOCATION, "Astoria");
            cv.put(DATE, item.getDate());
            cv.put(DAY, item.getDay());
            cv.put(TIME, item.getTime());
            cv.put(HIGHLOW, item.getHighlow());

            db.insert(TIDEFORECAST, null, cv);
        }
        // Florence TideItems
        for (TideItem item : florenceTideItems)
        {
            cv.put(LOCATION, "Florence");
            cv.put(DATE, item.getDate());
            cv.put(DAY, item.getDay());
            cv.put(TIME, item.getTime());
            cv.put(HIGHLOW, item.getHighlow());

            db.insert(TIDEFORECAST, null, cv);
        }
        // South Beach TideItems
        for (TideItem item : southBeachTideItems)
        {
            cv.put(LOCATION, "South Beach");
            cv.put(DATE, item.getDate());
            cv.put(DAY, item.getDay());
            cv.put(TIME, item.getTime());
            cv.put(HIGHLOW, item.getHighlow());

            db.insert(TIDEFORECAST, null, cv);
        }
    }
}
