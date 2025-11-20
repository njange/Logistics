package com.example.logisticshub

import com.example.logisticshub.models.Pickup
import com.example.logisticshub.models.RoutePlan
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; ignoreUnknownKeys = true })
    }

    routing {
        post("/api/pickups") {
            val pickup = call.receive<Pickup>()
            // In a real server we'd save to DB and trigger assignment
            call.respond(mapOf("id" to pickup.id, "status" to "received"))
        }

        get("/api/route/{driverId}") {
            val driverId = call.parameters["driverId"] ?: ""
            // Return an empty route plan stub
            val plan = RoutePlan(driverId = driverId, orderedStops = emptyList(), estimatedTotalSeconds = 0)
            call.respond(plan)
        }
    }
}
