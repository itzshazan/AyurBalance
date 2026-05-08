package com.ayurbalance.ui.prakriti

import android.content.Context

class PrakritiRepository(context: Context) {

    private val storage = LocalStorageManager(context)

    fun save(state: PrakritiState) = storage.saveState(state)

    fun load(): PrakritiState? = storage.loadState()

    fun clear() = storage.clearState()

    fun hasSavedProgress(): Boolean = storage.hasSavedState()
}
