package com.alese.tides;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    // declarations
    ArrayList<TideItem> tideItems;
    ArrayList<HashMap<String, String>> tideListData;

    public static final String DATE_LOW_1 = "DateLow1";
    public static final String TIME_LOW_1 = "TimeLow1";
    public static final String DATE_HIGH_1 = "DateHigh1";
    public static final String TIME_HIGH_1 = "TimeHigh1";
    public static final String DATE_LOW_2 = "DateLow2";
    public static final String TIME_LOW_2 = "TimeLow2";
    public static final String DATE_HIGH_2 = "DateHigh2";
    public static final String TIME_HIGH_2 = "TimeHigh2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create Dal (Data access layer) for parsing
        Dal dal = new Dal(this);

        // Parse XMLFile and get tideItems
        tideItems = dal.parseXmlFile("Astoria_2019_tide_predictions.xml");

        // Create ArrayList for HashMap objects
        ArrayList<HashMap<String, String>> tideListData = new ArrayList<HashMap<String, String>>();

        // For each TideItem in tideItems
        for (TideItem tideItem: tideItems)
        {
            // Create each HashMap object, add data and put it in the tideItems List
            HashMap<String, String> map = new HashMap<>();
            map.put(DATE_LOW_1, tideItem.getDate());
            map.put(TIME_LOW_1, tideItem.getTime());
            map.put(DATE_HIGH_1, tideItem.getDate());
            map.put(TIME_HIGH_1, tideItem.getTime());
            map.put(DATE_LOW_2, tideItem.getDate());
            map.put(TIME_LOW_2, tideItem.getTime());
            map.put(DATE_HIGH_2, tideItem.getDate());
            map.put(TIME_HIGH_2, tideItem.getTime());
            tideListData.add(map);
        }

        // Create SimpleAdapter object
        SimpleAdapter adapter =
                new SimpleAdapter(this, tideListData, R.layout.tides_list_layout,
                        new String[]{DATE_LOW_1, TIME_LOW_1, DATE_HIGH_1, TIME_HIGH_1,
                        DATE_LOW_2, TIME_LOW_2, DATE_HIGH_2, TIME_HIGH_2},
                        new int[]{R.id.dateLow1, R.id.timeLow1, R.id.dateHigh1, R.id.timeHigh1,
                        R.id.dateLow2, R.id.timeLow2, R.id.dateHigh2, R.id.timeHigh2});

        // Set up ListView in layout
        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);
    }

    // TODO: Make toast to display tide height in cm
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        //Toast.makeText(this, "Row " + i + " was clicked", Toast.LENGTH_SHORT).show();
    }
}
