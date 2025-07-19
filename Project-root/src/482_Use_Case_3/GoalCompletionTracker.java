import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

interface GoalObserver {
    void update(GoalCompletionData data);
}

interface GoalSubject {
    void addObserver(GoalObserver observer);
    void removeObserver(GoalObserver observer);
    void notifyObservers(GoalCompletionData data);
}

class GoalCompletionData {
    private final int goalId;
    private final String goalName;
    private final LocalDateTime completedAt;
    private final int totalCompletions;
    private final int currentStreak;
    private final int targetFrequency;
    private final String event;
    private final LocalDateTime brokenAt;

    public GoalCompletionData(int goalId, String goalName, LocalDateTime completedAt, 
                            int totalCompletions, int currentStreak, int targetFrequency) {
        this.goalId = goalId;
        this.goalName = goalName;
        this.completedAt = completedAt;
        this.totalCompletions = totalCompletions;
        this.currentStreak = currentStreak;
        this.targetFrequency = targetFrequency;
        this.event = "goal_completed";
        this.brokenAt = null;
    }

    public GoalCompletionData(int goalId, String goalName, String event, LocalDateTime brokenAt) {
        this.goalId = goalId;
        this.goalName = goalName;
        this.event = event;
        this.brokenAt = brokenAt;
        this.completedAt = null;
        this.totalCompletions = 0;
        this.currentStreak = 0;
        this.targetFrequency = 0;
    }

    public int getGoalId() { return goalId; }
    public String getGoalName() { return goalName; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public int getTotalCompletions() { return totalCompletions; }
    public int getCurrentStreak() { return currentStreak; }
    public int getTargetFrequency() { return targetFrequency; }
    public String getEvent() { return event; }
    public LocalDateTime getBrokenAt() { return brokenAt; }
}

class Goal implements GoalSubject {
    private final int id;
    private final String name;
    private final int targetFrequency;
    private int completionCount;
    private int streak;
    private boolean isCompleted;
    private final List<GoalCompletionData> completionHistory;
    private final LocalDateTime createdAt;
    private final List<GoalObserver> observers;

