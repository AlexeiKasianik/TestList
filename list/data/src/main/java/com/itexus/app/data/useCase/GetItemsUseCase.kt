package com.itexus.app.data.useCase

import com.itexus.app.data.api.ItemsApi
import com.itexus.app.data.model.Item

class GetItemsUseCase(
    private val api: ItemsApi
) {
    suspend operator fun invoke(): List<Item> {
        return api.loadItems()
    }
}
