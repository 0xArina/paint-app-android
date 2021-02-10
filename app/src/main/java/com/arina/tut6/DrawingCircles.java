package com.arina.tut6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DrawingCircles extends View {
    // Paint class holds style and colour info
    // about how to draw geometry, text and bitmaps
    public Paint mPaint;
    // Path class encapsulates multiple contours paths
    // of straight lines, quadratic and cubic curves
    public Path mPath;

    // class variables
    public LinkedList<Paint> paintContainer;
    public LinkedList<Path> pathsContainer;

    // constructor to create custom View class
    public DrawingCircles(Context context, @Nullable AttributeSet attrs) {
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


    }
}
