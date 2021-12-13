package dora.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dora.widget.action.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)
        (LineTo(100f, 0f) + LineTo(0f, 100f)).startAnimation(tv, 3000)
        (RotateAction(90f) + RotateAction(180f)).startAnimation(tv, 3000)
        (AlphaAction(0.5f) + AlphaAction(1f)).startAnimation(tv, 3000)
        (ScaleAction(2f, 2f) + ScaleAction(1f, 1f)).startAnimation(tv, 3000)
    }
}