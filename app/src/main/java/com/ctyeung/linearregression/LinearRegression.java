package com.ctyeung.linearregression;

import android.util.Pair;

import java.util.List;

public class LinearRegression
{
    /*
     * Discrete Least Squares Approximation
     * Numerical Analysis 5th edition by Burden Chapter 8.1 Page 436-442
     */
    public static Pair<MyPoint, MyPoint> findLeastSquare(List<MyPoint> points,
                                                        double startX,
                                                        double endX)
    {
        if(null==points || points.size() < 3) {
            return null;
        }

        // find slope
        double m=0;
        double b=0;
        double sumXY=0;
        double sumX=0;
        double sumY=0;
        double sumX2=0;

        for(MyPoint p : points) {
            sumXY += p.x * p.y;
            sumX += p.x;
            sumY += p.y;
            sumX2 += p.x * p.x;
        }

        m = (points.size() * sumXY - sumX * sumY) / (points.size() *sumX2 - sumX*sumX);
        b = (sumY - m*sumX) / points.size();

        // return regression line end points
        MyPoint p0 = new MyPoint();
        p0.x = startX;
        p0.y = b;

        MyPoint p1 = new MyPoint();
        p1.x = endX;
        p1.y = m*endX+b;

        Pair<MyPoint, MyPoint> pair = new Pair(p0, p1);
        return pair;
    }
}
