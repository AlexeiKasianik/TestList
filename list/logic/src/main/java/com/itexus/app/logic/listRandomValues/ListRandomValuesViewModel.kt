package com.itexus.app.logic.listRandomValues

import androidx.lifecycle.viewModelScope
import com.itexus.app.data.model.Item
import com.itexus.app.data.useCase.GetItemsUseCase
import com.itexus.app.uikit.coroutines.launchWithErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class ListRandomValuesViewModel(
    private val getItemsUseCase: GetItemsUseCase
) : BaseRandomValuesViewModel() {

    override val listItems: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val error: MutableStateFlow<String> = MutableStateFlow(String())

    init {
        viewModelScope.launchWithErrorHandler(
            context = Dispatchers.IO,
            exception = ::handleError,
            loading = isLoading
        ) {
            listItems.value = getItemsUseCase()
        }
    }

    private fun handleError(throwable: Throwable) {
        error.tryEmit(throwable.toString())
    }

}
