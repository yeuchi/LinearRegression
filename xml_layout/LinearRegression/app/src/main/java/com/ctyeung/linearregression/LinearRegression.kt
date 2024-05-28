package com.ctyeung.linearregression


object LinearRegression {
    /*
     * Discrete Least Squares Approximation
     * Numerical Analysis 5th edition by Burden Chapter 8.1 Page 436-442
     */
    open fun findLeastSquare(points: List<MyPoint>,
                        startX: Double,
                        endX: Double):
                        Pair<MyPoint, MyPoint> {


        // find slope
        var m = 0.0
        var b = 0.0
        var sumXY = 0.0
        var sumX = 0.0
        var sumY = 0.0
        var sumX2 = 0.0

        for (p in points) {
            sumXY += p.x * p.y
            sumX += p.x
            sumY += p.y
            sumX2 += p.x * p.x
        }

        m = (points.size * sumXY - sumX * sumY) / (points.size * sumX2 - sumX * sumX)
        b = (sumY - m * sumX) / points.size

        // return regression line end points
        val p0 = MyPoint()
        p0.x = startX
        p0.y = b

        val p1 = MyPoint()
        p1.x = endX
        p1.y = m * endX + b

        return Pair(p0, p1)
    }
}
