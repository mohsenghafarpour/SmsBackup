package com.mo22sen.smsbackup.di

import com.mo22sen.smsbackup.ui.MainViewModel
import com.mo22sen.smsbackup.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
}