package com.example.logisticshub.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logisticshub.models.Driver
import com.example.logisticshub.models.Pickup

@Composable
fun DriverScreen(onGoOnline: (Driver) -> Unit, driver: Driver?) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Driver")
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Name: ${driver?.name ?: "Unknown"}")
                Text(text = "Online: ${driver?.online ?: false}")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { driver?.let { onGoOnline(it) } }) {
                    Text("Go Online")
                }
            }
        }
    }
}

@Composable
fun VendorScreen(onSchedule: (Pickup) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Vendor")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            // In a real app collect pickup details; stubbed here
        }) {
            Text("Schedule Pickup")
        }
    }
}
