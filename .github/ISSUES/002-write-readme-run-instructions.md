# Write README with run instructions for Android, iOS, and server

Description
---
Write a comprehensive `README.md` (or enhance existing `README-LOGISTICS-KMP.md`) that documents how to build and run each module in this workspace:

- `server/` — how to run the integration runner with Gradle and how to start the Ktor server (port, env vars).
- `shared/` — how to build the Kotlin Multiplatform library and generate SQLDelight sources.
- `androidApp/` / `composeApp` — how to open, build, and run the Android app (Android Studio, Gradle commands). Show how to provide `AndroidDatabaseDriverFactory` and start Koin.
- `iosApp/` — how to open the Xcode workspace, build the iOS app, and run the Kotlin/Native framework.
- Developer notes: required tools and versions (JDK, Kotlin, Gradle, Android SDK, Xcode), typical troubleshooting steps, and common Gradle tasks.

Acceptance Criteria
---
- One README containing clear copy-paste commands for Windows PowerShell (and optionally macOS/Linux) to build and run server, Android, and iOS.
- Sample `gradlew` commands for `:server:run`, `:shared:linkDebugFrameworkIosX64` (or appropriate KMP iOS tasks), and `:androidApp:installDebug`.
- Notes on credentials or env variables (e.g., `GH_TOKEN` for automation) and how to run the `scripts/create_github_issues` script if needed.

Suggested tasks
---
1. Consolidate `README-LOGISTICS-KMP.md` into `README.md` or add a `Getting Started` section.
2. Add commands and examples for PowerShell and macOS shells.
3. Add troubleshooting for SQLDelight generated sources and Gradle sync.

Labels: documentation, good first issue
