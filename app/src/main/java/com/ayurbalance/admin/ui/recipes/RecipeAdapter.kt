package com.ayurbalance.admin.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.admin.data.Recipe

class RecipeAdapter(
    private val onAction: (Recipe) -> Unit
) : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvRecipeName)
        val tvDosha: TextView = itemView.findViewById(R.id.tvRecipeDosha)
        val tvSeason: TextView = itemView.findViewById(R.id.tvRecipeSeason)
        val tvPrepTime: TextView = itemView.findViewById(R.id.tvRecipePrepTime)
        val tvStatus: TextView = itemView.findViewById(R.id.tvRecipeStatus)
        val btnAction: TextView = itemView.findViewById(R.id.btnRecipeAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.tvName.text = recipe.name
        holder.tvSeason.text = recipe.season
        holder.tvPrepTime.text = "${recipe.prepTime} min"

        val (doshaColor) = when {
            "Pitta" in recipe.dosha -> listOf(0xFFEEEDFE.toInt())
            "Kapha" in recipe.dosha -> listOf(0xFFF1EFE8.toInt())
            else -> listOf(0xFFE1F5EE.toInt())
        }
        val doshaText = when {
            "Pitta" in recipe.dosha -> 0xFF534AB7.toInt()
            "Kapha" in recipe.dosha -> 0xFF5F5E5A.toInt()
            else -> 0xFF0F6E56.toInt()
        }
        holder.tvDosha.setBackgroundColor(doshaColor)
        holder.tvDosha.setTextColor(doshaText)
        holder.tvDosha.text = recipe.dosha

        val (stBg, stText) = when (recipe.status) {
            "published" -> Pair(0xFFE1F5EE.toInt(), 0xFF0F6E56.toInt())
            "draft" -> Pair(0xFFFAEEDA.toInt(), 0xFF854F0B.toInt())
            "flagged" -> Pair(0xFFFCEBEB.toInt(), 0xFFA32D2D.toInt())
            else -> Pair(0xFFF1EFE8.toInt(), 0xFF5F5E5A.toInt())
        }
        holder.tvStatus.setBackgroundColor(stBg)
        holder.tvStatus.setTextColor(stText)
        holder.tvStatus.text = recipe.status.replaceFirstChar { it.uppercase() }

        holder.btnAction.text = when (recipe.status) {
            "published" -> "Edit"
            "draft" -> "Publish"
            "flagged" -> "Review"
            else -> "Edit"
        }
        holder.btnAction.setOnClickListener { onAction(recipe) }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(a: Recipe, b: Recipe) = a.id == b.id
            override fun areContentsTheSame(a: Recipe, b: Recipe) = a == b
        }
    }
}
