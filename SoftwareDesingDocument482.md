# Software Design Document

**Authors:** Emre Ekşi, Mehmet Olcaytuğ Erdoğan

## 1. System Overview

The Micro Goal Tracker is an Android-based mobile application designed to help users define, track, and analyze personal goals. It provides features such as user registration, daily checklists, goal streak tracking, and progress visualization. This document focuses on the "Add New Goal" use case, which allows users to create customized goals with optional fields such as reminders, categories, and repetition frequency through a structured process.

## 2. System Context

The system operates in a client-server environment, where the Android client communicates with Firebase backend services. The client handles the UI and business logic using the MVVM (Model-View-ViewModel) pattern. Firebase manages authentication, data storage, synchronization, and notification delivery.

### Actors

* User (initiates goal creation)
* Firebase (stores and syncs goal data)

### External Systems

* Firebase Firestore (data synchronization)
* Room (local offline database)
* Firebase Cloud Messaging (reminders)

## 3. Key Features and Functionality

The "Add New Goal" use case includes the following capabilities:

* Goal creation with optional parameters (reminder time, repeat frequency, notes)
* Input validation (e.g., empty fields, invalid time values)
* Local saving for offline access
* Background synchronization with Firestore
* Reminder notification scheduling

## 4. Assumptions and Dependencies

* Firebase Authentication is completed prior to goal creation.
* The application requires internet access at some point for syncing.
* Notification permissions are granted by the user.
* The project uses Android MVVM architecture.

### Dependencies

* Firebase SDKs (Auth, Firestore, Messaging)
* Android Jetpack Libraries (ViewModel, Room, LiveData)

## 5. Architectural Design

### 5.1 System Architecture Diagram (High-Level)

\[Goal Creation Fragment]
↓
\[GoalViewModel]
↓
\[AddGoalUseCase]
↓
\[GoalRepository]
↓       ↓
Room DB    Firebase Firestore

### 5.2 Architectural Patterns and Styles

* Layered Architecture (3-tier)
* MVVM (Android-specific)
* Repository Pattern for clean data source separation
* Builder Pattern for flexible goal object creation

### 5.3 Rationale for Architectural Decisions

* MVVM facilitates UI state management.
* Repository enables offline-first functionality.
* Builder Pattern avoids constructor overloading and improves extensibility.

## 6. Component Design

### 6.1 Subsystems and Modules

* GoalCreationFragment (UI input)
* GoalViewModel (business logic)
* AddGoalUseCase (encapsulates logic for adding goals)
* GoalBuilder (builds customizable Goal objects)
* GoalRepository (handles data persistence)
* RoomDAO / FirestoreService (data sources)

### 6.2 Responsibilities of Each Component

| Component            | Responsibility                                     |
| -------------------- | -------------------------------------------------- |
| GoalCreationFragment | Collects user input and renders UI                 |
| GoalViewModel        | Performs validation and invokes AddGoalUseCase     |
| AddGoalUseCase       | Applies business logic, builds and sends the goal  |
| GoalBuilder          | Constructs Goal instances with optional parameters |
| GoalRepository       | Manages data persistence to Room and Firestore     |
| RoomDAO              | Local database operations                          |
| FirestoreService     | Remote database sync operations                    |

### 6.3 Interfaces Between Components

* ViewModel exposes LiveData to the Fragment
* UseCase accepts plain data objects and returns operation results
* Repository provides a unified data access interface for local and remote sources

## 7. Data Design

### 7.1 Data Model

**Entity: Goal**

* id: String (UUID)
* title: String
* description: String (optional)
* reminderTime: Timestamp (optional)
* repeatFrequency: Enum (Daily, Weekly, etc.)
* createdAt: Timestamp
* userId: String

### 7.2 Data Storage

* Room: Caches all goal data for offline availability
* Firestore: Stores goal data for real-time synchronization

### 7.3 Data Flow

Fragment → ViewModel → UseCase → Builder → Repository → \[Room, Firestore]

### 7.4 Data Validation Rules

* Title must not be empty
* Reminder time must be in the future (if provided)
* Frequency must be a valid enumeration value

## 8. Design Patterns

### 8.1 Builder Pattern

**Context:** Goal entity includes several optional parameters.

**Justification:**

* Avoids excessive constructor overloading
* Enables readable and maintainable object creation
* Allows future extensibility for additional optional fields

