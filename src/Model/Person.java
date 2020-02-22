package Model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String userID;
    private String password;
    private String passwordSalt;
    private Name name;
    private UserType userType;
    private Address address;

    Person(String userID, String password, String firstName, String lastName, String middleName,
           String suffix, String prefix, String email){
        this.userID = userID;
        this.passwordSalt = PasswordUtils.getSalt(30);
        this.password = PasswordUtils.generateSecurePassword(password, passwordSalt);
        name = new Name(firstName, lastName, middleName, suffix, prefix);
        address = new Address();
        address.setEmail(email);
    }
    //Password verification method
    public boolean verifyPassword(String password){
        return PasswordUtils.verifyUserPassword(password, this.password, passwordSalt);
    }
    //Get userID
    public String getUserID() { return new String(userID.toCharArray()); }
    //Get encrypted password
    String getPassword(){ return new String(password.toCharArray()); }
    //Get password salt
    String getPasswordSalt(){ return new String(passwordSalt.toCharArray()); }
    //Set password salt
    void setPasswordSalt(String passwordSalt) {this.passwordSalt = new String(passwordSalt.toCharArray()); }
    //Get first name
    public String getFirstName(){ return name.getFirstName(); }
    //Get last name
    public String getLastName(){ return name.getLastName(); }
    //Get middle name
    public String getMiddleName(){ return name.getMiddleName(); }
    //Get name suffix
    public String getSuffix(){ return name.getSuffix(); }
    //Get name prefix
    public String getPrefix(){ return name.getPrefix(); }

    //Get address
    public String getAddress(){ return address.getFullAddress(); }
    //Get email value from Address object
    public String getEmail(){ return address.getEmail(); }
    //Set email value in Address object. Returns false for an invalid email address, true if successful.
    public boolean setEmail(String email){ return address.setEmail(email); }
    //Set address method
    public void setAddress(String street, String city, String state, String zipCode) {
        address.setAddress(street, city, StateName.getStateValue(state), zipCode);
    }
    //Set address method passing a new Address object
    void setAddress(Address newAddress){ this.address = newAddress.deepCopy(); }
    Address getAddressObject() { return address; }
    //Set userType method
    void setUserType(UserType userType){ this.userType = userType; }
    //Get userType method
    public String getUserType() { return userType.name(); }

    //Check if a UserID is valid
    public static boolean validUserID(String userID){
        String regex = "^[\\w]*";
        return userID.matches(regex) && userID.length() >= 6 && userID.length() <=15;
    }
    //Equals method
    public boolean equals(String userID){
        return this.userID.equals(userID);
    }
}
