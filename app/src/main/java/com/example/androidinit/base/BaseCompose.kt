package com.example.androidinit.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidinit.view.ui.theme.AndroidInitTheme

class BaseCompose {
    var topBar: @Composable (() -> Unit)? = null
    var content: @Composable (PaddingValues) -> Unit = {}
    var surface: @Composable (() -> Unit)? = null
    var bottomBar: @Composable (() -> Unit)? = null

    @OptIn(ExperimentalMaterial3Api::class)
    val baseScreen: @Composable () -> Unit = {
        AndroidInitTheme {
//            BackHandler(true) {
//                // Back Key 막음.
//            }
            Scaffold(
                topBar = { topBar?.invoke() },
                content = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        content.invoke(it)
                    }
                },
                bottomBar = { bottomBar?.invoke() }
            )
            surface?.invoke()
        }
    }
}

