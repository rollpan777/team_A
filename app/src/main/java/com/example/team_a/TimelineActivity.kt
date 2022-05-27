package com.example.team_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TimelineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)


        val timelineRecycle = findViewById<RecyclerView>(R.id.timelineRecycle)

        val timelineList = mutableListOf<WhisperRowData>()
        for (i in 1..10) {
            timelineList.add(WhisperRowData("名前", "テキスト"))
        }

        timelineRecycle.layoutManager = LinearLayoutManager(applicationContext)

        val adapter = WhisperAdapter(timelineList)

        timelineRecycle.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflowmenu,menu)
        return true
    }
}