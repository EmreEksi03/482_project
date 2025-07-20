public class FacebookFactory extends UserFactory{
    @Override
    public User createUser(String email, String facebookToken) {
        System.out.println("Creating Facebook user: " + email);


        if (!isValidFacebookToken(facebookToken)) {
            throw new IllegalArgumentException("Invalid Facebook token");
        }

        return new FacebookUser(email, facebookToken);
    }

    private boolean isValidFacebookToken(String token) {
        return token != null && token.startsWith("facebook_");
    }
}
