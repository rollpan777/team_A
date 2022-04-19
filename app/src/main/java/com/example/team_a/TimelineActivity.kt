package com.example.team_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class TimelineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflowmenu,menu)
        return true
    }
}