package com.example.logisticshub.engines

import com.example.logisticshub.core.RouteOptimizationEngine
import com.example.logisticshub.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

class RouteOptimizationEngineImpl : RouteOptimizationEngine {
    // Simple nearest-neighbor heuristic (stub). Returns stops ordered by greedy closest-next.
    override suspend fun optimizeRoute(driverId: String, stops: List<Stop>): RoutePlan = withContext(Dispatchers.Default) {
        if (stops.isEmpty()) return@withContext RoutePlan(driverId = driverId, orderedStops = emptyList(), estimatedTotalSeconds = 0)

        val remaining = stops.toMutableList()
        val ordered = mutableListOf<Stop>()

        // start at first stop as origin (could be driver's location in real implementation)
        var current = remaining.removeAt(0)
        ordered.add(current)

        while (remaining.isNotEmpty()) {
            val nextIndex = remaining.indices.minByOrNull { idx ->
                distanceMeters(current.location, remaining[idx].location)
            } ?: 0
            current = remaining.removeAt(nextIndex)
            ordered.add(current)
        }

        // rudimentary ETA estimate: assume 40 km/h -> 11.11 m/s
        val speedMps = 11.11
        var totalSec = 0L
        for (i in 0 until ordered.size - 1) {
            val d = distanceMeters(ordered[i].location, ordered[i + 1].location)
            totalSec += (d / speedMps).toLong()
        }

        RoutePlan(driverId = driverId, orderedStops = ordered, estimatedTotalSeconds = totalSec)
    }

    private fun distanceMeters(a: Location, b: Location): Double {
        // Haversine formula
        val R = 6371000.0 // meters
        val lat1 = Math.toRadians(a.lat)
        val lat2 = Math.toRadians(b.lat)
        val dLat = lat2 - lat1
        val dLon = Math.toRadians(b.lon - a.lon)
        val u = sin(dLat / 2).pow(2.0) + cos(lat1) * cos(lat2) * sin(dLon / 2).pow(2.0)
        return R * 2 * asin(sqrt(u))
    }
}
