public class LocalDatabase {
    public void save(Goal goal) {
        System.out.println("Saved to local DB: " + goal.getTitle());
    }
}