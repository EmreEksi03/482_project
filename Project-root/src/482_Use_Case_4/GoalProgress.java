import java.time.LocalDate;

// Represents analyzed progress data for one specific goal
public class GoalProgress {
    private String goalId;
    private String goalTitle;
    private LocalDate lastCompletionDate;
    private int currentStreak;
    private int longestStreak;
    private double completionRate;
    
    public GoalProgress(String goalId, String goalTitle, LocalDate lastCompletionDate, 
                       int currentStreak, int longestStreak, double completionRate) {
        this.goalId = goalId;
        this.goalTitle = goalTitle;
        this.lastCompletionDate = lastCompletionDate;
        this.currentStreak = currentStreak;
        this.longestStreak = longestStreak;
        this.completionRate = completionRate;
    }
    
    public String getGoalId() { return goalId; }
    public String getGoalTitle() { return goalTitle; }
    public LocalDate getLastCompletionDate() { return lastCompletionDate; }
    public int getCurrentStreak() { return currentStreak; }
    public int getLongestStreak() { return longestStreak; }
    public double getCompletionRate() { return completionRate; }
}
