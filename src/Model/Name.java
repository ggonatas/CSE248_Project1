package Model;

import java.io.Serializable;

public class Name implements Serializable {
    private String firstName;
    private String lastName;
    private String middleName;
    private String suffix;
    private String prefix;

    public Name(String firstName, String lastName, String middleName, String suffix, String prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.prefix = prefix;
    }

    public String getFirstName() { return new String(firstName.toCharArray()); }
    public String getLastName() {
        return new String(lastName.toCharArray());
    }
    public String getMiddleName() { return new String(middleName.toCharArray()); }
    public String getSuffix() { return new String(suffix.toCharArray()); }
    public String getPrefix() { return new String(prefix.toCharArray()); }
    public String getFullName() {
        String fullName = "";
        if(! prefix.equals("")){
            fullName += prefix + " ";
        }
        if(! firstName.equals("")){
            fullName += firstName + " ";
        }
        if(! middleName.equals("")){
            fullName += middleName + " ";
        }
        if(! lastName.equals("")){
            fullName += lastName + " ";
        }
        if(! suffix.equals("")){
            fullName += suffix;
        }
        return fullName;
    }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }

}
