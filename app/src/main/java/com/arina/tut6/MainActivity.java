package com.arina.tut6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity  {

    DrawingCanvas dCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // canvas reference
        dCanvas = findViewById(R.id.myCanvas);
        // set path colour to yellow
        dCanvas.pathColour = Color.YELLOW;

        dCanvas.strokeWidth = 10;

        /* ****************************** *
         *            BUTTONS             *
         * ****************************** */

        // clear the scene
        Button clearBtn = findViewById(R.id.ClearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clear clicked", Toast.LENGTH_SHORT).show();

                dCanvas.pathsContainer.clear();
                dCanvas.invalidate();

            }
        });

        // undo
        Button undoBtn = findViewById(R.id.UndoBtn);
        undoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "undo clicked", Toast.LENGTH_SHORT).show();

                dCanvas.pathsContainer.removeFirst();
                dCanvas.invalidate();

            }
        });
        

        // colour picker
        Button colorPickerBtn = findViewById(R.id.ColorPickerBtn);
        colorPickerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "color picker", Toast.LENGTH_SHORT).show();

                openColourPicker();
            }
        });
    }

    public void openColourPicker(){
        // from tutorial ---> https://www.youtube.com/watch?v=7qXwgD45b4w
        // Indonesian for pick a colour dialog
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, dCanvas.pathColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                dCanvas.pathColour = color;
            }
        });
        colorPicker.show();
    }
}