    public Goal(int id, String name, int targetFrequency) {
        this.id = id;
        this.name = name;
        this.targetFrequency = targetFrequency;
        this.completionCount = 0;
        this.streak = 0;
        this.isCompleted = false;
        this.completionHistory = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(GoalObserver observer) {
        observers.add(observer);
        System.out.println("Observer " + observer.getClass().getSimpleName() + " added");
    }

    @Override
    public void removeObserver(GoalObserver observer) {
        observers.remove(observer);
        System.out.println("Observer " + observer.getClass().getSimpleName() + " removed");
    }

    @Override
    public void notifyObservers(GoalCompletionData data) {
        for (GoalObserver observer : observers) {
            observer.update(data);
        }
    }

    public void markCompleted() {
        if (!isCompleted) {
            completionCount++;
            isCompleted = true;
            streak++;
            
            GoalCompletionData completionData = new GoalCompletionData(
                id, name, LocalDateTime.now(), completionCount, streak, targetFrequency
            );
            
            completionHistory.add(completionData);
            
            System.out.println("Goal \"" + name + "\" marked as completed!");
            
            notifyObservers(completionData);
        } else {
            System.out.println("Goal \"" + name + "\" is already completed today");
        }
    }

    public void resetDailyStatus() {
        if (isCompleted) {
            isCompleted = false;
            System.out.println("Goal \"" + name + "\" reset for new day");
        }
    }

    public void breakStreak() {
        if (streak > 0) {
            System.out.println("Streak broken for goal \"" + name + "\" (was " + streak + " days)");
            streak = 0;
            
            GoalCompletionData streakBrokenData = new GoalCompletionData(
                id, name, "streak_broken", LocalDateTime.now()
            );
            
            notifyObservers(streakBrokenData);
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCompletionCount() { return completionCount; }
    public int getStreak() { return streak; }
    public boolean isCompleted() { return isCompleted; }
}

class ProgressTracker implements GoalObserver {
    private final Map<Integer, ProgressData> progressData;

    public ProgressTracker() {
        this.progressData = new ConcurrentHashMap<>();
    }

    @Override
    public void update(GoalCompletionData data) {
        if ("streak_broken".equals(data.getEvent())) {
            System.out.println("ProgressTracker: Streak broken for " + data.getGoalName());
            return;
        }

        int goalId = data.getGoalId();
        
        progressData.computeIfAbsent(goalId, k -> new ProgressData(data.getGoalName()));
        
        ProgressData progress = progressData.get(goalId);
        progress.setTotalCompletions(data.getTotalCompletions());
        progress.addCompletionDate(data.getCompletedAt());

        System.out.println("ProgressTracker: Updated progress for \"" + data.getGoalName() + 
                         "\" - Total completions: " + data.getTotalCompletions());
        
        double completionRate = data.getTotalCompletions() * 100.0 / Math.max(1, data.getTotalCompletions());
        System.out.printf("ProgressTracker: Completion rate: %.1f%%\n", completionRate);
    }

    public ProgressData getProgressSummary(int goalId) {
        return progressData.get(goalId);
    }

    static class ProgressData {
        private String goalName;
        private int totalCompletions;
        private List<LocalDateTime> completionDates;

        public ProgressData(String goalName) {
            this.goalName = goalName;
            this.totalCompletions = 0;
            this.completionDates = new ArrayList<>();
        }

        public void setTotalCompletions(int totalCompletions) {
            this.totalCompletions = totalCompletions;
        }

        public void addCompletionDate(LocalDateTime date) {
            completionDates.add(date);
        }

        public String getGoalName() { return goalName; }
        public int getTotalCompletions() { return totalCompletions; }
        public List<LocalDateTime> getCompletionDates() { return new ArrayList<>(completionDates); }
    }
}

class StreakCounter implements GoalObserver {
    private final Map<Integer, StreakData> streakData;
    private final List<Integer> streakMilestones;

    public StreakCounter() {
        this.streakData = new ConcurrentHashMap<>();
        this.streakMilestones = Arrays.asList(7, 30, 100, 365);
    }

    @Override
    public void update(GoalCompletionData data) {
        if ("streak_broken".equals(data.getEvent())) {
            StreakData existing = streakData.get(data.getGoalId());
            int longestStreak = existing != null ? existing.getLongestStreak() : 0;
            
            streakData.put(data.getGoalId(), new StreakData(data.getGoalName(), 0, longestStreak));
            System.out.println("StreakCounter: Streak reset for \"" + data.getGoalName() + "\"");
            return;
        }

        int goalId = data.getGoalId();
        int currentStreak = data.getCurrentStreak();
        
        streakData.computeIfAbsent(goalId, k -> new StreakData(data.getGoalName(), 0, 0));
        
        StreakData streakInfo = streakData.get(goalId);
        streakInfo.setCurrentStreak(currentStreak);
        streakInfo.updateLongestStreak(currentStreak);

        System.out.println("StreakCounter: Current streak for \"" + data.getGoalName() + "\": " + 
                         currentStreak + " days");

        if (streakMilestones.contains(currentStreak)) {
            System.out.println("StreakCounter: MILESTONE! " + currentStreak + 
                             "-day streak achieved for \"" + data.getGoalName() + "\"!");
        }
    }

    public StreakData getStreakInfo(int goalId) {
        return streakData.getOrDefault(goalId, new StreakData("Unknown", 0, 0));
    }

    static class StreakData {
        private String goalName;
        private int currentStreak;
        private int longestStreak;

        public StreakData(String goalName, int currentStreak, int longestStreak) {
            this.goalName = goalName;
            this.currentStreak = currentStreak;
            this.longestStreak = longestStreak;
        }

        public void setCurrentStreak(int currentStreak) {
            this.currentStreak = currentStreak;
        }

        public void updateLongestStreak(int streak) {
            this.longestStreak = Math.max(this.longestStreak, streak);
        }

        public String getGoalName() { return goalName; }
        public int getCurrentStreak() { return currentStreak; }
        public int getLongestStreak() { return longestStreak; }

        @Override
        public String toString() {
            return String.format("StreakData{goalName='%s', currentStreak=%d, longestStreak=%d}", 
                               goalName, currentStreak, longestStreak);
        }
    }
}

class NotificationSystem implements GoalObserver {
    private final List<Notification> notifications;

    public NotificationSystem() {
        this.notifications = new ArrayList<>();
    }

    @Override
    public void update(GoalCompletionData data) {
        if ("streak_broken".equals(data.getEvent())) {
            Notification notification = new Notification(
                System.currentTimeMillis(),
                "streak_broken",
                "Your " + data.getGoalName() + " streak was broken. Don't give up!",
                data.getBrokenAt()
            );
            notifications.add(notification);
            System.out.println("NotificationSystem: " + notification.getMessage());
            return;
        }

        Notification notification = new Notification(
            System.currentTimeMillis(),
            "goal_completed",
            "Great job! You completed \"" + data.getGoalName() + "\". Keep it up!",
            data.getCompletedAt()
        );

        notifications.add(notification);
        System.out.println("NotificationSystem: " + notification.getMessage());

        if (data.getCurrentStreak() > 1) {
            Notification streakNotification = new Notification(
                System.currentTimeMillis() + 1,
                "streak_update",
                "You're on fire! " + data.getCurrentStreak() + " days in a row for \"" + 
                data.getGoalName() + "\"!",
                data.getCompletedAt()
            );
            notifications.add(streakNotification);
            System.out.println("NotificationSystem: " + streakNotification.getMessage());
        }
    }

    public List<Notification> getRecentNotifications(int limit) {
        return notifications.stream()
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .limit(limit)
                .collect(ArrayList::new, (list, item) -> list.add(item), List::addAll);
    }

    static class Notification {
        private final long id;
        private final String type;
        private final String message;
        private final LocalDateTime timestamp;

        public Notification(long id, String type, String message, LocalDateTime timestamp) {
            this.id = id;
            this.type = type;
            this.message = message;
            this.timestamp = timestamp;
        }

        public long getId() { return id; }
        public String getType() { return type; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }

        @Override
        public String toString() {
            return String.format("Notification{type='%s', message='%s', timestamp=%s}", 
                               type, message, timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }
}

class AchievementSystem implements GoalObserver {
    private final Map<Integer, GoalAchievements> achievements;
    private final Map<String, Achievement> availableAchievements;

    public AchievementSystem() {
        this.achievements = new ConcurrentHashMap<>();
        this.availableAchievements = new HashMap<>();
        initializeAchievements();
    }

    private void initializeAchievements() {
        availableAchievements.put("first_completion", 
            new Achievement("First Step", "Complete your first goal"));
        availableAchievements.put("week_streak", 
            new Achievement("Consistent Week", "Maintain a 7-day streak"));
        availableAchievements.put("month_streak", 
            new Achievement("Monthly Habit", "Maintain a 30-day streak"));
        availableAchievements.put("centurion", 
            new Achievement("Centurion", "Maintain a 100-day streak"));
        availableAchievements.put("completionist", 
            new Achievement("Completionist", "Complete 100 goals total"));
    }

    @Override
    public void update(GoalCompletionData data) {
        if ("streak_broken".equals(data.getEvent())) return;

        int goalId = data.getGoalId();
        
        achievements.computeIfAbsent(goalId, k -> new GoalAchievements(data.getGoalName()));
        
        GoalAchievements goalAchievements = achievements.get(goalId);
        goalAchievements.setTotalCompletions(data.getTotalCompletions());

        checkAchievements(goalId, data);
    }

    private void checkAchievements(int goalId, GoalCompletionData data) {
        GoalAchievements goalAchievements = achievements.get(goalId);
        Set<String> unlocked = goalAchievements.getUnlockedAchievements();

        if (data.getTotalCompletions() == 1 && !unlocked.contains("first_completion")) {
            unlockAchievement(goalId, "first_completion", data.getGoalName());
        }

        if (data.getCurrentStreak() == 7 && !unlocked.contains("week_streak")) {
            unlockAchievement(goalId, "week_streak", data.getGoalName());
        }

        if (data.getCurrentStreak() == 30 && !unlocked.contains("month_streak")) {
            unlockAchievement(goalId, "month_streak", data.getGoalName());
        }

        if (data.getCurrentStreak() == 100 && !unlocked.contains("centurion")) {
            unlockAchievement(goalId, "centurion", data.getGoalName());
        }

        if (data.getTotalCompletions() == 100 && !unlocked.contains("completionist")) {
            unlockAchievement(goalId, "completionist", data.getGoalName());
        }
    }

    private void unlockAchievement(int goalId, String achievementKey, String goalName) {
        Achievement achievement = availableAchievements.get(achievementKey);
        GoalAchievements goalAchievements = achievements.get(goalId);
        
        goalAchievements.addUnlockedAchievement(achievementKey);
        
        System.out.println("AchievementSystem: ACHIEVEMENT UNLOCKED! \"" + achievement.getName() + 
                         "\" for \"" + goalName + "\"");
        System.out.println("AchievementSystem: " + achievement.getDescription());
    }

    public GoalAchievements getAchievements(int goalId) {
        return achievements.getOrDefault(goalId, new GoalAchievements("Unknown"));
    }

    static class Achievement {
        private final String name;
        private final String description;

        public Achievement(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
    }

    static class GoalAchievements {
        private String goalName;
        private Set<String> unlockedAchievements;
        private int totalCompletions;

        public GoalAchievements(String goalName) {
            this.goalName = goalName;
            this.unlockedAchievements = new HashSet<>();
            this.totalCompletions = 0;
        }

        public void addUnlockedAchievement(String achievementKey) {
            unlockedAchievements.add(achievementKey);
        }

        public void setTotalCompletions(int totalCompletions) {
            this.totalCompletions = totalCompletions;
        }

        public String getGoalName() { return goalName; }
        public Set<String> getUnlockedAchievements() { return new HashSet<>(unlockedAchievements); }
        public int getTotalCompletions() { return totalCompletions; }

        @Override
        public String toString() {
            return String.format("GoalAchievements{goalName='%s', unlockedAchievements=%s, totalCompletions=%d}", 
                               goalName, unlockedAchievements, totalCompletions);
        }
    }
}

public class GoalCompletionTracker {
    public static void main(String[] args) {
        System.out.println("=== Goal Completion Tracker Demo ===\n");

        Goal workoutGoal = new Goal(1, "Daily Workout", 1);

        ProgressTracker progressTracker = new ProgressTracker();
        StreakCounter streakCounter = new StreakCounter();
        NotificationSystem notificationSystem = new NotificationSystem();
        AchievementSystem achievementSystem = new AchievementSystem();

        workoutGoal.addObserver(progressTracker);
        workoutGoal.addObserver(streakCounter);
        workoutGoal.addObserver(notificationSystem);
        workoutGoal.addObserver(achievementSystem);

        System.out.println("\n=== Simulating Goal Completions ===\n");

        for (int day = 1; day <= 8; day++) {
            System.out.println("--- Day " + day + " ---");
            workoutGoal.markCompleted();
            
            if (day < 8) {
                workoutGoal.resetDailyStatus();
            }
            
            System.out.println();
        }

        System.out.println("--- Missing a day (streak broken) ---");
        workoutGoal.breakStreak();

        System.out.println("\n=== Final Status ===");
        System.out.println("Streak Info: " + streakCounter.getStreakInfo(1));
        System.out.println("Progress Summary: " + progressTracker.getProgressSummary(1));
        System.out.println("Recent Notifications: " + notificationSystem.getRecentNotifications(3));
        System.out.println("Achievements: " + achievementSystem.getAchievements(1));
    }
}