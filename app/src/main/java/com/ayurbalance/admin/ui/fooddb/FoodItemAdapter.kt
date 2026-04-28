package com.ayurbalance.admin.ui.fooddb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.admin.data.FoodItem

class FoodItemAdapter(
    private val onEdit: (FoodItem) -> Unit
) : ListAdapter<FoodItem, FoodItemAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvSource: TextView = itemView.findViewById(R.id.tvFoodSource)
        val tvCalories: TextView = itemView.findViewById(R.id.tvFoodCalories)
        val tvDosha: TextView = itemView.findViewById(R.id.tvFoodDosha)
        val tvStatus: TextView = itemView.findViewById(R.id.tvFoodStatus)
        val btnAction: TextView = itemView.findViewById(R.id.btnFoodAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvCalories.text = "${item.calories} kcal/100g"
        holder.tvDosha.text = item.doshaImpact ?: "—"

        val (srcBg, srcText) = when (item.source) {
            "ICMR" -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
            "USDA" -> Pair(0xFFE6F1FB.toInt(), 0xFF185FA5.toInt())
            else -> Pair(0xFFEEEDFE.toInt(), 0xFF534AB7.toInt())
        }
        holder.tvSource.setBackgroundColor(srcBg)
        holder.tvSource.setTextColor(srcText)
        holder.tvSource.text = item.source

        val (stBg, stText) = when (item.status) {
            "verified" -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
            "pending" -> Pair(0xFFFAEEDA.toInt(), 0xFF854F0B.toInt())
            "flagged" -> Pair(0xFFFCEBEB.toInt(), 0xFFA32D2D.toInt())
            else -> Pair(0xFFF1EFE8.toInt(), 0xFF5F5E5A.toInt())
        }
        holder.tvStatus.setBackgroundColor(stBg)
        holder.tvStatus.setTextColor(stText)
        holder.tvStatus.text = item.status.replaceFirstChar { it.uppercase() }

        holder.btnAction.text = if (item.status == "flagged") "Review" else if (item.status == "pending") "Score" else "Edit"
        holder.btnAction.setOnClickListener { onEdit(item) }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<FoodItem>() {
            override fun areItemsTheSame(a: FoodItem, b: FoodItem) = a.id == b.id
            override fun areContentsTheSame(a: FoodItem, b: FoodItem) = a == b
        }
    }
}
