public class FacebookUser extends User{
    private String facebookId;
    private String profilePicture;

    public FacebookUser(String email, String facebookToken) {
        super(email);
        this.facebookId = extractFacebookId(facebookToken);
        this.profilePicture = extractProfilePicture(facebookToken);
    }

    @Override
    public void authenticate() {
        System.out.println("Facebook OAuth authentication for: " + email);
        System.out.println("Facebook ID: " + facebookId);
        System.out.println("Profile Picture: " + profilePicture);
    }

    private String extractFacebookId(String token) {
        return "fb_" + token.substring(9);
    }

    private String extractProfilePicture(String token) {
        return "adres";
    }
}
