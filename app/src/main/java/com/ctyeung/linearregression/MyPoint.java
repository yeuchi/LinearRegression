package com.ctyeung.linearregression;

import android.graphics.Point;

public class MyPoint
{
    public double x;
    public double y;

    public MyPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public MyPoint()
    {
        x = 0;
        y = 0;
    }

    protected double slopeFromPoint(Point p)
    {
        double slope = (double)(p.y-y)/(double)(p.x-x);
        return slope;
    }
}
