public class FirestoreService {
    public void sync(Goal goal) {
        System.out.println("Synced to Firestore: " + goal.getTitle());
    }
}