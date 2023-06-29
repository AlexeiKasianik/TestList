package com.itexus.app.logic.listRandomValues

import androidx.lifecycle.ViewModel
import com.itexus.app.data.model.Item
import kotlinx.coroutines.flow.StateFlow

abstract class BaseRandomValuesViewModel: ViewModel() {

    abstract val listItems: StateFlow<List<Item>>
    abstract val isLoading: StateFlow<Boolean>
    abstract val error: StateFlow<String>

}
