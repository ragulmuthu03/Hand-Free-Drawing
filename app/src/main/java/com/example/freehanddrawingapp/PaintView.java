package com.example.freehanddrawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;


public class PaintView extends View {

    private Paint paint;
    private Path path;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private boolean isEraserMode = false; // Variable to track eraser mode

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public PaintView(Context context) {
        super(context);
        setup();
    }

    private void setup() {
        paint = new Paint();
        path = new Path();
        paint.setColor(Color.BLACK); // Default paint color
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10); // Default stroke width
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Create a bitmap to hold the drawing
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawPath(path, paint); // Draw the current path on the canvas
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y); // Start a new path
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y); // Draw a line to the current position
                break;
            case MotionEvent.ACTION_UP:
                if (isEraserMode) {
                    // If in eraser mode, clear the path on the bitmap using the background color
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    bitmapCanvas.drawPath(path, paint); // Draw the eraser path on the bitmap
                } else {
                    bitmapCanvas.drawPath(path, paint); // Draw the path on the bitmap
                }
                path.reset(); // Clear the path for the next stroke
                break;
            default:
                return false;
        }

        invalidate(); // Request a redraw
        return true;
    }

    public void clearCanvas() {
        path.reset(); // Reset the path
        bitmap.eraseColor(Color.WHITE); // Clear the bitmap to white
        invalidate(); // Request a redraw
    }

    public void setColor(int color) {
        paint.setColor(color); // Change the paint color
        isEraserMode = false; // Ensure we're not in eraser mode
    }

    public void setBrushSize(float size) {
        paint.setStrokeWidth(size); // Change the brush size
    }

    // Method to switch to pen mode
    public void setPenMode() {
        isEraserMode = false;
        paint.setXfermode(null);
    }

    // Method to switch to eraser mode
    public void setEraserMode() {
        isEraserMode = true;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }
}
