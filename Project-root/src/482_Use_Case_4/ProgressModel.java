import java.time.LocalDate;
import java.util.*;

/**
 * MODEL: Progress Analysis Engine
 * Design Pattern: MVC - Model
 * Intent: Encapsulate business logic for progress calculation and data analysis
 * Motivation: Separate data processing logic from presentation and user interaction
 */
public class ProgressModel {
    private List<ProgressGoal> goals;
    private Map<String, List<LocalDate>> goalCompletions;
    
    public ProgressModel() {
        this.goals = new ArrayList<>();
        this.goalCompletions = new HashMap<>();
    }
    
    /**
     * Initialize model with goal and completion data
     */
    public void setData(List<ProgressGoal> goals, Map<String, List<LocalDate>> completions) {
        this.goals = goals;
        this.goalCompletions = completions;
    }
    
    /**
     * Core business logic: Analyze progress and generate statistics
     */
    public ProgressStatistics analyzeProgress() {
        ProgressStatistics stats = new ProgressStatistics();
        
        // Basic metrics calculation
        stats.setTotalGoals(goals.size());
        
        int totalCompletions = goalCompletions.values().stream()
            .mapToInt(List::size)
            .sum();
        stats.setTotalCompletions(totalCompletions);
        
        // Advanced analytics
        double overallRate = calculateOverallCompletionRate();
        stats.setOverallCompletionRate(overallRate);
        
        int longestStreak = calculateLongestOverallStreak();
        stats.setLongestOverallStreak(longestStreak);
        
        // Individual goal analysis
        List<GoalProgress> goalProgressList = generateGoalProgressList();
        stats.setGoalProgressList(goalProgressList);
        
        // Timeline analysis
        Map<LocalDate, Integer> dailyCompletions = generateDailyCompletionsMap();
        stats.setDailyCompletions(dailyCompletions);
        
        return stats;
    }
    
    /**
     * Calculate overall completion rate for the last 30 days
     */
    private double calculateOverallCompletionRate() {
        if (goals.isEmpty()) return 0.0;
        
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        int totalPossibleCompletions = goals.size() * 30;
        
        int actualCompletions = 0;
        for (List<LocalDate> completions : goalCompletions.values()) {
            actualCompletions += (int) completions.stream()
                .filter(date -> date.isAfter(thirtyDaysAgo))
                .count();
        }
        
        return totalPossibleCompletions > 0 ? 
            (double) actualCompletions / totalPossibleCompletions * 100 : 0.0;
    }
    
    /**
     * Find the longest consecutive completion streak across all goals
     */
    private int calculateLongestOverallStreak() {
        Set<LocalDate> allCompletionDates = new HashSet<>();
        for (List<LocalDate> completions : goalCompletions.values()) {
            allCompletionDates.addAll(completions);
        }
        
        if (allCompletionDates.isEmpty()) return 0;
        
        List<LocalDate> sortedDates = new ArrayList<>(allCompletionDates);
        Collections.sort(sortedDates);
        
        int maxStreak = 1;
        int currentStreak = 1;
        
        for (int i = 1; i < sortedDates.size(); i++) {
            if (sortedDates.get(i).equals(sortedDates.get(i-1).plusDays(1))) {
                currentStreak++;
                maxStreak = Math.max(maxStreak, currentStreak);
            } else {
                currentStreak = 1;
            }
        }
        
        return maxStreak;
    }
    
    /**
     * Generate progress analysis for each individual goal
     */
    private List<GoalProgress> generateGoalProgressList() {
        List<GoalProgress> progressList = new ArrayList<>();
        
        for (ProgressGoal goal : goals) {
            List<LocalDate> completions = goalCompletions.getOrDefault(goal.getId(), new ArrayList<>());
            
            int currentStreak = calculateCurrentStreak(completions);
            int longestStreak = calculateLongestStreak(completions);
            double completionRate = calculateGoalCompletionRate(completions);
            
            LocalDate lastCompletion = completions.isEmpty() ? null : 
                Collections.max(completions);
            
            GoalProgress progress = new GoalProgress(
                goal.getId(),
                goal.getTitle(),
                lastCompletion,
                currentStreak,
                longestStreak,
                completionRate
            );
            
            progressList.add(progress);
        }
        
        return progressList;
    }
    
    /**
     * Calculate current active streak for a goal
     */
    private int calculateCurrentStreak(List<LocalDate> completions) {
        if (completions.isEmpty()) return 0;
        
        Collections.sort(completions, Collections.reverseOrder());
        
        int streak = 0;
        LocalDate currentDate = LocalDate.now();
        
        for (LocalDate completion : completions) {
            if (completion.equals(currentDate) || completion.equals(currentDate.minusDays(streak))) {
                streak++;
                currentDate = completion.minusDays(1);
            } else {
                break;
            }
        }
        
        return streak;
    }
    
    /**
     * Calculate longest streak ever achieved for a goal
     */
    private int calculateLongestStreak(List<LocalDate> completions) {
        if (completions.isEmpty()) return 0;
        
        Collections.sort(completions);
        
        int maxStreak = 1;
        int currentStreak = 1;
        
        for (int i = 1; i < completions.size(); i++) {
            if (completions.get(i).equals(completions.get(i-1).plusDays(1))) {
                currentStreak++;
                maxStreak = Math.max(maxStreak, currentStreak);
            } else {
                currentStreak = 1;
            }
        }
        
        return maxStreak;
    }
    
    /**
     * Calculate completion rate for a specific goal (last 30 days)
     */
    private double calculateGoalCompletionRate(List<LocalDate> completions) {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        long recentCompletions = completions.stream()
            .filter(date -> date.isAfter(thirtyDaysAgo))
            .count();
        
        return (double) recentCompletions / 30 * 100;
    }
    
    /**
     * Generate daily completion count map for visualization
     */
    private Map<LocalDate, Integer> generateDailyCompletionsMap() {
        Map<LocalDate, Integer> dailyMap = new HashMap<>();
        
        // Initialize last 30 days with 0
        for (int i = 0; i < 30; i++) {
            dailyMap.put(LocalDate.now().minusDays(i), 0);
        }
        
        // Count actual completions per day
        for (List<LocalDate> completions : goalCompletions.values()) {
            for (LocalDate completion : completions) {
                if (dailyMap.containsKey(completion)) {
                    dailyMap.put(completion, dailyMap.get(completion) + 1);
                }
            }
        }
        
        return dailyMap;
    }
    
    /**
     * Validate if sufficient data exists for analysis
     */
    public boolean hasValidData() {
        return goals != null && !goals.isEmpty() && 
               goalCompletions != null && !goalCompletions.isEmpty();
    }
}