**Code Example:**

```java
Goal goal = new GoalBuilder("Drink Water")
    .setReminderTime("08:00 AM")
    .setFrequency("Daily")
    .build();
```

**Comment:**

```java
// Design Pattern: Builder
// Intent: Simplify creation of Goal objects with optional properties
// Motivation: Avoid constructor overloads and improve readability
// Improvement: Easier to add/remove goal fields in the future
```

## 9. Implementation Notes

* GoalBuilder is implemented as a static inner class of Goal
* AddGoalUseCase handles input validation before building the goal
* Room writes are transactional
* Sync to Firestore is triggered via listeners after local save

## 10. User Interface Design

### 10.1 UI Description

The Add Goal screen includes:

* Goal Title (required)
* Description (optional)
* Reminder Time (time picker)
* Repeat Frequency (dropdown)
* Save and Cancel buttons

### 10.2 UI Mockup (Text Representation)

```
------------------------------
|        Add New Goal        |
------------------------------
| Title:           [       ] |
| Description:     [       ] |
| Reminder Time:   [08:00 AM]|
| Frequency:       [Daily  v]|
|                              |
|   [ Save ]      [ Cancel ]  |
------------------------------
```

## 11. External Interfaces

### 11.1 APIs

**Implemented:**

* Goal creation logic through domain-layer Use Cases and integration with Firebase Firestore

**Planned:**

* Firebase Authentication for user registration and login
* Progress analytics endpoints via domain services such as `ProgressAnalyzer`

### 11.2 Third-Party Systems

| Service                  | Status      | Purpose                                     |
| ------------------------ | ----------- | ------------------------------------------- |
| Firebase Firestore       | Implemented | Goal storage and real-time synchronization  |
| Firebase Authentication  | Planned     | Secure user login and registration          |
| Android AlarmManager     | Planned     | Local notification scheduling               |
| Firebase Cloud Messaging | Not Used    | Optional cloud-based reminder notifications |

## 12. Performance Considerations

### 12.1 Current Requirements

* Goal creation must complete within 1 second
* Builder Pattern prevents constructor overloads
* Optimized for Android 10+ with efficient Room queries

### 12.2 Planned Optimizations

* Enable offline persistence using Room database
* Add lazy-loading and pagination for large goal lists
* Use WorkManager for background sync on reconnection

## 13. Error Handling and Logging

### 13.1 Current Implementation

* Basic validation using validators in the ViewModel
* Firestore operations wrapped in try-catch blocks in repository layer

### 13.2 Planned Enhancements

* Centralized error handling at ViewModel level
* UI feedback for common error cases
* Crash reporting via Firebase Crashlytics or similar tool

## 14. Design for Testability

* Builder Pattern ensures modular and testable Goal creation
* MVVM enables independent testing of ViewModels
* Repository interfaces allow data source mocking

### 14.1 Current Status

* No current test coverage (unit or UI)

### 14.2 Planned Enhancements

* Add unit tests for Use Cases, Repositories, and ViewModels
* Add UI automation tests with Espresso or Jetpack Compose

## 15. Deployment and Installation Design

### 15.1 Environment Configuration

* Firebase integrated via `google-services.json`
* Android development using Android Studio and Gradle

### 15.2 Packaging

* Gradle for dependency management and build
* Deployed as local debug APK for internal testing

## 16. Change Log

| Version | Date          | Feature                                              | Status      |
| ------- | ------------- | ---------------------------------------------------- | ----------- |
| v0.1.0  | June 29, 2025 | Add Goal (Builder Pattern, MVVM, Repository Pattern) | Implemented |
| v0.1.1  | TBD           | User Registration (Factory Pattern + Firebase Auth)  | Planned     |
| v0.1.2  | TBD           | Goal Completion (Observer Pattern + Streak Service)  | Planned     |
| v0.1.3  | TBD           | View Progress (ProgressAnalyzer + MVVM architecture) | Planned     |

## 17. Future Work and Open Issues

* Integrate Firebase Authentication for user registration and login
* Implement reminder scheduling using AlarmManager or WorkManager
* Extend analytics and streak tracking with ProgressAnalyzer
* Improve modularity and maintainability in MVVM components
* Add comprehensive unit and UI test coverage
* Finalize offline mode using Room caching and sync listeners
