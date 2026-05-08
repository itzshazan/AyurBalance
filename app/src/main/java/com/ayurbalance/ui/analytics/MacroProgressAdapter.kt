package com.ayurbalance.ui.analytics

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.databinding.ItemMacroRowBinding

class MacroProgressAdapter : ListAdapter<MacroItem, MacroProgressAdapter.VH>(DIFF) {

    inner class VH(val binding: ItemMacroRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemMacroRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvMacroName.text    = item.name
            tvMacroPercent.text = "${item.percent}%"
            tvMacroPercent.setTextColor(item.color)
            progressFill.setBackgroundColor(item.color)

            progressFill.post {
                val fullWidth = progressTrack.width.takeIf { it > 0 } ?: return@post
                val target    = (fullWidth * item.percent / 100f).toInt()
                ValueAnimator.ofInt(0, target).apply {
                    duration    = 700
                    startDelay  = position * 120L
                    interpolator = DecelerateInterpolator()
                    addUpdateListener { anim ->
                        val lp = progressFill.layoutParams
                        lp.width = anim.animatedValue as Int
                        progressFill.layoutParams = lp
                    }
                    start()
                }
            }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<MacroItem>() {
            override fun areItemsTheSame(a: MacroItem, b: MacroItem) = a.name == b.name
            override fun areContentsTheSame(a: MacroItem, b: MacroItem) = a == b
        }
    }
}
