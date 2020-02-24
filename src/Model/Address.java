package Model;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String city;
    private StateName state;
    private String zipCode;
    private String email;

    Address(){
        this.street = "";
        this.city = "";
        this.state = null;
        this.zipCode = "";
    }
    Address(String street, String city, String state, String zipCode, String email) {
        this.street = street;
        this.city = city;
        this.state = StateName.getStateValue(state);
        this.zipCode = zipCode;
        this.email = email;
    }

    public String getStreet() { return new String(street.toCharArray()); }
    public String getCity() { return new String(city.toCharArray()); }
    public String getState() { return state.getStateName(); }
    public String getZipCode() { return new String(zipCode.toCharArray()); }
    public String getEmail() { return new String(email.toCharArray()); }

    String getFullAddress(){
        return street + "\n" + city + ", " + state + " " + zipCode;
    }

    public boolean addressIsBlank(){
        return street.equals("") && city.equals("") && state == null & zipCode.equals("");
    }
    public static boolean isValidEmail(String email){
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    boolean setEmail(String email){
        if(isValidEmail(email)){
            this.email = new String(email.toCharArray());
            return true;
        }
        else return false;
    }
    void setAddress(String street, String city, String state, String zipCode) {
        if(! street.equals("") && ! city.equals("") && StateName.getStateValue(state) != null && ! zipCode.equals("")) {
            this.street = street;
            this.city = city;
            this.state = StateName.getStateValue(state);
            this.zipCode = zipCode;
        }
    }
    //Deep copy method
    public Address deepCopy(){
        Address copy = new Address();
        copy.street = getStreet();
        copy.city = getCity();
        copy.state = StateName.getStateValue(getState());
        copy.zipCode = getZipCode();
        copy.email = getEmail();
        return copy;
    }
}
