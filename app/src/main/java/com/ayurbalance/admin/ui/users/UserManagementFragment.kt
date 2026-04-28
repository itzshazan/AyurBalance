package com.ayurbalance.admin.ui.users

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
import com.ayurbalance.databinding.FragmentUserManagementBinding

class UserManagementFragment : Fragment() {

    private var _binding: FragmentUserManagementBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserManagementViewModel by viewModels()

    private lateinit var adapter: UserRowAdapter
    private var activeFilter = "all"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupSearch()
        setupFilterChips()
        observeViewModel()
    }

    private fun setupRecycler() {
        adapter = UserRowAdapter(
            onSuspend = { user -> viewModel.suspendUser(user.id) },
            onView = { user -> Toast.makeText(requireContext(), "Viewing: ${user.fullName}", Toast.LENGTH_SHORT).show() }
        )
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter
    }

    private fun setupSearch() {
        binding.etUserSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.filterUsers(s?.toString() ?: "", activeFilter)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupFilterChips() {
        val chips = listOf(
            binding.chipAll to "all",
            binding.chipActive to "active",
            binding.chipInactive to "inactive",
            binding.chipFlagged to "flagged"
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
                viewModel.filterUsers(binding.etUserSearch.text.toString(), filter)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }

        viewModel.actionResult.observe(viewLifecycleOwner) { msg ->
            if (msg != null) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
