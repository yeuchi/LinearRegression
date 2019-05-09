package com.ctyeung.linearregression

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.ctyeung.linearregression.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/*
 * Exercise using mix of Kotlin + Java for linear regression + data-binding + drawing line
 */
class MainActivity : AppCompatActivity(), PaperEvent
{
    internal var mBinding: ActivityMainBinding?=null
    internal var mPaper:MyPaperView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding?.listener = this

        mPaper = this.findViewById(R.id.paper)
        mPaper?.setListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
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
        var points:List<MyPoint> = mPaper?.getPoints()?: ArrayList()

        // regression line
        if(points.size>1) {
            var p0: MyPoint = points.get(0)
            var p1: MyPoint = points.get(points.size-1)

            if (points.size > 2) {
                val (a, b) = LinearRegression.findLeastSquare(points,
                                                            points[0].x,
                                                            points.get(points.size - 1).x)
                p0 = a;
                p1 = b;
            }

            mPaper?.setRegressionLine(p0, p1)
        }
    }

    /*
     * clear the screen
     * - clear paperview points and path
     */
    fun onClickButtonClear()
    {
        mPaper?.clear()
    }
}
