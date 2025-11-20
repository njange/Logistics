package com.example.logisticshub.presentation

import com.example.logisticshub.core.DriverRepository
import com.example.logisticshub.models.Driver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DriverViewModel(private val driverRepository: DriverRepository) {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _driverState = MutableStateFlow<Driver?>(null)
    val driverState: StateFlow<Driver?> = _driverState

    fun loadDriver(id: String) {
        scope.launch {
            val d = driverRepository.getDriver(id)
            _driverState.value = d
        }
    }

    fun setOnline(driver: Driver, online: Boolean) {
        scope.launch {
            driverRepository.updateDriver(driver.copy(online = online))
            _driverState.value = driver.copy(online = online)
        }
    }
}
