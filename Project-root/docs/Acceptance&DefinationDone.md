## Table of Contents

1. \[Use Case 1: User Registration with Factory Method Pattern]
2. \[Use Case 2: Goal Creation and Sync via Builder Pattern]
3. \[Use Case 3: Observer Pattern Implementation]
4. \[Use Case 4: View Progress History with MVC Pattern]


# Use Case 1: User Registration with Factory Method Pattern

### Acceptance Criteria and Definition of Done

**Acceptance Criteria:**

* The system should accept the user's email if it is in a valid format (contains `@` and `.`).
* The system should throw an `IllegalArgumentException` if the user enters an invalid email format.
* The system should throw an `IllegalArgumentException` if the user enters a password shorter than 6 characters.
* When a user registers with valid information, the system should create an `EmailUser` object within 3 seconds.
* The system must store all passwords in hashed form.
* For newly created users, the `emailVerified` status should be set to `false`.

**Definition of Done:**

* All acceptance criteria have been successfully tested and passed.
* Necessary unit tests have been completed.
* `EmailFactory` and `EmailUser` classes have been implemented and are functional.
* The demo application is running properly.
* Required documentation and test reports have been created.


# Use Case 2: Goal Creation and Sync via Builder Pattern

### Acceptance Criteria and Definition of Done

**Acceptance Criteria:**

* The user should be able to create a goal with a valid title, description, and time information.
* The created goal should be constructed using the Builder Pattern.
* The goal must be successfully saved to the local database.
* If the device is online, the goal should be synchronized with Firestore.
* The Builder Pattern should ensure immutability and a fluent API.
* The MVVM structure should encapsulate the goal creation logic at the ViewModel level.
* All operations should complete without errors, and expected results should be achieved in the test environment.

**Definition of Done:**

* All acceptance criteria have been successfully tested and passed.
* Required unit and integration tests have been completed.
* Builder, Repository, and MVVM patterns have been properly integrated according to their principles.
* Code has been reviewed and is ready for merging.
* Necessary documentation and test reports have been prepared.

# Use Case 3: Observer Pattern Implementation

### Acceptance Criteria and Definition of Done

**Acceptance Criteria:**
- All observers should be notified promptly and correctly when the goal they observe is marked as completed.
- The streak counter should increment accurately on each completion and trigger a milestone notification at a 7-day streak.
- Multiple observers should be able to watch the same goal without conflict, and a single observer should be able to track multiple goals.
- Observers should be dynamically attachable and removable during runtime without breaking system behavior.
- Edge cases (e.g., duplicate completions in one day, observer removal, broken streaks) should be handled gracefully.
- Achievements (e.g., "First Completion") should be triggered at the correct time and only once per goal.

**Definition of Done:**
- All acceptance criteria have been successfully tested and passed in their respective scenarios.
- Unit tests for all relevant components (Goal, Observer subclasses, etc.) have been completed.
- The code follows the Observer Pattern with proper separation of concerns and loose coupling.
- Code is ready for merge after review, meeting quality and maintainability standards.
- Test cases and results have been fully documented, with a 100% test pass rate achieved.

## USE CASE 3 - OBSERVER PATTERN TEST SUITE

### TEST SCENARIO 1: Normal Goal Completion Flow
**Purpose:** Test basic Observer pattern implementation  
**Expected:** All observers should be notified when goal is completed

```
Observer TestProgressTracker added
Observer TestStreakCounter added
Goal "Test Workout" marked as completed!
TestProgressTracker: Received update for Test Workout
TestStreakCounter: Received update for Test Workout
```

**Test Results:**
- Progress Tracker Updated: **PASS**
- Streak Counter Updated: **PASS**
- Goal Marked Complete: **PASS**

### TEST SCENARIO 2: Streak Tracking Accuracy
**Purpose:** Test streak calculation and milestone detection  
**Expected:** Streak should increment correctly and detect milestones

```
Observer StreakCounter added
Goal "Reading Goal" marked as completed!
StreakCounter: Current streak for "Reading Goal": 1 days
[... progression through 7 days ...]
Goal "Reading Goal" marked as completed!
StreakCounter: Current streak for "Reading Goal": 7 days
StreakCounter: MILESTONE! 7-day streak achieved for "Reading Goal"!
```

**Test Results:**
- Current Streak (Expected: 7): 7 **PASS**
- Longest Streak (Expected: 7): 7 **PASS**
- Milestone Detection: **PASS** (Check console for 7-day milestone)

### TEST SCENARIO 3: Observer Notifications
**Purpose:** Test that all observers receive proper notifications  
**Expected:** All 4 observer types should be notified

```
Observer ProgressTracker added
Observer StreakCounter added
Observer NotificationSystem added
Observer AchievementSystem added
Goal "Meditation Goal" marked as completed!
ProgressTracker: Updated progress for "Meditation Goal" - Total completions: 1
ProgressTracker: Completion rate: 100.0%
StreakCounter: Current streak for "Meditation Goal": 1 days
NotificationSystem: Great job! You completed "Meditation Goal". Keep it up!
AchievementSystem: ACHIEVEMENT UNLOCKED! "First Step" for "Meditation Goal"
AchievementSystem: Complete your first goal
```

**Test Results:**
- Progress Tracker Notified: **PASS**
- Streak Counter Notified: **PASS**
- Notification System Notified: **PASS**
- Achievement System Notified: **PASS**

### TEST SCENARIO 4: Achievement Unlocking
**Purpose:** Test achievement unlock triggers  
**Expected:** First completion achievement should unlock

