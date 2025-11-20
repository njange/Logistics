# Expand Compose UI into end-to-end driver/vendor flow

Description
---
Extend the minimal Compose screens in `composeApp` to a working end-to-end flow that:

- Displays a list of pending pickups (from the `InMemoryPickupRepository` when running in dev mode).
- Allows a vendor to schedule a pickup via a small form (address, time window, package details).
- Allows a driver to accept assignments, update statuses (Arrived → Picked → Completed), and send location updates.

Acceptance Criteria
---
- `composeApp` shows a pickups list and allows scheduling a pickup that appears in the list.
- Driver screen can change status and reflect updates in the shared repository.
- The UI is small and dependency-free beyond KMP shared module and Compose Multiplatform.

Labels: enhancement, ux
