package com.ctyeung.linearregression

import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ctyeung.linearregression.databinding.ActivityMainBinding
import com.ctyeung.linearregression.views.LinearRegression
import com.ctyeung.linearregression.views.MyPaperView
import com.ctyeung.linearregression.views.PaperEvent

class MainActivity : AppCompatActivity(), PaperEvent
{
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mPaper: MyPaperView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mPaper = this.findViewById(R.id.paper)
        mPaper.setListener(this)
    }

    override fun onResume() {
        super.onResume()
        mBinding.btnDeleteLine.setOnClickListener {
            mPaper.clear()
        }
    }

    /*
     * touch finger up
     * - update linear regression
     * - re-render
     */
    override fun onActionUp()
    {
        // calculate least square
        val points:List<PointF> = mPaper.getPoints() ?: ArrayList()

        // regression line
        if(points.size>1) {
            var p0: PointF = points.get(0)
            var p1: PointF = points.get(points.size-1)

            if (points.size > 2) {
                val (a, b) = LinearRegression.findLeastSquare(points,
                    points[0].x,
                    points.get(points.size - 1).x)
                p0 = a;
                p1 = b;
            }

            mPaper.setRegressionLine(p0, p1)
        }
    }
}
