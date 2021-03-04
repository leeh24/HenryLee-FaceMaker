//@author: Henry Lee

package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

public class FaceView extends SurfaceView {

    Paint hairPaint = new Paint();  // variable for the paint object of the hair
    Paint eyePaint = new Paint();   // variable for the paint object of the eyes
    Paint skinPaint = new Paint();  // variable for the paint object of the skin

    public static final float fx = 1300;    // set the start of the face's x position
    public static final float fy = 550; // set the start of the face's y position
    public static final float fDiameter = 400;  //set the face's diameter

    protected Random gen = new Random(); //for generating random numbers

    private Face myFace;    //variable for myFace model

    /**
     * constructor for FaceView
     * @param context
     * @param attrs
     */
    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        myFace = new Face();

        setWillNotDraw(false);

        // // generate random numbers between 0 to 256 for colors
        myFace.skinColor = Color.rgb(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));
        myFace.hairColor = Color.rgb(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));
        myFace.eyeColor = Color.rgb(gen.nextInt(256),gen.nextInt(256),gen.nextInt(256));

        // random hair style generator
        myFace.hairStyle = gen.nextInt(3);

        // set the background color to white
        setBackgroundColor(Color.WHITE);
    }

    /**
     * onDraw method for face, hair and eyes
     * @param canvas
     */
    public void onDraw(Canvas canvas){
        drawFace(canvas);
        drawHair(canvas);
        drawEyes(canvas);
    }

    /**
     * getter to return face model
     * @return
     */
    public Face getFace(){
        return myFace;
    }

    /**
     * method for drawing the face
     * @param canvas
     */
    protected void drawFace(Canvas canvas){
        skinPaint.setColor(myFace.skinColor);
        skinPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(fx,fy,fDiameter, skinPaint);
    }

    /**
     * method to the draw the hair based on the selected style
     * @param canvas
     */
    protected void drawHair(Canvas canvas){
        // set the color and style for the hair
        hairPaint.setColor(myFace.hairColor);
        hairPaint.setStyle(Paint.Style.FILL);

        // call and draw the different hair styles
        if(myFace.hairStyle == 0) {
            drawHair1(canvas);
        } else if(myFace.hairStyle == 1){
            drawHair2(canvas);
        } else {
            drawHair3(canvas);
        }
    }

    /**
     * method to draw the first hair style
     * @param canvas
     */
    protected void drawHair1(Canvas canvas){
        float r = fDiameter;	//radius of head
        float cx = fx;	//center x coordinate of eye
        float cy = fy - 90;	// center y coordinate of eye
        for(int i = 0; i < 100; i++) {
            double degrees = (i * Math.PI / 100) - Math.PI;
            float hair1x = (float) (cx + r * Math.cos(degrees));
            float hair1y = (float) (cy + r * Math.sin(degrees));
            canvas.drawLine(hair1x, hair1y, hair1x, hair1y+100, hairPaint);
        }

    }

    /**
     * method to draw the second hair style
     * @param canvas
     */
    protected void drawHair2(Canvas canvas){
        float r = fDiameter;	//radius of head
        float cx = fx;	//center x coordinate of head
        float cy = fy + 90;	// center y coordinate of head
        for(int i = 0; i < 100; i++) {
            double degrees = (i * Math.PI / 100) - Math.PI;
            float hair1x = (float) (cx + r * Math.cos(degrees));
            float hair1y = (float) (cy + r * Math.sin(degrees));
            canvas.drawLine(hair1x, hair1y, hair1x, hair1y-100, hairPaint);
        }

    }

    /**
     * method to draw the third hair style
     * @param canvas
     */
    protected void drawHair3(Canvas canvas){
        float r = fDiameter;	//radius of head
        float cx = fx;	//center x coordinate of head
        float cy = fy + 90;	// center y coordinate of head
        for(int i = 0; i < 200; i++) {
            double degrees = (i * Math.PI / 200) - Math.PI;
            float hair1x = (float) (cx + r * Math.cos(degrees));
            float hair1y = (float) (cy + r * Math.sin(degrees));
            canvas.drawLine(hair1x, hair1y, hair1x, hair1y-100, hairPaint);
        }

        for (int i = 0; i < 20; i++){
            canvas.drawLine(cx+r+i, cy-50, cx+r+i, cy+400, hairPaint);
            canvas.drawLine(cx-r+i, cy-50, cx-r+i, cy+400, hairPaint);
        }

    }

    /**
     * method to draw the eyes
     * @param canvas
     */
    protected void drawEyes(Canvas canvas){
        // set eye color and paint eye
        eyePaint.setColor(myFace.eyeColor);
        eyePaint.setStyle(Paint.Style.FILL);
        canvas.drawOval((fx-fDiameter/2), (fy-50), (fx-fDiameter/2 + 100), (fy - 150), eyePaint);
        canvas.drawOval((fx+fDiameter/2), (fy-50), (fx+fDiameter/2 - 100), (fy - 150), eyePaint);
    }
}

