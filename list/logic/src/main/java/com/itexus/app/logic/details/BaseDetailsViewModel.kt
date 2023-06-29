package com.itexus.app.logic.details

import androidx.lifecycle.ViewModel
import com.itexus.app.data.model.Item
import java.util.*
import kotlinx.coroutines.flow.StateFlow

abstract class BaseDetailsViewModel: ViewModel() {

    abstract val item: StateFlow<Optional<Item>>
    abstract val isLoading: StateFlow<Boolean>
    abstract val error: StateFlow<String>

}
