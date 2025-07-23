#  Project Plan Document: Micro Goal Tracker (Android Application)

---

## • Project Scope

**In Scope:**
- A standalone Android application to help users build and maintain daily micro-habits such as hydration, stretching, or walking.
- User registration and authentication via Firebase.
- Goal creation with customizable fields (name, frequency, reminder time).
- Visual tracking of progress using streaks and progress bars.
- A daily checklist interface for active goals.
- Push notification reminders for each goal.
- Performance history view with graphical data.

**Out of Scope:**
- Integration with wearable devices (e.g., smartwatches, fitness bands).
- Social features like goal sharing or messaging.
- iOS version or cross-platform support (initially).

---

## • Project Organization - People (Roles and Responsibilities)

| Role               | Responsibilities                                                       | Person                                                 |
|--------------------|------------------------------------------------------------------------|--------------------------------------------------------|
| Project Manager     | Planning, coordination, and project delivery oversight.               |Emre Ekşi                                               |
| Android Developer   | Develops the application using Kotlin or Java, integrates Firebase.   |Mehmet Olcaytuğ Erdoğan,Mustafa Berke İmamoğlu,Osman Kaymakçı,Doğukan Yıldırım,Emre Ekşi |
| UI/UX Designer      | Creates mobile-friendly and minimalistic UI/UX flows.                 |Mehmet Olcaytuğ Erdoğan,Mustafa Berke İmamoğlu,Osman Kaymakçı,Doğukan Yıldırım,Emre Ekşi |
| QA Tester           | Tests application functionality, performance, and usability.          |Emre Ekşi                                       |

---




## • Objectives

- Deliver a stable and user-friendly Android app before **July 26, 2025**.
- Ensure seamless goal creation and tracking with real-time data sync via Firebase.
- Provide effective visual motivation tools (streaks, progress bars).
- Implement timely and accurate reminder notifications.
- Maintain lightweight performance with fast user interactions.

---

## • Key Phases and Timeline

### 🗓 Timeline (List View):

| Phase                     | Start Date   | End Date     | Deliverables                                |
|---------------------------|--------------|--------------|---------------------------------------------|
| Requirements Gathering    | June 2, 2025| June 15, 2025| Software Requirements Specification (SRS) Document |
| PA1 – Use Case Definition + Architecture + Pattern Mapping    | June 10, 2025| June 20, 2025| Software Architecture Design Document Use Case & Pattern Mapping |
| PA2 – Implementation of 1 Use Case with Design Pattern | June 20, 2025| July 1, 2025 | - Source Code of Selected Use Case - Software Design Document (PA2 version) - TASK_MATRIX.md |
| PA3 – Implementation of Remaining 3 Use Cases + Testing | July 2, 2025 | July 23, 2025| - Source Code for Remaining Use Cases - Final Software Design Document - Test Report (.xlsx)  |
| Final Delivery & Evaluation   | July 23, 2025| July 26, 2025| - Final GitHub Submission - APK Build - Project Demo & Documentation   |

### 📌 Delivery Dates:
- **Alpha Demo:** July 26, 2025  
- **Final Submission:** July 26, 2025  
- **Project Evaluation/Closure:** July 26, 2025

---

## • Resource Planning (Simple)

### 🔧 Required Tools & Resources:

- **Programming Language:** Kotlin or Java (Android)
- **IDE:** Android Studio
- **Database:** Firebase Firestore
- **Authentication:** Firebase Auth
- **Notifications:** Firebase Cloud Messaging / Android AlarmManager
- **Version Control:** Git + GitHub
- **Design:** Figma or XML layout files in Android Studio

---

## • Risk Management (Simple)

| Risk                                 | Likelihood | Mitigation Strategy                                |
|--------------------------------------|------------|----------------------------------------------------|
| Firebase downtime or limitations     | Low        | Monitor usage; utilize offline persistence         |
| Notification delays or failures      | Medium     | Use fallback in-app alarms                         |
| Delay in development or illness      | Medium     | Maintain modular codebase; assign backup tasks     |
| Performance bottlenecks              | Medium     | Optimize RecyclerViews and database access         |

---

## • Communication Plan (Simple)

- **Internal Team:**
  - Daily status updates via WhatsApp/Slack.
  - Weekly task reviews via Google Meet.
  - Shared documentation via Google Drive.



---

## • Change Management Plan

Only internal team members can submit a **Change Request (CR)**.
1. The CR is submitted via team communication tools.
2. It is reviewed during the next internal team meeting.
3. If approved, it is added to the sprint backlog.
4. All changes are documented and version-controlled in GitHub.
---

## • Budget Plan

| Resource                 | Cost Estimate |
|--------------------------|---------------|
| Firebase Services (Free Tier) | $0           |
| Android Studio IDE        | $0           |
| Design Tools (Figma)      | $0           |
| GitHub (Student Plan)     | $0           |

**Total Estimated Cost:** **$0**  
> Project will be developed using only free/open-source tools and cloud services with free tiers.

---

## • Acceptance Tests and Acceptance Criteria

| Feature                | Acceptance Criteria                                                                 |
|------------------------|-------------------------------------------------------------------------------------|
| Registration/Login     | User can create and access account via Firebase Auth.                              |
| Goal Creation          | User can input goal title, frequency, time; goal is saved and shown.               |
| Daily Checklist        | User can check off goals; progress bar updates correctly.                          |
| Notification System    | App sends timely daily notifications for active goals.                             |
| Progress History       | User can view previous completions and active streaks.                             |
| Offline Handling       | App functions in read-only mode when offline; syncs on reconnection.               |


Author: Mehmet Olcaytuğ Erdoğan


| Task / Section              | Contribution                                                                                   | Author                     | Status    |
|----------------------------|-----------------------------------------------------------------------------------------------|----------------------------|-----------|
| Project Scope               | Defined in-scope and out-of-scope features for the Android app                                | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Project Organization        | Defined team roles                                                       | Mehmet Olcaytuğ Erdoğan    | Complete   |
| Objectives                  | Listed app goals like visual motivation, real-time sync, and notifications                    | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Key Phases & Timeline       | Created phase-wise plan with start/end dates and deliverables                                | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Resource Planning           | Chose tech stack: Kotlin/Java, Firebase, Git, Figma                                           | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Risk Management             | Identified project risks and mitigation strategies                                            | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Communication Plan          | Defined team communication methods and tools                                                  | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Change Management Plan      | Established change request and version control policy                                         | Mehmet Olcaytuğ Erdoğan    | Complete  |
| Budget Plan                 | Estimated cost using only free tools                                                          | Mehmet Olcaytuğ Erdoğan    | Complete  |
