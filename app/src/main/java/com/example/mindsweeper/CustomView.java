package com.example.mindsweeper;

//imports
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//class definition
public class CustomView extends View{

    //private fields used for rendering the view


    //default constructor for the class CustomView that takes in a context
    public CustomView(Context context) {
        //call the superclass method
        super(context);
        //call refactored init method which shares
        // a lot of the same initialisation code
        init();
    }

    //constructor that takes in a context and also a list of attributes
    //that were set through XML
    public CustomView(Context context, AttributeSet attributeSet){
        //call the superclass method
        super(context, attributeSet);
        //call refactored init method which shares
        // a lot of the same initialisation code
        init();
    }

    // constructor that take in a context, attribute set and also a default
    // style in case the view is to be styled in a certain way
    public CustomView(Context context, AttributeSet attributeSet, int default_style){
        //call the superclass method
        super(context, attributeSet, default_style);
        //call refactored init method which shares
        //a lot of the same initialisation code
        init();
    }

    // refactored init method as most of this code is
    // shared by all the constructors
    // create the paint objects for rendering our rectangles
    private void init(){

    }

    // public method that needs to be overridden to
    // draw the contents of this widget
    public void onDraw(Canvas canvas){
        //call the superclass method
        super.onDraw(canvas);


    }

    // public method that needs to be overridden to
    // handle the touches from a user
    public boolean onTouchEvent(MotionEvent event){


        // if we get to this point they we have not handled the
        // touch ask the system to handle it instead
        return super.onTouchEvent(event);
    }

}
