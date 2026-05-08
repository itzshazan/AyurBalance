package com.ayurbalance.admin.ui.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.databinding.FragmentSystemHealthBinding

class SystemHealthFragment : Fragment() {

    private var _binding: FragmentSystemHealthBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SystemHealthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSystemHealthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ServiceAdapter()
        binding.rvServices.layoutManager = LinearLayoutManager(requireContext())
        binding.rvServices.adapter = adapter

        viewModel.uptime.observe(viewLifecycleOwner) { binding.tvUptime.text = it }
        viewModel.avgLatency.observe(viewLifecycleOwner) { binding.tvAvgLatency.text = it }
        viewModel.errorRate.observe(viewLifecycleOwner) { binding.tvErrorRate.text = it }
        viewModel.dbConnections.observe(viewLifecycleOwner) { binding.tvDbConnections.text = it }
        viewModel.services.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
