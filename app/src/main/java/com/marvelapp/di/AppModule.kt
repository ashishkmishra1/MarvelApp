package com.marvelapp.di

import org.koin.core.module.Module

val appModule: List<Module> = listOf(networkModule, dataModule, presentationModule)