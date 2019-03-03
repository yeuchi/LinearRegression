package com.ctyeung.linearregression;

import android.graphics.Point;

import java.util.Comparator;

public class MyPoint implements Comparable<MyPoint>
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

    @Override
    public int compareTo(MyPoint o) {
        return Comparators.CompareX.compare(this, o);
    }

    public static class Comparators {

        public static Comparator<MyPoint> CompareX = new Comparator<MyPoint>() {
            @Override
            public int compare(MyPoint o1, MyPoint o2) {
                return (int) (o1.x - o2.x);
            }
        };
    }
}
