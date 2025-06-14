# Design Pattern Application for Use Cases

## Use Case 1: User Registration

- **Pattern Name**: Factory Method

- **Why It’s Appropriate**:  
  The Factory Method is ideal for encapsulating the creation logic of different types of user accounts or registration methods (e.g., email/password, Google, Facebook). This allows the app to remain flexible and easily extendable without changing client code.

- **Application in Architecture**:  
  Implement a `UserFactory` class that decides which subclass of `UserAccount` to instantiate based on the input (email/password or social login). This isolates user creation logic, making it easy to test, extend, and maintain.

---

## Use Case 2: Add New Goal

- **Pattern Name**: Builder Pattern

- **Why It’s Appropriate**:  
  The Builder Pattern is suitable for constructing complex objects like a `Goal`, which has multiple optional fields (name, frequency, reminder time). This avoids telescoping constructors and keeps object creation clean.

- **Application in Architecture**:  
  Use a `GoalBuilder` class with fluent methods like `.SetName()`, `.SetFrequency()`, `.SetReminderTime()`, and finally `.Build()` to return the complete `Goal` object. This improves code readability and makes it easier to modify the goal creation logic.

---

## Use Case 3: Track Goal Completion

- **Pattern Name**: Observer Pattern

- **Why It’s Appropriate**:  
  The Observer Pattern allows the system to automatically update other components (like progress bars, streak counters, and notifications) when a goal's completion status changes, without tightly coupling components.

- **Application in Architecture**:  
  When a user marks a goal as complete, the `Goal` object notifies observers like `StreakTracker`, `ProgressUpdater`, or `UIUpdater` that react accordingly. This promotes modularity and simplifies future UI or logic changes.

---

## Use Case 4: View Progress History

- **Pattern Name**: Model-View-Controller (MVC)

- **Why It’s Appropriate**:  
  MVC cleanly separates the concerns of data (model), user interface (view), and interaction logic (controller), making it ideal for managing data-driven visual components like progress graphs and stats.

- **Application in Architecture**:  
  - **Model**: Handles data retrieval and processing for completed goals.  
  - **View**: Displays progress charts, summaries.  
  - **Controller**: Handles user interactions (e.g., selecting date ranges or filters).  

  This separation makes it easy to modify the UI or back-end logic independently.

---

## Summary Table

| Use Case                  | Design Pattern       | Benefits                                                                 |
|---------------------------|----------------------|--------------------------------------------------------------------------|
| User Registration         | Factory Method       | Easy extensibility for user creation logic                              |
| Add New Goal              | Builder              | Clean, maintainable construction of complex `Goal` objects              |
| Track Goal Completion     | Observer             | Decoupled, reactive UI and logic updates                                |
| View Progress History     | MVC                  | Clear separation of UI, logic, and data enhances maintainability        |


Author: Mehmet Olcaytuğ Erdoğan

| Task / Section             | Contribution                                                                                          | Author                    | Status   |
|----------------------------|-------------------------------------------------------------------------------------------------------|---------------------------|----------|
| Use Case 1: Registration   | Selected Factory Method pattern to decouple user account creation logic                              | Mehmet Olcaytuğ Erdoğan   | Complete |
| Use Case 2: Add New Goal   | Applied Builder pattern for flexible goal construction with optional fields                          | Mehmet Olcaytuğ Erdoğan   | Complete |
| Use Case 3: Track Goal     | Applied Observer pattern to notify dependent components (UI, streaks) upon goal status change         | Mehmet Olcaytuğ Erdoğan   | Complete |
| Use Case 4: View Progress  | Applied MVC pattern to separate data, UI, and user interactions for analytics and chart components    | Mehmet Olcaytuğ Erdoğan   | Complete |
| Summary Table              | Compiled design pattern mappings with rationale and benefits per use case                            | Mehmet Olcaytuğ Erdoğan   | Complete |
