package com.example.team_a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    //オーバーフロー
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflowmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.timeline -> {
                val intent = Intent(this, TimelineActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.whisper -> {
                val intent = Intent(this, TimelineActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.myprofile -> {
                val intent = Intent(this, TimelineActivity::class.java)
                val userid = ""
                intent.putExtra("userid", userid)
                startActivity(intent)
                true
            }
            R.id.profileedit -> {
                val intent = Intent(this, TimelineActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.logout -> {
                val intent = Intent(this, TimelineActivity::class.java)
//                var loginUserId = ""
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}