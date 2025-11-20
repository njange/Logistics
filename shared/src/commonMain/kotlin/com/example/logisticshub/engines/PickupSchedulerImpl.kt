package com.example.logisticshub.engines

import com.example.logisticshub.core.*
import com.example.logisticshub.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PickupSchedulerImpl(
    private val pickupRepository: PickupRepository,
    private val assignmentRepository: AssignmentRepository,
    private val assignmentEngine: DriverAssignmentEngine
): PickupScheduler {

    override suspend fun schedulePickup(pickup: Pickup): String = withContext(Dispatchers.Default) {
        // persist pickup
        val id = pickupRepository.createPickup(pickup)

        // attempt to find a driver
        val driver = assignmentEngine.findDriverForPickup(pickup)
        if (driver != null) {
            assignmentRepository.assignPickupToDriver(id, driver.id)
        }

        id
    }
}
