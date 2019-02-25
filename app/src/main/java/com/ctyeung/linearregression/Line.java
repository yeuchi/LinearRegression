package com.ctyeung.linearregression;

public class Line {

    protected MyPoint p0;
    protected MyPoint p1;

    /*
     * default constructor
     */
    public Line(MyPoint p0, MyPoint p1)
    {
        this.p0 = p0;
        this.p1 = p1;
    }

    /*
     * construct from 1 point and slope
     */
    public Line(MyPoint p0, double slope)
    {
        this.p0 = p0;

        // horionzontal line
        if(0==slope)
        {
            this.p1 = new MyPoint(p0.x + 5, p0.y);
        }
        else
        {
            // default as yIntercept
            double y = p0.y - (slope * p0.x);
            this.p1 = new MyPoint(0, y);
        }
    }

    /*
     * Find tangent line input point
     */
    public Line findNormalLineFrom(MyPoint p)
    {
        // if vertical line
        if(isVertical())
        {
            return new Line(p, new MyPoint(p0.x, p.y));
        }

        // if horizontal line
        else if(isHorizontal())
        {
            return new Line(p, new MyPoint(p.x, p0.y));
        }
        // tangent line
        else
        {
            // slope is inverse of this line
            return new Line(p, -1/getSlope());
        }
    }

    /*
     * Find intersection point from a given line
     */
    public MyPoint findIntersectionFrom(Line line)
    {
        // parallel lines
        if((this.isVertical() && line.isVertical()) ||
            (this.isHorizontal() && line.isHorizontal()))
            return null;

        if(isVertical())
        {
            double x = this.p0.x;
            double y = line.findY(x);
            return new MyPoint(x,y);
        }
        else if (isHorizontal())
        {
            double y = this.p0.y;
            double x = line.findX(y);
            return new MyPoint(x,y);
        }
        else {
            double m = this.getSlope();
            double b = this.getYIntercept();
            double x = (b - line.getYIntercept()) / (m - line.getSlope());
            double y = findY(x);
            return new MyPoint(x, y);
        }
    }

    public double findY(double x)
    {
        if(isVertical())
        {
            return Double.NaN;
        }
        else if(isHorizontal())
        {
            return p0.y;
        }
        else
        {
            // y = mx + b
            double y = getSlope() * x + getYIntercept();
            return y;
        }
    }

    public double findX(double y)
    {
        if(isVertical())
        {
            return Double.NaN;
        }
        else if (isHorizontal())
        {
            return p0.x;
        }
        else {
            double x = (y - getYIntercept()) / getSlope();
            return x;
        }
    }

    /*
     * Find slope of this line
     * return: Double.NAN if vertical
     */
    public double getSlope()
    {
        if(isVertical())
        {
            return Double.NaN;
        }
        return (p1.y - p0.y)/(p1.x-p0.x);
    }

    /*
     * Find Y-Intercept or b
     * - x point where y=0
     *
     * return: x if line is not vertical or horizontal
     */
    public double getYIntercept()
    {
        if(isHorizontal())
        {
            return Double.NaN;
        }
        else
        {
            double m = getSlope();
            if(m==Double.NaN)
                return Double.NaN;

            double x = p1.x - p1.y / m;
            return x;
        }
    }

    /*
     * Is this a vertical line ?
     */
    public boolean isVertical()
    {
        return (p0.x == p1.x)? true:false;
    }

    /*
     * Is this a horizontal line ?
     */
    public boolean isHorizontal()
    {
        return (p0.y == p1.y)?true:false;
    }
}
