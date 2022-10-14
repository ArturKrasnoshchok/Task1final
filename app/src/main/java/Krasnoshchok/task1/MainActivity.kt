package Krasnoshchok.task1

import Krasnoshchok.task1.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var bindibg: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindibg = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindibg.root)
    }

}