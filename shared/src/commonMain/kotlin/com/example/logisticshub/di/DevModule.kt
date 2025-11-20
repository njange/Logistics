package com.example.logisticshub.di

import com.example.logisticshub.engines.*
import com.example.logisticshub.repo.InMemoryDriverRepository
import com.example.logisticshub.repo.InMemoryPickupRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val devModule: Module = module {
    single { InMemoryPickupRepository() }
    single { InMemoryDriverRepository() }
    single { get<InMemoryPickupRepository>() as com.example.logisticshub.core.PickupRepository }
    single { get<InMemoryPickupRepository>() as com.example.logisticshub.core.AssignmentRepository }
    single { get<InMemoryDriverRepository>() as com.example.logisticshub.core.DriverRepository }

    single<com.example.logisticshub.core.DriverAssignmentEngine> { DriverAssignmentEngineImpl(get()) }
    single<com.example.logisticshub.core.RouteOptimizationEngine> { RouteOptimizationEngineImpl() }
    single<com.example.logisticshub.core.PickupScheduler> { PickupSchedulerImpl(get(), get(), get()) }
}
