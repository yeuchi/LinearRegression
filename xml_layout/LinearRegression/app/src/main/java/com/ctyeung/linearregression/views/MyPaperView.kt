package com.ctyeung.linearregression.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/*
 * view to render points and lines
 * - listens to touch actions (callback)
 */
class MyPaperView(  context: Context,
                    attrs: AttributeSet) :
    View(context, attrs) {

    val regressionColor = Color.BLACK
    val dotColor = Color.BLUE
    val tangentColor = Color.GREEN

    // defines paint and canvas
    var drawPaint: Paint? = null
    var path: Path? = null
    var listPoints: MutableList<PointF>? = null
    var mListener: PaperEvent? = null

    private var mLineRegression: Line? = null

    fun setRegressionLine(
        p0: PointF,
        p1: PointF
    ) {
        this.mLineRegression = Line(p0, p1)
        render()
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        path = Path()
        listPoints = ArrayList()
    }

    fun setListener(listener: PaperEvent) {
        mListener = listener
    }

    fun getPoints(): List<PointF> {
        val points:List<PointF> = listPoints!!.sortedWith(Comparators.CompareX)
        return points;
    }

    override fun onDraw(canvas: Canvas) {
        drawRegressionLine(canvas)
        drawTouchPoints(canvas)
        drawTangentLines(canvas)
    }

    /*
     * Draw regression line
     */
    private fun drawRegressionLine(canvas: Canvas) {
        drawPaint = Paint()
        drawPaint!!.isAntiAlias = true
        drawPaint!!.strokeWidth = 5f
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        drawPaint!!.color = regressionColor
        canvas.drawPath(path!!, drawPaint!!)
    }

    /*
     * Draw touch points
     */
    private fun drawTouchPoints(canvas: Canvas) {
        val dotPaint = Paint()
        dotPaint.style = Paint.Style.FILL
        dotPaint.color = dotColor

        for (p in listPoints!!) {
            // highlight point
            canvas.drawCircle(p.x.toFloat(), p.y.toFloat(), 5f, dotPaint)
        }
    }

    /*
     * Draw tangent lines
     */
    private fun drawTangentLines(canvas: Canvas) {
        drawPaint = Paint()
        drawPaint!!.isAntiAlias = true
        drawPaint!!.strokeWidth = 3f
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        drawPaint!!.color = tangentColor

        for (p in listPoints!!) {

            val line = mLineRegression!!.findNormalLineFrom(p)
            val pp = mLineRegression!!.findIntersectionFrom(line)

            if (null != pp) {
                val tpath = Path()
                tpath.moveTo(p.x.toFloat(), p.y.toFloat())
                tpath.lineTo(pp.x.toFloat(), pp.y.toFloat())
                canvas.drawPath(tpath, drawPaint!!)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //height = View.MeasureSpec.getSize(heightMeasureSpec)
        //width = View.MeasureSpec.getSize(widthMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    protected fun render(): Boolean {
        path = Path()
        if (null == listPoints || listPoints!!.size < 2)
            return false

        if (listPoints!!.size > 2) {
            // render regression line
            path!!.moveTo(mLineRegression!!.p0.x.toFloat(), mLineRegression!!.p0.y.toFloat())
            path!!.lineTo(mLineRegression!!.p1.x.toFloat(), mLineRegression!!.p1.y.toFloat())
        }

        postInvalidate() // Indicate view should be redrawn
        return true
    }

    fun clear() {
        listPoints!!.clear()
        path = Path()
        postInvalidate() // Indicate view should be redrawn
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val point = PointF(event.x, event.y)

        // Checks for the event that occurs
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
            }

            /*case MotionEvent.ACTION_MOVE:
                path.lineTo(point.x, point.y);
                break;*/

            MotionEvent.ACTION_UP -> {
                listPoints!!.add(point)
                mListener!!.onActionUp()
            }

            else -> return false
        }// Starts a new line in the path

        return true
    }
}
