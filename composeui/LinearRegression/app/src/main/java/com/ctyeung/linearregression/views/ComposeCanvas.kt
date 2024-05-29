package com.ctyeung.linearregression.views

import android.graphics.PointF
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComposeCanvas(viewModel: MainViewModel) {
    var regressionLine: Line? = null
    viewModel.apply {
        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> select(PointF(it.rawX, it.rawY))
                    MotionEvent.ACTION_MOVE -> drag(PointF(it.rawX, it.rawY))
                    MotionEvent.ACTION_UP -> save(PointF(it.rawX, it.rawY))
                    else -> false
                }
                true
            })
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            /**
             * Draw regression line
             */
            regressionLine = null
            val trianglePath = when (points.size) {

                0, 1 -> {
                    Path().let {
                        it.moveTo(this.size.width * .20f, this.size.height * .77f)
                        it.lineTo(this.size.width * .20f, this.size.height * 0.95f)
                        it.lineTo(this.size.width * .37f, this.size.height * 0.86f)
                        it.close()
                        it
                    }
                }

                //1 -> {}

                2 -> {
                    Path().let { path ->
                        path.moveTo(points[0].x, points[0].y)
                        path.lineTo(points[1].x, points[1].y)
                        path
                    }
                }

                else -> {
                    val (a, b) = LinearRegression.findLeastSquare(
                        points,
                        points[0].x,
                        points[points.size - 1].x
                    )
                    regressionLine = Line(a, b)
                    Path().let { path ->
                        path.moveTo(a.x, a.y)
                        path.lineTo(b.x, b.y)
                        path
                    }
                }
            }

            val colors = listOf(Color.Green, Color(0xFF0277fe))
            this.drawPath(
                path = trianglePath,
                Brush.verticalGradient(colors = colors),
                style = Stroke(width = 5f, cap = StrokeCap.Round)
            )

            /**
             * Draw selected points
             */

            points.forEach {
                drawCircle(Color.Blue, radius = 20F, center = Offset(it.x, it.y))
            }

            /**
             * Draw tangent lines
             */

            regressionLine?.let { line ->
                points.forEach { p ->
                    val tangent = line.findNormalLineFrom(p)
                    val pp = line.findIntersectionFrom(tangent)
                    pp?.let {
                        Path().let { path ->
                            path.moveTo(p.x, p.y)
                            path.lineTo(pp.x, pp.y)

                            this.drawPath(
                                path = path,
                                Brush.verticalGradient(colors = listOf(Color.DarkGray, Color.DarkGray)),
                                style = Stroke(width = 5f, cap = StrokeCap.Round)
                            )
                        }
                    }
                }
            }
        }
    }
}

