import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//The real UI implementation that shows data to users
public class ProgressHistoryView implements ProgressView {
    private boolean isLoading = false;
    
    @Override
    public void showLoading() {
        isLoading = true;
        System.out.println("Loading progress data...");
    }
    
    @Override
    public void hideLoading() {
        isLoading = false;
        System.out.println("Loading complete");
    }
    
    @Override
    public void displayProgressStatistics(ProgressStatistics statistics) {
        System.out.println("\nPROGRESS OVERVIEW");
        System.out.println("====================");
        System.out.printf("Total Goals: %d%n", statistics.getTotalGoals());
        System.out.printf("Total Completions: %d%n", statistics.getTotalCompletions());
        System.out.printf("Overall Success Rate: %.1f%%%n", statistics.getOverallCompletionRate());
        System.out.printf("Best Streak: %d days%n", statistics.getLongestOverallStreak());
    }
    
    @Override
    public void updateGoalProgress(List<GoalProgress> goalProgressList) {
        System.out.println("\nGOAL PERFORMANCE");
        System.out.println("===================");
        for (GoalProgress progress : goalProgressList) {
            System.out.printf("%s%n", progress.getGoalTitle());
            System.out.printf("   Current Streak: %d days%n", progress.getCurrentStreak());
            System.out.printf("   Best Streak: %d days%n", progress.getLongestStreak());
            System.out.printf("   Success Rate: %.1f%%%n", progress.getCompletionRate());
            if (progress.getLastCompletionDate() != null) {
                System.out.printf("   Last Completed: %s%n", 
                    progress.getLastCompletionDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
            }
            System.out.println();
        }
    }
    
    @Override
    public void updateDailyChart(Map<LocalDate, Integer> dailyCompletions) {
        System.out.println("ACTIVITY CHART (Last 7 Days)");
        System.out.println("================================");
        LocalDate today = LocalDate.now();
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            int completions = dailyCompletions.getOrDefault(date, 0);
            String bars = "█".repeat(completions);
            System.out.printf("%s: %d %s%n", 
                date.format(DateTimeFormatter.ofPattern("MMM dd")), 
                completions, 
                bars);
        }
    }
    
    @Override
    public void showNoDataMessage() {
        System.out.println("\nNo Progress Data Available");
        System.out.println("Start completing goals to see your progress!");
    }
    
    @Override
    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}