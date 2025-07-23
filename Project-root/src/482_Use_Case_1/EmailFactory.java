public class EmailFactory extends UserFactory{
    @Override
    public User createUser(String email, String password) {
        System.out.println("Creating Email user: " + email);


        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }


        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        return new EMailUser(email, password);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

}
