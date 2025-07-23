
**Software Requirements Specification Document**

---

### **1. Product Perspective**

Micro Goal Tracker is a standalone mobile application aimed at enhancing users' personal productivity and well-being. It is not part of a larger system and will function independently. The app focuses on enabling users to define, track, and complete micro-level daily goals like hydration, stretching, or short walks. It emphasizes visual motivation through streaks, progress bars, and checklists.

---

### **2. Product Functions**

- User registration and login
- Goal creation with customizable parameters (name, frequency, reminder time)
- Visual tracking of goal completion (streaks, progress bars)
- Daily checklist interface for active goals
- Notification/reminder system for each goal
- Historical view of progress and performance

---

### **3. User Characteristics**

- Target users: individuals seeking to build habits and improve daily productivity.
- Age range: 16-45 years old.
- Basic familiarity with mobile applications is expected.
- No technical expertise required.

---

### **4. Constraints**

- Platform: Android (initial release)
- Language: Java for Android app development
- Database: Firebase (Authentication, Firestore)
- Deadline: July 23, 2025

---

### **5. System Features (Use Case Based)**

#### **Use Case 1: User Registration**
- **Main Actor:** New User
- **Goal:** Register and create an account
- **Preconditions:** App is installed, internet access is available
- **Main Flow:**
  1. User opens app and selects "Sign Up"
  2. Enters email and password
  3. Presses "Register"
  4. System validates and stores credentials
- **Postconditions:** New user account is created

#### **Use Case 2: Add New Goal**
- **Main Actor:** Logged-in User
- **Goal:** Create a new daily goal
- **Preconditions:** User is authenticated
- **Main Flow:**
  1. User taps "Add Goal"
  2. Enters goal name and frequency
  3. Sets time for reminder
  4. Saves the goal
- **Postconditions:** Goal is saved and ready to track

#### **Use Case 3: Track Goal Completion**
- **Main Actor:** Logged-in User
- **Goal:** Mark a goal as completed
- **Preconditions:** User has existing active goals
- **Main Flow:**
  1. User opens daily checklist
  2. Taps checkbox next to completed goal
  3. System updates streak and progress bar
- **Postconditions:** Goal completion is recorded

#### **Use Case 4: View Progress History**
- **Main Actor:** Logged-in User
- **Goal:** Review personal goal completion statistics
- **Preconditions:** At least one goal has been completed
- **Main Flow:**
  1. User taps "Progress"
  2. Views graphical summary of streaks and completions
- **Postconditions:** System displays performance overview

---

### **6. Use Case Diagram**
![microgoal_usecase_diagram](https://github.com/user-attachments/assets/d2ecd976-c774-4bf3-9d11-c29cfe0104ca)

---

### **7. Non-Functional Requirements**

- **Usability:** Intuitive and minimalist UI for quick interactions
- **Performance:** Goal completion actions must execute within 1 second
- **Portability:** Must support at least Android 10 and above
- **Reliability:** 99.9% uptime for database and notification delivery

---

### **8. External Interface Requirements**

- Firebase Authentication
- Firebase Firestore
- Android Notification System

---

### **9. User Interfaces**

- **Login/Register Screen:** For account access
- **Dashboard:** Displays active goals and progress
- **Add/Edit Goal Screen:** To manage goals
- **Checklist View:** For daily goal checking
- **Progress Analytics:** Shows streaks and history

---

### **10. Software Interfaces**

- Firebase SDK (Authentication, Firestore)
- Android AlarmManager & Notification APIs


| Task / Section               | Contribution                                                                                 | Author(s)                 | Status    |
|-----------------------------|----------------------------------------------------------------------------------------------|---------------------------|-----------|
| Product Perspective          | Described app focus, independence, and daily habit features                                 | Osman, Doğukan            | Complete  |
| Product Functions            | Listed main functionalities: registration, tracking, notifications, etc.                    | Osman, Doğukan            | Complete  |
| User Characteristics         | Defined target user demographics and expectations                                           | Osman, Doğukan            | Complete  |
| Constraints                  | Defined platform, tech, and deadline limitations                                            | Osman, Doğukan            | Complete  |
| System Features (Use Cases) | Detailed 4 main use cases (Register, Add Goal, Track Goal, View Progress)                   | Osman, Doğukan            | Complete  |
| Use Case Diagram             | Visualized system actors and main interactions                                              | Emre Ekşi                 | Complete  |
| Non-Functional Requirements | Specified usability, reliability, portability, performance                                  | Osman, Doğukan            | Complete  |
| External Interface Reqs     | Listed Firebase, Android API integrations                                                   | Osman, Doğukan            | Complete  |
| UI Descriptions              | Listed core app screens and interactions                                                    | Osman, Doğukan            | Complete  |
| Software Interfaces          | Documented APIs and SDKs used                                                               | Osman, Doğukan            | Complete  |
