package io.vnicius.github.kmmtest.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import io.vnicius.github.kmmtest.android.ui.home.HomeScreen
import io.vnicius.github.kmmtest.android.ui.theme.KMMTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KMMTestTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    HomeScreen()
}
