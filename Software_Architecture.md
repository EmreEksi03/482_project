# Software Architecture Overview

## High-Level Architecture Design

The Micro Goal Tracker follows a **3-tier layered architecture** with **client-server components**, designed specifically to support Android mobile development with Firebase backend services. This architecture ensures separation of concerns, maintainability, and scalability while providing offline capabilities.

## Major Architectural Components

### 1. **Presentation Layer (Android UI)**
- **Activities**: Handle user interactions and navigation (Login, Dashboard, Goal Management, Progress View)
- **Fragments**: Reusable UI components for goal lists, daily checklists, and progress charts
- **ViewModels**: Manage UI-related data and handle business logic calls using MVVM pattern
- **Adapters**: Manage RecyclerView data binding for lists
- **Notification System**: Handle reminder notifications and background services

### 2. **Business Logic Layer**
- **Repository Pattern**: Centralizes data access logic and manages local/remote data sources
- **Use Cases**: Encapsulate specific business operations (AddGoal, TrackGoal, Authentication, ViewProgress)
- **Domain Services**: Handle complex business logic like streak calculations and progress analysis
- **Data Models**: Define core entities (Goal, User, Progress)
- **Validators**: Ensure data integrity and input validation

### 3. **Data Layer**
- **Local Storage**: Room database for offline data persistence and SharedPreferences for app settings
- **Data Sources**: Abstract local and remote data access
- **DAOs (Data Access Objects)**: Define database operations
- **Caching**: Local cache for improved performance and offline capabilities

### 4. **Firebase Backend Services**
- **Firebase Authentication**: Secure user registration and login
- **Firestore**: NoSQL database for real-time data synchronization
- **Cloud Functions**: Server-side logic for data processing
- **Firebase Cloud Messaging (FCM)**: Push notification delivery

![microgoal_architecture](https://github.com/user-attachments/assets/055f13c8-02f7-4b64-8e26-3230476bee7d)


## How Architecture Facilitates Use Case Realization

### **Use Case 1: User Registration**
- **Flow**: Presentation Layer (Registration Activity) → Business Layer (AuthUseCase) → Data Layer (UserRepository) → Firebase Auth
- **Benefits**: Separation allows for easy authentication method changes, input validation, and error handling

### **Use Case 2: Add New Goal**
- **Flow**: UI (Goal Creation Fragment) → ViewModel → AddGoalUseCase → GoalRepository → Local Database + Firestore
- **Benefits**: Repository pattern enables offline-first functionality; data persists locally and syncs when online

### **Use Case 3: Track Goal Completion**
- **Flow**: Daily Checklist → GoalViewModel → TrackGoalUseCase → ProgressRepository → StreakCalculator → Database Update
- **Benefits**: Business logic layer calculates streaks automatically; UI updates reactively through ViewModels

### **Use Case 4: View Progress History**
- **Flow**: Progress View → ProgressViewModel → ProgressRepository → ProgressAnalyzer → Chart Components
- **Benefits**: Layered approach allows complex data analysis in business layer while keeping UI simple

## Key Architectural Benefits

1. **Offline-First Design**: Local Room database ensures app functionality without internet connectivity
2. **Scalability**: Layered architecture allows easy feature additions without affecting other layers
3. **Testability**: Each layer can be tested independently with mock dependencies
4. **Maintainability**: Clear separation of concerns makes code easier to understand and modify
5. **Real-time Sync**: Firebase integration provides seamless data synchronization across devices
6. **Performance**: Local caching and optimized database queries ensure smooth user experience
