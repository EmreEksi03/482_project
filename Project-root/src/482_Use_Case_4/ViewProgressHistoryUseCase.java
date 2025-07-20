public class ViewProgressHistoryUseCase {
    
    // Main entry point that sets up and executes the entire use case
    public static void executeUseCase(String userId) {
        System.out.println("EXECUTING USE CASE 4: VIEW PROGRESS HISTORY");
        System.out.println("Design Pattern: Model-View-Controller (MVC)");
        System.out.println("===============================================");
        
        // Initialize MVC components
        ProgressModel model = new ProgressModel();           // MODEL: Business logic and data processing
        ProgressView view = new ProgressHistoryView();       // VIEW: User interface and presentation
        ProgressController controller = new ProgressController(model, view); // CONTROLLER: User interaction coordination
        
        // Execute the main use case flow
        controller.handleViewProgressRequest(userId);
        
        // Demonstrate additional controller capabilities
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing refresh functionality:");
        controller.handleRefreshRequest(userId);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing goal selection:");
        controller.handleGoalSelection("goal1");
        
        System.out.println("\nUse Case 4 execution complete");
        System.out.println("MVC Pattern successfully implemented:");
        System.out.println("• MODEL: ProgressModel - Data analysis and business logic");
        System.out.println("• VIEW: ProgressHistoryView - UI presentation and rendering");
        System.out.println("• CONTROLLER: ProgressController - User interaction coordination");
    }
}