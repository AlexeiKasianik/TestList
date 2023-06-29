package com.itexus.app.data.api

import com.itexus.app.data.model.Item

interface ItemsApi {

    suspend fun loadItems(): List<Item>

}