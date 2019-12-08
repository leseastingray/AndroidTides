package com.alese.tides;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class FirstActivity extends AppCompatActivity
        implements TextView.OnEditorActionListener {

    // Widget Variables
    RadioButton astoriaLocation;
    RadioButton florenceLocation;
    RadioButton southBeachLocation;
    EditText dateEditText;
    Button showTidesButton;

    // Other Variables
    public String tideLocation;
    public String tideDate;

    // Fields
    public static final String LOCATION = "tideLocation";
    public static final String DATE = "tideDate";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // Set up widgets
        astoriaLocation = (RadioButton) findViewById(R.id.astoriaRadioButton);
        florenceLocation = (RadioButton) findViewById(R.id.florenceRadioButton);
        southBeachLocation = (RadioButton) findViewById(R.id.southBeachRadioButton);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        dateEditText.setOnEditorActionListener(this);
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
        //Toast.makeText(this, tideLocation, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
    {
        if(i == EditorInfo.IME_ACTION_DONE ||
                i == EditorInfo.IME_ACTION_UNSPECIFIED ||
                i == EditorInfo.IME_ACTION_NEXT ||
                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)
        {
            showTidesButton.hasFocus();
        }
        // Hide the soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
        return false;
    }

    // Click handler for onShowTides, referenced in layout/activity_first.xml
    // Will incorporate user selections and move to the second activity for list result
    public void onShowTidesButtonClick(View v)
    {
        // Get data from dateEditText
        String draftTideDate = dateEditText.getText().toString();
        String month = draftTideDate.substring(0,2);
        String day = draftTideDate.substring(3,5);
        String year = draftTideDate.substring(6,10);
        tideDate = year + "/" + month + "/" + day;

        // Create new intent
        Intent intent = new Intent(this, SecondActivity.class);
        // Put tideLocation and tideDate into intent
        intent.putExtra(LOCATION, tideLocation);
        intent.putExtra(DATE, tideDate);
        // Start new activity with intent and data
        startActivity(intent);
    }
}
