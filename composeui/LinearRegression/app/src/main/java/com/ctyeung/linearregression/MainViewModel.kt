package com.ctyeung.linearregression

import android.graphics.PointF
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    init {

    }

    fun select(p:PointF) {

    }

    fun drag(p: PointF) {

    }

    fun save(p:PointF) {

    }

    fun render(drawScope: DrawScope) {
        val trianglePath = Path().let {
            it.moveTo(drawScope.size.width * .20f, drawScope.size.height * .77f)
            it.lineTo(drawScope.size.width * .20f, drawScope.size.height * 0.95f)
            it.lineTo(drawScope.size.width * .37f, drawScope.size.height * 0.86f)
            it.close()
            it
        }

        val colors = listOf(Color(0xFF02b8f9), Color(0xFF0277fe))
        drawScope.drawPath(
            path = trianglePath,
            Brush.verticalGradient(colors = colors),
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )
    }
}