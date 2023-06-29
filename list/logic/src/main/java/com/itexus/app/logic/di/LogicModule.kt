package com.itexus.app.logic.di

import com.itexus.app.logic.listRandomValues.BaseRandomValuesViewModel
import com.itexus.app.logic.listRandomValues.ListRandomValuesViewModel
import com.itexus.app.logic.details.BaseDetailsViewModel
import com.itexus.app.logic.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val logicModule = module {

    viewModelOf(::ListRandomValuesViewModel) bind BaseRandomValuesViewModel::class

    viewModel<BaseDetailsViewModel> {(arg: Int) ->
        DetailsViewModel(
            selectedItemId = arg,
            getItemsUseCase = get(),
        )
    }
}