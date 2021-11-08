package io.vnicius.github.kmmtest.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.vnicius.github.kmmtest.Greeting
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val greeting = Greeting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)


        MainScope().launch {
            val text = StringBuilder()

            greeting.getPokemons()?.forEach {
                text.append(it.name)
                text.append("\n")
            }

            tv.text = text
        }
    }
}
