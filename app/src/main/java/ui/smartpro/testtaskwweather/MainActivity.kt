package ui.smartpro.testtaskwweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ui.smartpro.testtaskwweather.ui.FragmentJava

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,FragmentJava())
                .commit()
        }
    }
}