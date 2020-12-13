package com.example.mindsweeper;

//imports
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//class definition
public class CustomView extends View{
    //public field

    //private fields used for rendering the view
    private String cellString;
    private int cellColor = Color.WHITE;
    private float cellDimension = 0;
    private Drawable cellDrawable;
    private Paint white, blue, p;

    Rect square;
    int rectBounds;


    //default constructor for the class CustomView that takes in a context
    public CustomView(Context context) {
        //call the superclass method
        super(context);
        //call refactored init method which shares
        // a lot of the same initialisation code
        init(null,0);
    }

    //constructor that takes in a context and also a list of attributes
    //that were set through XML
    public CustomView(Context context, AttributeSet attributeSet){
        //call the superclass method
        super(context, attributeSet);
        //call refactored init method which shares
        // a lot of the same initialisation code
        init(attributeSet,0);
    }

    // constructor that take in a context, attribute set and also a default
    // style in case the view is to be styled in a certain way
    public CustomView(Context context, AttributeSet attributeSet, int default_style){
        //call the superclass method
        super(context, attributeSet, default_style);
        //call refactored init method which shares
        //a lot of the same initialisation code
        init(attributeSet,default_style);
    }

    // refactored init method as most of this code is
    // shared by all the constructors
    // create the paint objects for rendering our rectangles
    private void init(AttributeSet attributeSet, int default_style){
        //load shared attributes

        final TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomView,default_style, 0);

        //Read a string
        cellString = getResources().getString(R.string.cell_string);

        //Get a color from color.xml.
        cellColor = getResources().getColor(R.color.colorAccent);

        cellDimension = a.getDimension(
                R.styleable.CustomView_cellDimension,
                cellDimension);

        if (a.hasValue(R.styleable.CustomView_cellDrawable)) {
            cellDrawable = a.getDrawable(
                    R.styleable.CustomView_cellDrawable);
            cellDrawable.setCallback(this);
        }

        a.recycle();
    }

    // public method that needs to be overridden to
    // draw the contents of this widget
    public void onDraw(Canvas canvas){
        //call the superclass method
        super.onDraw(canvas);

        setPadding(10,10,10,10);

        //allocations per draw cycle
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        setBackgroundColor(Color.WHITE);

        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);

        white = new Paint(Paint.ANTI_ALIAS_FLAG);
        white.setColor(0xFFFFFFFF);

        canvas.save();
        canvas.translate(0,0);

        int xorig = 10;
        int yorig = 5;
        int sideLength = (getWidth()/4) - 164;
        rectBounds = sideLength + 1;



        square = new Rect(xorig, yorig, sideLength, sideLength);

        int i;
        int j;

        float x,y;
        x = paddingLeft;
        y = paddingTop;

        //Nested for loops for multiple rows of squares.
        //i is columns (y coord). j is rows (x coord).
        for(i=0; i<=9; i++) {

            //Save the canvas origin onto the stack.
            canvas.save();

            //Draw rows.
            for(j=0; j<=9; j++){
                //Save the current origin.
                canvas.save();

                //Move to origin of this column.
                canvas.translate( (i * rectBounds), (j * rectBounds));

                //Draw a square in this i,j position.
                canvas.drawRect(square, p);
               // canvas.drawCircle((rectBounds/2), (sideLength/2),20, white);
               // canvas.drawText("(" + i * rectBounds + ", " + j * rectBounds + ")", rectBounds/2, rectBounds/2, textPainter);

                //Restore to the starting origin.
                canvas.restore();
            }
            //Restore the canvas to the starting origin. Remember that restores must match saves (stack/LIFO).
            canvas.restore();
        }
        // Draw the example drawable on top of the text.
        if (cellDrawable != null) {
            cellDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            cellDrawable.draw(canvas);
        }

    }

    // public method that needs to be overridden to
    // handle the touches from a user
    public boolean onTouchEvent(MotionEvent event){


        // if we get to this point they we have not handled the
        // touch ask the system to handle it instead
        return super.onTouchEvent(event);
    }

}
