package com.ayurbalance.admin.ui.shared

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.admin.data.ActivityLogEntry
import com.ayurbalance.admin.data.LogType

class ActivityLogAdapter : ListAdapter<ActivityLogEntry, ActivityLogAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.findViewById(R.id.tvLogTime)
        val tvBadge: TextView = itemView.findViewById(R.id.tvLogBadge)
        val tvMessage: TextView = itemView.findViewById(R.id.tvLogMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity_log, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = getItem(position)
        holder.tvTime.text = entry.time
        holder.tvBadge.text = entry.type.label

        val (bg, text) = when (entry.type) {
            LogType.FOOD -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
            LogType.CHECKIN -> Pair(0xFFEEEDFE.toInt(), 0xFF534AB7.toInt())
            LogType.AUTH -> Pair(0xFFE6F1FB.toInt(), 0xFF185FA5.toInt())
            LogType.ERROR -> Pair(0xFFFCEBEB.toInt(), 0xFFA32D2D.toInt())
            LogType.WARN -> Pair(0xFFFAEEDA.toInt(), 0xFF854F0B.toInt())
            LogType.INFO -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
        }
        holder.tvBadge.setBackgroundColor(bg)
        holder.tvBadge.setTextColor(text)
        holder.tvMessage.text = entry.message
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<ActivityLogEntry>() {
            override fun areItemsTheSame(a: ActivityLogEntry, b: ActivityLogEntry) = a.time == b.time && a.message == b.message
            override fun areContentsTheSame(a: ActivityLogEntry, b: ActivityLogEntry) = a == b
        }
    }
}
