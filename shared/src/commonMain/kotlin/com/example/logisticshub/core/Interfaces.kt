package com.example.logisticshub.core

import com.example.logisticshub.models.*
import kotlinx.coroutines.flow.Flow

interface PickupRepository {
    suspend fun createPickup(pickup: Pickup): String
    suspend fun getPickup(id: String): Pickup?
    fun observePendingPickups(): Flow<List<Pickup>>
}

interface DriverRepository {
    suspend fun getDriver(id: String): Driver?
    suspend fun updateDriver(driver: Driver)
    fun observeOnlineDrivers(): Flow<List<Driver>>
}

interface AssignmentRepository {
    suspend fun assignPickupToDriver(pickupId: String, driverId: String)
}

interface RouteOptimizationEngine {
    suspend fun optimizeRoute(driverId: String, stops: List<Stop>): RoutePlan
}

interface DriverAssignmentEngine {
    suspend fun findDriverForPickup(pickup: Pickup): Driver?
}

interface PickupScheduler {
    suspend fun schedulePickup(pickup: Pickup): String
}
