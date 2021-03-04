//@author: Henry Lee

package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // define the hair styles using array
    String[] hairStyles = {"Sticking Upward", "Drooping Down", "Long Hair"};

    /**
     * upon create the view register the event listener to the view object
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the content view and the orientation
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // pass the faceView to the faceController
        FaceView faceView = findViewById(R.id.faceView);
        FaceController faceController = new FaceController(faceView);

        // get the radioGroup view and register event
        RadioGroup radioGroup = findViewById(R.id.faceRadioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(faceController);

        // get the spinner view and register event
        Spinner spin = findViewById(R.id.HairStyle);
        spin.setOnItemSelectedListener(faceController);

        //Creating the ArrayAdapter instance having the hairstyles list
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hairStyles);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(ad);

        // get the red seek bar and register event
        SeekBar redSeekBar = findViewById(R.id.RedSeekBar);
        redSeekBar.setOnSeekBarChangeListener(faceController);

        // get the green seek bar and register event
        SeekBar greenSeekBar = findViewById(R.id.GreenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(faceController);

        // get the blue seek bar and register event
        SeekBar blueSeekBar = findViewById(R.id.BlueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(faceController);

        //get the RandomFace button and register event
        Button randomButton = findViewById(R.id.RandomFace);
        randomButton.setOnClickListener(faceController);
    }
}