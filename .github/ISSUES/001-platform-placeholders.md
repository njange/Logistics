# Create platform app placeholders and wire shared module

Description
---
Create minimal Android and iOS placeholder apps that reference the `shared` Kotlin Multiplatform module and provide platform wiring for:

- `Android`: `Application` class that initializes Koin, provides `AndroidDatabaseDriverFactory`, and sets up the Compose Multiplatform `MainActivity` to display the `DriverScreen` and `VendorScreen` composables.
- `iOS`: Xcode project entrypoint that initializes Koin (or provides dependencies via `UIApplicationDelegate` / Kotlin entrypoint) and exposes a SwiftUI or UIKit host for the `DriverVendorUi` Compose screens (or a simple SwiftUI placeholder that calls shared code).

Acceptance Criteria
---
- Android app builds and runs, showing the `DriverScreen` composable with a working "Go Online" button (no network required).
- iOS project builds and runs in Xcode simulator showing a basic placeholder screen and the shared module is linkable.
- `AndroidDatabaseDriverFactory` is constructed in the Android `Application` and registered in Koin for the shared module to use.

Suggested tasks
---
1. Add `androidApp/src/main/kotlin/.../App.kt` with `Application` subclass that starts Koin and provides `AndroidDatabaseDriverFactory`.
2. Add `androidApp/src/main/kotlin/.../MainActivity.kt` that sets Compose content and calls `DriverScreen`.
3. Add minimal `iosApp` Xcode project (or update existing) with Kotlin/Native framework integration instructions.
4. Document how to run Android and iOS placeholders in the README.

Labels: enhancement, platform, help wanted