```
Observer AchievementSystem added
Goal "Water Intake" marked as completed!
AchievementSystem: ACHIEVEMENT UNLOCKED! "First Step" for "Water Intake"
AchievementSystem: Complete your first goal
```

**Test Results:**
- First Step Achievement Unlocked: **PASS**
- Total Completions Tracked: 1 **PASS**

### TEST SCENARIO 5: Streak Broken Handling
**Purpose:** Test observer notifications when streak is broken  
**Expected:** All observers should handle streak broken event

```
Observer StreakCounter added
Observer NotificationSystem added
[... 5 day progression ...]
Streak broken for goal "Exercise Goal" (was 5 days)
StreakCounter: Streak reset for "Exercise Goal"
NotificationSystem: Your Exercise Goal streak was broken. Don't give up!
```

**Test Results:**
- Current Streak Reset to 0: **PASS**
- Longest Streak Preserved (5): **PASS**
- Streak Broken Notification Sent: **PASS**

### TEST SCENARIO 6: Multiple Goals Tracking
**Purpose:** Test observer pattern with multiple goal subjects  
**Expected:** Single observer should track multiple goals correctly

```
Observer ProgressTracker added
Observer StreakCounter added
Goal "Morning Run" marked as completed!
ProgressTracker: Updated progress for "Morning Run" - Total completions: 1
StreakCounter: Current streak for "Morning Run": 1 days
Goal "Evening Reading" marked as completed!
ProgressTracker: Updated progress for "Evening Reading" - Total completions: 1
StreakCounter: Current streak for "Evening Reading": 1 days
```

**Test Results:**
- Both Goals Progress Tracked: **PASS**
- Both Goals Streaks Tracked: **PASS**
- Goal 1 Completions: 1
- Goal 2 Completions: 1

### TEST SCENARIO 7: Edge Cases & Error Handling
**Purpose:** Test boundary conditions and error scenarios  
**Expected:** System should handle edge cases gracefully

```
Observer ProgressTracker added
Goal "Edge Case Goal" marked as completed!
ProgressTracker: Updated progress for "Edge Case Goal" - Total completions: 1
Goal "Edge Case Goal" is already completed today
Observer ProgressTracker removed
Goal "Edge Case Goal" reset for new day
Goal "Edge Case Goal" marked as completed!
```

**Test Results:**
- Prevented Double Completion: **PASS**
- Observer Removal Works: **PASS**
- Break Non-existent Streak Handled: **PASS**

## COMPREHENSIVE TEST SUMMARY

**Total Tests Executed:** 7  
**Tests Passed:** 7  
**Tests Failed:** 0  
**Success Rate:** 100.0%

### OBSERVER PATTERN VERIFICATION:
- Subject-Observer relationship established
- Multiple observers can watch same subject
- Single observer can watch multiple subjects
- Loose coupling maintained between components
- Dynamic observer addition/removal supported
- Event-driven notifications working

### DESIGN PATTERN BENEFITS DEMONSTRATED:
- **Modularity:** New observers can be added without changing Goal class
- **Loose Coupling:** Observers don't depend on each other
- **Scalability:** System handles multiple goals and observers
- **Maintainability:** Each observer has single responsibility

### Test Observations:
- The system exhibits high modularity, allowing new observers to be added without modifying the Goal class.
- Loose coupling is consistently maintained between subjects and observers, supporting maintainability and flexibility.
- Test scenarios closely simulate real user behavior and workflows, all of which passed successfully.
- Edge conditions like streak breaks and duplicate completions were handled correctly and provided clear feedback.
- Dynamic observer registration and removal worked without issues or side effects.
- Overall, the implementation demonstrates the benefits of the Observer Pattern in building scalable, testable, and extensible systems.

**ALL TESTS PASSED! Observer Pattern implementation is solid!**


# Use Case 4: View Progress History with MVC Pattern

### ACCEPTANCE CRITERIA

* The current streak calculation must be 100% accurate and displayed on screen.

* The longest streak must be calculated 100% accurately based on historical data.

* The system must not crash in case of empty data and should display a "No data available" message.

* All statistics should be printed to the console in a formatted manner.

* The Model layer should contain only business logic, no UI elements.

* The View layer should only handle display responsibilities and must not perform calculations.

* The Controller must coordinate between the Model and View layers and ensure correct data flow.

* The `executeUseCase()` method should run without throwing any exceptions.

* All error scenarios (empty data, null values) should be handled gracefully.

* The system should produce 100% consistent results when run multiple times with the same data.

* Code should include explanations of the MVC pattern and design pattern commentary.

### DEFINITION OF DONE

* All acceptance criteria have been successfully tested and passed.

* Eight Java classes (`ProgressGoal`, `GoalProgress`, `ProgressStatistics`, `ProgressModel`, `ProgressView`, `ProgressHistoryView`, `ProgressController`, `ViewProgressHistoryUseCase`) have been fully implemented.

* Code has been written 100% in accordance with MVC pattern rules.

* Design pattern explanations have been added as comments for all public methods.

* The normal scenario (4 goals + completion data) has been successfully tested.

* The edge case scenario (empty data) has been tested, and error handling has been verified.

* Partial data scenario (only goals, no completions) has been tested.

* Single goal + single completion scenario has been tested.

* The system ran without crashes in all test scenarios.

* Code compiles and runs (`javac *.java`).

* Error handling has been implemented with try-catch blocks in all methods.

* Design pattern implementation explanations are present in the code.

* Each class’s role in the MVC layer is clearly stated.

* Business logic explanations have been added as code comments for all methods.
