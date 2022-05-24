package com.example.team_a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WhisperAdapter(private val dataSet: MutableList<WhisperRowData>) : RecyclerView.Adapter<WhisperAdapter.ViewHolder>() {

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item) {
        val userImage : ImageView
        val userNameText : TextView
        val whisperText : TextView
        val goodImage : ImageView

        init {
            userImage = item.findViewById(R.id.userImage)
            userNameText = item.findViewById(R.id.userNameText)
            whisperText = item.findViewById(R.id.whisperText)
            goodImage = item.findViewById(R.id.goodImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhisperAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WhisperAdapter.ViewHolder, position: Int) {
        /*
        holder.userNameText.text = dataSet[position].userNameText.toString()

        holder.userImage.setOnClickListener {

        }

        holder.goodImage.setOnClickListener {

        }
         */
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}