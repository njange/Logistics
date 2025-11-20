package com.example.logisticshub.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class User(
    val id: String,
    val name: String,
    val role: UserRole
)

@Serializable
enum class UserRole { VENDOR, DRIVER, ADMIN }

@Serializable
data class Vendor(
    val id: String,
    val name: String,
    val address: String
)

@Serializable
data class Driver(
    val id: String,
    val name: String,
    val vehicleId: String? = null,
    val online: Boolean = false,
    val lastLocation: Location? = null
)

@Serializable
data class Location(
    val lat: Double,
    val lon: Double
)

@Serializable
data class Pickup(
    val id: String,
    val vendorId: String,
    val requestedTimeWindowStart: Instant,
    val requestedTimeWindowEnd: Instant,
    val packages: List<PackageItem>,
    val location: Location,
    val status: PickupStatus = PickupStatus.PENDING
)

@Serializable
data class PackageItem(
    val id: String,
    val weightKg: Double,
    val lengthCm: Double,
    val widthCm: Double,
    val heightCm: Double,
    val category: String? = null
)

@Serializable
enum class PickupStatus { PENDING, ASSIGNED, EN_ROUTE, ARRIVED, PICKED, COMPLETED, CANCELLED }

@Serializable
data class Stop(
    val pickupId: String,
    val location: Location,
    val etaSeconds: Long? = null
)

@Serializable
data class RoutePlan(
    val driverId: String,
    val orderedStops: List<Stop>,
    val estimatedTotalSeconds: Long
)
