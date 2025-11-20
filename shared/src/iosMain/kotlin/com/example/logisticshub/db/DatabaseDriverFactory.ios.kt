package com.example.logisticshub.db

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

class IOSDatabaseDriverFactory: DatabaseDriverFactory {
    override fun createDriver(): Any {
        return NativeSqliteDriver(LogisticsDb.Schema, "logistics.db")
    }
}
