package com.ayurbalance.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayurbalance.R
import com.ayurbalance.data.models.MealItem
import com.ayurbalance.data.models.MealType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SwapMealBottomSheet : BottomSheetDialogFragment() {

    interface SwapListener {
        fun onMealSwapped(oldMeal: MealItem, newMeal: MealItem)
    }

    private lateinit var currentMeal: MealItem
    private lateinit var options: List<MealItem>
    private var listener: SwapListener? = null

    fun bind(meal: MealItem, swapOptions: List<MealItem>, listener: SwapListener): SwapMealBottomSheet {
        this.currentMeal = meal
        this.options     = swapOptions
        this.listener    = listener
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.bottom_sheet_swap_meal, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvSwapCurrentMeal).text = currentMeal.name
        view.findViewById<TextView>(R.id.tvCancel).setOnClickListener { dismiss() }

        val rv = view.findViewById<RecyclerView>(R.id.rvSwapOptions)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = SwapOptionAdapter { selected ->
            listener?.onMealSwapped(currentMeal, selected)
            dismiss()
        }.also { adapter ->
            adapter.submitList(options.ifEmpty {
                listOf(
                    MealItem("empty","No alternatives available",
                        currentMeal.type, currentMeal.calories, 0,
                        currentMeal.doshaCompatible, currentMeal.seasonCompatible, false)
                )
            })
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    companion object {
        const val TAG = "SwapMealBottomSheet"
    }
}
