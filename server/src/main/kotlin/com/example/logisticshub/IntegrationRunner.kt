package com.example.logisticshub

import com.example.logisticshub.di.devModule
import com.example.logisticshub.di.sharedModule
import com.example.logisticshub.models.*
import com.example.logisticshub.repo.InMemoryDriverRepository
import com.example.logisticshub.repo.InMemoryPickupRepository
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

fun main() = runBlocking {
    // Start Koin with dev bindings
    startKoin {
        modules(listOf(devModule, sharedModule))
    }

    val koin = GlobalContext.get().koin

    val driverRepo = koin.get<InMemoryDriverRepository>()
    val pickupRepo = koin.get<InMemoryPickupRepository>()
    val scheduler = koin.get<com.example.logisticshub.core.PickupScheduler>()
    val routeEngine = koin.get<com.example.logisticshub.core.RouteOptimizationEngine>()

    // Create a driver and mark online
    val driver = Driver(
        id = "driver1",
        name = "Alice",
        vehicleId = "VAN-123",
        online = true,
        lastLocation = Location(lat = 37.7749, lon = -122.4194)
    )
    driverRepo.addOrReplace(driver)

    // Create a pickup request
    val start = Clock.System.now()
    val end = start.plus(2, DateTimeUnit.HOUR)

    val pickup = Pickup(
        id = "pickup1",
        vendorId = "vendor1",
        requestedTimeWindowStart = start,
        requestedTimeWindowEnd = end,
        packages = listOf(
            PackageItem(id = "pkg1", weightKg = 2.0, lengthCm = 10.0, widthCm = 10.0, heightCm = 5.0, category = "small")
        ),
        location = Location(lat = 37.7849, lon = -122.4094)
    )

    println("Scheduling pickup: ${pickup.id}")
    val scheduledId = scheduler.schedulePickup(pickup)
    println("Scheduled id: $scheduledId")

    val fetched = pickupRepo.getPickup(scheduledId)
    println("Fetched pickup status: ${fetched?.status}")

    // Request optimized route for driver
    val stops = listOf(Stop(pickupId = pickup.id, location = pickup.location))
    val route = routeEngine.optimizeRoute(driver.id, stops)
    println("Optimized route for driver=${driver.id}: stops=${route.orderedStops.size}, eta=${route.estimatedTotalSeconds}s")

    // Stop Koin
    GlobalContext.stop()
}
