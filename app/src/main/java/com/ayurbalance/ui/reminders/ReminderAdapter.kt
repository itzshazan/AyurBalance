package com.ayurbalance.ui.reminders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import java.util.Locale

class ReminderAdapter(
    private val onViewMeal: (ReminderItem.MealCard) -> Unit,
    private val onSnooze: (ReminderItem.MealCard) -> Unit,
    private val onLogWater: () -> Unit,
    private val onMarkDinacharya: (Int) -> Unit,
    private val onStartCheckin: () -> Unit
) : ListAdapter<ReminderItem, RecyclerView.ViewHolder>(DIFF) {

    companion object {
        private const val TYPE_MEAL       = 0
        private const val TYPE_HYDRATION  = 1
        private const val TYPE_DINACHARYA = 2
        private const val TYPE_VIKRITI    = 3
        private const val TYPE_INSIGHT    = 4
        private const val TYPE_STREAK     = 5
        private const val TYPE_SEASONAL   = 6

        private val DIFF = object : DiffUtil.ItemCallback<ReminderItem>() {
            override fun areItemsTheSame(a: ReminderItem, b: ReminderItem) = a == b
            override fun areContentsTheSame(a: ReminderItem, b: ReminderItem) = a == b
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ReminderItem.MealCard       -> TYPE_MEAL
        is ReminderItem.HydrationCard  -> TYPE_HYDRATION
        is ReminderItem.DinacharyaCard -> TYPE_DINACHARYA
        is ReminderItem.VikritiCard    -> TYPE_VIKRITI
        is ReminderItem.InsightCard    -> TYPE_INSIGHT
        is ReminderItem.StreakCard     -> TYPE_STREAK
        is ReminderItem.SeasonalTipCard -> TYPE_SEASONAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inf = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_MEAL       -> MealVH(inf.inflate(R.layout.item_reminder_meal, parent, false))
            TYPE_HYDRATION  -> HydrationVH(inf.inflate(R.layout.item_reminder_hydration, parent, false))
            TYPE_DINACHARYA -> DinacharyaVH(inf.inflate(R.layout.item_reminder_dinacharya, parent, false))
            TYPE_VIKRITI    -> VikritiVH(inf.inflate(R.layout.item_reminder_vikriti, parent, false))
            TYPE_STREAK     -> StreakVH(inf.inflate(R.layout.item_reminder_streak, parent, false))
            else            -> InsightVH(inf.inflate(R.layout.item_reminder_insight, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ReminderItem.MealCard       -> (holder as MealVH).bind(item)
            is ReminderItem.HydrationCard  -> (holder as HydrationVH).bind(item)
            is ReminderItem.DinacharyaCard -> (holder as DinacharyaVH).bind(item)
            is ReminderItem.VikritiCard    -> (holder as VikritiVH).bind(item)
            is ReminderItem.InsightCard    -> (holder as InsightVH).bind(item)
            is ReminderItem.StreakCard     -> (holder as StreakVH).bind(item)
            is ReminderItem.SeasonalTipCard -> (holder as InsightVH).bindSeasonal(item)
        }
    }

    // ── View Holders ──────────────────────────────────────────────────────────

    inner class MealVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTypeLabel: TextView = view.findViewById(R.id.tvMealTypeLabel)
        private val tvTime: TextView      = view.findViewById(R.id.tvMealTime)
        private val tvName: TextView      = view.findViewById(R.id.tvMealName)
        private val tvMeta: TextView      = view.findViewById(R.id.tvMealMeta)
        private val btnView: TextView     = view.findViewById(R.id.btnViewMeal)
        private val btnSnooze: TextView   = view.findViewById(R.id.btnSnooze)

        fun bind(item: ReminderItem.MealCard) {
            tvTypeLabel.text = "${item.reminder.title.uppercase(Locale.ROOT)} TIMING"
            tvTime.text      = formatTime(item.reminder.scheduledHour, item.reminder.scheduledMinute)
            tvName.text      = item.mealName
            tvMeta.text      = "${item.doshaNote} · ${item.calories} kcal"
            btnView.setOnClickListener { onViewMeal(item) }
            btnSnooze.setOnClickListener { onSnooze(item) }
        }
    }

    inner class HydrationVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTarget: TextView   = view.findViewById(R.id.tvHydrationTarget)
        private val progress: ProgressBar = view.findViewById(R.id.progressHydration)
        private val tvFraction: TextView  = view.findViewById(R.id.tvHydrationFraction)
        private val btnLog: TextView      = view.findViewById(R.id.btnLogWater)

        fun bind(item: ReminderItem.HydrationCard) {
            tvTarget.text   = "${item.dosha} target: ${item.goalL}L · ${"%.1f".format(item.currentL)}L logged"
            tvFraction.text = "${"%.1f".format(item.currentL)} / ${item.goalL}L today"
            val pct = ((item.currentL / item.goalL) * 100).toInt().coerceIn(0, 100)
            progress.progress = pct
            btnLog.setOnClickListener { onLogWater() }
        }
    }

    inner class DinacharyaVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView  = view.findViewById(R.id.tvDinacharyaTitle)
        private val tvBody: TextView   = view.findViewById(R.id.tvDinacharyaBody)
        private val tvTime: TextView   = view.findViewById(R.id.tvDinacharyaTime)
        private val ivDone: ImageView  = view.findViewById(R.id.ivDinacharyaDone)

        fun bind(item: ReminderItem.DinacharyaCard) {
            tvTitle.text = item.reminder.title
            tvBody.text  = item.reminder.body
            tvTime.text  = formatTime(item.reminder.scheduledHour, item.reminder.scheduledMinute)
            ivDone.alpha = if (item.reminder.isCompleted) 1f else 0.3f
            ivDone.setOnClickListener { onMarkDinacharya(item.reminder.id) }
            itemView.setOnClickListener { onMarkDinacharya(item.reminder.id) }
        }
    }

    inner class VikritiVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvStatus: TextView    = view.findViewById(R.id.tvVikritiStatus)
        private val btnCheckin: TextView  = view.findViewById(R.id.btnStartCheckin)

        fun bind(item: ReminderItem.VikritiCard) {
            if (item.isCompleted) {
                tvStatus.text = "Done ✓"
                btnCheckin.text = "Completed today"
                btnCheckin.alpha = 0.5f
            } else {
                tvStatus.text = "Pending"
                btnCheckin.text = "Start check-in  →"
                btnCheckin.alpha = 1f
                btnCheckin.setOnClickListener { onStartCheckin() }
            }
        }
    }

    inner class InsightVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvRitu: TextView    = view.findViewById(R.id.tvInsightRitu)
        private val tvText: TextView    = view.findViewById(R.id.tvInsightText)

        fun bind(item: ReminderItem.InsightCard) {
            tvRitu.text = item.ritu.uppercase(Locale.ROOT)
            tvText.text = item.text
        }

        fun bindSeasonal(item: ReminderItem.SeasonalTipCard) {
            tvRitu.text = "SEASONAL TIP · ${item.ritu.uppercase(Locale.ROOT)}"
            tvText.text = item.tip
        }
    }

    inner class StreakVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDays: TextView    = view.findViewById(R.id.tvStreakDays)
        private val tvMessage: TextView = view.findViewById(R.id.tvStreakMessage)

        fun bind(item: ReminderItem.StreakCard) {
            tvDays.text    = "${item.days} Day Streak"
            tvMessage.text = item.message
        }
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val amPm = if (hour < 12) "AM" else "PM"
        val h    = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour
        return "%d:%02d %s".format(h, minute, amPm)
    }
}
