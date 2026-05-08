package com.ayurbalance.admin.ui.users

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.admin.data.UserRecord

class UserRowAdapter(
    private val onSuspend: (UserRecord) -> Unit,
    private val onView: (UserRecord) -> Unit
) : ListAdapter<UserRecord, UserRowAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvUserName)
        val tvEmail: TextView = itemView.findViewById(R.id.tvUserEmail)
        val tvPrakriti: TextView = itemView.findViewById(R.id.tvUserPrakriti)
        val tvStatus: TextView = itemView.findViewById(R.id.tvUserStatus)
        val tvJoined: TextView = itemView.findViewById(R.id.tvUserJoined)
        val tvLastActive: TextView = itemView.findViewById(R.id.tvUserLastActive)
        val btnView: TextView = itemView.findViewById(R.id.btnUserView)
        val btnAction: TextView = itemView.findViewById(R.id.btnUserAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.tvName.text = user.fullName
        holder.tvEmail.text = user.email
        holder.tvPrakriti.text = user.prakriti ?: "Not done"
        holder.tvJoined.text = user.createdAt
        holder.tvLastActive.text = user.lastActive ?: "—"

        val (statusBg, statusText) = when (user.status) {
            "active" -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
            "inactive" -> Pair(0xFFFAEEDA.toInt(), 0xFF854F0B.toInt())
            "flagged" -> Pair(0xFFFCEBEB.toInt(), 0xFFA32D2D.toInt())
            "new" -> Pair(0xFFE6F1FB.toInt(), 0xFF185FA5.toInt())
            else -> Pair(0xFFF1EFE8.toInt(), 0xFF5F5E5A.toInt())
        }
        holder.tvStatus.setBackgroundColor(statusBg)
        holder.tvStatus.setTextColor(statusText)
        holder.tvStatus.text = user.status.replaceFirstChar { it.uppercase() }

        val (pBg, pText) = when {
            user.prakriti == null -> Pair(0xFFF1EFE8.toInt(), 0xFF5F5E5A.toInt())
            "Pitta" in (user.prakriti) -> Pair(0xFFEEEDFE.toInt(), 0xFF534AB7.toInt())
            "Kapha" in (user.prakriti) -> Pair(0xFFF1EFE8.toInt(), 0xFF5F5E5A.toInt())
            else -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
        }
        holder.tvPrakriti.setBackgroundColor(pBg)
        holder.tvPrakriti.setTextColor(pText)

        holder.btnView.setOnClickListener { onView(user) }

        if (user.status == "flagged") {
            holder.btnAction.text = "Review flag"
            holder.btnAction.setTextColor(Color.parseColor("#A32D2D"))
        } else if (user.status == "active") {
            holder.btnAction.text = "Suspend"
            holder.btnAction.setTextColor(Color.parseColor("#A32D2D"))
        } else {
            holder.btnAction.text = "Re-engage"
            holder.btnAction.setTextColor(Color.parseColor("#854F0B"))
        }
        holder.btnAction.setOnClickListener { onSuspend(user) }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<UserRecord>() {
            override fun areItemsTheSame(a: UserRecord, b: UserRecord) = a.id == b.id
            override fun areContentsTheSame(a: UserRecord, b: UserRecord) = a == b
        }
    }
}
