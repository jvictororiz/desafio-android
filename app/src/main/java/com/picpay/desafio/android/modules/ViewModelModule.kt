package com.picpay.desafio.android.modules

import com.picpay.desafio.android.ui.navigations.mainFlow.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        MainViewModel(
            get()
        )
    }
}