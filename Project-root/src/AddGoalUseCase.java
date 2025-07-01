public class AddGoalUseCase {
    private final GoalRepository repository;

    public AddGoalUseCase(GoalRepository repository) {
        this.repository = repository;
    }

    public void execute(Goal goal) {
        repository.addGoal(goal);
    }
}