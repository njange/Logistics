plugins {
    kotlin("jvm") version "1.8.22"
    id("application")
    kotlin("plugin.serialization") version "1.8.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")

    implementation("org.koin:koin-core:3.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
}

application {
    mainClass.set("com.example.logisticshub.IntegrationRunnerKt")
}

kotlin {
    jvmToolchain(11)
}
