package com.ayurbalance.ui.meals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.data.models.MealItem

class SwapOptionAdapter(
    private val onSelect: (MealItem) -> Unit
) : ListAdapter<MealItem, SwapOptionAdapter.SwapViewHolder>(DIFF) {

    inner class SwapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName:     TextView = itemView.findViewById(R.id.tvSwapMealName)
        val tvMeta:     TextView = itemView.findViewById(R.id.tvSwapMealMeta)
        val tvSelect:   TextView = itemView.findViewById(R.id.tvSwapSelect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwapViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_swap_meal, parent, false)
        return SwapViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwapViewHolder, position: Int) {
        val meal = getItem(position)
        holder.tvName.text = meal.name
        holder.tvMeta.text = "${meal.calories} kcal · ${meal.prepTimeMin} min"
        holder.tvSelect.setOnClickListener { onSelect(meal) }
        holder.itemView.setOnClickListener  { onSelect(meal) }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<MealItem>() {
            override fun areItemsTheSame(a: MealItem, b: MealItem) = a.id == b.id
            override fun areContentsTheSame(a: MealItem, b: MealItem) = a == b
        }
    }
}
