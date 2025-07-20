import java.time.LocalDate;
import java.util.*;

// Aggregated statistics for all goals combined
public class ProgressStatistics {
    private int totalGoals;
    private int totalCompletions;
    private double overallCompletionRate;
    private int longestOverallStreak;
    private List<GoalProgress> goalProgressList;
    private Map<LocalDate, Integer> dailyCompletions;
    
    public ProgressStatistics() {
        this.goalProgressList = new ArrayList<>();
        this.dailyCompletions = new HashMap<>();
    }
    
    // Getters and setters
    public int getTotalGoals() { return totalGoals; }
    public void setTotalGoals(int totalGoals) { this.totalGoals = totalGoals; }
    
    public int getTotalCompletions() { return totalCompletions; }
    public void setTotalCompletions(int totalCompletions) { this.totalCompletions = totalCompletions; }
    
    public double getOverallCompletionRate() { return overallCompletionRate; }
    public void setOverallCompletionRate(double overallCompletionRate) { 
        this.overallCompletionRate = overallCompletionRate; 
    }
    
    public int getLongestOverallStreak() { return longestOverallStreak; }
    public void setLongestOverallStreak(int longestOverallStreak) { 
        this.longestOverallStreak = longestOverallStreak; 
    }
    
    public List<GoalProgress> getGoalProgressList() { return goalProgressList; }
    public void setGoalProgressList(List<GoalProgress> goalProgressList) { 
        this.goalProgressList = goalProgressList; 
    }
    
    public Map<LocalDate, Integer> getDailyCompletions() { return dailyCompletions; }
    public void setDailyCompletions(Map<LocalDate, Integer> dailyCompletions) { 
        this.dailyCompletions = dailyCompletions; 
    }
}