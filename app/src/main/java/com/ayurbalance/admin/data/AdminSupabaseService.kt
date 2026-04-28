package com.ayurbalance.admin.data

import com.ayurbalance.AyurBalanceApp
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from

object AdminSupabaseService {

    private val supabase get() = AyurBalanceApp.supabaseClient

    suspend fun verifyAdminRole(email: String): String? {
        return try {
            val users = supabase.from("admin_users")
                .select()
                .decodeList<AdminUser>()
            users.firstOrNull { it.email == email }?.role
        } catch (e: Exception) {
            null
        }
    }

    fun getCurrentUserEmail(): String {
        return try {
            supabase.auth.currentUserOrNull()?.email ?: ""
        } catch (e: Exception) { "" }
    }

    suspend fun signOut() {
        try { supabase.auth.signOut() } catch (_: Exception) {}
    }

    // ── User Management ──────────────────────────────────────────

    suspend fun fetchUsers(): List<UserRecord> {
        return try {
            supabase.from("users").select().decodeList<UserRecord>()
        } catch (e: Exception) { sampleUsers() }
    }

    suspend fun updateUserStatus(userId: String, status: String): Boolean {
        return try {
            supabase.from("users").update({ set("status", status) }) {
                filter { eq("id", userId) }
            }
            true
        } catch (e: Exception) { false }
    }

    // ── Food Database ─────────────────────────────────────────────

    suspend fun fetchFoodItems(): List<FoodItem> {
        return try {
            supabase.from("food_items").select().decodeList<FoodItem>()
        } catch (e: Exception) { sampleFoodItems() }
    }

    suspend fun updateFoodItemStatus(itemId: String, status: String): Boolean {
        return try {
            supabase.from("food_items").update({ set("status", status) }) {
                filter { eq("id", itemId) }
            }
            true
        } catch (e: Exception) { false }
    }

    // ── Recipes ────────────────────────────────────────────────────

    suspend fun fetchRecipes(): List<Recipe> {
        return try {
            supabase.from("recipes").select().decodeList<Recipe>()
        } catch (e: Exception) { sampleRecipes() }
    }

    suspend fun updateRecipeStatus(recipeId: String, status: String): Boolean {
        return try {
            supabase.from("recipes").update({ set("status", status) }) {
                filter { eq("id", recipeId) }
            }
            true
        } catch (e: Exception) { false }
    }

    // ── Compliance ─────────────────────────────────────────────────

    suspend fun fetchPendingDeletions(): List<DeletionRequest> {
        return try {
            // TODO: query deletion_requests table
            sampleDeletionRequests()
        } catch (e: Exception) { sampleDeletionRequests() }
    }

    suspend fun processDeletion(userId: String): Boolean {
        return try {
            // TODO: cascade delete user data
            false
        } catch (e: Exception) { false }
    }

    // ── Sample data (used when tables don't exist yet) ────────────

    private fun sampleUsers() = listOf(
        UserRecord("1", "arjun@mail.com", "Arjun Mehta", "Vata-Pitta", "active", "12 Apr 25", "Today"),
        UserRecord("2", "meera@mail.com", "Meera Iyer", "Pitta", "active", "3 Mar 25", "Yesterday"),
        UserRecord("3", "rohit@mail.com", "Rohit Das", "Kapha", "inactive", "18 Jan 25", "5 days ago"),
        UserRecord("4", "priya@mail.com", "Priya Nair", "Vata", "flagged", "27 Feb 25", "2 days ago"),
        UserRecord("5", "suresh@mail.com", "Suresh Kumar", null, "active", "21 Apr 26", "Today")
    )

    private fun sampleFoodItems() = listOf(
        FoodItem("1", "Moong dal", "ICMR", 347, "Vata↓ Pitta↓", "Sweet · light · cooling", "verified"),
        FoodItem("2", "Ghee", "ICMR", 900, "Vata↓", "Sweet · heavy · warm", "verified"),
        FoodItem("3", "Amul Butter", "USDA", 717, null, null, "pending"),
        FoodItem("4", "Fenugreek seeds", "ICMR", 323, "Kapha↓ Vata↑", "Bitter · pungent · hot", "verified"),
        FoodItem("5", "Biryani (mixed)", "Custom", 290, "Needs review", "Mixed", "flagged")
    )

    private fun sampleRecipes() = listOf(
        Recipe("1", "Moong dal khichdi", "Vata Pitta", "All", 25, "published"),
        Recipe("2", "Sesame rice", "Kapha", "Hemanta", 20, "published"),
        Recipe("3", "Spiced pumpkin soup", "Vata", "Hemanta", 35, "draft"),
        Recipe("4", "Cold smoothie bowl", "Pitta", "Grishma", 10, "flagged")
    )

    private fun sampleDeletionRequests() = listOf(
        DeletionRequest("user_3312", "19 Apr", 72),
        DeletionRequest("user_8801", "20 Apr", 55),
        DeletionRequest("user_1102", "21 Apr", 28)
    )
}
