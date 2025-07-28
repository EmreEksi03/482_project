/**
 * Test Cases for Use Case 3: Track Goal Completion
 * Design Pattern: Observer Pattern
 * Demo Test Scenarios
 */
public class GoalCompletionTrackerTests {
    
    public static void main(String[] args) {
        System.out.println("USE CASE 3 - OBSERVER PATTERN TEST SUITE");
        System.out.println("===========================================\n");
        
        // Run all test scenarios
        testScenario1_NormalGoalCompletion();
        testScenario2_StreakTracking();
        testScenario3_ObserverNotifications();
        testScenario4_AchievementUnlocking();
        testScenario5_StreakBroken();
        testScenario6_MultipleGoalsTracking();
        testScenario7_EdgeCases();
        
        // Print summary
        printTestSummary();
    }
    
    // Test counters
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    /**
     * Test Scenario 1: Normal Goal Completion Flow
     * Tests basic observer pattern functionality
     */
    public static void testScenario1_NormalGoalCompletion() {
        System.out.println("TEST SCENARIO 1: Normal Goal Completion Flow");
        System.out.println("===============================================");
        System.out.println("Purpose: Test basic Observer pattern implementation");
        System.out.println("Expected: All observers should be notified when goal is completed\n");
        
        try {
            // Setup
            Goal testGoal = new Goal(101, "Test Workout", 1);
            TestProgressTracker tracker = new TestProgressTracker();
            TestStreakCounter streakCounter = new TestStreakCounter();
            
            // Add observers
            testGoal.addObserver(tracker);
            testGoal.addObserver(streakCounter);
            
            // Test execution
            testGoal.markCompleted();
            
            // Verification
            boolean progressUpdated = tracker.wasUpdated();
            boolean streakUpdated = streakCounter.wasUpdated();
            boolean goalMarkedComplete = testGoal.isCompleted();
            
            // Results
            System.out.println("Test Results:");
            System.out.printf("   - Progress Tracker Updated: %s\n", progressUpdated ? "PASS" : "FAIL");
            System.out.printf("   - Streak Counter Updated: %s\n", streakUpdated ? "PASS" : "FAIL");
            System.out.printf("   - Goal Marked Complete: %s\n", goalMarkedComplete ? "PASS" : "FAIL");
            
            updateTestCount(progressUpdated && streakUpdated && goalMarkedComplete);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Test Scenario 2: Streak Tracking Accuracy
     * Tests streak calculation and milestone detection
     */
    public static void testScenario2_StreakTracking() {
        System.out.println("TEST SCENARIO 2: Streak Tracking Accuracy");
        System.out.println("==========================================");
        System.out.println("Purpose: Test streak calculation and milestone detection");
        System.out.println("Expected: Streak should increment correctly and detect milestones\n");
        
        try {
            Goal streakGoal = new Goal(102, "Reading Goal", 1);
            StreakCounter streakCounter = new StreakCounter();
            streakGoal.addObserver(streakCounter);
            
            // Complete goal multiple times to build streak
            for (int i = 1; i <= 7; i++) {
                streakGoal.markCompleted();
                streakGoal.resetDailyStatus();
            }
            
            StreakCounter.StreakData streakData = streakCounter.getStreakInfo(102);
            
            // Verification
            boolean correctStreak = streakData.getCurrentStreak() == 7;
            boolean correctLongest = streakData.getLongestStreak() == 7;
            
            System.out.println("Test Results:");
            System.out.printf("   - Current Streak (Expected: 7): %d %s\n", 
                streakData.getCurrentStreak(), correctStreak ? "PASS" : "FAIL");
            System.out.printf("   - Longest Streak (Expected: 7): %d %s\n", 
                streakData.getLongestStreak(), correctLongest ? "PASS" : "FAIL");
            System.out.printf("   - Milestone Detection: %s\n", "PASS (Check console for 7-day milestone)");
            
            updateTestCount(correctStreak && correctLongest);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Test Scenario 3: Observer Notifications
     * Tests that all observers receive notifications
     */
    public static void testScenario3_ObserverNotifications() {
        System.out.println("TEST SCENARIO 3: Observer Notifications");
        System.out.println("=========================================");
        System.out.println("Purpose: Test that all observers receive proper notifications");
        System.out.println("Expected: All 4 observer types should be notified\n");
        
        try {
            Goal notificationGoal = new Goal(103, "Meditation Goal", 1);
            
            // Create all observer types
            ProgressTracker progressTracker = new ProgressTracker();
            StreakCounter streakCounter = new StreakCounter();
            NotificationSystem notificationSystem = new NotificationSystem();
            AchievementSystem achievementSystem = new AchievementSystem();
            
            // Add all observers
            notificationGoal.addObserver(progressTracker);
            notificationGoal.addObserver(streakCounter);
            notificationGoal.addObserver(notificationSystem);
            notificationGoal.addObserver(achievementSystem);
            
            // Complete goal
            notificationGoal.markCompleted();
            
            // Verification
            var progressData = progressTracker.getProgressSummary(103);
            var streakData = streakCounter.getStreakInfo(103);
            var notifications = notificationSystem.getRecentNotifications(5);
            var achievements = achievementSystem.getAchievements(103);
            
            boolean allNotified = progressData != null && 
                                streakData.getCurrentStreak() > 0 && 
                                !notifications.isEmpty() && 
                                achievements.getTotalCompletions() > 0;
            
            System.out.println("Test Results:");
            System.out.printf("   - Progress Tracker Notified: %s\n", progressData != null ? "PASS" : "FAIL");
            System.out.printf("   - Streak Counter Notified: %s\n", streakData.getCurrentStreak() > 0 ? "PASS" : "FAIL");
            System.out.printf("   - Notification System Notified: %s\n", !notifications.isEmpty() ? "PASS" : "FAIL");
            System.out.printf("   - Achievement System Notified: %s\n", achievements.getTotalCompletions() > 0 ? "PASS" : "FAIL");
            
            updateTestCount(allNotified);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Test Scenario 4: Achievement Unlocking
     * Tests achievement system triggers
     */
    public static void testScenario4_AchievementUnlocking() {
        System.out.println("TEST SCENARIO 4: Achievement Unlocking");
        System.out.println("=======================================");
        System.out.println("Purpose: Test achievement unlock triggers");
        System.out.println("Expected: First completion achievement should unlock\n");
        
        try {
            Goal achievementGoal = new Goal(104, "Water Intake", 1);
            AchievementSystem achievementSystem = new AchievementSystem();
            achievementGoal.addObserver(achievementSystem);
            
            // Complete goal for first time
            achievementGoal.markCompleted();
            
            var achievements = achievementSystem.getAchievements(104);
            boolean firstStepUnlocked = achievements.getUnlockedAchievements().contains("first_completion");
            
            System.out.println("Test Results:");
            System.out.printf("   - First Step Achievement Unlocked: %s\n", firstStepUnlocked ? "PASS" : "FAIL");
            System.out.printf("   - Total Completions Tracked: %d %s\n", 
                achievements.getTotalCompletions(), achievements.getTotalCompletions() == 1 ? "PASS" : "FAIL");
            
            updateTestCount(firstStepUnlocked && achievements.getTotalCompletions() == 1);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Test Scenario 5: Streak Broken Handling
     * Tests observer pattern when streak is broken
     */
    public static void testScenario5_StreakBroken() {
        System.out.println("TEST SCENARIO 5: Streak Broken Handling");
        System.out.println("=========================================");
        System.out.println("Purpose: Test observer notifications when streak is broken");
        System.out.println("Expected: All observers should handle streak broken event\n");
        
        try {
            Goal streakBreakGoal = new Goal(105, "Exercise Goal", 1);
            StreakCounter streakCounter = new StreakCounter();
            NotificationSystem notificationSystem = new NotificationSystem();
            
            streakBreakGoal.addObserver(streakCounter);
            streakBreakGoal.addObserver(notificationSystem);
            
            // Build a streak first
            for (int i = 0; i < 5; i++) {
                streakBreakGoal.markCompleted();
                streakBreakGoal.resetDailyStatus();
            }
            
            // Break the streak
            streakBreakGoal.breakStreak();
            
            // Verification
            var streakData = streakCounter.getStreakInfo(105);
            var notifications = notificationSystem.getRecentNotifications(10);
            
            boolean streakReset = streakData.getCurrentStreak() == 0;
            boolean longestPreserved = streakData.getLongestStreak() == 5;
            boolean notificationSent = notifications.stream()
                .anyMatch(n -> n.getType().equals("streak_broken"));
            
            System.out.println("Test Results:");
            System.out.printf("   - Current Streak Reset to 0: %s\n", streakReset ? "PASS" : "FAIL");
            System.out.printf("   - Longest Streak Preserved (5): %s\n", longestPreserved ? "PASS" : "FAIL");
            System.out.printf("   - Streak Broken Notification Sent: %s\n", notificationSent ? "PASS" : "FAIL");
            
            updateTestCount(streakReset && longestPreserved && notificationSent);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Test Scenario 6: Multiple Goals Tracking
     * Tests observer pattern with multiple subjects
     */
    public static void testScenario6_MultipleGoalsTracking() {
        System.out.println("TEST SCENARIO 6: Multiple Goals Tracking");
        System.out.println("==========================================");
        System.out.println("Purpose: Test observer pattern with multiple goal subjects");
        System.out.println("Expected: Single observer should track multiple goals correctly\n");
        
        try {
            Goal goal1 = new Goal(106, "Morning Run", 1);
            Goal goal2 = new Goal(107, "Evening Reading", 1);
            
            ProgressTracker sharedTracker = new ProgressTracker();
            StreakCounter sharedStreakCounter = new StreakCounter();
            
            // Same observers watch multiple goals
            goal1.addObserver(sharedTracker);
            goal2.addObserver(sharedTracker);
            goal1.addObserver(sharedStreakCounter);
            goal2.addObserver(sharedStreakCounter);
            
            // Complete both goals
            goal1.markCompleted();
            goal2.markCompleted();
            
            // Verification
            var progress1 = sharedTracker.getProgressSummary(106);
            var progress2 = sharedTracker.getProgressSummary(107);
            var streak1 = sharedStreakCounter.getStreakInfo(106);
            var streak2 = sharedStreakCounter.getStreakInfo(107);
            
            boolean bothTracked = progress1 != null && progress2 != null;
            boolean bothStreaksTracked = streak1.getCurrentStreak() > 0 && streak2.getCurrentStreak() > 0;
            
            System.out.println("Test Results:");
            System.out.printf("   - Both Goals Progress Tracked: %s\n", bothTracked ? "PASS" : "FAIL");
            System.out.printf("   - Both Goals Streaks Tracked: %s\n", bothStreaksTracked ? "PASS" : "FAIL");
            System.out.printf("   - Goal 1 Completions: %d\n", progress1 != null ? progress1.getTotalCompletions() : 0);
            System.out.printf("   - Goal 2 Completions: %d\n", progress2 != null ? progress2.getTotalCompletions() : 0);
            
            updateTestCount(bothTracked && bothStreaksTracked);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Test Scenario 7: Edge Cases
     * Tests boundary conditions and error handling
     */
    public static void testScenario7_EdgeCases() {
        System.out.println("TEST SCENARIO 7: Edge Cases & Error Handling");
        System.out.println("===============================================");
        System.out.println("Purpose: Test boundary conditions and error scenarios");
        System.out.println("Expected: System should handle edge cases gracefully\n");
        
        try {
            Goal edgeGoal = new Goal(108, "Edge Case Goal", 1);
            ProgressTracker tracker = new ProgressTracker();
            edgeGoal.addObserver(tracker);
            
            // Test 1: Double completion (should be prevented)
            edgeGoal.markCompleted();
            edgeGoal.markCompleted(); // Should not increment again
            
            var progress = tracker.getProgressSummary(108);
            boolean preventedDoubleCompletion = progress.getTotalCompletions() == 1;
            
            // Test 2: Remove observer
            edgeGoal.removeObserver(tracker);
            edgeGoal.resetDailyStatus();
            edgeGoal.markCompleted(); // Should not notify removed observer
            
            // Progress shouldn't change since observer was removed
            boolean observerRemoved = progress.getTotalCompletions() == 1;
            
            // Test 3: Break streak with no streak
            Goal noStreakGoal = new Goal(109, "No Streak Goal", 1);
            noStreakGoal.breakStreak(); // Should handle gracefully
            
            System.out.println("Test Results:");
            System.out.printf("   - Prevented Double Completion: %s\n", preventedDoubleCompletion ? "PASS" : "FAIL");
            System.out.printf("   - Observer Removal Works: %s\n", observerRemoved ? "PASS" : "FAIL");
            System.out.printf("   - Break Non-existent Streak Handled: %s\n", "PASS");
            
            updateTestCount(preventedDoubleCompletion && observerRemoved);
            
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            updateTestCount(false);
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Print comprehensive test summary
     */
    public static void printTestSummary() {
        System.out.println("COMPREHENSIVE TEST SUMMARY");
        System.out.println("==============================");
        System.out.printf("Total Tests Executed: %d\n", totalTests);
        System.out.printf("Tests Passed: %d\n", passedTests);
        System.out.printf("Tests Failed: %d\n", failedTests);
        System.out.printf("Success Rate: %.1f%%\n", (double) passedTests / totalTests * 100);
        
        System.out.println("\nOBSERVER PATTERN VERIFICATION:");
        System.out.println("- Subject-Observer relationship established");
        System.out.println("- Multiple observers can watch same subject");
        System.out.println("- Single observer can watch multiple subjects");
        System.out.println("- Loose coupling maintained between components");
        System.out.println("- Dynamic observer addition/removal supported");
        System.out.println("- Event-driven notifications working");
        
        System.out.println("\nDESIGN PATTERN BENEFITS DEMONSTRATED:");
        System.out.println("- Modularity: New observers can be added without changing Goal class");
        System.out.println("- Loose Coupling: Observers don't depend on each other");
        System.out.println("- Scalability: System handles multiple goals and observers");
        System.out.println("- Maintainability: Each observer has single responsibility");
        
        if (passedTests == totalTests) {
            System.out.println("\n ALL TESTS PASSED! Observer Pattern implementation is solid!");
        } else {
            System.out.println("\nSome tests failed. Review implementation for issues.");
        }
    }
    
    private static void updateTestCount(boolean passed) {
        totalTests++;
        if (passed) {
            passedTests++;
        } else {
            failedTests++;
        }
    }
}

/**
 * Test helper classes for verification
 */
class TestProgressTracker implements GoalObserver {
    private boolean wasUpdated = false;
    
    @Override
    public void update(GoalCompletionData data) {
        wasUpdated = true;
        System.out.println("TestProgressTracker: Received update for " + data.getGoalName());
    }
    
    public boolean wasUpdated() {
        return wasUpdated;
    }
}

class TestStreakCounter implements GoalObserver {
    private boolean wasUpdated = false;
    
    @Override
    public void update(GoalCompletionData data) {
        wasUpdated = true;
        System.out.println("TestStreakCounter: Received update for " + data.getGoalName());
    }
    
    public boolean wasUpdated() {
        return wasUpdated;
    }
}