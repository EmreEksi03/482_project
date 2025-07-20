public class GMailUser extends User {

        private String googleId;
        private String profilePicture;

        public GMailUser(String email, String googleToken) {
            super(email);
            this.googleId = extractGoogleId(googleToken);
            this.profilePicture = extractProfilePicture(googleToken);
        }

        @Override
        public void authenticate() {
            System.out.println("Google OAuth authentication for: " + email);
            System.out.println("Google ID: " + googleId);
            System.out.println("Profile Picture: " + profilePicture);
        }

        private String extractGoogleId(String token) {
            return "google_" + token.substring(7); // simülasyon
        }

        private String extractProfilePicture(String token) {
            return "https://lh3.googleusercontent.com/a/default-user=s100-p-k-rw-no"; //simülasyon
        }

}
