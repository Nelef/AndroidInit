package com.example.androidinit.view.fragment

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.example.androidinit.base.BaseFragment
import com.example.androidinit.view.ui.theme.AndroidInitTheme
import com.example.androidinit.viewModel.MainViewModel

class MainFragment : BaseFragment() {

    companion object {
        private const val WEB_INTERFACE_NAME = "WebInterface"
    }

    private val viewModel: MainViewModel by viewModels()

    init {
        // 웹뷰 디버그 가능하게
        WebView.setWebContentsDebuggingEnabled(true)

        baseCompose.content = {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidInitTheme {
        Greeting("Android")
    }
}