package Model;

public class GuestUser extends Customer {

    public GuestUser(){
        super("guest", "guestPassword123", "guest", "user", "", "", "", "guest@clothingstore.com");
        this.setUserType(UserType.GUEST);
    }
}
