package com.example.logisticshub.repo

import com.example.logisticshub.core.AssignmentRepository
import com.example.logisticshub.core.PickupRepository
import com.example.logisticshub.models.Pickup
import com.example.logisticshub.models.PickupStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.ConcurrentHashMap

class InMemoryPickupRepository: PickupRepository, AssignmentRepository {
    private val pickups = ConcurrentHashMap<String, Pickup>()
    private val _pendingFlow = MutableStateFlow<List<Pickup>>(emptyList())

    override suspend fun createPickup(pickup: Pickup): String {
        pickups[pickup.id] = pickup
        refreshFlows()
        return pickup.id
    }

    override suspend fun getPickup(id: String): Pickup? = pickups[id]

    override fun observePendingPickups(): kotlinx.coroutines.flow.Flow<List<Pickup>> = _pendingFlow.asStateFlow()

    override suspend fun assignPickupToDriver(pickupId: String, driverId: String) {
        val existing = pickups[pickupId] ?: return
        val updated = existing.copy(status = PickupStatus.ASSIGNED)
        pickups[pickupId] = updated
        refreshFlows()
    }

    private fun refreshFlows() {
        _pendingFlow.value = pickups.values.filter { it.status == PickupStatus.PENDING }
    }
}
