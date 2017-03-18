package ru.kulikovman.rgbcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class CanvasView extends View implements ICanvasView {
    private static int width;
    private static int height;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        initPaint();
        gameManager = new GameManager(this, width, height);
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();

    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            gameManager.oneTouchEvent(x, y);
        }

        invalidate();

        return true;
    }
}
