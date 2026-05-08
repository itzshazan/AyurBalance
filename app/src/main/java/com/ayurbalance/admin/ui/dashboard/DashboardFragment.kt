package com.ayurbalance.admin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.admin.ui.shared.ActivityLogAdapter
import com.ayurbalance.databinding.FragmentAdminDashboardBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("EEEE, d MMM yyyy · HH:mm z", Locale.getDefault())
        binding.tvDashboardDate.text = sdf.format(Date())

        setupAlertsRecycler()
        observeViewModel()
        animateConstitutionBars()
    }

    private fun setupAlertsRecycler() {
        val adapter = ActivityLogAdapter()
        binding.rvAlerts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlerts.adapter = adapter

        viewModel.alerts.observe(viewLifecycleOwner) { logs ->
            adapter.submitList(logs)
        }
    }

    private fun observeViewModel() {
        viewModel.metrics.observe(viewLifecycleOwner) { metrics ->
            binding.tvTotalUsers.text = "%,d".format(metrics.totalUsers)
            binding.tvTotalUsersDelta.text = metrics.totalUsersDelta
            binding.tvDau.text = "%,d".format(metrics.dau)
            binding.tvDauDelta.text = metrics.dauDelta
            binding.tvAssessments.text = "%,d".format(metrics.assessmentsDone)
            binding.tvMeals.text = "%,d".format(metrics.mealsToday)
        }
    }

    private fun animateConstitutionBars() {
        animateBar(binding.fillVata, binding.trackVata, 34)
        animateBar(binding.fillPitta, binding.trackPitta, 28)
        animateBar(binding.fillKapha, binding.trackKapha, 18)
    }

    private fun animateBar(fill: View, track: View, percent: Int) {
        track.post {
            val fullWidth = track.width.takeIf { it > 0 } ?: return@post
            val targetWidth = (fullWidth * percent / 100f).toInt()
            fill.animate()
                .setStartDelay(300)
                .setDuration(700)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    fill.layoutParams = fill.layoutParams.apply {
                        width = (targetWidth * it.animatedFraction).toInt()
                    }
                    fill.requestLayout()
                }.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
