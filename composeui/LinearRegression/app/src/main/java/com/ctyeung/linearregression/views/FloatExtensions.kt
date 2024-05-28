package com.ctyeung.linearregression.views

import android.graphics.Point
import android.graphics.PointF

fun PointF.slopeFromPoint(p: Point): Float {
    return (p.y - y) / (p.x - x)
}