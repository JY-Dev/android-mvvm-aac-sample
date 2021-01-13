package com.jydev.riiidsimapleapllication.di


import com.jydev.riiidsimapleapllication.ui.detail.DetailViewModel
import com.jydev.riiidsimapleapllication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
    viewModel { (userId : Int) ->
        DetailViewModel(userId,get())
    }
}