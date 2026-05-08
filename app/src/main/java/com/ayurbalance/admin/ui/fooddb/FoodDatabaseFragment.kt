package com.ayurbalance.admin.ui.fooddb

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.R
import com.ayurbalance.databinding.FragmentFoodDatabaseBinding

class FoodDatabaseFragment : Fragment() {

    private var _binding: FragmentFoodDatabaseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FoodDatabaseViewModel by viewModels()

    private lateinit var adapter: FoodItemAdapter
    private var activeFilter = "all"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodDatabaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FoodItemAdapter { item ->
            Toast.makeText(requireContext(), "Edit: ${item.name}", Toast.LENGTH_SHORT).show()
        }
        binding.rvFoodItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFoodItems.adapter = adapter

        setupSearch()
        setupChips()

        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }

    private fun setupSearch() {
        binding.etFoodSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.filterItems(s?.toString() ?: "", activeFilter)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupChips() {
        val chips = listOf(
            binding.chipFoodAll to "all",
            binding.chipFoodIcmr to "ICMR",
            binding.chipFoodUsda to "USDA",
            binding.chipFoodFlagged to "flagged"
        )
        chips.forEach { (chip, filter) ->
            chip.setOnClickListener {
                activeFilter = filter
                chips.forEach { (c, _) ->
                    c.setBackgroundResource(R.drawable.bg_activity_card)
                    c.setTextColor(requireContext().getColor(R.color.text_body))
                }
                chip.setBackgroundResource(R.drawable.bg_btn_primary)
                chip.setTextColor(requireContext().getColor(R.color.white))
                viewModel.filterItems(binding.etFoodSearch.text.toString(), filter)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
