package Model;

public class Address {
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

    String getStreet() { return new String(street.toCharArray()); }
    String getCity() { return new String(city.toCharArray()); }
    String getState() { return state.getStateName(); }
    String getZipCode() { return new String(zipCode.toCharArray()); }
    String getEmail() { return new String(email.toCharArray()); }

    String getFullAddress(){
        return street + "\n" + city + ", " + state + " " + zipCode;
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
    void setAddress(String street, String city, StateName state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
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
