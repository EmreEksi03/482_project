public abstract class User {
    private String email;
    private String userId;
    private long registrationDate;

    public User(String email) {
        this.email = email;
        this.userId = generateUserId();
        this.registrationDate = System.currentTimeMillis();
    }

    public String getEmail() { return email; }
    public String getUserId() { return userId; }
    public long getRegistrationDate() { return registrationDate; }

    public abstract void authenticate();

    private String generateUserId() {
        return "user_" + System.currentTimeMillis();
    }
}