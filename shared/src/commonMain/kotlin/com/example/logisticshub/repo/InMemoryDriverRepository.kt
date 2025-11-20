package com.example.logisticshub.repo

import com.example.logisticshub.core.DriverRepository
import com.example.logisticshub.models.Driver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.ConcurrentHashMap

class InMemoryDriverRepository: DriverRepository {
    private val drivers = ConcurrentHashMap<String, Driver>()
    private val _onlineFlow = MutableStateFlow<List<Driver>>(emptyList())

    override suspend fun getDriver(id: String): Driver? = drivers[id]

    override suspend fun updateDriver(driver: Driver) {
        drivers[driver.id] = driver
        refreshFlows()
    }

    override fun observeOnlineDrivers(): kotlinx.coroutines.flow.Flow<List<Driver>> = _onlineFlow.asStateFlow()

    fun addOrReplace(driver: Driver) {
        drivers[driver.id] = driver
        refreshFlows()
    }

    private fun refreshFlows() {
        _onlineFlow.value = drivers.values.filter { it.online }
    }
}
