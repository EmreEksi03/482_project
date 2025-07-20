public class GMailFactory extends UserFactory{

    @Override
    public User createUser(String email, String googleToken) {
        System.out.println("Creating Gmail user: " + email);


        if (!isValidGoogleToken(googleToken)) {
            throw new IllegalArgumentException("Invalid Google token");
        }

        return new GMailUser(email, googleToken);
    }

    private boolean isValidGoogleToken(String token) {
        return token != null && token.startsWith("google_");
    }
}
