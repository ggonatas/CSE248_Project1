package Model;

public abstract class Person {
    private String userID;
    private String password;
    private Name name;
    private UserType userType;
    private Address address;

    Person(String userID, String password, String firstName, String lastName, String middleName,
           String suffix, String prefix){
        this.userID = userID;
        this.password = encryptPassword(password);
        name = new Name(firstName, lastName, middleName, suffix, prefix);
        address = new Address();
    }
    //Password encryption method
    private String encryptPassword(String password) {
        return password;
    }
    //Password decryption method
    private String decryptPassword(){
        return password;
    }
    //Get userID
    public String getUserID() { return userID; }
    //Get address
    public String getAddress(){ return address.getFullAddress(); }
    //Set email value in Address object. Returns false for an invalid email address, true if successful.
    public boolean setEmail(String email){ return address.setEmail(email); }
    //Set address method
    public void setAddress(String street, String city, String state, String zipCode) {
        address.setAddress(street, city, StateName.getStateValue(state), zipCode);
    }
    //Set userType method
    void setUserType(UserType userType){ this.userType = userType; }
    //Get userType method
    public String getUserType() { return userType.name(); }

    //Check password
    boolean checkPassword(String password){
        return decryptPassword().equals(password);
    }
}
