import java.util.Scanner;

public class Basit_demo {
    public static void main(String[] args){
        UserFactory fact;
        GMailFactory gmail = new GMailFactory();
        EmailFactory email = new EmailFactory();
        User user = null;
        FacebookFactory facebook = new FacebookFactory();
        System.out.println("Kayıt olmak için yöntem seçin:");
        System.out.println("1.Email 2.Gmail 3.Facebook");
        Scanner scanner = new Scanner(System.in);
//Factory method open to extension closed to modification
        int x = scanner.nextInt();
        if(x == 1){
            fact = email;
            System.out.println("Email giriniz:");
            String emaill = scanner.next();
            System.out.println("Şifre giriniz:");
            String siffre = scanner.next();
            user = fact.createUser(emaill,siffre);
        }
        if(x == 2){
            fact = gmail;
            System.out.println("Email giriniz:");
            String emaill = scanner.next();
            System.out.println("Google_token giriniz:");
            String siffre = scanner.next();
             user = fact.createUser(emaill,siffre);
        }
        if(x == 3){
            fact = facebook;
            System.out.println("Email giriniz:");
            String emaill = scanner.next();
            System.out.println("Facebook_token giriniz:");
            String siffre = scanner.next();
            user =fact.createUser(emaill,siffre);
        }
        user.authenticate();
    }
}
