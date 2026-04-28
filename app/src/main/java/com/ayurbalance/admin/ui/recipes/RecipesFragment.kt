package com.ayurbalance.admin.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayurbalance.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeAdapter { recipe ->
            if (recipe.status == "draft") {
                viewModel.publishRecipe(recipe.id)
            } else {
                Toast.makeText(requireContext(), "${recipe.name}: ${recipe.status}", Toast.LENGTH_SHORT).show()
            }
        }
        binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecipes.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
            binding.tvRecipeCount.text = recipes.size.toString()
            binding.tvFlaggedCount.text = recipes.count { it.status == "flagged" }.toString()
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
