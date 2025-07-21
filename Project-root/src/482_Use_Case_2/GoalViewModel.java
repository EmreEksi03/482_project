public class GoalViewModel {
    private final AddGoalUseCase useCase;

    public GoalViewModel(AddGoalUseCase useCase) {
        this.useCase = useCase;
    }

    public void onAddGoalClicked(String title, String description, long deadline) {
        Goal goal = new Goal.Builder()
                .setTitle(title)
                .setDescription(description)
                .setDeadlineTimestamp(deadline)
                .build();

        useCase.execute(goal);
    }
}