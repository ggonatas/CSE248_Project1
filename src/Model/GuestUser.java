package Model;

public class GuestUser extends Person {

    public GuestUser(String userID, String password){
        super(userID, password, "guest", "user", "", "", "", "guest@clothingstore.com");
        this.setUserType(UserType.GUEST);
    }
}
