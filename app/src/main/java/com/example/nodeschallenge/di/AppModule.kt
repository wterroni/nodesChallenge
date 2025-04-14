package com.example.nodeschallenge.di

import com.example.nodeschallenge.data.remote.KtorClient
import com.example.nodeschallenge.data.repository.NodeRepository
import com.example.nodeschallenge.data.service.NodeService
import com.example.nodeschallenge.ui.NodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { KtorClient.client  }

    single { NodeService(get()) }

    single { NodeRepository(get()) }

    viewModel { NodeViewModel(get()) }
}