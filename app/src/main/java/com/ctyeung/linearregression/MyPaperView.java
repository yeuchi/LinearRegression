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
    private final int regressionColor = Color.BLACK;
    private final int dotColor = Color.BLUE;
    private final int tangentColor = Color.GREEN;
    // defines paint and canvas
    private Paint drawPaint;
    private Path path;
    protected List<MyPoint> points = null;
    private PaperEvent mListener;

    public int width;
    public int height;

    private Line mLineRegression;

    public void setRegressionLine(MyPoint p0,
                                  MyPoint p1)
    {
        this.mLineRegression = new Line(p0, p1);
        render();
    }

    public MyPaperView(Context context,
                       AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
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
        drawRegressionLine(canvas);
        drawTouchPoints(canvas);
        drawTangentLines(canvas);
    }

    /*
     * Draw regression line
     */
    private void drawRegressionLine(Canvas canvas)
    {
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setColor(regressionColor);
        canvas.drawPath(path, drawPaint);
    }

    /*
     * Draw touch points
     */
    private void drawTouchPoints(Canvas canvas)
    {
        Paint dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(dotColor);

        for (MyPoint p : points) {
            // highlight point
            canvas.drawCircle((float) p.x, (float) p.y, 5, dotPaint);
        }
    }

    /*
     * Draw tangent lines
     */
    private void drawTangentLines(Canvas canvas)
    {
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(3);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setColor(tangentColor);

        for (MyPoint p : points) {

            Line line = mLineRegression.findNormalLineFrom(p);
            MyPoint pp = mLineRegression.findIntersectionFrom(line);

            if(null!= pp) {
                Path tpath = new Path();
                tpath.moveTo((float) p.x, (float) p.y);
                tpath.lineTo((float) pp.x, (float) pp.y);
                canvas.drawPath(tpath, drawPaint);
            }
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        height= MeasureSpec.getSize(heightMeasureSpec);
        width= MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected boolean render()
    {
        path = new Path();
        if (null==points || points.size()<2)
            return false;

        if(points.size() > 2)
        {
            // render regression line
            path.moveTo((float)mLineRegression.p0.x, (float)mLineRegression.p0.y);
            path.lineTo((float)mLineRegression.p1.x, (float)mLineRegression.p1.y);
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
