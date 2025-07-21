public class Main {
    public static void main(String[] args) {
        LocalDatabase db = new LocalDatabase();
        FirestoreService firestore = new FirestoreService();
        GoalRepository repo = new GoalRepositoryImpl(db, firestore);
        AddGoalUseCase useCase = new AddGoalUseCase(repo);
        GoalViewModel viewModel = new GoalViewModel(useCase);

        viewModel.onAddGoalClicked(
            "Design Pattern Öğren",
            "Builder pattern ile hedef oluştur",
            System.currentTimeMillis() + 86400000
        );
    }
}