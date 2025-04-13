package com.example.nodeschallenge.di

import com.example.nodeschallenge.data.NodeRepository
import com.example.nodeschallenge.view.NodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { NodeRepository() }

    viewModel { NodeViewModel(get()) }
}