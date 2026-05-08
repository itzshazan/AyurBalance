package com.ayurbalance.admin.ui.system

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.admin.data.ServiceStatus
import com.ayurbalance.admin.data.SystemService

class ServiceAdapter : ListAdapter<SystemService, ServiceAdapter.VH>(DIFF) {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvServiceName)
        val tvStatus: TextView = itemView.findViewById(R.id.tvServiceStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_row, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val service = getItem(position)
        holder.tvName.text = service.name
        when (service.status) {
            ServiceStatus.OPERATIONAL -> {
                holder.tvStatus.text = "Operational"
                holder.tvStatus.setBackgroundColor(0xFFE1F5EE.toInt())
                holder.tvStatus.setTextColor(0xFF0F6E56.toInt())
            }
            ServiceStatus.DEGRADED -> {
                holder.tvStatus.text = "Degraded"
                holder.tvStatus.setBackgroundColor(0xFFFAEEDA.toInt())
                holder.tvStatus.setTextColor(0xFF854F0B.toInt())
            }
            ServiceStatus.DOWN -> {
                holder.tvStatus.text = "Down"
                holder.tvStatus.setBackgroundColor(0xFFFCEBEB.toInt())
                holder.tvStatus.setTextColor(0xFFA32D2D.toInt())
            }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SystemService>() {
            override fun areItemsTheSame(a: SystemService, b: SystemService) = a.name == b.name
            override fun areContentsTheSame(a: SystemService, b: SystemService) = a == b
        }
    }
}
