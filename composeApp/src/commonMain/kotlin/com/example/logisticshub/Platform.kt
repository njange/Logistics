package com.example.logisticshub

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform