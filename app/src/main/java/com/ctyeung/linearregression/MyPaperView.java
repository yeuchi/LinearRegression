package com.ctyeung.linearregression;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/*
 * view to render points and lines
 * - listens to touch actions (callback)
 */
public class MyPaperView extends View
{
    private final int strokeColor = Color.BLACK;
    private final int fillColor = Color.BLUE;
    // defines paint and canvas
    private Paint drawPaint;
    private Path path;
    protected List<MyPoint> points = null;
    private PaperEvent mListener;

    public int width;
    public int height;

    public MyPaperView(Context context,
                       AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
       // setupPaint();
        path = new Path();
        points = new ArrayList<MyPoint>();
    }

    public void setListener(PaperEvent listener)
    {
        mListener = listener;
    }

    public List<MyPoint> getPoints()
    {
        return points;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // stroke style
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setColor(strokeColor);
        canvas.drawPath(path, drawPaint);

        // fill style
        Paint dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(fillColor);

        for (MyPoint p : points)
            canvas.drawCircle((float)p.x, (float)p.y, 5, dotPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        height= MeasureSpec.getSize(heightMeasureSpec);
        width= MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public boolean render(MyPoint p0,
                          MyPoint p1)
    {
        path = new Path();
        if (null==points || points.size()<2)
            return false;

        if(points.size() > 2)
        {
            // render regression line
            path.moveTo((float)p0.x, (float)p0.y);
            path.lineTo((float)p1.x, (float)p1.y);
        }

        postInvalidate(); // Indicate view should be redrawn
        return true;
    }

    public void clear()
    {
        points.clear();
        path = new Path();
        postInvalidate(); // Indicate view should be redrawn
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        MyPoint point = new MyPoint(event.getX(), event.getY());

        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Starts a new line in the path
                break;

            /*case MotionEvent.ACTION_MOVE:
                path.lineTo(point.x, point.y);
                break;*/

            case MotionEvent.ACTION_UP:
                points.add(point);
                mListener.onActionUp();
                break;

            default:
                return false;
        }

        return true;
    }
}
