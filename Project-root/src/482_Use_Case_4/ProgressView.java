import java.time.LocalDate;
import java.util.*;

/**
 * VIEW: Progress History Display Interface
 * Design Pattern: MVC - View
 * Intent: Define contract for progress history presentation
 * Motivation: Separate UI rendering logic from business logic and user interaction
 */
public interface ProgressView {
    void showLoading();
    void hideLoading();
    void displayProgressStatistics(ProgressStatistics statistics);
    void showNoDataMessage();
    void showError(String message);
    void updateGoalProgress(List<GoalProgress> goalProgressList);
    void updateDailyChart(Map<LocalDate, Integer> dailyCompletions);
}