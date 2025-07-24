import java.time.LocalDate;
import java.util.*;

// Coordinates between Model and View, handles user actions
public class ProgressController {
    private ProgressModel model;
    private ProgressView view;
    
    //Constructor establishes MVC relationships
    public ProgressController(ProgressModel model, ProgressView view) {
        this.model = model;
        this.view = view;
    }
    
    /**
     * Main controller method: Handle "View Progress History" user action
     * Coordinates the entire MVC flow
     */
    public void handleViewProgressRequest(String userId) {
        // Show loading state to user
        view.showLoading();
        
        try {
            // Simulate data fetching
            List<ProgressGoal> userGoals = fetchUserGoals(userId);
            Map<String, List<LocalDate>> completionData = fetchCompletionData(userId);
            
            // Set data in model
            model.setData(userGoals, completionData);
            
            // Validate business rules
            if (!model.hasValidData()) {
                view.hideLoading();
                view.showNoDataMessage();
                return;
            }
            
            // Execute business logic through model
            ProgressStatistics statistics = model.analyzeProgress();
            
            // Update view with processed data
            view.displayProgressStatistics(statistics);
            view.updateGoalProgress(statistics.getGoalProgressList());
            view.updateDailyChart(statistics.getDailyCompletions());
            
            view.hideLoading();
            
        } catch (Exception e) {
            view.hideLoading();
            view.showError("Failed to load progress data: " + e.getMessage());
        }
    }
    
    
     //Handle refresh user action
     
    public void handleRefreshRequest(String userId) {
        handleViewProgressRequest(userId); // Reload all data
    }
    
    
     //Handle goal selection for detailed view
     
    public void handleGoalSelection(String goalId) {
        try {
            // In real implementation, this would fetch specific goal details
            System.out.println("Loading details for goal: " + goalId);
            // Could trigger navigation to goal detail view
        } catch (Exception e) {
            view.showError("Failed to load goal details");
        }
    }
    
    //Simulate fetching user goals from database
    private List<ProgressGoal> fetchUserGoals(String userId) {
        List<ProgressGoal> goals = new ArrayList<>();
        goals.add(new ProgressGoal("goal1", "Drink 2L Water", "Daily hydration"));
        goals.add(new ProgressGoal("goal2", "10 Push-ups", "Daily exercise"));
        goals.add(new ProgressGoal("goal3", "Read 15 minutes", "Daily learning"));
        goals.add(new ProgressGoal("goal4", "Meditate 5 minutes", "Mindfulness practice"));
        return goals;
    }
    
    //Simulate fetching completion data from database
    private Map<String, List<LocalDate>> fetchCompletionData(String userId) {
        Map<String, List<LocalDate>> completions = new HashMap<>();
        LocalDate today = LocalDate.now();
        
        // Simulate various completion patterns
        completions.put("goal1", Arrays.asList(
            today, today.minusDays(1), today.minusDays(2), today.minusDays(4), today.minusDays(5)
        ));
        
        completions.put("goal2", Arrays.asList(
            today, today.minusDays(3), today.minusDays(6), today.minusDays(7)
        ));
        
        completions.put("goal3", Arrays.asList(
            today, today.minusDays(1), today.minusDays(3)
        ));
        
        completions.put("goal4", Arrays.asList(
            today.minusDays(2), today.minusDays(5)
        ));
        
        return completions;
    }
}
