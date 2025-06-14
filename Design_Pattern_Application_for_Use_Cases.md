# Pattern–Use Case Mapping Matrix

This document maps at least one relevant software design pattern to each use case, with explanations and justifications for why the pattern is appropriate and how it improves the system.

---

## Use Case 1: User Registration

- **Design Pattern**: Factory Method

- **Explanation**:  
  During registration, users may sign up using different methods (e.g., email, Google, Facebook). The Factory Method pattern abstracts the user creation logic and allows instantiation of different account types without altering the core logic.

- **Justification**:  
  Enhances flexibility and scalability by making it easy to add new registration types with minimal code changes.

---

## Use Case 2: Add New Goal

- **Design Pattern**: Builder Pattern

- **Explanation**:  
  A goal may include multiple optional fields like name, frequency, and reminder time. The Builder pattern enables step-by-step object creation in a clear and structured way.

- **Justification**:  
  Improves code readability, avoids constructor overloading, and makes it easy to build complex objects with optional properties.

---

## Use Case 3: Track Goal Completion

- **Design Pattern**: Observer Pattern

- **Explanation**:  
  When a goal is marked as completed, several components (e.g., streak tracker, progress bar) need to react to that change. The Observer pattern allows these components to subscribe and respond automatically.

- **Justification**:  
  Promotes loose coupling and enables modularity. New features can be added without modifying the core goal logic.

---

## Use Case 4: View Progress History

- **Design Pattern**: Model-View-Controller (MVC)

- **Explanation**:  
  Viewing statistics and progress charts requires separation between the data (Model), UI (View), and interaction logic (Controller). MVC provides a structured way to organize these layers.

- **Justification**:  
  Enhances maintainability and testability by separating concerns and allowing UI or logic updates independently.

---

## Summary Table

| Use Case                | Design Pattern           | Explanation (Short)                                                  | Justification (Short)                                               |
|-------------------------|--------------------------|-----------------------------------------------------------------------|----------------------------------------------------------------------|
| User Registration       | Factory Method           | Abstracts creation of different account types                         | Easily extend registration methods                                  |
| Add New Goal            | Builder Pattern          | Builds complex `Goal` objects step-by-step                            | Improves clarity and avoids constructor overload                    |
| Track Goal Completion   | Observer Pattern         | Notifies related components when a goal is completed                  | Enables reactive UI and decoupled architecture                      |
| View Progress History   | Model-View-Controller    | Separates model, view, and controller for stat visualization          | Improves modularity and allows independent updates                  |



Author: Mehmet Olcaytuğ Erdoğan

| Task / Section             | Contribution                                                                                          | Author                    | Status   |
|----------------------------|-------------------------------------------------------------------------------------------------------|---------------------------|----------|
| Use Case 1: Registration   | Selected Factory Method pattern to decouple user account creation logic                              | Mehmet Olcaytuğ Erdoğan   | Complete |
| Use Case 2: Add New Goal   | Applied Builder pattern for flexible goal construction with optional fields                          | Mehmet Olcaytuğ Erdoğan   | Complete |
| Use Case 3: Track Goal     | Applied Observer pattern to notify dependent components (UI, streaks) upon goal status change         | Mehmet Olcaytuğ Erdoğan   | Complete |
| Use Case 4: View Progress  | Applied MVC pattern to separate data, UI, and user interactions for analytics and chart components    | Mehmet Olcaytuğ Erdoğan   | Complete |
| Summary Table              | Compiled design pattern mappings with rationale and benefits per use case                            | Mehmet Olcaytuğ Erdoğan   | Complete |
