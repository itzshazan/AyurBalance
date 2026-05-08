package com.ayurbalance.admin.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.admin.ui.shared.ActivityLogAdapter
import com.ayurbalance.databinding.FragmentLiveActivityBinding

class LiveActivityFragment : Fragment() {

    private var _binding: FragmentLiveActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LiveActivityViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLiveActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ActivityLogAdapter()
        binding.rvActivityLog.layoutManager = LinearLayoutManager(requireContext())
        binding.rvActivityLog.adapter = adapter

        viewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
