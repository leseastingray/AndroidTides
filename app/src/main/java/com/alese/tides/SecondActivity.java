package com.alese.tides;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alese.tides.FirstActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    // declarations
    ArrayList<TideItem> tideItems;

    public static final String DATE = "Date";
    public static final String DAY = "Day";
    public static final String HIGHLOW = "HighLow";
    public static final String TIME = "Time";
    public static final String DAY_FORMAT = " ";
    public static final String TIME_FORMAT = ": ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


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
            map.put(DATE + DAY_FORMAT + DAY, (tideItem.getDate() + DAY_FORMAT + tideItem.getDay()));
            map.put(HIGHLOW + TIME_FORMAT + TIME, (tideItem.getHighlow() + TIME_FORMAT + tideItem.getTime()));
            tideListData.add(map);
        }

        // Create SimpleAdapter object
        SimpleAdapter adapter =
                new SimpleAdapter(this, tideListData, R.layout.tides_list_layout,
                        new String[]{DATE + DAY_FORMAT + DAY, HIGHLOW + TIME_FORMAT + TIME},
                        new int[]{R.id.date, R.id.time});

        // Set up ListView in layout
        ListView tideListView = findViewById(R.id.tidesListView);
        tideListView.setAdapter(adapter);
        tideListView.setOnItemClickListener(this);
        tideListView.setFastScrollEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

        Toast.makeText(this, "Tide Height: " + tideItems.get(i).getPredInCm() + " cm", Toast.LENGTH_SHORT).show();
    }
}
