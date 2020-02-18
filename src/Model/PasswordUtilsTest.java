package Model;

public class PasswordUtilsTest {

    public static void main(String [] args){
        String password = "Hello!43";
        String salt = PasswordUtils.getSalt(30);
        String securedPassword = PasswordUtils.generateSecurePassword(password, salt);
        String wrongPassword = "hello!43";
        System.out.println("Unsecured password: "+ password + "\nSecure password: " + securedPassword);
        System.out.println("Password is correct: "+ PasswordUtils.verifyUserPassword(wrongPassword, securedPassword, salt));
        System.out.println("Incorrect password is still valid: " + PasswordUtils.isValidPassword(wrongPassword));
    }
}
