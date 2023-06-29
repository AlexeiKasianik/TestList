package com.itexus.app.data.di

import com.itexus.app.data.api.ItemsApi
import com.itexus.app.data.api.MockApi
import com.itexus.app.data.useCase.GetItemsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private val useCase = module {
    singleOf(::GetItemsUseCase)
}

private val api = module {
    singleOf(::MockApi) bind ItemsApi::class
}

val dataModule = useCase + api