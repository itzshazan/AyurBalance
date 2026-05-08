package com.ayurbalance.admin.ui.ml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ayurbalance.R
import com.ayurbalance.databinding.FragmentMlMonitorBinding

class MLMonitorFragment : Fragment() {

    private var _binding: FragmentMlMonitorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MLMonitorViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMlMonitorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.metrics.observe(viewLifecycleOwner) { m ->
            binding.tvAvgConfidence.text = "${m.avgConfidence}%"
            binding.tvTop1Accuracy.text = "${m.top1Accuracy}%"
            binding.tvFallbackRate.text = "${m.fallbackRate}%"
            binding.tvModelSize.text = "${m.modelSizeMb} MB"

            binding.tvAvgConfidence.setTextColor(
                requireContext().getColor(if (m.avgConfidence < 75) R.color.pitta_terracotta else R.color.text_heading_dark)
            )

            animateBar(binding.fillHighConf, binding.trackHighConf, 62)
            animateBar(binding.fillMidConf, binding.trackMidConf, 20)
            animateBar(binding.fillLowConf, binding.trackLowConf, 18)
        }
    }

    private fun animateBar(fill: View, track: View, percent: Int) {
        track.post {
            val fullWidth = track.width.takeIf { it > 0 } ?: return@post
            fill.animate()
                .setStartDelay(200)
                .setDuration(700)
                .setInterpolator(DecelerateInterpolator())
                .setUpdateListener {
                    fill.layoutParams = fill.layoutParams.apply {
                        width = ((fullWidth * percent / 100f) * it.animatedFraction).toInt()
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
