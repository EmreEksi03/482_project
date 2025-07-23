# Micro Goal Tracker 

A lightweight Android productivity application that helps users set and track small, daily personal goals (micro-goals) to build consistent habits and improve daily productivity.


## Project Overview

Micro Goal Tracker is designed to solve the common problem of goal-setting apps being too complex for simple daily habits. It focuses on enabling users to define, track, and complete micro-level daily goals like:

- Drink 2L of water
- Stretch for 5 minutes  
- Take a 10-minute walk
- Write 100 words

The app emphasizes visual motivation through streaks, progress bars, and checklists to help users build consistency and maintain motivation.

## Features

### Core Features
- **User Authentication** - Secure registration and login via Firebase
- **Goal Management** - Create, edit, and delete daily micro-goals
- **Daily Checklist** - Simple interface to mark goals as completed
- **Progress Tracking** - Visual indicators with streaks and progress bars
- **History Analytics** - View goal completion statistics and performance
- **Smart Reminders** - Customizable daily notifications
- **Offline Support** - Works without internet, syncs when connected

### Key Characteristics
- **Minimalist Design** - Distraction-free, intuitive interface
- **Habit-Focused** - Specifically designed for daily micro-goals
- **Visual Motivation** - Streak counters and progress visualization
- **Fast Performance** - Goal completion actions execute within 1 second

## Design Patterns

The project implements several design patterns to ensure code quality and maintainability:

| Use Case | Design Pattern | Purpose |
|----------|---------------|---------|
| **User Registration** | Factory Method | Abstract creation of different account types |
| **Add New Goal** | Builder Pattern | Construct complex Goal objects with optional properties |
| **Track Goal Completion** | Observer Pattern | Notify UI components when goals are completed |
| **View Progress History** | MVC Pattern | Separate data, view, and controller for analytics |

### Pattern Benefits
- **Builder Pattern** - Avoids constructor overloading, improves readability
- **Repository Pattern** - Clean data source abstraction
- **MVVM Pattern** - Reactive UI updates and testable ViewModels
- **Observer Pattern** - Loose coupling between components

## 🛠️ Technology Stack

### Frontend
- **Platform**: Android (API 29+)
- **Language**: Java/Kotlin
- **UI Framework**: Android Jetpack Components
- **Architecture**: MVVM with LiveData

### Backend & Database
- **Authentication**: Firebase Authentication
- **Database**: Firebase Firestore (remote) + Room (local)
- **Cloud Messaging**: Firebase Cloud Messaging
- **Storage**: Local SharedPreferences for app settings

### Development Tools
- **IDE**: Visual Studio Code
- **Build System**: Gradle
- **Version Control**: Git + GitHub
- **Design**: Figma (UI/UX mockups)


## 🔧 Installation & Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 29+
- Java 8+ or Kotlin 1.5+
- Firebase project setup


## Usage

### Getting Started
1. **Register/Login** - Create an account or sign in
2. **Add Goals** - Tap "+" to create new daily micro-goals
3. **Daily Tracking** - Use the checklist to mark completed goals
4. **View Progress** - Check your streaks and completion statistics

### Example Goals
- "Drink 8 glasses of water" (Daily)
- "Do 10 push-ups" (Daily)
- "Read for 15 minutes" (Daily)
- "Meditate for 5 minutes" (Daily)

## Team

| Role | Responsibilities | Name  |
|------|------|-----------------|
| Project Manager     | Planning, coordination, and project delivery oversight.               |Emre Ekşi                                               |
| Android Developer   | Develops the application using Kotlin or Java, integrates Firebase.   |Mehmet Olcaytuğ Erdoğan,Mustafa Berke İmamoğlu,Osman Kaymakçı,Doğukan Yıldırım,Emre Ekşi |
| UI/UX Designer      | Creates mobile-friendly and minimalistic UI/UX flows.                 |Mehmet Olcaytuğ Erdoğan,Mustafa Berke İmamoğlu,Osman Kaymakçı,Doğukan Yıldırım,Emre Ekşi |
| QA Tester           | Tests application functionality, performance, and usability.          |Emre Ekşi                                       |

## Documentation

### Project Documents
- **[Software Requirements Specification](Micro_Goal_Tracker_SRS.md)** - Detailed requirements and use cases
- **[Project Plan](MicroGoalTracker_ProjectPlan.md)** - Timeline, resources, and deliverables
- **[Software Architecture](Software_Architecture.md)** - System design and architecture
- **[Software Design Document](SoftwareDesingDocument482.md)** - Detailed design specifications
- **[Design Patterns](Design_Pattern_Application_for_Use_Cases.md)** - Pattern implementations per use case

### Key Features Documentation
- **Use Cases**: User registration, goal management, progress tracking
- **Architecture Diagrams**: High-level system design
- **Database Schema**: Goal and user data models
- **API Specifications**: Firebase integration details


*Helping you build better habits, one micro-goal at a time.*
