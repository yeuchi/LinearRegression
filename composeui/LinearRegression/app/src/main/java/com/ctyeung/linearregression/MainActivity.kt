package com.ctyeung.linearregression

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.ctyeung.linearregression.ui.theme.LinearRegressionTheme
import com.ctyeung.linearregression.views.ComposeCanvas
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInvalidated()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.event.collect {
                when (it) {
                    is MainViewEvent.invalidated -> onInvalidated()
                }
            }
        }
    }

    private fun onInvalidated() {
        setContent {
            LinearRegressionTheme {
                // A surface container using the 'background' color from the theme
                ComposeCanvas(viewModel)
            }
        }
    }
}

