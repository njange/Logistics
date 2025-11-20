package com.example.logisticshub.usecases

import com.example.logisticshub.core.*
import com.example.logisticshub.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CreatePickupUseCase(private val scheduler: PickupScheduler) {
    suspend fun execute(pickup: Pickup): String = scheduler.schedulePickup(pickup)
}

class GetOptimizedRouteUseCase(private val engine: RouteOptimizationEngine) {
    suspend fun execute(driverId: String, stops: List<Stop>): RoutePlan = engine.optimizeRoute(driverId, stops)
}

class UpdateDriverStatusUseCase(private val driverRepo: DriverRepository) {
    suspend fun execute(driver: Driver) = driverRepo.updateDriver(driver)
}
