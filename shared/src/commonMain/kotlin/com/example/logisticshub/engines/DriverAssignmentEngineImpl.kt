package com.example.logisticshub.engines

import com.example.logisticshub.core.DriverAssignmentEngine
import com.example.logisticshub.core.DriverRepository
import com.example.logisticshub.models.Driver
import com.example.logisticshub.models.Pickup
import com.example.logisticshub.models.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.math.*

class DriverAssignmentEngineImpl(private val driverRepository: DriverRepository) : DriverAssignmentEngine {
    override suspend fun findDriverForPickup(pickup: Pickup): Driver? = withContext(Dispatchers.Default) {
        // Get snapshot of online drivers
        val onlineDrivers = driverRepository.observeOnlineDrivers().first()
        if (onlineDrivers.isEmpty()) return@withContext null

        // Choose nearest driver by distance to pickup location
        onlineDrivers.minByOrNull { d -> distanceMeters(d.lastLocation ?: pickup.location, pickup.location) }
    }

    private fun distanceMeters(a: Location, b: Location): Double {
        val R = 6371000.0
        val lat1 = Math.toRadians(a.lat)
        val lat2 = Math.toRadians(b.lat)
        val dLat = lat2 - lat1
        val dLon = Math.toRadians(b.lon - a.lon)
        val u = sin(dLat / 2).pow(2.0) + cos(lat1) * cos(lat2) * sin(dLon / 2).pow(2.0)
        return R * 2 * asin(sqrt(u))
    }
}
