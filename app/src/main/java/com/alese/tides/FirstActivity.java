package com.alese.tides;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FirstActivity extends AppCompatActivity {

    // Widget Variables
    RadioButton astoriaLocation;
    RadioButton florenceLocation;
    RadioButton southBeachLocation;
    Button showTidesButton;

    // Other Variables
    public String tideLocation = "";

    // Fields
    public static final String TIDE_LOCATION = "tideLocation";
    public static final String TIDE_DATE = "tideDate";
    public static final int REQUEST_1 = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // Set up widgets
        astoriaLocation = (RadioButton) findViewById(R.id.astoriaRadioButton);
        florenceLocation = (RadioButton) findViewById(R.id.florenceRadioButton);
        southBeachLocation = (RadioButton) findViewById(R.id.southBeachRadioButton);
        showTidesButton = (Button) findViewById(R.id.showTidesButton);
    }

    // TODO: Location Radio Group
    public void onLocationSelect(View v)
    {
        // set tideLocation to the location radio button that is checked
        if (astoriaLocation.isChecked())
        {
            tideLocation = "Astoria";
        }
        else if (florenceLocation.isChecked())
        {
            tideLocation = "Florence";
        }
        else if (southBeachLocation.isChecked())
        {
            tideLocation = "South Beach";
        }
    }
    // TODO: Need way to get date selection - EditText?

    // TODO: Finish Show Tides Button
    // Click handler for onShowTides, referenced in layout/activity_first.xml
    // Will incorporate user selections and move to the second activity for list result
    public void onShowTidesButtonClick(View v)
    {
        // Create new intent
        Intent intent = new Intent(this, SecondActivity.class);
        // Put tideLocation and tideDate into intent
        intent.putExtra(TIDE_LOCATION, tideLocation);
        //intent.putExtra(TIDE_DATE, tideDate);
        // Start new activity with intent and data
        startActivityForResult(intent, REQUEST_1);
    }
}
