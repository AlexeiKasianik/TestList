package com.itexus.app.logic.details

import androidx.lifecycle.viewModelScope
import com.itexus.app.data.model.Item
import com.itexus.app.data.useCase.GetItemsUseCase
import com.itexus.app.uikit.coroutines.launchWithErrorHandler
import java.util.*
import kotlinx.coroutines.flow.MutableStateFlow

class DetailsViewModel(
    selectedItemId: Int,
    private val getItemsUseCase: GetItemsUseCase,
) : BaseDetailsViewModel() {

    override val item: MutableStateFlow<Optional<Item>> = MutableStateFlow(Optional.empty<Item>())
    override val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val error: MutableStateFlow<String> = MutableStateFlow(String())

    init {
        viewModelScope.launchWithErrorHandler(
            loading = isLoading,
            exception = ::handleError
        ) {
            getItemsUseCase().let { items ->
                items.find { it.id == selectedItemId }?.let { selectedItem ->
                    item.tryEmit(Optional.of(selectedItem))
                }
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        error.tryEmit(throwable.toString())
    }
}
