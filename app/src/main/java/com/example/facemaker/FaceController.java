//@author: Henry Lee

package com.example.facemaker;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.util.Random;

public class FaceController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener{

    private FaceView myFaceView;    // FaceView object variable
    private Face myFace;    //Face object variable
    private int redColor = 255; //redColor variable
    private int greenColor = 255;   //greenColor variable
    private int blueColor = 255;    //blueColor variable
    private int currentColor = 0;   //currentColor variable
    private int selectItem; //selectItem variable

    protected Random gen = new Random(); //for generating random numbers

    /**
     * constructor to set faceView
     * @param faceView
     */
    public FaceController(FaceView faceView){
        myFaceView = faceView;
        myFace = myFaceView.getFace();
    }

    /**
     * handle button onClick event
     * @param v
     */
    @Override
    public void onClick(View v) {
        // generate random numbers between 0 to 256 for colors
        myFace.skinColor = Color.rgb(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));
        myFace.hairColor = Color.rgb(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));
        myFace.eyeColor = Color.rgb(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));

        // random hair style generator
        myFace.hairStyle = gen.nextInt(3);

        // invalidate view in order to redraw
        myFaceView.invalidate();
    }

    /**
     * seekbar change handler
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //get the id and set the model color based on number
        if (seekBar.getId() == R.id.RedSeekBar) {
            redColor = progress;
        } else if (seekBar.getId() == R.id.GreenSeekBar) {
            greenColor = progress;
        } else {
            blueColor = progress;
        }

        //convert color from rgb to int
        currentColor = Color.rgb(redColor,greenColor,blueColor);

        // set color to select object
        setSelectedItemColor();

        // invalidate view in order to redraw
        myFaceView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * handler for radio button selection
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d("RadioGroup", "click:"+checkedId);

        //set current selected item
        selectItem = checkedId;

        // set current selected item's color
        setSelectedItemColor();

        // invalidate view in order to redraw
        myFaceView.invalidate();
    }

    /**
     * handler for spinner menu
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Spinner", "Hair Styles:"+position);

        // set hair style based on its drop down selection
        myFace.hairStyle = position;

        // invalidate view in order to redraw
        myFaceView.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * method to set currently selected item and color to the model
     */
    private void setSelectedItemColor() {
        // Get the selected Radio Button
        if (selectItem == R.id.hair) {
            myFace.hairColor = currentColor;
        } else if (selectItem == R.id.eyes){
            myFace.eyeColor = currentColor;
        } else {
            myFace.skinColor = currentColor;
        }
    }
}
