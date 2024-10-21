package com.example.freehanddrawingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paintView = findViewById(R.id.paintView);

        // Set up color buttons
        setupColorButtons();

        // Set up tool buttons
        setupToolButtons();
    }

    private void setupColorButtons() {
        Button colorRed = findViewById(R.id.colorRed);
        Button colorGreen = findViewById(R.id.colorGreen);
        Button colorBlue = findViewById(R.id.colorBlue);
        Button colorYellow = findViewById(R.id.colorYellow);
        Button colorPurple = findViewById(R.id.colorPurple);
        Button colorOrange = findViewById(R.id.colorOrange);
        Button colorCyan = findViewById(R.id.colorCyan);
        Button colorBlack = findViewById(R.id.colorBlack);
        Button colorWhite = findViewById(R.id.colorWhite);
        Button colorGrey = findViewById(R.id.colorGrey);

        // Setting click listeners for all color buttons
        colorRed.setOnClickListener(v -> paintView.setColor(Color.RED));
        colorGreen.setOnClickListener(v -> paintView.setColor(Color.GREEN));
        colorBlue.setOnClickListener(v -> paintView.setColor(Color.BLUE));
        colorYellow.setOnClickListener(v -> paintView.setColor(Color.YELLOW));
        colorPurple.setOnClickListener(v -> paintView.setColor(Color.parseColor("#8E24AA"))); // Purple color
        colorOrange.setOnClickListener(v -> paintView.setColor(Color.parseColor("#FFA500"))); // Orange color
        colorCyan.setOnClickListener(v -> paintView.setColor(Color.CYAN));
        colorBlack.setOnClickListener(v -> paintView.setColor(Color.BLACK));
        colorWhite.setOnClickListener(v -> paintView.setColor(Color.WHITE));
        colorGrey.setOnClickListener(v -> paintView.setColor(Color.GRAY));
    }

    private void setupToolButtons() {
        Button buttonPen = findViewById(R.id.buttonPen);
        Button buttonEraser = findViewById(R.id.buttonEraser);
        Button buttonClear = findViewById(R.id.buttonClear);

        buttonPen.setOnClickListener(v -> paintView.setPenMode());
        buttonEraser.setOnClickListener(v -> paintView.setEraserMode());
        buttonClear.setOnClickListener(v -> paintView.clearCanvas());
    }
}
