package com.example.logisticshub.di

import com.example.logisticshub.core.*
import com.example.logisticshub.usecases.*
import org.koin.core.module.Module
import org.koin.dsl.module

// Provide a Koin module that the platform apps can include and override platform-specific bindings
val sharedModule: Module = module {
    // Bindings for repositories and engines should be provided by platform-specific modules
    single { CreatePickupUseCase(get()) }
    single { GetOptimizedRouteUseCase(get()) }
    single { UpdateDriverStatusUseCase(get()) }
}
