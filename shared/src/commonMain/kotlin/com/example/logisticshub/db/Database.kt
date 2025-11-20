package com.example.logisticshub.db

interface DatabaseDriverFactory {
    // Platform-specific implementation should provide a SQLDelight driver
    fun createDriver(): Any
}
