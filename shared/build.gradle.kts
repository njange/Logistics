plugins {
    kotlin("multiplatform") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
    id("com.android.library") version "7.4.2" apply false
    id("com.squareup.sqldelight") version "1.5.5"
}

repositories {
    mavenCentral()
}

kotlin {
    android()
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.koin:koin-core:3.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }

        val iosMain by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
        }

        // wire ios source sets
        getByName("iosX64Main").dependsOn(iosMain)
        getByName("iosArm64Main").dependsOn(iosMain)
        getByName("iosSimulatorArm64Main").dependsOn(iosMain)
    }
}

sqldelight {
    database("LogisticsDb") {
        packageName = "com.example.logisticshub.db"
    }
}
