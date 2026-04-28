package com.ayurbalance.admin.ui.fooddb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayurbalance.admin.data.AdminSupabaseService
import com.ayurbalance.admin.data.FoodItem
import kotlinx.coroutines.launch

class FoodDatabaseViewModel : ViewModel() {

    private val _items = MutableLiveData<List<FoodItem>>()
    val items: LiveData<List<FoodItem>> = _items

    private val allItems = mutableListOf<FoodItem>()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            val result = AdminSupabaseService.fetchFoodItems()
            allItems.clear()
            allItems.addAll(result)
            _items.postValue(result)
        }
    }

    fun filterItems(query: String, sourceFilter: String = "all") {
        val filtered = allItems.filter { item ->
            val matchesQuery = query.isEmpty() || item.name.contains(query, ignoreCase = true)
            val matchesSource = sourceFilter == "all" ||
                item.source.equals(sourceFilter, ignoreCase = true) ||
                (sourceFilter == "flagged" && item.status == "flagged")
            matchesQuery && matchesSource
        }
        _items.value = filtered
    }
}
