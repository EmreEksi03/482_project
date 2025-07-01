public class GoalRepositoryImpl implements GoalRepository {
    private final LocalDatabase localDb;
    private final FirestoreService firestore;

    public GoalRepositoryImpl(LocalDatabase localDb, FirestoreService firestore) {
        this.localDb = localDb;
        this.firestore = firestore;
    }

    @Override
    public void addGoal(Goal goal) {
        localDb.save(goal);
        if (NetworkUtils.isOnline()) {
            firestore.sync(goal);
        }
    }
}