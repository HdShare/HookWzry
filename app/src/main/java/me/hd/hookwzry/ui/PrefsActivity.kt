package me.hd.hookwzry.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.hd.hookwzry.databinding.ActivityPrefsBinding

class PrefsActivity : AppCompatActivity() {
    private lateinit var activityPrefsBinding: ActivityPrefsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPrefsBinding = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(activityPrefsBinding.root)
    }
}