package com.ayurbalance.admin.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.admin.data.AdminSupabaseService
import com.ayurbalance.admin.data.Recipe
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _actionResult = MutableLiveData<String?>()
    val actionResult: LiveData<String?> = _actionResult

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            _recipes.postValue(AdminSupabaseService.fetchRecipes())
        }
    }

    fun publishRecipe(recipeId: String) {
        viewModelScope.launch {
            val ok = AdminSupabaseService.updateRecipeStatus(recipeId, "published")
            _actionResult.postValue(if (ok) "Recipe published!" else "Update queued")
            loadRecipes()
        }
    }
}
