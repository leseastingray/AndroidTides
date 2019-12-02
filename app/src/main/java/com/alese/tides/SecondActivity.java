package com.alese.tides;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alese.tides.FirstActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    // declarations
    ArrayList<TideItem> tideItems;
    ListView tideListView;

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

        // Get intent from FirstActivity
        String tideLocation = getIntent().getStringExtra(FirstActivity.TIDE_LOCATION);
        String tideDate = getIntent().getStringExtra(FirstActivity.TIDE_DATE);

        // Initialize database
        TideSQLiteHelper tideHelper = new TideSQLiteHelper(this);
        SQLiteDatabase db = tideHelper.getReadableDatabase();

        // Query database for data relevant to selected location and date
        String query = "SELECT * " +
                " FROM " + TideSQLiteHelper.TIDEFORECAST
                + " WHERE " + TideSQLiteHelper.LOCATION + " = '" + tideLocation
                + "' AND " + TideSQLiteHelper.DATE + " = '" + tideDate
                +"';";
        Cursor cursor = db.rawQuery(query, null);

        // TODO: new cursor adaptor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.tides_list_layout,
                cursor,
                new String[]{TideSQLiteHelper.DATE, TideSQLiteHelper.DAY,
                        TideSQLiteHelper.TIME, TideSQLiteHelper.HIGHLOW
                },
                new int[]{
                        R.id.tidesListView
                },
                0 );	// no flags
        // Set up ListView in layout
        tideListView = findViewById(R.id.tidesListView);
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
