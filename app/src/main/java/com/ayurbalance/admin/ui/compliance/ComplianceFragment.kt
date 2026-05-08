package com.ayurbalance.admin.ui.compliance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ayurbalance.R
import com.ayurbalance.databinding.FragmentComplianceBinding

class ComplianceFragment : Fragment() {

    private var _binding: FragmentComplianceBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComplianceViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentComplianceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtons()
        observeViewModel()
    }

    private fun setupButtons() {
        binding.btnProcessAll.setOnClickListener { viewModel.processAll() }
    }

    private fun observeViewModel() {
        viewModel.deletions.observe(viewLifecycleOwner) { deletions ->
            binding.tvPendingCount.text = deletions.size.toString()
            binding.tvPendingCount.setTextColor(
                requireContext().getColor(if (deletions.any { it.ageHours > 48 }) R.color.error_red else R.color.text_heading_dark)
            )

            // Populate deletion rows
            binding.containerDeletions.removeAllViews()
            deletions.forEach { req ->
                val row = layoutInflater.inflate(
                    com.ayurbalance.R.layout.item_deletion_row,
                    binding.containerDeletions,
                    false
                )
                row.findViewById<android.widget.TextView>(com.ayurbalance.R.id.tvDelUserId).text = req.userId
                row.findViewById<android.widget.TextView>(com.ayurbalance.R.id.tvDelDate).text = req.requestedDate
                val tvAge = row.findViewById<android.widget.TextView>(com.ayurbalance.R.id.tvDelAge)
                tvAge.text = "${req.ageHours} h"
                tvAge.setTextColor(requireContext().getColor(if (req.ageHours > 48) R.color.error_red else R.color.pitta_terracotta))
                row.findViewById<android.widget.TextView>(com.ayurbalance.R.id.btnDelProcess).setOnClickListener {
                    viewModel.processDeletion(req.userId)
                }
                binding.containerDeletions.addView(row)
            }
        }

        viewModel.actionResult.observe(viewLifecycleOwner) { msg ->
            if (msg != null) Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
