package com.ayurbalance.ui.location

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ayurbalance.R
import com.ayurbalance.ui.location.model.RegionData

class RegionDropdownAdapter(context: Context, private val regions: List<RegionData>) :
    ArrayAdapter<RegionData>(context, 0, regions) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        createView(position, convertView, parent)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        createView(position, convertView, parent)

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_region_dropdown, parent, false)
        val region = regions[position]
        view.findViewById<TextView>(R.id.tvRegionName).text = region.label
        view.findViewById<TextView>(R.id.tvClimateZone).text =
            region.climateZone.replaceFirstChar { it.uppercase() }
        return view
    }
}
