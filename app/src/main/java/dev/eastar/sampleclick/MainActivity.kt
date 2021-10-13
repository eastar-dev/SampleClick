package dev.eastar.sampleclick

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<CheckBox>(R.id.focusable).setOnCheckedChangeListener { _, isChecked ->
            findViewById<View>(R.id.blue).isFocusable = isChecked
            update()
        }
        findViewById<CheckBox>(R.id.enable).setOnCheckedChangeListener { _, isChecked ->
            findViewById<View>(R.id.blue).isEnabled = isChecked
            update()
        }
        findViewById<CheckBox>(R.id.clickable).setOnCheckedChangeListener { _, isChecked ->
            findViewById<View>(R.id.blue).isClickable = isChecked
            update()
        }
        findViewById<CheckBox>(R.id.clickListener).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                findViewById<View>(R.id.blue).setOnClickListener(this::onClicked)
            else
                findViewById<View>(R.id.blue).setOnClickListener(null)
            update()
        }
        update()
    }

    fun onClicked(v: View) {
        val name = resources.getResourceEntryName(v.id)
        android.util.Log.e("button", "clicked $name")
        findViewById<TextView>(R.id.log).run {
            text = "clicked $name"
            postDelayed({ text = "" }, 500)
        }
    }

    fun update() {
        val blue = findViewById<View>(R.id.blue)
        findViewById<CheckBox>(R.id.clickListener).isChecked = blue.hasOnClickListeners()
        findViewById<CheckBox>(R.id.focusable).isChecked = blue.isFocusable
        findViewById<CheckBox>(R.id.enable).isChecked = blue.isEnabled
        findViewById<CheckBox>(R.id.clickable).isChecked = blue.isClickable
    }
}