package Model;

public class Administrator extends Person{

    public Administrator(String userID, String password){
        super(userID, password, "admin", "", "", "", "", "admin@clothingstore.com");
        setUserType(UserType.ADMINISTRATOR);
        setAddress("1103 E Jericho Tpke", "Huntington", "NY", "11743");
    }
}
