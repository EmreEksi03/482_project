public class EMailUser extends User{


        private String hashedPassword;
        private boolean emailVerified;

        public EMailUser(String email, String password) {
            super(email);
            this.hashedPassword = hashPassword(password);
            this.emailVerified = false;
        }

        @Override
        public void authenticate() {
            System.out.println("Email authentication for: " + email);
            System.out.println("Email verified: " + emailVerified);
        }

        private String hashPassword(String password) {
            return "hashed_" + password; // Simulated password hashing
        }

        public void verifyEmail() {
            this.emailVerified = true;
            System.out.println("Email verified for: " + email);
        }

}
