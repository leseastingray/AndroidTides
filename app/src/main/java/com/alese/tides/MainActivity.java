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

    public static final String DATE = "Date";
    public static final String DAY = "Day";
    public static final String HIGHLOW = "HighLow";
    public static final String TIME = "Time";

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
            map.put(DATE + DAY, (tideItem.getDate() + tideItem.getDay()));
            map.put(HIGHLOW + TIME, (tideItem.getHighlow() + tideItem.getTime()));
            tideListData.add(map);
        }

        // Create SimpleAdapter object
        SimpleAdapter adapter =
                new SimpleAdapter(this, tideListData, R.layout.tides_list_layout,
                        new String[]{DATE + DAY, HIGHLOW + TIME},
                        new int[]{R.id.date, R.id.time});

        // Set up ListView in layout
        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);
    }

    // TODO: Make toast to display tide height in cm
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

        Toast.makeText(this, "Tide Height: " + tideItems.get(i).getPredInCm() + " cm", Toast.LENGTH_SHORT).show();
    }
}
