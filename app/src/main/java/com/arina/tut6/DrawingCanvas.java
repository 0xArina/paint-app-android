package com.arina.tut6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;

// custom view with drawing logic
public class DrawingCanvas extends View {

    // Paint class holds style and colour info
    // about how to draw geometry, text and bitmaps
    public Paint mPaint;
    // Path class encapsulates multiple contours paths
    // of straight lines, quadratic and cubic curves
    public Path mPath;

    // class variables
    public LinkedList<Paint> paintContainer;
    public LinkedList<Path> pathsContainer;

    // initialize path colour & stroke width variables
    int pathColour = Color.BLUE;
    int strokeWidth = 10;

    // constructor to create custom View class
    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // initialize containers to Linked Lists to hold arrays of lines
        paintContainer = new LinkedList<>();
        pathsContainer = new LinkedList<>();

        // initialize Paint and Paths classes
        mPaint = new Paint();
        mPath = new Path();

    }

    // to update canvas
    @Override
    protected void onDraw(Canvas canvas){


        // check if list is not empty
        if (pathsContainer.size() > 0) {
            // iterate through  the list
            for (int i = 0; i < pathsContainer.size(); ++i) {
                // draw each element
                canvas.drawPath(pathsContainer.get(i), paintContainer.get(i));
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        // get current number of touch points
        int touchCount = event.getPointerCount();

        // if single touch, just draw strokes
        if (touchCount == 1) {

            switch (event.getAction()) {
                                // upon touching the screen
                case MotionEvent.ACTION_DOWN:
                    // paint properties
                    mPaint.setColor(pathColour);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    mPaint.setStrokeWidth(strokeWidth);

                    // add paint and path to the 1st index in the lists,
                    // pushing the rest of the elements down by one
                    pathsContainer.addFirst(mPath);
                    paintContainer.addFirst(mPaint);

                    // start point (X,Y)
                    pathsContainer.getFirst().moveTo(event.getX(), event.getY());
                    break;
                                // upon dragging finger
                case MotionEvent.ACTION_MOVE:
                    // move line to curr (X,Y)
                    pathsContainer.getFirst().lineTo(event.getX(), event.getY());
                    invalidate(); // forces to call onDraw()
                    break;
                                // when you lift your touch
                case MotionEvent.ACTION_UP:
                    mPaint = new Paint();
                    mPath = new Path();


                    // change colour
                    if (pathColour == Color.WHITE) {
                        pathColour = Color.RED;
                    }

                    break;

            }

        // if two fingers down, draw a line between them using curr paint properties
        } else if(touchCount == 2){

            switch(event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    // paint properties
                    mPaint.setColor(pathColour);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    mPaint.setStrokeWidth(strokeWidth);

                    pathsContainer.addFirst(mPath);
                    paintContainer.addFirst(mPaint);

                    // first finger with index 0 and start pos (X,Y)
                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    // second finger with index 1 and line to this point (X,Y)
                    pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    invalidate();

                    break;

                case MotionEvent.ACTION_MOVE:
                    // get new position of 1st finger and move line to the position of 2nd finger
                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    invalidate();

                    break;
            }

        // if three fingers down, draw three lines simultaneously
        } else if(touchCount == 3){

            switch(event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    // paint properties
                    mPaint.setColor(pathColour);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeJoin(Paint.Join.ROUND);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                    mPaint.setStrokeWidth(strokeWidth);

                    pathsContainer.addFirst(mPath);
                    paintContainer.addFirst(mPaint);

                    // first finger down with index 0, start point
                    pathsContainer.getFirst().moveTo(event.getX(0), event.getY(0));
                    // second finger down with index 1, start point
                    pathsContainer.getFirst().moveTo(event.getX(1), event.getY(1));
                    // third finger down with index 2, start point
                    pathsContainer.getFirst().moveTo(event.getX(2), event.getY(2));
                    invalidate();

                    break;
                case MotionEvent.ACTION_MOVE:
                    // create lines from start pos of each finger to curr pos
                    pathsContainer.getFirst().lineTo(event.getX(0), event.getY(0));
                     pathsContainer.getFirst().lineTo(event.getX(1), event.getY(1));
                    pathsContainer.getFirst().lineTo(event.getX(2), event.getY(2));
                    invalidate();

                    break;
            }
        }
        return true;
    }
}
