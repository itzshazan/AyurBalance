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
import com.ayurbalance.data.models.MealType

class MealCardAdapter(
    private val onSwap: (MealItem) -> Unit
) : ListAdapter<MealItem, MealCardAdapter.MealViewHolder>(DIFF) {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIcon:     TextView = itemView.findViewById(R.id.tvMealIcon)
        val tvTypetime: TextView = itemView.findViewById(R.id.tvMealTypeTime)
        val tvName:     TextView = itemView.findViewById(R.id.tvMealName)
        val tvMeta:     TextView = itemView.findViewById(R.id.tvMealMeta)
        val tvSwap:     TextView = itemView.findViewById(R.id.tvSwapBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_card, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = getItem(position)
        val type = meal.mealType

        holder.tvIcon.text = type.icon
        holder.tvTypetime.text = "${type.displayName.uppercase()} · ${type.time}"
        holder.tvName.text = meal.name
        holder.tvMeta.text = "${meal.calories} kcal · ${meal.prepTimeMin} min"

        holder.tvSwap.visibility = if (meal.swappable) View.VISIBLE else View.GONE
        holder.tvSwap.setOnClickListener { onSwap(meal) }

        holder.itemView.setOnClickListener {
            it.animate()
                .scaleX(0.97f).scaleY(0.97f).setDuration(80)
                .withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(80).start()
                }.start()
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<MealItem>() {
            override fun areItemsTheSame(a: MealItem, b: MealItem) = a.id == b.id
            override fun areContentsTheSame(a: MealItem, b: MealItem) = a == b
        }
    }
}
