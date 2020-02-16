package Model;

public class Administrator extends Person{

    public Administrator(String userID, String password){
        super(userID, password, "admin", "", "", "", "");
        setUserType(UserType.ADMINISTRATOR);
        setAddress("1103 E Jericho Tpke", "Huntington", "NY", "11743");
        setEmail("admin@clothingstore.com");
    }
}
